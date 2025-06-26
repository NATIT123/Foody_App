package com.example.foodyapplication.ui.main.settings

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodyapplication.R
import com.example.foodyapplication.base.fragment.BaseFragment
import com.example.foodyapplication.common.EventObserver
import com.example.foodyapplication.data.models.SettingItem
import com.example.foodyapplication.data.models.UserInfoItem
import com.example.foodyapplication.databinding.BottomSheetAvatarBinding
import com.example.foodyapplication.databinding.FragmentUserInfoBinding
import com.example.foodyapplication.ui.auth.common.AuthViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

@AndroidEntryPoint
class UserInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentUserInfoBinding

    private val authViewModel by activityViewModels<AuthViewModel>()

    private val settingsViewModel by activityViewModels<SettingsViewModel>()

    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>
    private lateinit var pickImageLauncher: ActivityResultLauncher<String>
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = authViewModel.user.value!!
        val infoItems = listOf(
            UserInfoItem.Header(user.fullname, user.photo),
            UserInfoItem.Field("Tên đăng nhập", user.fullname, false),
            UserInfoItem.Field("Số điện thoại", user.phone),
            UserInfoItem.Field("Tên", user.fullname),
            UserInfoItem.Field("Email", user.email),
            UserInfoItem.Field("Giới tính", user.fullname ?: "Cập nhật ngay"),
            UserInfoItem.Field("Ngày sinh", user.fullname ?: "Cập nhật ngay"),
            UserInfoItem.Field("Nghề nghiệp", user.fullname ?: "Cập nhật ngay")
        )
        binding.recyclerUserInfo.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = UserInfoAdapter(infoItems, ::listItemClicked)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        takePictureLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
                if (success && imageUri != null) {
                    Log.d("MyApp", "Chụp xong: $imageUri")
                }
            }

        pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                val part = prepareFilePart("image", uri, requireContext())
                settingsViewModel.updatePhoto(part)
            }
        }

        settingsViewModel.updateAvatarSuccess.observe(
            viewLifecycleOwner,
            EventObserver { isSuccess ->
                if (isSuccess) {
                    val avatar = settingsViewModel.latestAvatarUrl
                    val currentUser = authViewModel.user.value
                    if (avatar != null) {
                        currentUser?.photo = avatar
                        if (currentUser != null) {
                            authViewModel.setUser(currentUser)
                            findNavController().popBackStack()
                        }
                    }

                }
            })
    }

    private fun showChangeAvatarBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val binding = BottomSheetAvatarBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(binding.root)

        binding.takePhoto.setOnClickListener {
            bottomSheetDialog.dismiss()

            imageUri = createImageUri()
            takePictureLauncher.launch(imageUri)
        }

        binding.chooseFromGallery.setOnClickListener {
            bottomSheetDialog.dismiss()
            pickImageLauncher.launch("image/*")
        }

        binding.cancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }


    private fun listItemClicked(selectedItem: UserInfoItem) {
        if (selectedItem is UserInfoItem.Field) {
        }

        if (selectedItem is UserInfoItem.Header) {
            showChangeAvatarBottomSheet()
        }
    }

    private fun createImageUri(): Uri {
        val imageFile = File(
            requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "avatar_${System.currentTimeMillis()}.jpg"
        )
        return FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.fileprovider",
            imageFile
        )
    }

    private fun prepareFilePart(name: String, uri: Uri, context: Context): MultipartBody.Part {
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(uri)!!
        val fileBytes = inputStream.readBytes()
        val requestBody = fileBytes.toRequestBody("image/*".toMediaTypeOrNull())
        val fileName = "avatar_${System.currentTimeMillis()}.jpg"
        return MultipartBody.Part.createFormData(name, fileName, requestBody)
    }


}

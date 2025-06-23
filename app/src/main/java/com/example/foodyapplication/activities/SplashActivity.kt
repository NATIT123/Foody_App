package com.example.foodyapplication.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.foodyapplication.R
import com.example.foodyapplication.common.AppSharePreference
import com.example.foodyapplication.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var appSharePreference: AppSharePreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(500)
            val token = appSharePreference.getToken()
            if (token.isNullOrEmpty()) {
                goToLogin()
            } else {
                fetchAccountAndNavigate(token)
            }
        }


    }

    private suspend fun fetchAccountAndNavigate(token: String) {
        try {
            val account = withContext(Dispatchers.IO) {
                // Giả sử dùng Retrofit
                val api = ApiClient.create()
                api.getMe("Bearer $token")
            }
            if (account.isSuccessful) {
                goToMain()
            } else {
                goToLogin()
            }
        } catch (e: Exception) {
            goToLogin()
        }
    }

    private fun goToLogin() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}
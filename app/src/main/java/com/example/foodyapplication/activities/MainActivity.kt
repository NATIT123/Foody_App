package com.example.foodyapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.foodyapplication.R
import com.example.foodyapplication.common.TokenManager
import com.example.foodyapplication.databinding.ActivityMainBinding
import com.example.foodyapplication.ui.auth.common.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var tokenManager: TokenManager

    private val authViewModel by viewModels<AuthViewModel>()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(3000)
            val token = tokenManager.getToken()
            if (token.isNullOrEmpty()) {
                goToLogin()
            } else {
                authViewModel.getInfoUser()
            }
        }

        authViewModel.user.observe(this@MainActivity) { account ->
            Log.d("MainActivity", "Observed user: $account")
            if (::tokenManager.isInitialized && tokenManager.getToken() != null) {
                if (account == null) goToLogin()
            }
        }


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainContainter) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.searchFragment, R.id.detailFragment, R.id.deliveryAddressFragment, R.id.addAddressFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }

                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun goToLogin() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

}
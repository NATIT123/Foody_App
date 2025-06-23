import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.lifecycleScope
import com.example.foodyapplication.activities.AuthActivity
import com.example.foodyapplication.activities.MainActivity
import com.example.foodyapplication.common.TokenManager
import com.example.foodyapplication.databinding.ActivitySplashBinding
import com.example.foodyapplication.ui.auth.common.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var tokenManager: TokenManager

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        authViewModel.user.observe(this@SplashActivity) { account ->
            if (account != null) goToMain() else goToLogin()
        }

        lifecycleScope.launch {
            delay(500)
            val token = tokenManager.getToken()
            if (token.isNullOrEmpty()) {
                goToLogin()
            } else {
                authViewModel.getUser()
            }
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

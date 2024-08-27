package com.kantinku.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kantinku.R
import com.kantinku.databinding.ActivityLoginBinding
import com.kantinku.ui.SplashScreenActivity
import com.kantinku.ui.homepage.HomeActivity
import com.raion.hackjam.utils.PrefManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityLoginBinding
    private val binding get() = _binding
    private val viewModel = LoginViewModel()
    private lateinit var prefManager: PrefManager
    
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val reqCode: Int = 123
    private val firebaseAuth = FirebaseAuth.getInstance()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        prefManager = PrefManager(this)
        
        binding.btnLogIn.setOnClickListener {
            val email = binding.etEmail.text?.trim().toString()
            val password = binding.etPassword.text?.trim().toString()
            
            Log.d("user", "email $email password $password")
            
            Log.d("LoginActivity", "Login button clicked")
            viewModel.login(email, password)
        }
        
        viewModel.loginStatus.observe(this) { isSuccess ->
            if (isSuccess) {
                finish()
                intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Log.d("LoginActivity", "Login failed")
                message("Login failed")
            }
        }
        
        binding.tvGoRegister.setOnClickListener {
            Log.d("LoginActivity", "Register button clicked")
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        
        binding.signGoogle.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
            
            signInGoogle()
            Log.d("LoginActivity", "Google sign in button clicked")
        }
    }
    
    // signInGoogle() function
    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, reqCode)
    }
    
    // onActivityResult() function : this is where we provide the task and data for the Google Account
    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == reqCode) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }
    
    // handleResult() function -  this is where we update the UI after Google signin takes place
    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                updateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    
    // UpdateUI() function - this is where we specify what UI updation are needed after google signin has taken place.
    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                prefManager.setEmail(account.email.toString())
                prefManager.setUsername(account.displayName.toString())
                prefManager.setLogin(true)
                
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    
    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(this) != null || prefManager.isLogin()) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
    
    private fun message(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
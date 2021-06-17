package com.cotion.it_consultiong.UI

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Pair
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.UI.Sign.SignInActivity
import com.cotion.it_consultiong.UI.Sign.SignUpActivity
import com.cotion.it_consultiong.databinding.ActivityLoginMainBinding
import com.cotion.it_consultiong.mvvm.models.ObjectClass
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class LoginMainActivity() : AppCompatActivity() {


    private val TAG = "LoginActivity"
    private val binding by lazy { ActivityLoginMainBinding.inflate(layoutInflater) }

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    val objectClass = ObjectClass()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        Log.d(TAG, "onCreate: ")
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)


        binding.signUp.setOnClickListener() {
            Log.d(TAG, "LoginActivity - signUp() called")
            val intent = Intent(this, SignUpActivity::class.java)
            var options: ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                this,
                Pair.create(binding.signUp, "signUp"),
                Pair.create(binding.appName1, "logoTransition")

            )

            startActivity(intent, options.toBundle())

        }

        binding.signIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            var options: ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                this,
                Pair.create(binding.appName1, "logoTransition"),
                Pair.create(binding.signIn, "emailLoginTransition"),

                )
            startActivity(intent, options.toBundle())
        }


        binding.signInGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
                Toast.makeText(this, "구글 로그인 취소", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    Toast.makeText(this, "구글 로그인중", Toast.LENGTH_SHORT).show()
                    loginSuccess()
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "구글 로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun loginSuccess() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }


    companion object {
        val GOOGLETAG = "GoogleActivity"
        val AUTHTAG = "AuthCheck"
        private const val RC_SIGN_IN = 9001
    }


}



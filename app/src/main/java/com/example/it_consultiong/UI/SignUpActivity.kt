package com.example.it_consultiong.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.it_consultiong.LoginMainActivity
import com.example.it_consultiong.R
import com.example.it_consultiong.mvvm.models.SignUpData
import com.example.it_consultiong.data.data_model.signUp
import com.example.it_consultiong.mvvm.viewmodel.ObjectClass
import com.example.it_consultiong.mvvm.viewmodel.ShareViewModel
import com.example.it_consultiong.mvvm.viewmodel.SignUpViewModel
import com.example.it_consultiong.databinding.ActivitySignUpBinding
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
class SignUpActivity : AppCompatActivity() {

    private val TAG = "SignUpActivity"
    private val mSharedViewModel: ShareViewModel by viewModels()
    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val objectClass = ObjectClass()
    private val mSignUpViewModel: SignUpViewModel by viewModels()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        mSignUpViewModel.getAllData.observe(this, androidx.lifecycle.Observer { data ->
            mSharedViewModel.checkIfDatabaseEmpty(data)
        })

    }


    //실행이 안됌
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d(TAG, "onCreateOptionsMenu: ")
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.back_menu, menu)
        super.onCreateOptionsMenu(menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.back) {
            backSignUp()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun backSignUp() {
        val intent = Intent(this, LoginMainActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun SignUp() {

        insertDataToDb()

    }

    private fun insertDataToDb() {

         val signUpId = binding.signUpId.text.toString()
         val signUPwd = binding.signUpIdPassword.text.toString()
         val signUpGrade = binding.signUpGrade.selectedItem.toString()
         val signUpClassNumber = binding.signUpClassNumber.selectedItem.toString()
         val signUpGradeClass = binding.signUpGradeClass.selectedItem.toString()
         val signUpName = binding.signUpName.text.toString()

        val signUpData = signUp(signUpName, signUpId, signUPwd, signUpGrade, signUpGradeClass, signUpClassNumber)


        val validation = mSharedViewModel.verifyDtaFromUser(
                signUpData.signUpName,
                signUpData.signUpId,
                signUpData.signUpPwd,
                signUpData.SignUpGrade,
                signUpData.SignUpClass,
                signUpData.SignUpClassNumber


        )
        if (validation) {

            val signedData = SignUpData(
                    1,
                    signUpData.signUpName,
                    signUpData.signUpId,
                    signUpData.signUpPwd,
                    signUpData.SignUpGrade,
                    signUpData.SignUpClass,
                    signUpData.SignUpClassNumber
            )

            objectClass.showToast("$signedData")
            mSignUpViewModel.insertData(signedData)

            Log.d(TAG, "insertDataToDb: $signedData")


        }


    }
}






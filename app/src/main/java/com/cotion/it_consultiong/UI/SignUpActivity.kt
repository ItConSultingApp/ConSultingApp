package com.example.it_consultiong.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.it_consultiong.DialogInterface
import com.example.it_consultiong.MajorDialog
import com.example.it_consultiong.R
import com.example.it_consultiong.data.data_model.signUp
import com.example.it_consultiong.databinding.ActivitySignUpBinding
import com.example.it_consultiong.mvvm.models.SignUpData
import com.example.it_consultiong.mvvm.viewmodel.ObjectClass
import com.example.it_consultiong.mvvm.viewmodel.ShareViewModel
import com.example.it_consultiong.mvvm.viewmodel.SignUpViewModel
import kotlinx.coroutines.InternalCoroutinesApi


@Suppress("UNREACHABLE_CODE")
@InternalCoroutinesApi
class SignUpActivity : AppCompatActivity(), DialogInterface {

    private val TAG = "SignUpActivity"
    private val mSharedViewModel: ShareViewModel by viewModels()
    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val objectClass = ObjectClass()
    private val mSignUpViewModel: SignUpViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.upToolBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "회원가입"


        mSignUpViewModel.getAllData.observe(this, androidx.lifecycle.Observer { data ->
            mSharedViewModel.checkIfDatabaseEmpty(data)
        })

        binding.signUpBtn.setOnClickListener {
            insertDataToDb()


        }

        binding.majorBtn.setOnClickListener {
            onDialogBtnClicked(it)

        }



    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onDialogBtnClicked(view: View){
        Log.d(TAG, "onDialogBtnClicked: ")

        val myCustomDialog = MajorDialog(this, this)

        myCustomDialog.show()

    }


    private fun insertDataToDb() {

        val signUpId = binding.signUpEditId.text.toString()
        val signUPwd = binding.signUpPassword.text.toString()
        val signUpGrade = binding.signUpGrade.selectedItem.toString()
        val signUpClassNumber = binding.signUpClassNumber.selectedItem.toString()
        val signUpGradeClass = binding.signUpGradeClass.selectedItem.toString()
        val signUpName = binding.signUpName.text.toString()
        val signUpMajor = binding.signUpMajor.text.toString()
        val signUpPwdCheck = binding.signUpCheckPassword.text.toString()

        val signUpData =
            signUp(signUpName, signUpId, signUPwd, signUpGrade, signUpGradeClass, signUpClassNumber)


        val validation = mSharedViewModel.verifyDtaFromUser(
            signUpData.signUpName,
            signUpData.signUpId,
            signUpData.signUpPwd,
            signUpData.SignUpGrade,
            signUpData.SignUpClass,
            signUpData.SignUpClassNumber,
            signUpMajor


        )
        if (validation && signUpPwdCheck == signUPwd) {

            val signedData = SignUpData(
                1,
                signUpData.signUpName,
                signUpData.signUpId,
                signUpData.signUpPwd,
                signUpData.SignUpGrade,
                signUpData.SignUpClass,
                signUpData.SignUpClassNumber
            )


            mSignUpViewModel.insertData(signedData)


            Log.d(TAG, "insertDataToDb: $signedData")

            intent.putExtra("id", signUpData.signUpId)
            intent.putExtra("pwd", signUpData.signUpPwd)

            Log.d(TAG, "id: ${signUpData.signUpId}\t pwd : ${signUpData.signUpPwd}")

            objectClass.showToast(this, "회원가입 완료")

            val intent = Intent(this, LoginMainActivity::class.java)
            startActivity(intent)

        } else if (validation && signUpPwdCheck != signUPwd) {
            objectClass.showToast(this, "비밀번호를 확인해 주세요")

        } else {
            objectClass.showToast(this, "빈칸을 채워주세요!")
        }


    }

    override fun onItemClickListener() {
        objectClass.showToast(this,"아이템 클릭")
    }

}






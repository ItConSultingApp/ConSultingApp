package com.cotion.it_consultiong.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cotion.it_consultiong.DialogInterface
import com.cotion.it_consultiong.MajorDialog
import com.cotion.it_consultiong.data.data_model.signUp
import com.cotion.it_consultiong.databinding.ActivitySignUpBinding
import com.cotion.it_consultiong.mvvm.models.SignUpData
import com.cotion.it_consultiong.mvvm.viewmodel.ObjectClass
import com.cotion.it_consultiong.mvvm.viewmodel.ShareViewModel
import com.cotion.it_consultiong.mvvm.viewmodel.SignUpViewModel
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
            Log.d(TAG, "SignUpActivity - signUpBtn() called")
            insertDataToDb()


        }

        binding.majorBtn.setOnClickListener {
            Log.d(TAG, "SignUpActivity - majorBtn() called")
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


    fun onDialogBtnClicked(view: View) {
        Log.d(TAG, "onDialogBtnClicked: ")

        val myCustomDialog = MajorDialog(this, this)

        myCustomDialog.show()

    }


    private fun insertDataToDb() {




    }

    override fun onItemClickListener() {
        objectClass.showToast(this,"아이템 클릭")
        Toast.makeText(applicationContext,"아이템 클릭됨",Toast.LENGTH_SHORT).show()
        objectClass.showToast(this, "아이템 클릭")
    }

}






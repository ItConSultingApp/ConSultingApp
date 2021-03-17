package com.cotion.it_consultiong.UI

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cotion.it_consultiong.DialogInterface
import com.cotion.it_consultiong.MajorDialog
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.databinding.ActivitySignUpBinding
import com.cotion.it_consultiong.mvvm.viewmodel.ObjectClass
import com.cotion.it_consultiong.mvvm.viewmodel.SignUpUserModel
import com.fullpagedeveloper.toastegg.toastOrEgg
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity:AppCompatActivity(), DialogInterface {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val objectClass = ObjectClass()
    private val TAG = "SignUpActivity"
    private val TAG2 = "SignUpCheck"
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private val signUpUserModel = SignUpUserModel()


    override fun onCreate(savedInstanceState: Bundle? ){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()




                    binding.signUpBtn.setOnClickListener {
                        if (TextUtils.isEmpty(binding.signUpName.text.toString().trim { it <= ' ' } )){
                            toastOrEgg("이름을 입력하세요",0,R.color.black,R.color.white,R.drawable.warning)
                        } else{
                            val reg = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z]*@[gsm.hs.kr]$)"
                            var emailTxt = binding.signUpEditId.text.toString().trim()
                            var passwordTxt = binding.signUpPassword.text.toString().trim()
                            if (!emailTxt.matches(Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$"))){
                                toastOrEgg("이메일 형식을 올바르게 작성하세요",0,R.color.black,R.color.white,R.drawable.warning)
                            }else{
                                if (!passwordTxt.matches(Regex("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{8,15}\$"))){

                        toastOrEgg("비밀번호를 형식에 올바르게 작성하세요",0,R.color.black,R.color.white,R.drawable.warning)
                    }else{

                        if (binding.signUpPassword.text.toString() != binding.signUpCheckPassword.text.toString()){
                            toastOrEgg("재확인 비밀번호를 비밀번호와 같게 작성하세요",0,R.color.black,R.color.white,R.drawable.warning)
                        } else {
                            toastOrEgg("잠시만 기다려주세요",0,R.color.black,R.color.white,R.drawable.warning)

                            auth.createUserWithEmailAndPassword(emailTxt, passwordTxt)
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        Log.d(TAG, "createUserWithEmail:success")
                                        signUpUserModel.userName = binding.signUpName.text.toString()
                                        signUpUserModel.userEmail = binding.signUpEditId.text.toString()
                                        signUpUserModel.userPassword = binding.signUpPassword.text.toString()

                                        onSignUpSuccess()
                                    //학년, 반, 번호, 전공은 다이얼로그가 만들어지면 설정하는 코드 작성하기

                                    } else {
                                        if (task.exception.toString() == "com.google.firebase.auth.FirebaseAuthUserCollisionException: The email address is already in use by another account."){
                                            toastOrEgg("이미 가입되어 있습니다",0,R.color.black,R.color.white,R.drawable.check)
                                        }else{
                                            toastOrEgg("회원가입에 실패했습니다",0,R.color.black,R.color.white,R.drawable.check)
                                        }
                                    }
                                }
                        }
                    }
                }
            }
        }

        binding.majorBtn.setOnClickListener {
            Log.d(TAG, "SignUpActivity - majorBtn() called")
            onDialogBtnClicked(it)

        }

    }


    fun onDialogBtnClicked(view: View) {
        Log.d(TAG, "onDialogBtnClicked: ")

        val myCustomDialog = MajorDialog(this, this)

        myCustomDialog.show()

    }

    //비밀번호 인증완료 했을시 user정보를 rtdb에 set
    private fun onSignUpSuccess(){
        auth.currentUser?.uid.let {
            if (it != null) {
                database.reference.child("users").child(it).setValue(signUpUserModel)
//                val intent = Intent(this,)
            }

        }
    }

    override fun onItemClickListener() {

        objectClass.showToast(this, "아이템 클릭")

        objectClass.showToast(this,"아이템 클릭")
        Toast.makeText(applicationContext,"아이템 클릭됨",Toast.LENGTH_SHORT).show()

    }
}
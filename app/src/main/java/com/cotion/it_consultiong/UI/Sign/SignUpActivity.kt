package com.cotion.it_consultiong.UI.Sign

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.UI.FragmentMainActivity
import com.cotion.it_consultiong.UI.Main.Splash.Companion.userName
import com.cotion.it_consultiong.UI.Sign.Dialog.MajorDialog
import com.cotion.it_consultiong.databinding.ActivitySignUpBinding
import com.cotion.it_consultiong.mvvm.models.SignUpUserModel
import com.cotion.it_consultiong.mvvm.viewmodel.ObjectClass
import com.cotion.it_consultiong.mvvm.viewmodel.ShareViewModel
import com.cotion.it_consultiong.mvvm.viewmodel.SignUpViewModel
import com.fullpagedeveloper.toastegg.toastOrEgg
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay


@Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
@InternalCoroutinesApi
class SignUpActivity : AppCompatActivity() {

    private val TAG = "SignUpActivity"
    private val mSharedViewModel: ShareViewModel by viewModels()
    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val objectClass = ObjectClass()
    private val mSignUpViewModel: SignUpViewModel by viewModels()
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private val signUpUserModel = SignUpUserModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        setSupportActionBar(binding.upToolBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "회원가입"





        binding.signUpBtn.setOnClickListener {


            if (TextUtils.isEmpty(binding.signUpName.text.toString().trim { it <= ' ' })) {
                toastOrEgg("이름을 입력하세요", 0, R.color.black, R.color.white, R.drawable.warning)
            } else {
                val emailTxt = binding.signUpEditId.text.toString().trim()
                val passwordTxt = binding.signUpPassword.text.toString().trim()
                if (!emailTxt.matches(Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$"))) {
                    toastOrEgg(
                        "이메일 형식을 올바르게 작성하세요",
                        0,
                        R.color.black,
                        R.color.white,
                        R.drawable.warning
                    )
                } else {
                    if (!passwordTxt.matches(Regex("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{8,15}\$"))) {

                        toastOrEgg(
                            "비밀번호를 형식에 올바르게 작성하세요",
                            0,
                            R.color.black,
                            R.color.white,
                            R.drawable.warning
                        )
                    } else {

                        if (binding.signUpPassword.text.toString() != binding.signUpCheckPassword.text.toString()) {
                            toastOrEgg(
                                "재확인 비밀번호를 비밀번호와 같게 작성하세요",
                                0,
                                R.color.black,
                                R.color.white,
                                R.drawable.warning
                            )
                        } else {
                            toastOrEgg(
                                "잠시만 기다려주세요",
                                0,
                                R.color.black,
                                R.color.white,
                                R.drawable.check
                            )

                            auth.createUserWithEmailAndPassword(emailTxt, passwordTxt)
                                .addOnSuccessListener {

                                    Log.d(TAG, "createUserWithEmail:success")
                                    signUpUserModel.userName =
                                        binding.signUpName.text.toString()
                                    signUpUserModel.userEmail =
                                        binding.signUpEditId.text.toString()
                                    signUpUserModel.userPassword =
                                        binding.signUpPassword.text.toString()
                                    signUpUserModel.userJob = binding.signUpMajor.text.toString()
                                    onSignUpSuccess()
//                                        onSignUpSuccess()
                                //학년, 반, 번호, 전공은 다이얼로그가 만들어지면 설정하는 코드 작성하기


                                }
                                .addOnFailureListener {
                                    Log.i(TAG,"회원가입 오류 내용 : $it")
                                    if (it.toString() == "com.google.firebase.auth.FirebaseAuthUserCollisionException: The email address is already in use by another account.") {
                                        toastOrEgg(
                                            "이미 가입되어 있습니다",
                                            0,
                                            R.color.black,
                                            R.color.white,
                                            R.drawable.warning
                                        )
                                    } else if(it.toString() == "com.google.firebase.FirebaseNetworkException: A network error (such as timeout, interrupted connection or unreachable host) has occurred.") {
                                        toastOrEgg(
                                            "네트워크가 불안정 합니다",
                                            0,
                                            R.color.black,
                                            R.color.white,
                                            R.drawable.warning
                                        )
                                    }else{
                                        toastOrEgg(
                                            "회원가입에 예기치 못한 오류가 발생했습니다",
                                            0,
                                            R.color.black,
                                            R.color.white,
                                            R.drawable.warning
                                        )
                                    }
                                }

                        }
                    }
                }
            }
        }


        binding.majorBtn.setOnClickListener {
            Log.d(TAG, "SignUpActivity - majorBtn() called")
            dialogShow(it)

        }

        //스피너
        val items1 = resources.getStringArray(R.array.grade)
        val items2 = resources.getStringArray(R.array.grad_class)
        val items3 = resources.getStringArray(R.array.class_number)

        var adapter1 = ArrayAdapter(this, R.layout.signup_item_spinner, items1)
        var adapter2 = ArrayAdapter(this, R.layout.signup_item_spinner, items2)
        var adapter3 = ArrayAdapter(this, R.layout.signup_item_spinner, items3)

        binding.signUpGrade.adapter = adapter1
        binding.signUpGradeClass.adapter = adapter2
        binding.signUpClassNumber.adapter = adapter3


        //스피너 종류 구별
        binding.signUpGrade.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                OnSignUpItemSelected(1, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        binding.signUpGradeClass.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    OnSignUpItemSelected(2, position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }

        binding.signUpClassNumber.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    OnSignUpItemSelected(3, position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

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


    //스피너 값
    private fun OnSignUpItemSelected(type: Int, position: Int) {
        if (type == 1) {
            signUpUserModel.userGrade = "${position + 1}"
        } else if (type == 2) {
            signUpUserModel.userClass = "${position + 1}"
        } else if (type == 3) {
            signUpUserModel.userNumber = "${position + 1}"
        } else {
            toastOrEgg(
                "예기치 못한 오류가 발생했습니다",
                0,
                R.color.black,
                R.color.white,
                R.drawable.warning
            )
        }

    }

    //회원가입 성공했을때
    private fun onSignUpSuccess() {

                database.reference.child("users").child(auth.currentUser.uid).setValue(signUpUserModel)


                Handler().postDelayed(
                    {
                        val shareViewModel = ShareViewModel(application)

                        shareViewModel.startGetUserInfo()
                    },
                    1500
                )
                Handler().postDelayed(
                    {
                        val intent = Intent(this, FragmentMainActivity::class.java)
                        startActivity(intent)
                        finish()
                    },
                    3000
                )
    }



    fun dialogShow(view: View) {
        Log.d(TAG, "SignUpActivity - dialog() called")

        val edit = binding.signUpMajor
        val btn_major_choose = binding.majorBtn

        val majorDialog = MajorDialog(this)

        // MajorDialog 에 있는 값 가져오기 -> content
        majorDialog.setOnOKClickedListener { content ->


            if (content == "기타") {

                Log.d(TAG, "SignUpActivity - editText() called")

                // 읽기모드
                edit.isFocusableInTouchMode = true
                edit.isClickable = true
                edit.isFocusable = true


                edit.text = null
                edit.hint = "기타"


            } else {

                //editText 에 값 넣기
                var sign_up_major = binding.signUpMajor.setText(content).toString()
                Log.d(TAG, "content : $content")
                Log.d(TAG, "sign_up_major: $sign_up_major ")
                btn_major_choose.text = "전공 선택 완료"

                // 쓰기 가능
                edit.isFocusableInTouchMode = false
                edit.isClickable = false
                edit.isFocusable = false

            }
        }

        majorDialog.start()


    }


}






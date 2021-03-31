package com.cotion.it_consultiong.UI.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.UI.FragmentMaInActivity
import com.cotion.it_consultiong.UI.Main.Splash.Companion.userClass
import com.cotion.it_consultiong.UI.Main.Splash.Companion.userGrade
import com.cotion.it_consultiong.UI.Main.Splash.Companion.userJob
import com.cotion.it_consultiong.UI.Main.Splash.Companion.userName
import com.cotion.it_consultiong.UI.Main.Splash.Companion.userNumber
import com.cotion.it_consultiong.UI.Main.Splash.Companion.userProfile
import com.cotion.it_consultiong.data.data_model.signInUserInfo
import com.cotion.it_consultiong.databinding.FragmentBoardPostDrawUpBinding
import com.fullpagedeveloper.toastegg.toastOrEgg
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class BoardPostDrawUp : AppCompatActivity() {
    private val binding by lazy { FragmentBoardPostDrawUpBinding.inflate(layoutInflater) }
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    //나중에 앞쪽에서 전역변수등으로 선언하기
    private val uid = auth.currentUser.uid
    private val currentDateTime = Calendar.getInstance().time
    private lateinit var dateFormat:String
    private lateinit var postInfo : HashMap<String, String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.postUpload.setOnClickListener {
            if (!TextUtils.isEmpty(binding.postTxt.text.toString().trim {it <=' '})){
                postInfo()
            }else{
                toastOrEgg(
                    "내용을 입력해 주세요",
                    0,
                    R.color.black,
                    R.color.white,
                    R.drawable.warning
                )
            }
        }

    }

    private fun postInfo(){
        dateFormat= SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)
        postInfo = hashMapOf(
            "name" to userName,
            "grade" to userGrade,
            "grad_class" to userClass,
            "class_number" to userNumber,
            "job" to userJob,
            "profile" to userProfile.toString(),
            "when" to dateFormat,
            "contents" to binding.postTxt.text.toString()
        )
        db.collection("board_post").document(uid+dateFormat)
            .set(postInfo)
            .addOnSuccessListener {
                toastOrEgg(
                    "게시글 올리기 성공",
                    0,
                    R.color.black,
                    R.color.white,
                    R.drawable.warning
                )
                val intent = Intent(this, FragmentMaInActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                toastOrEgg(
                    "게시글 올리기 실패",
                    0,
                    R.color.black,
                    R.color.white,
                    R.drawable.warning
                )
            }

    }
}
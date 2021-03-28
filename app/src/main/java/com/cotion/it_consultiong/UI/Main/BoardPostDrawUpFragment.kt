package com.cotion.it_consultiong.UI.Main

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.UI.FragmentMaInActivity
import com.cotion.it_consultiong.data.data_model.signInUserInfo
import com.cotion.it_consultiong.databinding.FragmentBoardPostDrawUpBinding
import com.cotion.it_consultiong.mvvm.viewmodel.ObjectClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class BoardPostDrawUpFragment : Fragment() {
    private var _binding: FragmentBoardPostDrawUpBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    //나중에 앞쪽에서 전역변수등으로 선언하기
    val objectClass = ObjectClass()

    private val uid = auth.currentUser?.uid // null
    private val currentDateTime = Calendar.getInstance().time
    private lateinit var dateFormat: String
    private lateinit var postInfo: HashMap<String, String?>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        _binding = FragmentBoardPostDrawUpBinding.inflate(inflater, container, false)


        binding.postUpload.setOnClickListener {
            if (!TextUtils.isEmpty(binding.postTxt.text.toString().trim { it <= ' ' })) {
                postInfo()
            } else objectClass.showToast(context, "내용을 입력해주세요")
        }
        return binding.root


    }


    private fun postInfo() {
        val userInfo = signInUserInfo()
        dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)
        postInfo = hashMapOf(
            "name" to userInfo.name,
            "grade" to userInfo.grade,
            "grad_class" to userInfo.grad_class,
            "class_number" to userInfo.class_number,
            "job" to userInfo.job,
            "profile" to userInfo.profileImg,
            "when" to dateFormat,
            "contents" to binding.postTxt.text.toString()
        )
        db.collection("board_post").document(uid + dateFormat)
            .set(postInfo)
            .addOnSuccessListener {
                objectClass.showToast(context, "게시글 올리기 성공")
//                val intent = Intent(this, FragmentMaInActivity::class.java)
//                startActivity(intent)
            }
            .addOnFailureListener {
                objectClass.showToast(context, "게시글 올리기 실패")
            }
    }
}
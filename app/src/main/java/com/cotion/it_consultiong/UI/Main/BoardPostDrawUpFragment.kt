package com.cotion.it_consultiong.UI.Main

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

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
            } else objectClass.showToast(requireContext(), "내용을 입력해주세요")
        }
        return binding.root


    }


    private fun postInfo() {
        dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)
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

        db.collection("board_post").document(uid + dateFormat)
            .set(postInfo)
            .addOnSuccessListener {
                objectClass.showToast(requireContext(), "게시글 올리기 성공")
                findNavController().navigate(R.id.action_fragment_board_post_navi_to_fragment_board_navi)
            }
            .addOnFailureListener {
                objectClass.showToast(requireContext(), "게시글 올리기 실패")
            }
    }
}
package com.cotion.it_consultiong.UI.Main

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.UI.Main.BoardFragment.Companion.TAG
import com.cotion.it_consultiong.databinding.FragmentBoardPostDrawUpBinding
import com.cotion.it_consultiong.model.recycler_model.BoardData
import com.cotion.it_consultiong.mvvm.viewmodel.MealViewModel
import com.cotion.it_consultiong.mvvm.viewmodel.ObjectClass
import com.cotion.it_consultiong.mvvm.viewmodel.ShareViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class BoardPostDrawUpFragment : Fragment() {
    private var _binding: FragmentBoardPostDrawUpBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val mealViewModel: MealViewModel by viewModels()

    private val shareViewModel: ShareViewModel by viewModels()

    //나중에 앞쪽에서 전역변수등으로 선언하기
    val objectClass = ObjectClass()

    private val uid = auth.currentUser?.uid // null
    private val currentDateTime = Calendar.getInstance().time

    private lateinit var userInfo: BoardData
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

        userInfo = BoardData(
            binding.postName.text.toString(),
            "",
            binding.postTxt.text.toString(),
            "",
            mealViewModel.formatted_custon
        )


        return binding.root

    }


    private fun postInfo() {


        postInfo = hashMapOf(
            "name" to userInfo.name,
            "title" to userInfo.title,
            "context" to binding.postTxt.text.toString(),
            "profile" to userInfo.profile,
            "day" to userInfo.day,
        )

        db.collection("board_post").document(uid + mealViewModel.formatted_custon)
            .set(postInfo)
            .addOnSuccessListener {
                objectClass.showToast(requireContext(), "게시글 올리기 성공")
                findNavController().navigate(R.id.action_fragment_board_post_navi_to_fragment_board_navi)
                Log.d(TAG, "postInfo: ${userInfo.context}")
                Log.d(TAG, "postInfo: $postInfo")
            }
            .addOnFailureListener {
                objectClass.showToast(requireContext(), "게시글 올리기 실패")
            }
    }


}
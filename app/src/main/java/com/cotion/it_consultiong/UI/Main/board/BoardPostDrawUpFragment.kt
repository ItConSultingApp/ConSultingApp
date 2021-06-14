package com.cotion.it_consultiong.ui.main.board

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cotion.it_consultiong.ui.main.HomeFragment.Companion.TAG
import com.cotion.it_consultiong.ui.SplashActivity.Companion.userJob
import com.cotion.it_consultiong.databinding.FragmentBoardPostDrawUpBinding
import com.cotion.it_consultiong.model.recycler_model.BoardData
import com.cotion.it_consultiong.mvvm.viewmodel.MealViewModel
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
    val bundle = Bundle()
    private val uid = auth.currentUser?.uid // null
    private val currentDateTime = Calendar.getInstance().time
    private lateinit var dateFormat: String

    private val mealViewModel: MealViewModel by viewModels()

    private lateinit var boardData: BoardData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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
        val day = mealViewModel.formatted_custon_board


        Log.d(TAG, "day: $day")
        boardData = BoardData(
            binding.postTxt.text.toString(),
            userJob.toString(),
            binding.postName.text.toString(),
            binding.postTitle.text.toString(),
            day
        )


        db.collection("board_post").document(auth.currentUser.uid + dateFormat)
            .set(boardData)
            .addOnSuccessListener {
                objectClass.showToast(requireContext(), "게시글 올리기 성공")
                Log.d(TAG, "postInfo: $boardData ")

                findNavController().navigate(com.cotion.it_consultiong.R.id.action_fragment_board_post_navi_to_fragment_board_navi)
            }
            .addOnFailureListener {
                objectClass.showToast(requireContext(), "게시글 올리기 실패")
            }
    }
}
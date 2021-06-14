package com.cotion.it_consultiong.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.ui.SplashActivity.Companion.userClass
import com.cotion.it_consultiong.ui.SplashActivity.Companion.userGrade
import com.cotion.it_consultiong.ui.SplashActivity.Companion.userJob
import com.cotion.it_consultiong.ui.SplashActivity.Companion.userName
import com.cotion.it_consultiong.ui.SplashActivity.Companion.userNumber
import com.cotion.it_consultiong.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    companion object{
        const val TAG : String = "로그"


    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val user_grade_class_number:String =userGrade+"학년 "+userClass+"반 "+userNumber+"번"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.myName.text = userName+"님의 전공은"
        binding.myJob.text = userJob+" 입니다"
//        binding.userGradeClassNumber.text = user_grade_class_number

//        binding.userName.text = userName
        setHasOptionsMenu(true)





        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.chat_menu, menu)

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.message_menu -> {
                findNavController().navigate(R.id.action_fragment_home_navi_to_fragment_chat_navi)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
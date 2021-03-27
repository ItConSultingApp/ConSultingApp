package com.cotion.it_consultiong.UI.Main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cotion.it_consultiong.databinding.FragmentBoardBinding

class FragmentBoard : Fragment() {
    companion object{
        const val TAG : String = "로그"

        fun newInstance() : FragmentBoard{
            return FragmentBoard()
        }
    }
    private var _binding: FragmentBoardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoardBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
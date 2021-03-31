package com.cotion.it_consultiong.UI.Main


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.databinding.FragmentBoardBinding

class BoardFragment : Fragment() {
    companion object {
        const val TAG: String = "로그"

        fun newInstance(): BoardFragment {
            return BoardFragment()
        }
    }

    private var _binding: FragmentBoardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoardBinding.inflate(inflater, container, false)

        binding.fabAddBtn.setOnClickListener() {

            findNavController().navigate(R.id.action_fragment_board_navi_to_fragment_board_post_navi)
        }

        return binding.root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
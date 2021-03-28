package com.cotion.it_consultiong.UI.Main

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    companion object{
        const val TAG : String = "로그"

        fun newInstance() : HomeFragment{
            return HomeFragment()
        }
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentHomeBinding.inflate(inflater, container, false)



        setHasOptionsMenu(true)


        Log.d(TAG, "onCreateView: ")


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
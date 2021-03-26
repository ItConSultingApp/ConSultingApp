package com.cotion.it_consultiong

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.databinding.FragmentHomeBinding

class FrgamentHome : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        // 안해주면 안뜸
        setHasOptionsMenu(true)


        setMenuVisibility(true)
        Log.d(TAG, "onCreateView: ")

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.chat_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.chatFragment -> {

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
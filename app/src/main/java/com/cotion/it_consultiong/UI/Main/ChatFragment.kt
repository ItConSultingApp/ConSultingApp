package com.cotion.it_consultiong.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cotion.it_consultiong.Recycler.ChatContextViewAdapter
import com.cotion.it_consultiong.Recycler.ChatViewAdapter
import com.cotion.it_consultiong.databinding.FragmentChatBinding

class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private val chatAdapter: ChatViewAdapter by lazy { ChatViewAdapter() }
    private val contextAdapter: ChatContextViewAdapter by lazy { ChatContextViewAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val chat_reclyclerView = binding.chatRecyclerView

        chat_reclyclerView.layoutManager = LinearLayoutManager(requireContext())
        chat_reclyclerView.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        val chatContextRecyclerView = binding.chatContextRecyclerview

        chatContextRecyclerView.apply {
            adapter = contextAdapter
            chatContextRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.it_consultiong.Recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.it_consultiong.databinding.ChatItemBinding

class ChatViewAdapter : RecyclerView.Adapter<ChatViewHolder>() {

    val chatList = emptyList<ChatData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

        val itemBinding =
            ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {

        holder.bind(chatList[position])
    }

    override fun getItemCount(): Int {

        return chatList.size
    }

}
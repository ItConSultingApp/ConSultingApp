package com.cotion.it_consultiong.Recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cotion.it_consultiong.databinding.ChatItemContextBinding

class ChatContextViewAdapter : RecyclerView.Adapter<ChatContextViewHolder>() {

    val chatList = emptyList<ChatContextData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatContextViewHolder {

        val itemBinding =
            ChatItemContextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatContextViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ChatContextViewHolder, position: Int) {

        holder.bind(chatList[position])
    }

    override fun getItemCount(): Int {

        return chatList.size
    }

}
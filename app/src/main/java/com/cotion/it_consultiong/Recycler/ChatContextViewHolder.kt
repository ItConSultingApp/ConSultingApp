package com.cotion.it_consultiong.Recycler

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.UI.Sign.Dialog.App
import com.cotion.it_consultiong.databinding.ChatItemBinding
import com.cotion.it_consultiong.databinding.ChatItemContextBinding

class ChatContextViewHolder(binding: ChatItemContextBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val chat_img = binding.chatImag
    val chat_name = binding.chatName
    val chat_context = binding.chatContext


    fun bind(chatContextData: ChatContextData) {
        chat_context.text=chatContextData.chat_context
        chat_name.text = chatContextData.chat_name


        //Glide 처리 만약 img 가없으면 아레 imag를 띄워준다
        Glide.with(App.instance) //context
            .load(chatContextData.chatImage)
            .placeholder(R.drawable.people)
            .into(chat_img)
    }
}
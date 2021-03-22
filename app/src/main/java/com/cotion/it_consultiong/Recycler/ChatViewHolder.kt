package com.cotion.it_consultiong.Recycler

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.UI.Sign.Dialog.App
import com.cotion.it_consultiong.databinding.ChatItemBinding

class ChatViewHolder(binding: ChatItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val chat_img = binding.chatImag
    val chat_name = binding.chatName

    fun bind(chatData: ChatData) {

        chat_name.text = chatData.chat_name


        //Glide 처리 만약 img 가없으면 아레 imag를 띄워준다
        Glide.with(App.instance) //context
            .load(chatData.chatImage)
            .placeholder(R.drawable.android_oreo)
            .into(chat_img)
    }
}

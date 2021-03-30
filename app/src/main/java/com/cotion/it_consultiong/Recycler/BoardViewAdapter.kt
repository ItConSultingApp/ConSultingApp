package com.cotion.it_consultiong.Recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.UI.Main.BoardFragmentDirections
import com.cotion.it_consultiong.databinding.BoardItemBinding
import com.cotion.it_consultiong.model.recycler_model.BoardData

class BoardViewAdapter : RecyclerView.Adapter<BoardViewAdapter.BoardViewHolder>() {
    var boardList = emptyList<BoardData>()


    class BoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = BoardItemBinding.bind(itemView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        return BoardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)
        )

    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {

        with(holder) {
            binding.boardName.text = boardList[position].name //이름
            binding.boardTitle.text = boardList[position].title //제목
            binding.boardContext.text = boardList[position].context // 내용


            binding.boardLayout.setOnClickListener() {
                val action =
                    BoardFragmentDirections.actionFragmentBoardNaviToFragmentBoardPostNavi(boardList[position])
                holder.itemView.findNavController().navigate(action)
            }
            binding.imgBoard.setOnClickListener() {

                //bottom dialog ??

            }
        }


    }

    override fun getItemCount(): Int {
        return boardList.size
    }

}
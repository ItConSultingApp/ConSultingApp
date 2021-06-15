package com.cotion.it_consultiong.recycler

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.databinding.BoardItemBinding
import com.cotion.it_consultiong.model.recycler_model.BoardData
import com.cotion.it_consultiong.ui.main.board.BoardFragmentDirections
import com.google.firebase.firestore.FirebaseFirestore

class BoardViewAdapter() :
    RecyclerView.Adapter<BoardViewAdapter.BoardViewHolder>() {
    private val db = FirebaseFirestore.getInstance()

    private var boardList = mutableListOf<BoardData>()

    init {

        db.collection("board_post")
            .addSnapshotListener { querySnapshot, _ ->
                // ArrayList 비워줌
                boardList.clear()

                Log.d(TAG, "데이터 들어옴: ")
                for (snapshot in querySnapshot!!.documents) {
                    val item = snapshot.toObject(BoardData::class.java)
                    boardList.add(item!!)
                    Log.d(TAG, "데이터 들어옴 : $item ")

                    notifyDataSetChanged()
                }
                notifyDataSetChanged()
            }


    }

    inner class BoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
            binding.boardContext.text = boardList[position].contents // 내용
            binding.boardDay.text = boardList[position].day // 날짜


            binding.boardLayout.setOnClickListener() {
                val action =
                    BoardFragmentDirections.actionFragmentBoardNaviToFragmentBoardUpdateNavi(
                        boardList[position]
                    )
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
package com.cotion.it_consultiong.ui.main.board

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.ui.main.HomeFragment.Companion.TAG
import com.cotion.it_consultiong.databinding.BoardItemBinding
import com.cotion.it_consultiong.databinding.FragmentBoardBinding
import com.cotion.it_consultiong.model.recycler_model.BoardData
import com.google.firebase.firestore.FirebaseFirestore

class BoardFragment : Fragment() {
    private var _binding: FragmentBoardBinding? = null
    val board_adapter: BoardViewAdapter by lazy { BoardViewAdapter() }
    private val binding get() = _binding!!
    var firestore: FirebaseFirestore? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBoardBinding.inflate(inflater, container, false)
        firestore = FirebaseFirestore.getInstance()

        val recyclerView = binding.boardRecycler //adapter
        recyclerView.adapter = board_adapter //adapter 연결

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.setHasFixedSize(true)


        setHasOptionsMenu(true)

        return binding.root
    }

    inner class BoardViewAdapter() :
        RecyclerView.Adapter<BoardViewAdapter.BoardViewHolder>() {

        private var boardList = ArrayList<BoardData>()

        init {
            firestore?.collection("board_post")
                ?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    // ArrayList 비워줌
                    boardList.clear()
                    Log.d(TAG, "dads: ")

                    for (snapshot in querySnapshot!!.documents) {
                        val item = snapshot.toObject(BoardData::class.java)
                        Log.d(TAG, "$item: ")
                        boardList.add(item!!)
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

                when (boardList[position].job) {


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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.write_menu, menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.write_menu -> {
                findNavController().navigate(R.id.action_fragment_board_navi_to_fragment_board_post_navi)
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


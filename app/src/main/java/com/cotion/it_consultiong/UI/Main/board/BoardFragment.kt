package com.cotion.it_consultiong.ui.main.board

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.databinding.FragmentBoardBinding
import com.cotion.it_consultiong.recycler.BoardViewAdapter
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


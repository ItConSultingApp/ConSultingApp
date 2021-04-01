package com.cotion.it_consultiong.UI.Main


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.Recycler.BoardViewAdapter
import com.cotion.it_consultiong.Recycler.ChatViewAdapter
import com.cotion.it_consultiong.databinding.FragmentBoardBinding
import com.cotion.it_consultiong.model.recycler_model.BoardData
import com.cotion.it_consultiong.mvvm.viewmodel.ShareViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

@Suppress("UNUSED_CHANGED_VALUE")
class BoardFragment : Fragment(), View.OnClickListener {
    companion object {
        const val TAG: String = "로그"

        fun newInstance(): BoardFragment {
            return BoardFragment()
        }
    }

    private val db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth

    var num = 0
    private var _binding: FragmentBoardBinding? = null
    private val binding get() = _binding!!

    private val boardAdapter: BoardViewAdapter by lazy { BoardViewAdapter() }
    private lateinit var major_add: ImageView
    private lateinit var major_3_layout: LinearLayout
    private lateinit var major_4_layout: LinearLayout
    private lateinit var major_previous: ImageView
    private val shareViewModel: ShareViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoardBinding.inflate(inflater, container, false)

        major_add = binding.majorAdd
        major_3_layout = binding.major3Layout
        major_4_layout = binding.major4Layout
        major_previous = binding.majorPrevious

        val board_recyclerView = binding.boardRecycler

        board_recyclerView.apply {
            adapter = boardAdapter
            layoutManager = LinearLayoutManager((requireContext()))
            setHasFixedSize(true)
        }

        val adapter = GroupAdapter<GroupieViewHolder>()
        auth = FirebaseAuth.getInstance()


        db.collection("board_post" + auth.currentUser?.uid).orderBy("when")
            .get()
            .addOnSuccessListener { result ->
                Log.d(TAG, "BoardFragment - onCreateView() called")
                for (document in result) {
                    adapter.add(
                        BoardData(
                            document.get("name").toString(),
                            document.get("title").toString(),
                            document.get("context").toString(),
                            document.get("profile").toString(),
                            document.get("day").toString()
                        )
                    )


                }

                binding.boardRecycler.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w("로그", "Error getting documents.", exception)
            }





        shareViewModel.emptyDatabase.observe(viewLifecycleOwner, Observer {
            showEmptyDatabaseView(it)
        })


        binding.text.setOnClickListener(this)
        binding.fabAddBtn.setOnClickListener(this)
        binding.majorAdd.setOnClickListener(this)
        binding.majorPrevious.setOnClickListener(this)


        return binding.root
    }


    fun add() {
        major_add.visibility = View.GONE
        major_3_layout.visibility = View.VISIBLE
        major_4_layout.visibility = View.VISIBLE
    }

    fun previous() {
        major_add.visibility = View.VISIBLE
        major_3_layout.visibility = View.GONE
        major_4_layout.visibility = View.GONE
    }

    private fun showEmptyDatabaseView(emptyDatabase: Boolean) {
        if (emptyDatabase) {
            binding.noDataImageView.visibility = View.VISIBLE
            binding.noDataTextView.visibility = View.VISIBLE
        } else {
            binding.noDataImageView.visibility = View.INVISIBLE
            binding.noDataTextView.visibility = View.INVISIBLE
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun textChange(text: TextView) {


        if (num == 0) {
            text.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            ++num
        } else {
            text.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
            --num

        }

    }

    override fun onClick(v: View?) {

        when (v!!) {
            binding.majorAdd -> add()
            binding.majorPrevious -> previous()
            binding.fabAddBtn -> findNavController().navigate(R.id.action_fragment_board_navi_to_fragment_board_post_navi)
            binding.text -> textChange(binding.text)
        }

    }

}
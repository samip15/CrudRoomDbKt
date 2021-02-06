package com.sam.mynotes.fragment.list
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sam.mynotes.R
import com.sam.mynotes.viewmodel.UserViewModel

class ListFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        // recycler view
        val  adapter = ListAdapter()
        val recyclerViewVal = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerViewVal.adapter = adapter
        recyclerViewVal.layoutManager = LinearLayoutManager(requireContext())
        //user view model
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })
        val fab = view.findViewById<FloatingActionButton>(R.id.floating_action_button)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        // Add Menu
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mUserViewModel.deleteAllUser()
            Toast.makeText(requireContext(),
                "Successfully removed everything ",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){
                _,_ ->
        }
        builder.setTitle("Delete everything ?")
        builder.setMessage("Are you sure you want to delete everything")
        builder.create().show()
    }
}
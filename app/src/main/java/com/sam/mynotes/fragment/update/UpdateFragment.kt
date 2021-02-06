package com.sam.mynotes.fragment.update
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sam.mynotes.R
import com.sam.mynotes.model.User
import com.sam.mynotes.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  = inflater.inflate(R.layout.fragment_update, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val updatefName = view.findViewById<TextView>(R.id.first_nameEt_up)
        val updatelName = view.findViewById<TextView>(R.id.last_nameEt_up)
        val updateage = view.findViewById<TextView>(R.id.age_Et_up)
        val updateBtn = view.findViewById<Button>(R.id.btn_up)
        updatefName.setText(args.currentUser.firstName)
        updatelName.setText(args.currentUser.lastName)
        updateage.setText(args.currentUser.age.toString())
        updateBtn.setOnClickListener {
            updateItem()
        }
        // Add menu
        setHasOptionsMenu(true)
        return view
    }

    private fun updateItem() {
        val firstName = first_nameEt_up.text.toString()
        val lastName = last_nameEt_up.text.toString()
        val age = Integer.parseInt(age_Et_up.text.toString())
        if (inputCheck(firstName,lastName,age_Et_up.text)){
            // Create User Object
            val updatedUser = User(args.currentUser.id,firstName,lastName,age)
            // update user
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please Fill Out All Fields", Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputCheck(firstName: String,lastName: String,age: Editable?): Boolean{
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || age!!.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),
                "Successfully removed: ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){
            _,_ ->
        }
        builder.setTitle("Delete ${args.currentUser.firstName} ?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}")
        builder.create().show()
    }
}
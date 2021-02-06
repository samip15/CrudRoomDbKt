package com.sam.mynotes.fragment.add
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sam.mynotes.R
import com.sam.mynotes.model.User
import com.sam.mynotes.viewmodel.UserViewModel

class AddFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val addBtn = view.findViewById<Button>(R.id.btn_add)
        addBtn.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val firstNameVal = view?.findViewById<EditText>(R.id.first_nameEt)
        val fnameVal = firstNameVal?.text.toString()
        val lastNameVal = view?.findViewById<EditText>(R.id.last_nameEt)
        val lnameVal = lastNameVal?.text.toString()
        val ageVal = view?.findViewById<EditText>(R.id.age_Et)
        val ageValue = ageVal?.text
        if (inputCheck(fnameVal,lnameVal,ageValue)){
            val user = User(0,fnameVal,lnameVal,Integer.parseInt(ageValue.toString()))
            // Add Data To Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Succusufully added!!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please Fill Out All Fields.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputCheck(firstName: String,lastName: String,age:Editable?): Boolean{
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || age!!.isEmpty())
    }
}
package com.sam.mynotes.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sam.mynotes.R
import com.sam.mynotes.model.User

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun getItemCount(): Int {
     return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentItem = userList[position]
        val idVal = holder.itemView.findViewById<TextView>(R.id.tv1)
        val fnameVal = holder.itemView.findViewById<TextView>(R.id.tv2)
        val lNameVal = holder.itemView.findViewById<TextView>(R.id.tv3)
        val ageVal = holder.itemView.findViewById<TextView>(R.id.tv4)
        idVal.text = currentItem.id.toString()
        fnameVal.text = currentItem.firstName
        lNameVal.text = currentItem.lastName
        ageVal.text = currentItem.age.toString()
        holder.itemView.findViewById<LinearLayout>(R.id.root_layout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

}
package com.example.wordsgame

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user.view.*


class UserAdapter(
    private val users: ArrayList<User>,
    private val listener: OnItemClickListener
):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        init{
           itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
            listener.onItemClick(position)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user,
                parent,
                false
            )
        )
    }

    private fun toggleStrikeThrough(tvUserName: TextView, isChecked: Boolean){
        if(isChecked){
            tvUserName.paintFlags = tvUserName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvUserName.paintFlags = tvUserName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val curUser = users[position]
        holder.itemView.apply {
            tvUserName.text = curUser.user
            cbDelete.isChecked=curUser.isChecked
            toggleStrikeThrough(tvUserName, curUser.isChecked)
            cbDelete.setOnCheckedChangeListener{ _, isChecked ->
                toggleStrikeThrough(tvUserName, isChecked)
                curUser.isChecked =!curUser.isChecked
            }
        }
    }

    fun addUser(user: User){
        users.add(user)
        notifyItemInserted(users.size-1)
    }

    fun deleteUsers(){
        users.removeAll {  user ->
            user.isChecked
        }


        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return users.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
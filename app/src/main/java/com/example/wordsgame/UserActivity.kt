package com.example.wordsgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), UserAdapter.OnItemClickListener {

    private lateinit var userAdapter:UserAdapter
    private var values = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        loadData()
        userAdapter = UserAdapter(values, this)
        rvUserItems.adapter = userAdapter
        rvUserItems.layoutManager = LinearLayoutManager(this)

        btnAddUser.setOnClickListener{
            val userName = etUserName.text.toString()
            if(userName.isNotEmpty()){
                val user = User(userName)
                userAdapter.addUser(user)
                etUserName.text.clear()
            }
            saveData()
        }

        btnDeleteUser.setOnClickListener{
            userAdapter.deleteUsers()
            saveData()
        }

    }

    override fun onItemClick(position: Int) {
        val clickedItem = values[position]
        val user_name:String = clickedItem.user
        val userIntents = Intent(this, OptionActivity::class.java)
        userIntents.putExtra("player", user_name)
        startActivity(userIntents)
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(values)
        editor.putString("user list", json)
        editor.apply()
    }

    private fun loadData(){
        val emptyList = Gson().toJson(ArrayList<User>())
        val sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("user list", emptyList)
        val type = object: TypeToken<ArrayList<User>>() {
        }.type

        values = gson.fromJson(json, type)
    }
}
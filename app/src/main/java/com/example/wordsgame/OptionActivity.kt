package com.example.wordsgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*

class OptionActivity : AppCompatActivity() {
    companion object{
        const val TAG = "OptionActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)

        val b_animals = findViewById<ImageButton>(R.id.b_animals)
        val b_cities = findViewById<ImageButton>(R.id.b_cities)
        val b_sports = findViewById<ImageButton>(R.id.b_sports)
        val b_careers = findViewById<ImageButton>(R.id.b_careers)
        val radioButton = findViewById<RadioGroup>(R.id.optionsGroup)
        val tv_difficulty = findViewById<TextView>(R.id.tv_difficulty)
        val tvPlayer = findViewById<TextView>(R.id.tvPlayer)


        val intentPlayer = intent
        val player: String = intentPlayer.getStringExtra("player")!!
        tvPlayer.text="${getString(R.string.player)} ${player}"

        var setTime: Int= 30
        var setSkip: Int = 2

        radioButton.setOnCheckedChangeListener{ group, checkedId->
        if(checkedId==R.id.radio_easy){
            tv_difficulty.text=getString(R.string.Easy)
            setTime=45
            setSkip=3
        }
        if(checkedId==R.id.radio_medium){
            tv_difficulty.text=getString(R.string.Medium)
            setTime=30
            setSkip=2
        }
        if(checkedId==R.id.radio_hard){
            tv_difficulty.text=getString(R.string.Hard)
            setTime=20
            setSkip=1
        }
    }

        b_animals.setOnClickListener{
            startGame(setTime, setSkip, player, "Animals")
        }

        b_cities.setOnClickListener{
            startGame(setTime, setSkip, player, "Cities")
        }
        b_sports.setOnClickListener{
            startGame(setTime, setSkip, player, "Sports")
        }
        b_careers.setOnClickListener{
            startGame(setTime, setSkip, player,"Careers")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when (item.itemId){
            R.id.nav_user -> finish()
            R.id.nav_score -> showScore()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showScore() {
        val intentPlayer = intent
        val player: String = intentPlayer.getStringExtra("player")!!

        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("player", player)
        startActivity(intent)
    }

    private fun startGame(time: Int, skip: Int, player: String, s: String) {
        val settingsIntents = Intent(this, MainActivity::class.java)
        val setTime2=time
        val setSkip2=skip
        settingsIntents.putExtra("player", player)
        settingsIntents.putExtra("currentCategory", s)
        settingsIntents.putExtra("setTime", setTime2)
        settingsIntents.putExtra("setSkip", setSkip2)
        startActivity(settingsIntents)
    }
    


}


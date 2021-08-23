package com.example.wordsgame

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val mainIntent = intent
        val player: String = mainIntent.getStringExtra("player")!!

        val tvName2 = findViewById<TextView>(R.id.tvName2)
        tvName2.text = "${getString(R.string.player)} ${player}"


        val currentScore = getSharedPreference(player, "currentScore")
        val bestScore = getSharedPreference(player, "bestScore")
        val secondScore = getSharedPreference(player, "secondScore")
        val thirdScore = getSharedPreference(player, "thirdScore")
        val fourthScore = getSharedPreference(player, "fourthScore")

        if(currentScore>bestScore){
            setSharedPreference(player,"bestScore", currentScore)
            setSharedPreference(player,"secondScore", bestScore)
            setSharedPreference(player,"thirdScore", secondScore)
            setSharedPreference(player,"fourthScore", thirdScore)
        }
        if((currentScore<bestScore) && (currentScore>secondScore)){
            setSharedPreference(player,"secondScore", currentScore)
            setSharedPreference(player,"thirdScore", secondScore)
            setSharedPreference(player,"fourthScore", thirdScore)
        }
        if((currentScore<secondScore) && (currentScore>thirdScore)){
            setSharedPreference(player,"thirdScore", currentScore)
            setSharedPreference(player,"fourthScore", thirdScore)
        }
        if((currentScore<thirdScore) && (currentScore>fourthScore)){
            setSharedPreference(player,"fourthScore", currentScore)
        }


        val bestScore2 = getSharedPreference(player, "bestScore")
        val secondScore2 = getSharedPreference(player, "secondScore")
        val thirdScore2 = getSharedPreference(player, "thirdScore")
        val fourthScore2 = getSharedPreference(player, "fourthScore")

        val total_score = findViewById<TextView>(R.id.current_score)
        val b_score = findViewById<TextView>(R.id.b_score)
        val s_score = findViewById<TextView>(R.id.s_score)
        val t_score = findViewById<TextView>(R.id.t_score)
        val f_score = findViewById<TextView>(R.id.f_score)


        total_score.text = "${getString(R.string.score) } ${currentScore}"
        b_score.text = "${getString(R.string.b_score) } ${bestScore2}"
        s_score.text = "${getString(R.string.s_score) } ${secondScore2}"
        t_score.text = "${getString(R.string.t_score) } ${thirdScore2}"
        f_score.text = "${getString(R.string.f_score) } ${fourthScore2}"

        val s_clear = findViewById<FloatingActionButton>(R.id.s_clear)
        s_clear.setOnClickListener{
            setSharedPreference(player,"bestScore", 0)
            setSharedPreference(player,"secondScore", 0)
            setSharedPreference(player,"thirdScore", 0)
            setSharedPreference(player,"fourthScore", 0)

            b_score.text = "${getString(R.string.b_score) } 0"
            s_score.text = "${getString(R.string.s_score) } 0"
            t_score.text = "${getString(R.string.t_score) } 0"
            f_score.text = "${getString(R.string.t_score) } 0"

        }
        val b_back = findViewById<FloatingActionButton>(R.id.b_back)
        b_back.setOnClickListener{
        finish()
        }
    }

    fun Context.getSharedPreference(prefsName: String, key: String): Int {
        getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            ?.getInt(key, 0)?.let { return it }
        return 0
    }

    fun Context.setSharedPreference(prefsName: String, key: String, value: Int) {
        getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            .edit().apply { putInt(key, value); apply() }
    }

}
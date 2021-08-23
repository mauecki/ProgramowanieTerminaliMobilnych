package com.example.wordsgame

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import android.os.CountDownTimer
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"
    }

    private var tvTimer: TextView? = null
    private var isStarted = false


    private var currentWord: String = ""
    private var currentScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTimer=findViewById(R.id.tv_timer)

        val b_check = findViewById<Button>(R.id.b_check)
        val b_new = findViewById<Button>(R.id.b_new)
        val b_end = findViewById<Button>(R.id.b_end)

        val optionsIntent = intent
        val setTime = optionsIntent.getIntExtra("setTime", 30)
        var currentSkip = optionsIntent.getIntExtra("setSkip", 2)
        val currentCategory: String = optionsIntent.getStringExtra("currentCategory")?:"Numbers"
        val player: String = optionsIntent.getStringExtra("player")!!

        val tv_skip = findViewById<TextView>(R.id.tv_skip)
        tv_skip.text = "${getString(R.string.skip) } $currentSkip"

        val countDownTimer = object: CountDownTimer((1000*setTime).toLong(), 1000){
            override fun onTick(millisUntilFinished: Long) {
                tvTimer?.text = getString(R.string.formatted_time,
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)%60,
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)%60)
            }

            override fun onFinish() {
                isStarted = false
                b_check.setBackgroundColor(Color.RED)
                b_end.setBackgroundColor(Color.GREEN)
                val tvTimer2=findViewById<TextView>(R.id.tv_timer)
                tvTimer2.text="00:00"
            }

        }

        startTimer(countDownTimer)
        updateCategory(currentCategory)
        newExercise(currentCategory)

        b_check.setOnClickListener{
            val et_guess = (findViewById<EditText>(R.id.et_guess).text).toString()
            if(isStarted) {
                if (et_guess.equals(currentWord, true)) {
                    newExercise(currentCategory)
                    updateScore()
                } else {
                    Snackbar.make(
                        findViewById(R.id.mainContainer),
                        getString(R.string.distractor),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

        b_new.setOnClickListener{
            if(currentSkip>0) {
                newExercise(currentCategory)
                currentSkip--
                Snackbar.make(findViewById(R.id.mainContainer),     "${getString(R.string.skipAlert) } $currentSkip", Snackbar.LENGTH_SHORT).show()
                tv_skip.text = "${getString(R.string.skip) } $currentSkip"
            }
            if(currentSkip<1){
                b_new.setBackgroundColor(Color.RED)
            }
        }

        b_end.setOnClickListener{
            goToScore(player)
        }

    }

    private fun goToScore(player: String) {
        val intent = Intent(this, ScoreActivity::class.java)
        setSharedPreference(player,"currentScore", currentScore )
        intent.putExtra("player", player)
        finish()
        startActivity(intent)
    }

    private fun updateCategory(currentCategory: String) {
        val tv_info = findViewById<TextView>(R.id.tv_info)
        tv_info.text="${getString(R.string.category) } $currentCategory"
    }

        private fun updateScore() {
        val tv_score = findViewById<TextView>(R.id.tv_score)
        currentScore++
        tv_score.text = "${getString(R.string.score) } $currentScore"
    }

    private fun newExercise(Category: String) {
        val tv_word = findViewById<TextView>(R.id.tv_word)
        val wordExercise = WordElement(Category)
        val wordBasic = wordExercise.wordGen()
        currentWord=wordBasic
        val wordShuffled = wordExercise.wordMix(wordBasic)
        tv_word.text = wordShuffled
        findViewById<EditText>(R.id.et_guess).text.clear()
    }

    fun Context.setSharedPreference(prefsName: String, key: String, value: Int) {
        getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            .edit().apply { putInt(key, value); apply() }
    }

    private fun startTimer(countDownTimer: CountDownTimer) {
        countDownTimer.start()
        isStarted=true
    }


}
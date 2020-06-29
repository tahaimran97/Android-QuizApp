package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TestLooperManager
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var question_word:TextView
    lateinit var correct_answer:TextView
    lateinit var enter_answer:TextView
    lateinit var result_view:TextView
    lateinit var change_btn:Button
    lateinit var submit_btn:Button
    lateinit var show_btn:Button
    lateinit var day:String
    lateinit var random:Random

    internal var Days = arrayOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        question_word = findViewById(R.id.question_word)
        correct_answer = findViewById(R.id.correct_answer)
        enter_answer = findViewById(R.id.enter_answer)
        result_view = findViewById(R.id.result_view)
        change_btn = findViewById(R.id.change_btn)
        submit_btn = findViewById(R.id.submit_btn)
        show_btn = findViewById(R.id.show_btn)
        random = Random()

        PlayQuiz()

    }

    private fun PlayQuiz(){

        day = Days[random.nextInt(Days.size)]
        question_word.text = mixLetters(day)

        submit_btn.setOnClickListener{

            var input:String = enter_answer.text.toString()
            var correctAnswer:String = day

            if(input == correctAnswer)
            {
                result_view.setText("You Win")
                result_view.setTextColor(Color.parseColor("#4aad38"))
                enter_answer.setText("")
                result_view.visibility = View.VISIBLE
            }
            else{
                result_view.setText("You Lose")
                result_view.setTextColor(Color.parseColor("#bc3329"))
                enter_answer.setText("")
                result_view.visibility = View.VISIBLE
            }

        }

        show_btn.setOnClickListener{

            correct_answer.text = day
            correct_answer.visibility = View.VISIBLE

        }

        change_btn.setOnClickListener{

            var intent = Intent(this@MainActivity, MainActivity::class.java)
            startActivity(intent)

        }


    }

    fun mixLetters(word:String): String {

        var words = Arrays.asList<String>(*word.split("".toRegex()).dropLastWhile({it.isEmpty() }).toTypedArray())
        Collections.shuffle(words)
        var mix = ""

        for(i in words){
            mix += i
        }

        return mix

    }



}
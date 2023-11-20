package com.techmania.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class result1 : AppCompatActivity() {
    lateinit var textResult:TextView
    lateinit var ButtonAgain:Button
    lateinit var ButtonExit:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result1)
        textResult=findViewById(R.id.textViewResult)
        ButtonExit=findViewById(R.id.buttonExit)
        ButtonAgain=findViewById(R.id.buttonAgain)

        val score=intent.getIntExtra("score",0)
        //keyword is given along with default value
        textResult.text="Your score is: " + score

        ButtonAgain.setOnClickListener {
            val intent=Intent(this@result1,MainActivity::class.java)
            startActivity(intent)
            finish()  //this is used so that as we move to main activity the result activity closes
        }
        ButtonExit.setOnClickListener {
            val intent=Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
//            line 27,28 and 29 represent the standard codes to close an application
            startActivity(intent)
        }
    }
}
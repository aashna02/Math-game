package com.techmania.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import kotlin.random.Random

class GameActivity3 : AppCompatActivity() {
    lateinit var textScore: TextView
    lateinit var textLife: TextView
    lateinit var textTime: TextView

    lateinit var textQuestion: TextView
    lateinit var edittextAnswer: EditText

    lateinit var ButtonOk: Button
    lateinit var ButtonNext: Button

    var correctAnswer=0
    var userScore=0
    var userLife=3
    lateinit var timer: CountDownTimer      //countdown timer is an abstract class
    private val startTimerInMillis: Long=60000  //time is defined in millisecond
    var timeLeftInMillis: Long=startTimerInMillis //this will show the time left but initially is equal to total time

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game3)
        supportActionBar!!.title="Multiplication"
        textScore=findViewById(R.id.textView2)
        textLife=findViewById(R.id.textView4)
        textTime=findViewById(R.id.textView6)

        textQuestion=findViewById(R.id.TextViewQuestion)
        edittextAnswer=findViewById(R.id.editTextAnswer)

        ButtonOk=findViewById(R.id.buttonOk)
        ButtonNext=findViewById(R.id.buttonNext)

        gameContinue()
        //as soon as the activity opens the function should be called

        ButtonOk.setOnClickListener {
//            we need to prevent the application from crashig if user enters nothing and presses ok button so we use
//                    an if else to check
            val input=edittextAnswer.text.toString()
            if(input==""){
                Toast.makeText(applicationContext,"please enter an answer or click on next button"
                    , Toast.LENGTH_LONG)
                    .show()
            }
            else{
                pauseTimer() //timer should pause regardless of the fact that answer is correct or not
//                firstly we'll convert the input to int to compare it with answer
                val userAnswer=input.toInt()
                if(userAnswer==correctAnswer){
                    userScore+=10
                    textQuestion.text="Congratulations,your answer is correct"
                    textScore.text=userScore.toString()
                }
                else{
                    userLife--
                    textLife.text=userLife.toString()
                    textQuestion.text="oops! that's the wrong answer"

                }

            }

        }
        ButtonNext.setOnClickListener {
            pauseTimer()
            resetTimer()
            if(userLife==0){
                Toast.makeText(applicationContext,"Sorry,Game Over"
                    , Toast.LENGTH_LONG).show()
                val intent= Intent(this@GameActivity3,result1::class.java)
                intent.putExtra("score",userScore)  //to send score to result activity
                startActivity(intent)
                finish()

            }
            else {
                gameContinue()
                edittextAnswer.setText("")     //so that the answer you entered earlier is now deleted
            }
        }

    }
    //    now we need a function that will generate random questions
    fun gameContinue(){
        val number1= Random.nextInt(0,20)
        val number2= Random.nextInt(0,10)

        textQuestion.text="$number1 * $number2"       //will display the question
        correctAnswer=number1 * number2
        startTimer()

    }
    fun startTimer(){
        timer =object: CountDownTimer(timeLeftInMillis,1000){
            //upar wali line mei jb tk object ni likhenge error ayega because we cannot create an  object from abstract class
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis=millisUntilFinished   //means on tick will work until 60s
                updateText()
            }

            override fun onFinish() {
                pauseTimer()    //ans kiya to
                resetTimer()      //after answering it should go back to 60
                updateText()      // updates the time or shows how much time is left

                userLife--   //if user is unable to answer within 60seconds
                textLife.text=userLife.toString()
                textQuestion.text="sorry,time's up"

            }

        }.start()
// the two functions onTick and onFinish will have to be used when using the object keyword

    }
    fun updateText(){
        val remainingTime= (timeLeftInMillis/1000).toInt()
        textTime.text=String.format(Locale.getDefault(),"%02d",remainingTime)
//        string.format is used to to set in which format the string will be displayed
//        %02d means time will be displayed in 2 digits
    }
    fun resetTimer(){
        timeLeftInMillis=startTimerInMillis
        updateText()
    }
    fun pauseTimer(){
        timer.cancel()     //this will pause the timer
    }
}


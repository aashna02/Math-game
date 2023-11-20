package com.techmania.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.techmania.mathgame.ui.theme.GameTheme

class MainActivity : ComponentActivity() {
    lateinit var addition:Button
    lateinit var subtraction:Button
    lateinit var multi:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition=findViewById(R.id.buttonAdd)
        subtraction=findViewById(R.id.buttonSub)
        multi=findViewById(R.id.buttonMulti)

        addition.setOnClickListener {
            val intent=Intent(this@MainActivity,GameActivity::class.java)
            startActivity(intent)
        }
        subtraction.setOnClickListener {
            val intent=Intent(this@MainActivity,GameActivity2::class.java)
            startActivity(intent)
        }
        multi.setOnClickListener {
            val intent=Intent(this@MainActivity,GameActivity3::class.java)
            startActivity(intent)
        }
    }
}






//        setContent {
//            GameTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }


//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    GameTheme {
//        Greeting("Android")
//    }
//}
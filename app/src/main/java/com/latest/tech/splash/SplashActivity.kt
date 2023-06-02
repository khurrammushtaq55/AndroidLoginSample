package com.latest.tech.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.latest.tech.R
import com.latest.tech.components.TopBar
import com.latest.tech.login.AuthActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding
//        createBinding()


        //compose
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                Column() {

                    TopBar(title = { Text("Test App!") })
                    splashView()
                }
            }
        }
    }

    /* private fun createBinding() {
         binding = ActivitySplashBinding.inflate(layoutInflater)
         setContentView(binding.root)

         val spButton = binding.spButton
         spButton.text = "Click Me"
         spButton.setOnClickListener {
             (it as Button).text = "Clicked"
             val intent = Intent(this@SplashActivity, AuthActivity::class.java)
             startActivity(intent)
         }

     }*/

    @Composable
    fun splashView() {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {


                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "dummy"
                )
                Text(
                    text = "Splash View",
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontSize = 30.sp

                )
                Button(modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth()
                    .height(50.dp), onClick = {
                    val intent = Intent(this@SplashActivity, AuthActivity::class.java)
                    startActivity(intent)
                    this@SplashActivity.finish()
                }) {

                    Text(text = "Next", fontSize = 20.sp)
                }
            }
        }

    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun previewSplash() {

        splashView()

    }

}
package com.example.cartaovisitaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cartaovisitaapp.ui.theme.CartaoVisitaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CartaoVisitaAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    //conteiner geral
    Column(modifier = modifier.fillMaxSize().background(Color(0xFF1F2440)),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //conteiner nome e icon
        Column(modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(painter = painterResource(R.drawable.android_logo),
                contentDescription ="Android Icon",
                modifier = modifier.size(120.dp)
            )
            Text(
                text = "Lucas Barauna",
                fontWeight=FontWeight.Bold,
                fontSize=42.sp,
                fontFamily= FontFamily.SansSerif,
                color = Color(0xFFFFFFFF),
                modifier = modifier.
                padding(8.dp),

                )

            Text(
                text = "Android Developer",
                fontSize=22.sp,
                color = Color(0xFF3ddc84),
                modifier = modifier
            )
        }


        //contact
        Column(modifier = modifier,
            verticalArrangement = Arrangement.SpaceBetween,
        ){
            //tel
            TemplenteTextConatc(Icons.Rounded.Call,"Icon Call","+55 11 94444-6666")

            //social media
            TemplenteTextConatc(Icons.Rounded.Share,"Icon Social Media","Bara_Lucas")

            //email
            TemplenteTextConatc( Icons.Rounded.Email,"Icon Email","lucas@gamil.com")
        }
    }
}

//templetes
@Composable
fun TemplenteTextConatc(ico: ImageVector, iconDesc: String, text:String, modifier: Modifier = Modifier){
    Row(modifier = modifier.padding(6.dp)) {
        Icon(
            imageVector = ico,
            contentDescription = iconDesc,
            tint = Color(0xFF3ddc84),
            modifier = modifier.padding(8.dp),
        )

        Text(
            text = text,
            fontSize=18.sp,
            color = Color(0xFFFFFFFF),
            modifier = modifier.padding(8.dp),
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CartaoVisitaAppTheme {
        Greeting("Android")
    }
}
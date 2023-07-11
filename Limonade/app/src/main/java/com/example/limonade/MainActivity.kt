package com.example.limonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.limonade.ui.theme.LimonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimonadeTheme {
                Applimonede()
            }
        }
    }
}

@Composable
fun Applimonede(modifier: Modifier = Modifier) {
    var setPage by remember { mutableStateOf(1) }

    //reseta loop
    if (setPage==5){
        setPage=1
    }

    //define a img
    val imageResource = when(setPage){
        1->R.drawable.lemon_tree
        2->R.drawable.lemon_squeeze
        3->R.drawable.lemon_drink
        4->R.drawable.lemon_restart
        else -> {R.drawable.lemon_tree}
    }

    //define o contentDescription
    val imageDescription = when(setPage){
        1->R.string.limonadeTree
        2->R.string.limon
        3->R.string.limonade_drink
        4->R.string.limonadeDrinkEmpty
        else -> {R.string.limonadeTree}
    }

    // define o text
    val textResource = when(setPage){
        1->R.string.textLimonadeTree
        2->R.string.textLimon
        3->R.string.textDrink
        4->R.string.textReset
        else -> {R.string.textLimonadeTree}
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = stringResource(textResource),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF23376e)
        )

        //cria um espaço entre elemntos, parecido com <br> em html
        Spacer(modifier = Modifier.height(26.dp))

        Button(onClick = {
            var tap=1
            //se for a tela do limão vai gera um numero
            // entre 2 e 4 vezes que tem que clicar para avançar
            if (setPage==2){
                tap++
                if (tap==(2..4).random()){
                    setPage++
                }
            }
            else {
                setPage++
            }
        }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xffffffff))
        )
        {
            Image(painter = painterResource(imageResource),
                contentDescription = stringResource(imageDescription)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LimonadeAppPreview() {
    LimonadeTheme {
        Applimonede(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
        )
    }
}
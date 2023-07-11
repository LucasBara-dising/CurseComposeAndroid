package com.example.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryTheme {
                ArtGallery()

            }
        }
    }
}

@Composable
fun ArtGallery(modifier: Modifier = Modifier) {
    var artId by remember { mutableStateOf(1) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 56.dp, end = 26.dp, bottom = 26.dp, start = 26.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {

        when(artId){
            1->{RetratoArt(R.drawable.capivara_art1," A moça do brinco de pérolas como capivara")
                DescArt("A Capivara do brinco de pérolas", "Johannes Vermeer", "1646")}

            2->{RetratoArt(R.drawable.capivara_art2,"Releitura Capivara como Van Gogh")
                DescArt("Capivara Van Gogh", "Van Gogh", "1853")}

            3->{RetratoArt(R.drawable.capivara_art3,"A Criação de Adão com Capivara")
                DescArt("A Criação da Capivara", "Michelangelo", "1511")}

            4->{RetratoArt(R.drawable.capivara_art4,"O Nascimento de Vênus Verção com capivara")
                DescArt("O Nascimento da Capivara", "Botticelli", "1485")}

            else->{RetratoArt(R.drawable.capivara_art1," A moça do brinco de pérolas como capivara")
            DescArt("A Capivara do brinco de pérolas", "Johannes Vermeer", "1646")}

        }

        Spacer(modifier.padding(8. dp))

        Row(
            modifier= modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                modifier = Modifier
                    .height(40.dp)
                    .width(120.dp),
                shape = CutCornerShape(10),
                //volta uma obra, se tiver no primeiro vai pro ultimo
                onClick = {
                    artId= if (artId == 1) 4 else artId - 1
                    println("id arte Preview $artId")
                }
            ){
                Text(
                    text = "Preview",
                )
            }

            Button(
                modifier = Modifier
                    .height(40.dp)
                    .width(120.dp),
                shape = CutCornerShape(10),
                //avança uma obra, se tiver no ultimo vai pro primeiro
                onClick = {
                    artId= if (artId == 4) 1 else artId+1
                    println("id arte $artId")
                }
            ){
                Text(
                    text = "Next",
                )
            }
        }
    }
}

@Composable
fun DescArt(titleArt: String, ArtistArt: String, anoArt: String, modifier: Modifier = Modifier){
    Column(modifier = modifier.fillMaxWidth()
    ) {
            Text(
                text = titleArt,
                fontSize = 26.sp,
                color = Color.Black,
                modifier = modifier.padding(bottom = 8.dp),
            )

            Text(
                text = "$ArtistArt ($anoArt)",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = modifier.padding(bottom = 8.dp),
            )
    }
}

@Composable
fun RetratoArt(ImgArt: Int, contentDesc:String){
    Box(
        modifier = Modifier
            .border(4. dp, Color.Gray)
            .fillMaxWidth()
            .padding(22. dp)
    ){
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(ImgArt),
            contentDescription =contentDesc,
        )
    }

    Spacer( modifier = Modifier.padding(14. dp))
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtGalleryTheme {
        ArtGallery()
    }
}
package com.example.coursera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursera.data.Datasource
import com.example.coursera.model.Topic
import com.example.coursera.ui.theme.CourseraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CourseraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicList()
                }
            }
        }
    }
}

@Composable
fun TopicList(){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ){
        items(Datasource.topics){ topic->
            TopicosCard(
                topic = topic,
            )
        }
    }


}

@Composable
fun TopicosCard(topic: Topic, modifier: Modifier = Modifier) {
   Card{
       Row{
           Image(
               painter = painterResource(topic.imageTema),
               contentDescription = null,
               modifier = modifier
                   .fillMaxHeight()
                   .width(68.dp),
               contentScale = ContentScale.Crop
           )

           Column(modifier = modifier.padding(12.dp).fillMaxSize()) {
               Text(
                   text = stringResource(topic.temaCurso),
                   style = MaterialTheme.typography.bodyMedium
               )

               Text(
                   text = topic.numCursos.toString(),
                   style = MaterialTheme.typography.labelMedium,
                   modifier = modifier.padding(top=dimensionResource(R.dimen.padding_small))
               )

           }
       }

   }
}

@Preview(showBackground = true)
@Composable
fun TopicosPreview() {
    CourseraTheme {
        TopicList()
    }
}
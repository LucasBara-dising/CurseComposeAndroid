package com.example.listcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listcompose.data.Datasource
import com.example.listcompose.model.Affirmation
import com.example.listcompose.ui.theme.ListComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationApp()
                }
            }
        }
    }
}

@Composable
fun AffirmationApp() {
        AffirmationList(affirmationList = Datasource().loadAffirmations())
}

@Composable
private fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier=Modifier) {
    LazyColumn{
        items(affirmationList){affirmation->AffirmationCard(affirmation)

        }
    }

}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier=Modifier){

    Card(modifier = modifier.padding(8.dp)) {
        Column{
            Image(painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )


            Text(
                text = stringResource(affirmation.stringResourceId),
                modifier =Modifier.padding(16.dp),
                style = MaterialTheme.typography.displayMedium
            )
        }
        
    }
}

@Preview
@Composable
private fun  AffirmationCardPreview(){
    AffirmationCard(Affirmation( R.string.app_name, R.drawable.ic_launcher_background))

}


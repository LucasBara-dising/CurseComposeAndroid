package com.example.listofdayfoods

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listofdayfoods.model.Foods
import com.example.listofdayfoods.model.FoodsRepository
import com.example.listofdayfoods.ui.theme.ListOfDayFoodsTheme


@Composable
fun ListofFoods(
    foods: List<Foods>,
    modifier: Modifier=Modifier
){
    LazyColumn(
        modifier = modifier.padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ){
        itemsIndexed(foods){
            index, foods ->
            ItemListFood(
                food = foods,
            )
        }
    }
}


@Composable
fun ItemListFood(
    food: Foods,
    modifier: Modifier=Modifier
){
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)) {
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = modifier.padding(horizontal = 8.dp)){
                Text(
                    modifier= Modifier
                        .align(alignment = CenterHorizontally),
                    text = stringResource(id = food.foodNumber),
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "de 30",
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(
                modifier = Modifier
                    .width(2.dp)
                    .height(38.dp)
                    .background(Color.LightGray)
            )
            
            Text(
                modifier = modifier.padding(horizontal = 8.dp),
                text = stringResource(id = food.foodName),
                style = MaterialTheme.typography.titleLarge
            )
        }

        Image(
            modifier= Modifier
                .align(alignment = CenterHorizontally)
                .clip(RoundedCornerShape(12.dp)),
            painter = painterResource(id = food.foodImage),
            contentDescription = stringResource(id = food.foodName)
        )
        
        Text(
            modifier= Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = stringResource(id = food.foodDesc)
        )
    }   
}

@Preview("Preview Itens")
@Composable
fun ItemFoodPreview(){
    val food= Foods(
        R.drawable.food1,
        R.string.food_name1,
        R.string.food_desc1,
        R.string.food_num1
    )

    ListOfDayFoodsTheme {
        ItemListFood(food = food)
    }
}

@Preview("Preview List")
@Composable
fun ListFoodPreviwe(){
    ListOfDayFoodsTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ListofFoods(foods = FoodsRepository.foods)
        }
    }
}
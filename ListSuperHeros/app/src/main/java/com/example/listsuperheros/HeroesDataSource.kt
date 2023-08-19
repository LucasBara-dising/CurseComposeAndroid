package com.example.listsuperheros

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listsuperheros.model.Hero
import com.example.listsuperheros.model.HeroesRepository
import com.example.listsuperheros.ui.theme.ListSuperHerosTheme

class HeroesDataSource {
    @Composable
    fun ItemHero(
        hero: Hero,
        modifier: Modifier=Modifier
    ){
        Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ){
            Row(
                modifier =Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .padding(16.dp)
                    .sizeIn(minHeight = 72.dp)
            ){
                HeroInformation(hero.nameHero,hero.descHero)
                HeroIcon(hero.imageIdHero)
            }
        }
    }

    @Composable
    fun HeroInformation(
        @StringRes HeroName: Int,
        @StringRes HeroDesc: Int,
        modifier: Modifier=Modifier
    ){
        Column(
            modifier = modifier.padding(end = 16. dp)
        ) {
            Text(
                text = stringResource(id = HeroName),
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = stringResource(id = HeroDesc),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    @Composable
    fun HeroIcon(
        @DrawableRes iconHero: Int,
        modifier: Modifier=Modifier
    ) {
        Box(
            modifier = modifier
                .clip(MaterialTheme.shapes.small)
                .fillMaxHeight()
            ){
            Image(
                painter = painterResource(id = iconHero),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.FillWidth
            )
        }
    }

    @Composable 
    fun ListHero(
        heros: List<Hero>,
        modifier: Modifier=Modifier
    ){
        val visibleState= remember {
            MutableTransitionState(false).apply {
                targetState=true
            }
        }

        LazyColumn{
            itemsIndexed(heros){
                index, hero ->
                ItemHero(
                    hero=hero,
                    modifier =  Modifier
                        .padding(horizontal = 16. dp, vertical = 8.dp)
                )
            }
        }

    }


    //Preview
    @Preview("Light Thema")
    @Preview("Drak Thema", uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    fun HeroPreviw(){
        val hero= Hero(
            R.string.hero1,
            R.string.description1,
            R.drawable.android_superhero1
        )
        ListSuperHerosTheme {
            ItemHero(hero = hero)
        }
    }


    @Preview("Heroes List")
    @Composable
    fun HeroesPreview() {
        ListSuperHerosTheme(darkTheme = false) {
            Surface (
                color = MaterialTheme.colorScheme.background
            ) {
                /* Important: It is not a good practice to access data source directly from the UI.
                In later units you will learn how to use ViewModel in such scenarios that takes the
                data source as a dependency and exposes heroes.
                */
                ListHero(heros = HeroesRepository.heroes)
            }
        }
    }

}
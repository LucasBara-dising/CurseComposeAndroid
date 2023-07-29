package com.example.listsuperheros.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.listsuperheros.R

class Hero (
    @StringRes val nameHero: Int,
    @StringRes val descHero: Int,
    @DrawableRes val imageIdHero: Int
        ){

}

object HeroesRepository {
    val heroes = listOf(
        Hero(
            nameHero = R.string.hero1,
            descHero = R.string.description1,
            imageIdHero = R.drawable.android_superhero1
        ),
        Hero(
            nameHero = R.string.hero2,
            descHero = R.string.description2,
            imageIdHero = R.drawable.android_superhero2
        ),
        Hero(
            nameHero = R.string.hero3,
            descHero = R.string.description3,
            imageIdHero = R.drawable.android_superhero3
        ),
        Hero(
            nameHero = R.string.hero4,
            descHero = R.string.description4,
            imageIdHero = R.drawable.android_superhero4
        ),
        Hero(
            nameHero = R.string.hero5,
            descHero = R.string.description5,
            imageIdHero = R.drawable.android_superhero5
        ),
        Hero(
            nameHero = R.string.hero6,
            descHero = R.string.description6,
            imageIdHero = R.drawable.android_superhero6
        )
    )
}
package com.example.coursera.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic (
    @StringRes val temaCurso: Int,
    val numCursos: Int,
    @DrawableRes val imageTema: Int
    )

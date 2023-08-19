package com.example.listofdayfoods.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Foods (
    @DrawableRes val foodImage: Int,
    @StringRes val foodName: Int,
    @StringRes val foodDesc: Int,
    @StringRes val foodNumber: Int,
)


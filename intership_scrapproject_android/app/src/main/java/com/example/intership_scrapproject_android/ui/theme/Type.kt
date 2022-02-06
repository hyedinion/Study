package com.example.intership_scrapproject_android.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    h5= TextStyle(
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        letterSpacing = 0.25.sp
    ),
    body1 = TextStyle(
        color = Color.DarkGray,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.5.sp
    ),

    h6 = TextStyle(
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = 0.15.sp
    ),
    body2= TextStyle(
        color = Color.DarkGray,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 0.25.sp
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
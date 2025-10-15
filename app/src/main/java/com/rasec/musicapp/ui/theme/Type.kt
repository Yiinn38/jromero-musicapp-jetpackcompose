package com.rasec.musicapp.ui.theme

import com.rasec.musicapp.R

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val poppinsFontFamily = FontFamily(
  Font(R.font.poppins_regular),
  Font(R.font.poppins_light, FontWeight.W300),
  Font(R.font.poppins_bold, FontWeight.W700)
)

val poppinsTypo = Typography(
  bodyLarge = TextStyle(
    fontWeight = FontWeight.Normal,
    fontFamily = poppinsFontFamily,
    fontSize = 16.sp,
    lineHeight = 24.sp
  ),
  bodyMedium = TextStyle(
    fontWeight = FontWeight.Normal,
    fontFamily = poppinsFontFamily,
    fontSize = 14.sp,
    lineHeight = 20.sp
  ),
  bodySmall = TextStyle(
    fontWeight = FontWeight.Normal,
    fontFamily = poppinsFontFamily,
    fontSize = 12.sp,
    lineHeight = 16.sp
  ),
)
package com.example.doodlefrontend.views.updatetext

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.doodlefrontend.R

data class FontOption(
    val displayName: String,
    val fontFamily: FontFamily
)

data class ColorOption(
    val displayName: String,
    val colorOption: Color
)

val listOfFonts = listOf(
    FontOption(
        fontFamily = FontFamily(Font(R.font.notcursivehand)),
        displayName = "hand written"
    ),
    FontOption(
        fontFamily = FontFamily(Font(R.font.cursivehand)),
        displayName = "cursive"
    ),
    FontOption(
        fontFamily = FontFamily(Font(R.font.bebas)),
        displayName = "bebas neue"
    ),
    FontOption(
        fontFamily = FontFamily(Font(R.font.instrumentserif)),
        displayName = "instrument serif"
    ),
    FontOption(
        fontFamily = FontFamily(Font(R.font.inter)),
        displayName = "Inter"
    ),
    FontOption(
        fontFamily = FontFamily(Font(R.font.playfair)),
        displayName = "playfair display"
    ),
    FontOption(
        fontFamily = FontFamily(Font(R.font.spacemono)),
        displayName = "typewriter"
    )
)

val listOfColor = listOf(
    ColorOption(displayName = "White", colorOption = Color.White),
    ColorOption(displayName = "Red", colorOption = Color(0xffc11d1d)),
    ColorOption(displayName = "Black", colorOption = Color(0xFF2B2B2B)),
    ColorOption(displayName = "Yellow", colorOption = Color(0xffF2E199)),
    ColorOption(displayName = "Green", colorOption = Color(0xff4F6815)),
    ColorOption(displayName = "Blue", colorOption = Color(0xFF7FB6E8)),
    ColorOption(displayName = "Pink", colorOption = Color(0xFFF7899C)),
    ColorOption(displayName = "Purple", colorOption = Color(0xFFB987F7))
)

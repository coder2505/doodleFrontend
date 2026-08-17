package com.example.doodlefrontend.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doodlefrontend.ui.theme.DoodleFrontendTheme
import com.example.doodlefrontend.ui.theme.notcursiveFont
import com.example.doodlefrontend.views.updatetext.BackgroundColorRow
import com.example.doodlefrontend.views.updatetext.Header
import com.example.doodlefrontend.views.updatetext.PreviewWidget
import com.example.doodlefrontend.views.updatetext.TypographyRow
import com.example.doodlefrontend.views.updatetext.listOfColor
import com.example.doodlefrontend.views.updatetext.listOfFonts
import kotlinx.coroutines.flow.MutableStateFlow

@Preview
@Composable
fun UpdateScreenTextScreen() {
    val scrollState = rememberScrollState()
    val width = LocalConfiguration.current.screenWidthDp
    val fontSelected = remember { MutableStateFlow<FontFamily>(listOfFonts[0].fontFamily) }
    val colorSelected = remember { MutableStateFlow(listOfColor[0].colorOption) }
    val textFieldState = rememberTextFieldState()

    DoodleFrontendTheme {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 8.dp)
                    .verticalScroll(scrollState)
            ) {
                Header()
                Spacer(Modifier.height(32.dp))
                PreviewWidget(width, fontSelected, colorSelected, textFieldState)

                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .border(
                            1.dp, Color(0x66000000),
                            RoundedCornerShape(4.dp)
                        )
                        .background(Color(0xfffffbec))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            "Typography",
                            style = TextStyle(
                                fontFamily = notcursiveFont,
                                fontWeight = FontWeight.Black,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        )
                        TypographyRow(fontSelected)

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Background Color",
                            style = TextStyle(
                                fontFamily = notcursiveFont,
                                fontWeight = FontWeight.Black,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        )
                        BackgroundColorRow(colorSelected)
                    }
                }
            }
        }
    }
}

package com.example.doodlefrontend.views.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.doodlefrontend.R
import com.example.doodlefrontend.ui.theme.notcursiveFont
import com.example.doodlefrontend.viewmodels.UpdateTextViewModel

@Composable
fun SubmitText(
    updateTextViewModel: UpdateTextViewModel = hiltViewModel()
) {

    val textFieldState = rememberTextFieldState("")
    val keyboardController = LocalSoftwareKeyboardController.current

    UI(textFieldState, updateTextViewModel)

}

@Preview
@Composable
private fun UI(
    textFieldState: TextFieldState = rememberTextFieldState(),
    updateTextViewModel: UpdateTextViewModel = hiltViewModel()
) {

    val width: Int = LocalConfiguration.current.screenWidthDp
    val height: Int = LocalConfiguration.current.screenHeightDp

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            state = textFieldState
        )

        Spacer(Modifier.weight(1f))

        Button(
            onClick = {
                val text = textFieldState.text.toString();
                updateTextViewModel.update(payload = text)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
            modifier = Modifier
                .width((width * 0.4).dp)
                .height((height * 0.1).dp)
                .paint(
                    painter = painterResource(R.drawable.doodlerectagle1),
                    contentScale = ContentScale.FillBounds
                )
        ) {

            Text("Submit", style = TextStyle(color = Color.Black, fontFamily = notcursiveFont))

        }
    }

}
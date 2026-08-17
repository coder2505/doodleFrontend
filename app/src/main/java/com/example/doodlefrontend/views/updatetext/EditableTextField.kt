package com.example.doodlefrontend.views.updatetext

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun EditableTextField(
    textFieldState: TextFieldState,
    font: MutableStateFlow<FontFamily>,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }
    val selectedFont by font.collectAsState()

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            state = textFieldState,
            textStyle = TextStyle(
                fontFamily = selectedFont,
                color = Color.Black,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            onKeyboardAction = {
                focusManager.clearFocus()
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isFocused = it.isFocused },
            decorator = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    if (textFieldState.text.isEmpty() && !isFocused) {
                        Text(
                            text = "tap here to put a text",
                            style = TextStyle(
                                fontFamily = selectedFont,
                                color = Color.Gray,
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

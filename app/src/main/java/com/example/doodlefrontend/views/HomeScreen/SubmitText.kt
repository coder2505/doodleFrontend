package com.example.doodlefrontend.views.HomeScreen
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.doodlefrontend.viewmodels.UpdateTextViewModel
import javax.inject.Inject

@Composable
fun SubmitText(
    updateTextViewModel: UpdateTextViewModel = hiltViewModel()
) {

    val textFieldState = rememberTextFieldState("")
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            state = textFieldState,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Send
            ),
            onKeyboardAction = {

                val text = textFieldState.text.toString();

                updateTextViewModel.update(payload = text)

                keyboardController?.hide()

            }
        )
    }


}
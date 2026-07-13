package com.example.doodlefrontend.views.HomeScreen

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doodlefrontend.R
import com.example.doodlefrontend.ui.theme.DoodleFrontendTheme
import com.example.doodlefrontend.ui.theme.cursiveFont
import com.example.doodlefrontend.ui.theme.notcursiveFont
import com.example.doodlefrontend.ui.theme.peach

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen() {

    var openBottomSheet by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )


    DoodleFrontendTheme {

        Scaffold(Modifier.fillMaxSize()) { padding ->


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 8.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "Backrooms",
                        fontFamily = cursiveFont,
                        fontSize = 32.sp,
                        color = Color.Black
                    )
                    Spacer(Modifier.weight(1f))
                    ProfileButton()
                }

                Spacer(modifier = Modifier.weight(1f))


                Text(
                    "Current Doodle",
                    fontFamily = notcursiveFont,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Image(
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                openBottomSheet = !openBottomSheet
                            }
                        ),
                    contentDescription = "",
                    painter = painterResource(R.drawable.doodlebackground)

                )
                Text(
                    "User123",
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = notcursiveFont,
                    fontSize = 20.sp,
                    textAlign = TextAlign.End,
                    color = Color.Black

                )


                Spacer(modifier = Modifier.weight(1f))


            }

            if (openBottomSheet) {


                ModalSheet(
                    bottomSheetState
                ) {
                    openBottomSheet = false
                }


            }


        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalSheet(
    bottomSheetState: SheetState,
    onDismiss: () -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState,
        scrimColor = Color.Transparent,
        containerColor = Color(0xffF5E3AF),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            SingleSegmentedButton()

        }

    }


}

@Composable
fun InputOptions(index: Int) {

    when (index) {

        0 -> DrawCanvas()
        1 -> PickMedia()
        2 -> SubmitText()


    }

}


@Composable
fun SingleSegmentedButton() {

    var selectedIndex by remember { mutableIntStateOf(0) }
    val options =
        listOf(R.drawable.editdoodle, R.drawable.editpic, R.drawable.editicon)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SingleChoiceSegmentedButtonRow {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options.size,
                    ),
                    onClick = { selectedIndex = index },
                    selected = index == selectedIndex,
                    colors = SegmentedButtonDefaults.colors(

                        activeContainerColor = peach,
                        activeBorderColor = Color.Black,
                        inactiveContainerColor = Color.White,

                        ),
                    label = {
                        Box(
                            modifier = Modifier.size(size = 25.dp)
                        ) {
                            Image(
                                contentDescription = "",
                                painter = painterResource(options[index]),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                )
            }
        }

        Spacer(Modifier.height(5.dp))

        Box(
            modifier = Modifier.size(400.dp).padding(10.dp)
        ) {
            InputOptions(selectedIndex)
        }

        Button(onClick = {}){
            Text("Submit")
        }
    }

}

@Composable
fun ProfileButton() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .height(60.dp)
            .paint(
                painter = painterResource(R.drawable.iconbutton),
            ),
    ) {
        Text(
            "m",
            color = Color.Black,
            fontFamily = cursiveFont,
            fontSize = 24.sp
        )

    }
}
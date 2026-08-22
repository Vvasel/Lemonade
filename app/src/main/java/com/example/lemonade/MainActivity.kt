package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp () {
    var currentStep by remember { mutableStateOf(1) }
    var tap by remember { mutableStateOf((2..4).random()) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {

            1 -> {
                ButtonImageText(
                    imageId = R.drawable.lemon_tree,
                    textId = R.string.tap_lemon_tree,
                    contentDescriptionId = R.string.lemon_tree_content_description,
                    imageClick = {
                        tap = (2..4).random()
                        currentStep = 2
                    }
                )
            }

            2 -> {
                ButtonImageText(
                    imageId = R.drawable.lemon_squeeze,
                    textId = R.string.tap_lemon,
                    contentDescriptionId = R.string.lemon_content_description,
                    imageClick = {
                        tap--
                        if (tap == 0) {
                            currentStep = 3
                        }
                    }
                )
            }

            3 -> {
                ButtonImageText(
                    imageId = R.drawable.lemon_drink,
                    textId = R.string.tap_lemonade,
                    contentDescriptionId = R.string.glass_of_lemonade_content_description,
                    imageClick = { currentStep = 4 }
                )
            }

            4 -> {
                ButtonImageText(
                    imageId = R.drawable.lemon_restart,
                    textId = R.string.tap_empty_glass,
                    contentDescriptionId = R.string.empty_glass_content_description,
                    imageClick = { currentStep = 1 }
                )
            }

            }
        }
    }
}

@Composable
fun ButtonImageText (
    imageId: Int,
    textId: Int,
    contentDescriptionId: Int,
    imageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = imageClick,
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = painterResource(imageId),
                contentDescription = stringResource(contentDescriptionId),
                modifier = Modifier
                    .width(128.dp)
                    .height(160.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(textId),
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}
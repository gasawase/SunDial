package com.sundial.v1001

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sundial.v1001.dto.Twilight
import com.sundial.v1001.ui.theme.SunDialTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.fetchData()
            val twilight by viewModel.twilight.observeAsState(initial = emptyList<Twilight>())
            SunDialTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    TwilightFacts("Android")
                }
            }
        }
    }
}

@Composable
fun TwilightFacts(name: String) {
    var sunrise by remember { mutableStateOf("")}
    var sunset by remember { mutableStateOf("")}
    val context = LocalContext.current
    Column {
        OutlinedTextField(
            value = sunrise,
            onValueChange = { sunrise = it },
            label = { Text(stringResource(R.string.sunrise)) }
        )
        OutlinedTextField(
            value = sunset,
            onValueChange = { sunset = it },
            label = { Text(stringResource(R.string.sunset)) }
        )
        Button(
            onClick = {
                Toast.makeText(context, "$sunrise $sunset", Toast.LENGTH_LONG).show()
            }
        ){
            Text(text = "save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SunDialTheme {
        TwilightFacts("Android")
    }
}
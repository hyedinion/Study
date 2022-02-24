package com.example.flowtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flowtest.ui.theme.FlowTestTheme

class MainActivity : ComponentActivity() {

    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowTestTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(text = "liveData")
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "liveData")
                    }
                    Text(text = "flow")
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "flow")
                    }
                    Text(text = "stateflow")
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "stateflow")
                    }
                    Text(text = "state")
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "state")
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FlowTestTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(text = "liveData")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "liveData")
            }
            Text(text = "flow")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "flow")
            }
            Text(text = "stateflow")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "stateflow")
            }
            Text(text = "state")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "state")
            }

        }
    }
}
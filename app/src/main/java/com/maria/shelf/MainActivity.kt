package com.maria.shelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maria.shelf.ui.theme.ShelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShelfTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun MyUI(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello from Maria!!",
            modifier = modifier
        )
        Text(
            text = "Sultana",
            modifier = modifier
        )
        Button(onClick = {println("Clicked me")}) {
            Text("Click Me")
        }
    }
}
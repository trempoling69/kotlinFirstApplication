package com.example.myapplication

import android.os.Bundle
import androidx.compose.material3.Text
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.viewModel.FragmentViewModel
import androidx.compose.runtime.getValue


class FragmentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFragmentScreen()
        }
    }
}

@Composable
fun MyFragmentScreen(viewModel: FragmentViewModel= viewModel()) {
    val messages by viewModel.messages.observeAsState(initial = emptyList())
    Scaffold { paddingValues ->
        Column(modifier = Modifier.fillMaxWidth().padding(paddingValues)) {
            for (message in messages) {
                Element(message, modifier = Modifier.fillMaxWidth().padding(4.dp))
            }
            Button(onClick = { viewModel.addMessage("Nouveau message") }, Modifier.fillMaxWidth().padding(20.dp)) {
                Text("Ajouter un message")
            }
        }
    }
}

@Composable
fun Element(string: String, modifier: Modifier){
    Text(string, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))
}
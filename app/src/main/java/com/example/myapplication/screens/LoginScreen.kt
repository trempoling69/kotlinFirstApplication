package com.example.myapplication.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.FragmentActivity
import com.example.myapplication.viewModel.MainViewModel

@Composable
fun LoginScreen(modifier: Modifier = Modifier,viewModel: MainViewModel = viewModel()) {
    val email by viewModel.email
    val password by viewModel.password
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Email")
        OutlinedTextField(
            value = email,
            onValueChange = { newEmail -> viewModel.onMailChanged(newEmail) },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        Text(text = "Mot de passe")
        OutlinedTextField(
            value = password,
            onValueChange = { newPassword -> viewModel.onPasswordChanged(newPassword) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                if (password.isNotEmpty() && email.isNotEmpty()) {
                    Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) {
            Text(text = "Se connecter", color = Color.White)
        }
        Button(
            onClick = {
                val intent = Intent(context, FragmentActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth().padding(0.dp, top = 8.dp)
        ) {
            Text(text = "Aller au fragment", color = Color.White)
        }
    }
}
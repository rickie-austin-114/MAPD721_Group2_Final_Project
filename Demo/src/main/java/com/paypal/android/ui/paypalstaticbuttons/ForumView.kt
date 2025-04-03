package com.paypal.android.ui.paypalstaticbuttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun ForumView() {
    Text(text = "Forum View")

    var inputText by remember { mutableStateOf(TextFieldValue("")) }
    var savedText by remember { mutableStateOf("Waiting for data...") }
    val db = FirebaseFirestore.getInstance()

    LaunchedEffect(Unit) {
        // Fetch text from Firebase on launch
        db.collection("messages").document("latest")
            .get()
            .addOnSuccessListener { document ->
                savedText = document.getString("text") ?: "No data"
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = savedText, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter text") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val textToSave = inputText.text
            val data = hashMapOf("text" to textToSave)

            db.collection("messages").document("latest")
                .set(data)
                .addOnSuccessListener {
                    savedText = textToSave
                }
        }) {
            Text("Save to Firebase")
        }
    }
}


package com.paypal.android.ui.paypalwebvault


import android.bluetooth.BluetoothAdapter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun BluetoothChatView() {
    var isBluetoothAvailable by remember { mutableStateOf(false) }

    // Check Bluetooth availability
    LaunchedEffect(Unit) {
        val btAdapter = BluetoothAdapter.getDefaultAdapter()
        isBluetoothAvailable = btAdapter != null
    }

    // UI
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isBluetoothAvailable) {
                    "Bluetooth is available on this device."
                } else {
                    "Bluetooth is NOT available on this device."
                },
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

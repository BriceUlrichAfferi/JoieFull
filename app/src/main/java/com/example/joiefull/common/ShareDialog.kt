package com.example.joiefull.common

import android.content.Context
import android.content.Intent
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShareDialog(
    shareText: String,
    onDismiss: () -> Unit,
    onTextChanged: (String) -> Unit
) {
    val context = LocalContext.current
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                shareProduct(context, shareText)
                onDismiss()
            }) {
                Text("Share")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        },
        text = {
            TextField(
                value = shareText,
                onValueChange = { onTextChanged(it) },
                label = { Text("Edit share text") }
            )
        }
    )
}

private fun shareProduct(context: Context, shareText: String) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }
    val chooser = Intent.createChooser(intent, "Share via")
    context.startActivity(chooser)
}

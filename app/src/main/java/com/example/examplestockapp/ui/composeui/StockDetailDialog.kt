package com.example.examplestockapp.ui.composeui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun StockDetailDialog(
    name: String,
    peRatio: String,
    pbRatio: String,
    dividendYield: String,
    onDismissRequest: () -> Unit,
) {
    val openDialog = remember { mutableStateOf(false) }
    if (!openDialog.value) {
        return
    }
    val infoString = createInfo(peRatio, pbRatio, dividendYield)
    AlertDialog(
        title = {
            Text(text = name)
        },
        text = {
            Text(text = infoString)
        },
        onDismissRequest = {
            openDialog.value = false
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("確定")
            }
        }
    )
}

private fun createInfo(
    peRatio: String,
    pbRatio: String,
    dividendYield: String,
): String {
    val dividendYieldPercent = dividendYield.toFloat() * 100f
    return "本益比:$${peRatio}、殖利率${dividendYieldPercent}%)、股價淨值比:${pbRatio}"
}
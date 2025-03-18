package com.example.examplestockapp.ui.composeui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@Composable
fun StockDetailDialog(
    name: String,
    peRatio: String?,
    pbRatio: String?,
    dividendYield: String?,
    onDismissRequest: () -> Unit = {},
) {

    val infoString = createInfo(peRatio, pbRatio, dividendYield)
    AlertDialog(
        title = {
            Text(text = name)
        },
        text = {
            Text(text = infoString)
        },
        onDismissRequest = {
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
    peRatio: String?,
    pbRatio: String?,
    dividendYield: String?,
): String {
    val dividendYieldPercent = if (dividendYield == null) 0.0 else dividendYield.toFloat() * 100f
    return "本益比:$${peRatio}、殖利率${dividendYieldPercent}%)、股價淨值比:${pbRatio}"
}
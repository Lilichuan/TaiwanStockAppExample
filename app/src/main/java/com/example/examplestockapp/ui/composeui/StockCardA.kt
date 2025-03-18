package com.example.examplestockapp.ui.composeui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examplestockapp.R
import com.example.examplestockapp.ui.theme.DeepGreen

@Composable
fun StockCardA(
    code: String?,
    name: String?,
    openingPrice: String?,
    closingPrice: String?,
    highestPrice: String?,
    lowestPrice: String?,
    change: String?,
    monthlyAveragePrice: String?,
    transaction: String?,
    tradeVolume: String?,
    tradeValue: String?,
    onclickEvent: () -> Unit = {}
) {

    Card(
        modifier = Modifier.clickable {
            onclickEvent()
        },
        shape = RoundedCornerShape(8.dp)
    ) {

        fun fixText(text: String?): String {
            return text ?: ""
        }

        Column {

            Text(
                modifier = Modifier.padding(5.dp), text = fixText(code)
            )

            Text(
                modifier = Modifier.padding(5.dp),
                text = fixText(name),
                fontSize = 16.sp
            )

            val rowModifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()

            Row(modifier = rowModifier)
            {
                val smallModifier = Modifier
                    .weight(1.0f)
                    .align(Alignment.CenterVertically)
                SmallTitle(
                    smallModifier,
                    title = R.string.start_price
                )
                ValueText(
                    smallModifier,
                    valueText = fixText(openingPrice)
                )
                SmallTitle(
                    smallModifier,
                    title = R.string.end_price
                )
                ValueText(
                    smallModifier,
                    valueText = fixText(closingPrice),
                    color = createColor(closingPrice, monthlyAveragePrice)
                )
            }

            Row(modifier = rowModifier) {
                val smallModifier = Modifier
                    .weight(1.0f)
                    .align(Alignment.CenterVertically)
                SmallTitle(smallModifier, title = R.string.height_price)
                ValueText(smallModifier, valueText = fixText(highestPrice))
                SmallTitle(smallModifier, title = R.string.low_price)
                ValueText(smallModifier, valueText = fixText(lowestPrice))
            }

            Row(modifier = rowModifier) {
                val smallModifier = Modifier
                    .weight(1.0f)
                    .align(Alignment.CenterVertically)
                SmallTitle(smallModifier, title = R.string.textA)
                ValueText(smallModifier, valueText = fixText(change), createColor(change))
                SmallTitle(smallModifier, title = R.string.average_month_price)
                ValueText(smallModifier, valueText = fixText(monthlyAveragePrice))
            }

            Row(modifier = rowModifier) {
                val smallModifier = Modifier
                    .weight(1.0f)
                    .align(Alignment.CenterVertically)
                SmallTitle(smallModifier, title = R.string.deal_count)
                ValueText(smallModifier, valueText = fixText(transaction))
                SmallTitle(smallModifier, title = R.string.deal_abc)
                ValueText(smallModifier, valueText = fixText(tradeVolume))
                SmallTitle(smallModifier, title = R.string.deal_money)
                ValueText(smallModifier, valueText = fixText(tradeValue))
            }
        }
    }
}


@Composable
fun SmallTitle(
    modifier: Modifier = Modifier,
    @StringRes title: Int
) {
    Text(
        modifier = modifier,
        text = stringResource(title),
        textAlign = TextAlign.Right,
        fontSize = 13.sp
    )
}

@Composable
fun ValueText(
    modifier: Modifier = Modifier,
    valueText: String,
    color: Color = Color.Black
) {
    Text(
        modifier = modifier.padding(5.dp),
        text = valueText,
        textAlign = TextAlign.Right,
        fontSize = 16.sp,
        color = color
    )
}

private fun createColor(baseNumber: String?, compareNumber: String?): Color {
    if (baseNumber == null || compareNumber == null) {
        return Color.Black
    }
    return checkColorByValue(baseNumber.toFloat(), compareNumber.toFloat())
}

private fun createColor(baseNumber: String?): Color {
    if (baseNumber == null) {
        return Color.Black
    }

    val baseNumberF = baseNumber.toFloat()
    return checkColorByValue(baseNumberF, 0.0f)
}

private fun checkColorByValue(floatA: Float, floatB: Float): Color {
    return if (floatA > floatB) {
        Color.Red
    } else if (floatA < floatB) {
        DeepGreen
    } else {
        Color.Black
    }
}

@Composable
@Preview
private fun PreviewStockCardA() {
    StockCardA(
        code = "123",
        name = "Name",
        openingPrice = "25.0",
        closingPrice = "32.1",
        highestPrice = "33.0",
        lowestPrice = "25.0",
        change = "20",
        monthlyAveragePrice = "20",
        transaction = "20",
        tradeVolume = "200",
        tradeValue = "200",
        {}
    )
}


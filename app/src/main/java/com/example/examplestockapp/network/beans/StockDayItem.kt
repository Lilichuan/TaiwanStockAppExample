package com.example.examplestockapp.network.beans

import com.google.gson.annotations.SerializedName

class StockDayItem {
    @SerializedName("Code")
    var code = ""
    @SerializedName("Name")
    var name = ""

    @SerializedName("TradeVolume")
    var tradeVolume = ""

    @SerializedName("TradeValue")
    var tradeValue = ""

    @SerializedName("OpeningPrice")
    var openingPrice = ""

    @SerializedName("HighestPrice")
    var highestPrice = ""

    @SerializedName("LowestPrice")
    var lowestPrice = ""

    @SerializedName("ClosingPrice")
    var closingPrice = ""

    @SerializedName("Change")
    var change = ""

    @SerializedName("Transaction")
    var transaction = ""
}
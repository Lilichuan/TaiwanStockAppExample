package com.example.examplestockapp.network.beans

import com.google.gson.annotations.SerializedName

class StockAvgItem {
    @SerializedName("Code")
    var code = ""
    @SerializedName("Name")
    var name = ""
    @SerializedName("ClosingPrice")
    var closingPrice = ""

    @SerializedName("MonthlyAveragePrice")
    var monthlyAverage = ""
}
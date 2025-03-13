package com.example.examplestockapp.network.beans

import com.google.gson.annotations.SerializedName

class BwibbuItem {
    @SerializedName("Code")
    var code = ""
    @SerializedName("Name")
    var name = ""
    @SerializedName("PEratio")
    var peRatio = ""

    @SerializedName("DividendYield")
    var dividendYield = ""

    @SerializedName("PBratio")
    var pbRatio = ""
}
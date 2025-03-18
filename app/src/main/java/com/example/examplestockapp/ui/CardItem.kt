package com.example.examplestockapp.ui

class CardItem (
    var code: String? = null,
    var name: String? = null,
    var openingPrice: String? = null,
    var closingPrice:String? = null,
    var highestPrice: String? = null,
    var lowestPrice: String? = null,
    var change: String? = null,
    var monthlyAveragePrice: String? = null,
    var transaction: String? = null,
    var tradeVolume: String? = null,
    var tradeValue: String? = null,

    var peRatio: String? = null,
    var dividendYield: String? = null,
    var pbRatio: String? = null,
)
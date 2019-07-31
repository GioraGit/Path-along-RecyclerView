package co.example.mystockapp.features.stockTrend.ui

import androidx.annotation.ColorRes

class DayStockUIModel(val day: String, val roundedValue: Double, val change: Change)

class Change(val amount: String, @ColorRes val color: Int)
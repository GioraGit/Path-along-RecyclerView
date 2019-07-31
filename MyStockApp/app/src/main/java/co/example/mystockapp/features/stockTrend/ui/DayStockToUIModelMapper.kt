package co.example.mystockapp.features.stockTrend.ui

import co.example.mystockapp.R
import co.example.mystockapp.features.stockTrend.domain.DayStock
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.roundToInt

const val DATE_FORMAT_MMMM_d = "MMMM d"
private val dateFormatMMMM_d = SimpleDateFormat(DATE_FORMAT_MMMM_d, Locale.getDefault())

internal interface IDayStockToUIModelMapper {
    fun map(dayStock: DayStock, previousDayStockUIModel: DayStockUIModel?): DayStockUIModel
}

internal class DayStockToUIModelMapper : IDayStockToUIModelMapper {

    override fun map(dayStock: DayStock, previousDayStockUIModel: DayStockUIModel?): DayStockUIModel {
        val roundedValue = dayStock.value.roundToDouble(2)
        val change = previousDayStockUIModel?.let { (roundedValue - it.roundedValue).roundToDouble(2) }
        return DayStockUIModel(
            dateFormatMMMM_d.format(dayStock.date),
            dayStock.value.roundToDouble(2),
            getChange(change)
        )
    }

    private fun getChange(change: Double?): Change {
        if (change == null || change == 0.0) {
            return Change("--", R.color.noChange)
        }

        return if (change > 0) {
            Change("+$${change.absoluteValue}", R.color.positiveChange)
        } else {
            Change("-$${change.absoluteValue}", R.color.negativeChange)
        }
    }

    companion object {
        val get: IDayStockToUIModelMapper = DayStockToUIModelMapper()
    }
}

fun Double.roundToDouble(places: Int): Double {
    val divisor = 10.0.pow(places.toDouble())
    return (this * divisor).roundToInt() / divisor
}
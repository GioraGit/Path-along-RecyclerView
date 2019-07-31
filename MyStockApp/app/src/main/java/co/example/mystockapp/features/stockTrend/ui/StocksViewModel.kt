package co.example.mystockapp.features.stockTrend.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import co.example.mystockapp.features.stockTrend.domain.IStocksUseCase

internal class StocksViewModel(
    private val stocksUseCase: IStocksUseCase,
    private val dayStockToUIModelMapper: IDayStockToUIModelMapper
) : ViewModel() {

    val dayStockUIModels: LiveData<List<DayStockUIModel>> = Transformations.map(stocksUseCase.dayStocks) {
        val UIModels = mutableListOf<DayStockUIModel>()
        var previousDayStockUIModel: DayStockUIModel? = null
        it.forEach { dayStock ->
            val dayStockUIModel = dayStockToUIModelMapper.map(dayStock, previousDayStockUIModel)
            UIModels.add(dayStockUIModel)
            previousDayStockUIModel = dayStockUIModel
        }
        UIModels
    }
}
package co.example.mystockapp.features.stockTrend.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.example.mystockapp.features.stockTrend.domain.IStocksUseCase
import co.example.mystockapp.features.stockTrend.domain.StocksUseCase

interface IStocksViewModelFactory : ViewModelProvider.Factory

internal class StocksViewModelFactory(
    private val stocksUseCase: IStocksUseCase,
    private val dayStockToUIModelMapper: IDayStockToUIModelMapper
) : IStocksViewModelFactory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(IStocksUseCase::class.java, IDayStockToUIModelMapper::class.java)
            .newInstance(stocksUseCase, dayStockToUIModelMapper)
    }

    companion object {
        val get: IStocksViewModelFactory = StocksViewModelFactory(StocksUseCase.get, DayStockToUIModelMapper.get)
    }
}
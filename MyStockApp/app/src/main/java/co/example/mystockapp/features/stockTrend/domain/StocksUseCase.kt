package co.example.mystockapp.features.stockTrend.domain

import androidx.lifecycle.LiveData
import co.example.mystockapp.features.stockTrend.data.StocksRepository

internal interface IStocksUseCase {

    val dayStocks: LiveData<List<DayStock>>
}

internal class StocksUseCase(private val stocksRepository: IStocksRepository) : IStocksUseCase {

    override val dayStocks: LiveData<List<DayStock>> = stocksRepository.dayStocks

    companion object {
        val get: IStocksUseCase = StocksUseCase(StocksRepository.get)
    }
}

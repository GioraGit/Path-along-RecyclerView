package co.example.mystockapp.features.stockTrend.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.example.mystockapp.features.stockTrend.domain.DayStock
import co.example.mystockapp.features.stockTrend.domain.IStocksRepository
import java.util.Date

internal class StocksRepository : IStocksRepository {

    private val mutableDayStocks = MutableLiveData<List<DayStock>>()

    init {
        mutableDayStocks.value = listOf(
            DayStock(Date(2019, 4, 1), 2.846),
            DayStock(Date(2019, 4, 2), 2.71),
            DayStock(Date(2019, 4, 3), 2.78),
            DayStock(Date(2019, 4, 4), 2.83),
            DayStock(Date(2019, 4, 5), 2.91),
            DayStock(Date(2019, 4, 6), 2.96),
            DayStock(Date(2019, 4, 7), 3.22),
            DayStock(Date(2019, 4, 8), 3.25),
            DayStock(Date(2019, 4, 9), 3.02),
            DayStock(Date(2019, 4, 10), 2.95),
            DayStock(Date(2019, 4, 11), 2.86),
            DayStock(Date(2019, 4, 12), 2.88),
            DayStock(Date(2019, 4, 13), 3.46),
            DayStock(Date(2019, 4, 14), 3.92),
            DayStock(Date(2019, 4, 15), 4.01342),
            DayStock(Date(2019, 4, 16), 4.11),
            DayStock(Date(2019, 4, 17), 4.11),
            DayStock(Date(2019, 4, 18), 3.95),
            DayStock(Date(2019, 4, 19), 3.83)
        )
    }

    override val dayStocks: LiveData<List<DayStock>> = mutableDayStocks

    companion object {
        val get: IStocksRepository = StocksRepository()
    }
}
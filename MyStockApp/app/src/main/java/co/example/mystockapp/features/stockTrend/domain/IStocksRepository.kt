package co.example.mystockapp.features.stockTrend.domain

import androidx.lifecycle.LiveData

internal interface IStocksRepository {

    val dayStocks: LiveData<List<DayStock>>
}



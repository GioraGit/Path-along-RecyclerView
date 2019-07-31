package co.example.mystockapp.features.stockTrend.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.example.mystockapp.R
import kotlinx.android.synthetic.main.activity_stocks.stocksActivity_stockTrend

class StocksActivity : AppCompatActivity() {

    private val viewModelFactory = StocksViewModelFactory.get

    private lateinit var viewModel: StocksViewModel
    private val stocksAdapter = StocksAdapter(this)
    private var graphDecorator: RecyclerView.ItemDecoration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stocks)
        initViewModel()

        setupStockTrend()
        observeDayStocks()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(StocksViewModel::class.java)
    }

    private fun setupStockTrend() {
        stocksActivity_stockTrend.apply {
            setHasFixedSize(true)
            adapter = stocksAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    private fun observeDayStocks() {
        viewModel.dayStockUIModels.observe(this, Observer { dayStockUIModels ->
            stocksAdapter.setData(dayStockUIModels)
            graphDecorator?.let { stocksActivity_stockTrend.removeItemDecoration(it) }
            graphDecorator = StockTrendGraphCurvyItemDecorator(dayStockUIModels, this)
            graphDecorator?.let { stocksActivity_stockTrend.addItemDecoration(it) }
        })
    }
}

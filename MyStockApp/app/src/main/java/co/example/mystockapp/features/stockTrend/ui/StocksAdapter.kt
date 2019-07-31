package co.example.mystockapp.features.stockTrend.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import co.example.mystockapp.R
import kotlinx.android.synthetic.main.item_day_stock.view.dayStockItem_change
import kotlinx.android.synthetic.main.item_day_stock.view.dayStockItem_day
import kotlinx.android.synthetic.main.item_day_stock.view.dayStockItem_value

internal class StocksAdapter(private val context: Context) : RecyclerView.Adapter<StocksAdapter.DayStockViewHolder>() {

    private val mutableDayStocksUIModels = mutableListOf<DayStockUIModel>()

    val dayStocksUIModles: List<DayStockUIModel>
        get() = mutableDayStocksUIModels

    fun setData(daysStocksUIModels: List<DayStockUIModel>) {
        this.mutableDayStocksUIModels.clear()
        this.mutableDayStocksUIModels.addAll(daysStocksUIModels)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayStockViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_day_stock, parent, false)

        return DayStockViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mutableDayStocksUIModels.size
    }

    override fun onBindViewHolder(holder: DayStockViewHolder, position: Int) {
        holder.bindView(position)
    }

    internal inner class DayStockViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {

        fun bindView(position: Int) {
            val dayStockUIModel = mutableDayStocksUIModels[position]
            view.dayStockItem_day.text = dayStockUIModel.day
            view.dayStockItem_value.text = "$${dayStockUIModel.roundedValue}"
            view.dayStockItem_change.text = dayStockUIModel.change.amount
            view.dayStockItem_change.setTextColor(ContextCompat.getColor(context, dayStockUIModel.change.color))
        }

    }

}

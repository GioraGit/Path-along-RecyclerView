package co.example.mystockapp.features.stockTrend.ui

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import co.example.mystockapp.R

private const val STROKE_WIDTH = 7f
private const val PATH_CORNER_RADIUS_IN_DP = 20
private const val CHILD_HEADER_OR_FOOTER_HEIGHT_IN_DP = 30

internal class StockTrendGraphLinearItemDecorator(dayStocksUIModels: List<DayStockUIModel>, context: Context) :
    RecyclerView.ItemDecoration() {

    private val drawPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = STROKE_WIDTH
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        color = ContextCompat.getColor(context, R.color.colorPrimaryDark)
        pathEffect = CornerPathEffect(PATH_CORNER_RADIUS_IN_DP.dpToPx)
    }

    private val normalizedDayStockValues = normalizeDayStockValues(dayStocksUIModels)

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)

        for (childIndex in 0 until parent.childCount) {
            val childView = parent.getChildAt(childIndex)
            val dataIndex = parent.getChildAdapterPosition(childView)
            val childViewHeight = childView.height

            canvas.drawLine(
                childView.left.toFloat(),
                calculateYValue(dataIndex, childViewHeight),
                childView.right.toFloat(),
                calculateYValue(getDataIndexOfNextChild(dataIndex), childViewHeight),
                drawPaint
            )
        }
    }

    private fun getDataIndexOfNextChild(currentChildDataIndex: Int): Int {
        val nextChildDataIndex = currentChildDataIndex + 1
        return if (nextChildDataIndex >= normalizedDayStockValues.size) {
            currentChildDataIndex
        } else {
            nextChildDataIndex
        }
    }

    private fun calculateYValue(dataIndex: Int, childViewHeight: Int): Float {
        val graphHeight = childViewHeight - (CHILD_HEADER_OR_FOOTER_HEIGHT_IN_DP * 2).dpToPx
        val graphStartHeightDelta = CHILD_HEADER_OR_FOOTER_HEIGHT_IN_DP.dpToPx

        return ((1 - normalizedDayStockValues[dataIndex]) * graphHeight + graphStartHeightDelta).toFloat()
    }

    private fun normalizeDayStockValues(dayStocksUIModles: List<DayStockUIModel>): List<Double> {
        val minDayStock = dayStocksUIModles.minBy { it.roundedValue }
        val maxDayStock = dayStocksUIModles.maxBy { it.roundedValue }

        if (minDayStock == null || maxDayStock == null) {
            return emptyList()
        }

        if (minDayStock.roundedValue >= maxDayStock.roundedValue) {
            return dayStocksUIModles.map { 0.5 }
        }

        val range = maxDayStock.roundedValue - minDayStock.roundedValue
        return dayStocksUIModles.map {
            val relativeValue = it.roundedValue - minDayStock.roundedValue
            return@map (relativeValue / range)
        }
    }
}

private val Int.dpToPx: Float
    get() = (this * Resources.getSystem().displayMetrics.density)
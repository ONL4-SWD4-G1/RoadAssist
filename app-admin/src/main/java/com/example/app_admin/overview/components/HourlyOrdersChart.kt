package com.example.app_admin.overview.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun HourlyOrdersChart(
    dataPoints: List<Float>,
    lineColor: Color = Color(0xFFF99806)
) {
    if (dataPoints.isEmpty()) return

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        val maxVal = dataPoints.maxOrNull()?.takeIf { it > 0 } ?: 1f
        val points = calculatePoints(dataPoints, maxVal)

        drawBackgroundGrid()
        drawAreaShadow(points, lineColor)
        drawChartLine(points, lineColor)
        drawDataPoints(points, lineColor)
    }
}

private fun DrawScope.calculatePoints(
    dataPoints: List<Float>,
    maxVal: Float
): List<Offset> {
    val spacing = size.width / (dataPoints.size - 1).coerceAtLeast(1)
    return dataPoints.mapIndexed { index, value ->
        val x = index * spacing
        val y = size.height - (value / maxVal * size.height)
        Offset(x, y)
    }
}

private fun DrawScope.drawBackgroundGrid(gridLines: Int = 4) {
    for (i in 0..gridLines) {
        val y = size.height - (i * (size.height / gridLines))
        drawLine(
            color = Color.LightGray.copy(alpha = 0.3f),
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = 1.dp.toPx()
        )
    }
}

private fun DrawScope.drawAreaShadow(points: List<Offset>, lineColor: Color) {
    if (points.isEmpty()) return

    val fillPath = Path().apply {
        moveTo(points.first().x, size.height)
        points.forEach { lineTo(it.x, it.y) }
        lineTo(points.last().x, size.height)
        close()
    }

    drawPath(
        path = fillPath,
        brush = Brush.verticalGradient(
            colors = listOf(
                lineColor.copy(alpha = 0.3f),
                Color.Transparent
            )
        )
    )
}

private fun DrawScope.drawChartLine(points: List<Offset>, lineColor: Color) {
    if (points.size < 2) return

    val strokePath = Path().apply {
        points.forEachIndexed { i, pt ->
            if (i == 0) moveTo(pt.x, pt.y) else lineTo(pt.x, pt.y)
        }
    }

    drawPath(
        path = strokePath,
        color = lineColor,
        style = Stroke(
            width = 3.dp.toPx(),
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
    )
}

private fun DrawScope.drawDataPoints(points: List<Offset>, lineColor: Color) {
    points.forEach { center ->
        drawCircle(color = Color.White, radius = 4.dp.toPx(), center = center)
        drawCircle(
            color = lineColor,
            radius = 4.dp.toPx(),
            center = center,
            style = Stroke(2.dp.toPx())
        )
    }
}
package com.otimeum.laetibeat.ui.theme

import android.graphics.Bitmap
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette

/**
 * 从 Bitmap 中提取颜色并生成 Material You 风格的 ColorScheme
 */
object DynamicThemeHelper {

    fun generateColorSchemeFromBitmap(
        bitmap: Bitmap,
        isDark: Boolean
    ): ColorScheme {
        val palette = Palette.from(bitmap).generate()
        
        // 提取主色调
        val dominantColor = palette.getDominantColor(0xFF6200EE.toInt())
        val vibrantColor = palette.getVibrantColor(0xFF6200EE.toInt())
        
        // 简单的颜色处理，实际项目中可以使用更复杂的算法
        val primaryColor = if (isDark) vibrantColor else dominantColor
        
        return if (isDark) {
            darkColorScheme(
                primary = androidx.compose.ui.graphics.Color(primaryColor),
                secondary = androidx.compose.ui.graphics.Color(palette.getLightVibrantColor(0xFFBB86FC.toInt())),
                tertiary = androidx.compose.ui.graphics.Color(palette.getLightMutedColor(0xFF03DAC6.toInt()))
            )
        } else {
            lightColorScheme(
                primary = androidx.compose.ui.graphics.Color(primaryColor),
                secondary = androidx.compose.ui.graphics.Color(palette.getMutedColor(0xFF6200EE.toInt())),
                tertiary = androidx.compose.ui.graphics.Color(palette.getDarkVibrantColor(0xFF3700B3.toInt()))
            )
        }
    }
}

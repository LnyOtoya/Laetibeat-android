package com.otimeum.laetibeat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.otimeum.laetibeat.data.model.Album
import com.otimeum.laetibeat.data.model.Artist

class LibraryViewModel : ViewModel() {

    enum class FilterType {
        MIXED,    // 混合显示
        ALBUMS,   // 只显示专辑
        ARTISTS   // 只显示艺人
    }

    var selectedFilter by mutableStateOf(FilterType.MIXED)
        private set

    // 模拟专辑数据
    val albums = listOf(
        Album("1", "范特西", "周杰伦", 2001, songCount = 10),
        Album("2", "叶惠美", "周杰伦", 2003, songCount = 11),
        Album("3", "七里香", "周杰伦", 2004, songCount = 10),
        Album("4", "Cross Road", "Bon Jovi", 1994, songCount = 14),
        Album("5", "Hotel California", "Eagles", 1976, songCount = 9),
        Album("6", "Thriller", "Michael Jackson", 1982, songCount = 9),
        Album("7", "Abbey Road", "The Beatles", 1969, songCount = 17),
        Album("8", "Dark Side of the Moon", "Pink Floyd", 1973, songCount = 10)
    )

    // 模拟艺人数据
    val artists = listOf(
        Artist("1", "周杰伦", albumCount = 15),
        Artist("2", "陈奕迅", albumCount = 20),
        Artist("3", "林俊杰", albumCount = 13),
        Artist("4", "五月天", albumCount = 10),
        Artist("5", "The Beatles", albumCount = 13),
        Artist("6", "Michael Jackson", albumCount = 10),
        Artist("7", "Queen", albumCount = 15),
        Artist("8", "Pink Floyd", albumCount = 15)
    )

    fun setFilter(filter: FilterType) {
        selectedFilter = filter
    }
}

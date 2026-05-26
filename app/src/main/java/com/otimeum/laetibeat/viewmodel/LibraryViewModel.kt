package com.otimeum.laetibeat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.otimeum.laetibeat.data.model.Album
import com.otimeum.laetibeat.data.model.Artist
import com.otimeum.laetibeat.data.model.Song

class LibraryViewModel : ViewModel() {

    enum class FilterType {
        MIXED,    // 混合显示
        ALBUMS,   // 只显示专辑
        ARTISTS,  // 只显示艺人
        SONGS     // 只显示歌曲
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

    // 模拟歌曲数据
    val songs = listOf(
        Song("s1", "晴天", "周杰伦", "叶惠美", 269),
        Song("s2", "以父之名", "周杰伦", "叶惠美", 299),
        Song("s3", "夜曲", "周杰伦", "十一月的萧邦", 244),
        Song("s4", "七里香", "周杰伦", "七里香", 299),
        Song("s5", "浮夸", "陈奕迅", "U87", 269),
        Song("s6", "K歌之王", "陈奕迅", "反正是我", 299),
        Song("s7", "江南", "林俊杰", "第二天堂", 289),
        Song("s8", "曹操", "林俊杰", "曹操", 269),
        Song("s9", "温柔", "五月天", "爱情万岁", 269),
        Song("s10", "倔强", "五月天", "神的孩子都在跳舞", 279),
        Song("s11", "Hotel California", "Eagles", "Hotel California", 391),
        Song("s12", "Thriller", "Michael Jackson", "Thriller", 358)
    )

    fun setFilter(filter: FilterType) {
        selectedFilter = filter
    }
}

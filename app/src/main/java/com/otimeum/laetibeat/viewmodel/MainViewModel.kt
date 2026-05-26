package com.otimeum.laetibeat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.otimeum.laetibeat.data.model.LibraryViewType
import com.otimeum.laetibeat.data.model.Playlist
import com.otimeum.laetibeat.data.model.PlaylistViewMode
import com.otimeum.laetibeat.data.model.Song
import com.otimeum.laetibeat.data.model.UserPreferences

class MainViewModel : ViewModel() {

    var userPreferences by mutableStateOf(UserPreferences())
        private set

    // 模拟数据
    val playlists = listOf(
        // ≤20首: PILL
        Playlist(
            id = "1",
            name = "心情舒畅时刻",
            description = "收集那些听了后心情很舒畅的曲子，适合放松时听",
            songs = listOf(
                Song("101", "晴天", "周杰伦", userNote = "下雨天听很有感觉"),
                Song("102", "起风了", "买辣椒也用券", userNote = "每次听都想起夏天"),
                Song("103", "平凡之路", "朴树", userNote = "人生感悟满满"),
                Song("104", "成都", "赵雷")
            )
        ),
        Playlist(
            id = "2",
            name = "深夜emo专用",
            description = "夜深人静时的情绪宣泄，每一首都戳心",
            songs = listOf(
                Song("201", "演员", "薛之谦", userNote = "太扎心了"),
                Song("202", "消愁", "毛不易", userNote = "一杯敬自由，一杯敬死亡"),
                Song("203", "南山南", "马頔"),
                Song("204", "董小姐", "宋冬野", userNote = "民谣经典"),
                Song("205", "斑马斑马", "宋冬野")
            )
        ),
        Playlist(
            id = "3",
            name = "运动健身必备",
            description = "节奏感强，让人充满动力！",
            songs = listOf(
                Song("301", "Uptown Funk", "Bruno Mars", userNote = "节奏超棒"),
                Song("302", "Shape of You", "Ed Sheeran"),
                Song("303", "Believer", "Imagine Dragons", userNote = "燃爆了"),
                Song("304", "Stronger", "Kelly Clarkson")
            )
        ),
        Playlist(
            id = "4",
            name = "工作专注BGM",
            description = "帮助集中注意力，提高工作效率的背景音乐",
            songs = listOf(
                Song("401", "Weightless", "Marconi Union", userNote = "最放松的音乐"),
                Song("402", "Experience", "Ludovico Einaudi"),
                Song("403", "Nuvole Bianche", "Ludovico Einaudi", userNote = "钢琴曲经典"),
                Song("404", "Comptine d'un autre été", "Yann Tiersen"),
                Song("405", "River Flows in You", "Yiruma"),
                Song("406", "Kiss The Rain", "Yiruma")
            )
        ),
        Playlist(
            id = "5",
            name = "怀旧金曲",
            description = "那些年我们一起听过的歌，满满的回忆",
            songs = listOf(
                Song("501", "海阔天空", "Beyond", userNote = "永远的经典"),
                Song("502", "朋友", "周华健"),
                Song("503", "吻别", "张学友", userNote = "90年代回忆"),
                Song("504", "红日", "李克勤"),
                Song("505", "千千阙歌", "陈慧娴")
            )
        ),
        Playlist(
            id = "6",
            name = "咖啡时光",
            description = "悠闲的下午，一杯咖啡，一首好歌",
            songs = listOf(
                Song("601", "Fly Me to the Moon", "Frank Sinatra", userNote = "爵士经典"),
                Song("602", "What a Wonderful World", "Louis Armstrong"),
                Song("603", "La Vie En Rose", "Édith Piaf"),
                Song("604", "Moon River", "Audrey Hepburn")
            )
        ),
        // >20且≤30首: CIRCLE
        Playlist(
            id = "7",
            name = "华语流行精选",
            description = "华语乐坛经典流行歌曲合集",
            songs = listOf(
                Song("701", "稻香", "周杰伦"),
                Song("702", "青花瓷", "周杰伦"),
                Song("703", "七里香", "周杰伦"),
                Song("704", "夜曲", "周杰伦"),
                Song("705", "简单爱", "周杰伦"),
                Song("706", "告白气球", "周杰伦"),
                Song("707", " Mojito", "周杰伦"),
                Song("708", "等你下课", "周杰伦"),
                Song("709", "说好不哭", "周杰伦"),
                Song("710", "不该", "周杰伦"),
                Song("711", "以父之名", "周杰伦"),
                Song("712", "东风破", "周杰伦"),
                Song("713", "发如雪", "周杰伦"),
                Song("714", "听妈妈的话", "周杰伦"),
                Song("715", "安静", "周杰伦"),
                Song("716", "龙卷风", "周杰伦"),
                Song("717", "双截棍", "周杰伦"),
                Song("718", "霍元甲", "周杰伦"),
                Song("719", "本草纲目", "周杰伦"),
                Song("720", "浪漫手机", "周杰伦"),
                Song("721", "退后", "周杰伦"),
                Song("722", "枫", "周杰伦"),
                Song("723", "彩虹", "周杰伦"),
                Song("724", "白色风车", "周杰伦")
            )
        ),
        Playlist(
            id = "8",
            name = "欧美热播单曲",
            description = "近年来欧美乐坛最受欢迎的歌曲",
            songs = listOf(
                Song("801", "Blinding Lights", "The Weeknd"),
                Song("802", "Shape of You", "Ed Sheeran"),
                Song("803", "Dance Monkey", "Tones and I"),
                Song("804", "Someone Like You", "Adele"),
                Song("805", "Hello", "Adele"),
                Song("806", "Rolling in the Deep", "Adele"),
                Song("807", "Uptown Funk", "Bruno Mars"),
                Song("808", "Thinking Out Loud", "Ed Sheeran"),
                Song("809", "Photograph", "Ed Sheeran"),
                Song("810", "Perfect", "Ed Sheeran"),
                Song("811", "Bad Guy", "Billie Eilish"),
                Song("812", "Lovely", "Billie Eilish"),
                Song("813", "Old Town Road", "Lil Nas X"),
                Song("814", "Sunflower", "Post Malone"),
                Song("815", "Circles", "Post Malone"),
                Song("816", "Señorita", "Shawn Mendes"),
                Song("817", "Stitches", "Shawn Mendes"),
                Song("818", "Treat You Better", "Shawn Mendes"),
                Song("819", "Havana", "Camila Cabello"),
                Song("820", "Never Be The Same", "Camila Cabello"),
                Song("821", "Shallow", "Lady Gaga"),
                Song("822", "Million Reasons", "Lady Gaga"),
                Song("823", "Perfect Illusion", "Lady Gaga"),
                Song("824", "Anybody", "Burnaboy"),
                Song("825", "Last Last", "Burnaboy")
            )
        ),
        // >30且≤40首: ARCH
        Playlist(
            id = "9",
            name = "经典摇滚合集",
            description = "摇滚乐黄金时代的经典之作",
            songs = listOf(
                Song("901", "Bohemian Rhapsody", "Queen"),
                Song("902", "Stairway to Heaven", "Led Zeppelin"),
                Song("903", "Smoke on the Water", "Deep Purple"),
                Song("904", "Hotel California", "Eagles"),
                Song("905", "Hotel California", "Eagles", userNote = "经典"),
                Song("906", "Born to Run", "Bruce Springsteen"),
                Song("907", "Thunder Road", "Bruce Springsteen"),
                Song("908", "Born in the USA", "Bruce Springsteen"),
                Song("909", "Dancing in the Dark", "Bruce Springsteen"),
                Song("910", "The River", "Bruce Springsteen"),
                Song("911", "Back in Black", "AC/DC"),
                Song("912", "Highway to Hell", "AC/DC"),
                Song("913", "Thunderstruck", "AC/DC"),
                Song("914", "You Shook Me All Night Long", "AC/DC"),
                Song("915", "Whole Lotta Rosie", "AC/DC"),
                Song("916", "Sweet Child O' Mine", "Guns N' Roses"),
                Song("917", "November Rain", "Guns N' Roses"),
                Song("918", "Paradise City", "Guns N' Roses"),
                Song("919", "Welcome to the Jungle", "Guns N' Roses"),
                Song("920", "Nothing Else Matters", "Metallica"),
                Song("921", "Enter Sandman", "Metallica"),
                Song("922", "Master of Puppets", "Metallica"),
                Song("923", "Fade to Black", "Metallica"),
                Song("924", "One", "Metallica"),
                Song("925", "Seek & Destroy", "Metallica"),
                Song("926", "Creeping Death", "Metallica"),
                Song("927", "For Whom the Bell Tolls", "Metallica"),
                Song("928", "Battery", "Metallica"),
                Song("929", "Fight Fire with Fire", "Metallica"),
                Song("930", "Ride the Lightning", "Metallica"),
                Song("931", "Disposable Heroes", "Metallica"),
                Song("932", "Leper Messiah", "Metallica"),
                Song("933", "Dyers Eve", "Metallica")
            )
        ),
        // >40首: SQUARE
        Playlist(
            id = "10",
            name = "古典音乐精选",
            description = "穿越时空的古典音乐瑰宝",
            songs = listOf(
                Song("1001", "Symphony No. 5", "Beethoven"),
                Song("1002", "Symphony No. 9", "Beethoven"),
                Song("1003", "Moonlight Sonata", "Beethoven"),
                Song("1004", "Für Elise", "Beethoven"),
                Song("1005", "Ode to Joy", "Beethoven"),
                Song("1006", "Piano Concerto No. 21", "Mozart"),
                Song("1007", "The Marriage of Figaro", "Mozart"),
                Song("1008", "Eine kleine Nachtmusik", "Mozart"),
                Song("1009", "Requiem", "Mozart"),
                Song("1010", "The Four Seasons", "Vivaldi"),
                Song("1011", "Spring", "Vivaldi"),
                Song("1012", "Summer", "Vivaldi"),
                Song("1013", "Autumn", "Vivaldi"),
                Song("1014", "Winter", "Vivaldi"),
                Song("1015", "The Nutcracker", "Tchaikovsky"),
                Song("1016", "Swan Lake", "Tchaikovsky"),
                Song("1017", "Sleeping Beauty", "Tchaikovsky"),
                Song("1018", "1812 Overture", "Tchaikovsky"),
                Song("1019", "Romeo and Juliet", "Tchaikovsky"),
                Song("1020", "Dance of the Swans", "Tchaikovsky"),
                Song("1021", "Clair de Lune", "Debussy"),
                Song("1022", "La Mer", "Debussy"),
                Song("1023", "Arabesque", "Debussy"),
                Song("1024", "Nocturnes", "Debussy"),
                Song("1025", "Images", "Debussy"),
                Song("1026", "Prelude to the Afternoon of a Faun", "Debussy"),
                Song("1027", "The Planets", "Holst"),
                Song("1028", "Mars", "Holst"),
                Song("1029", "Venus", "Holst"),
                Song("1030", "Mercury", "Holst"),
                Song("1031", "Jupiter", "Holst"),
                Song("1032", "Saturn", "Holst"),
                Song("1033", "Uranus", "Holst"),
                Song("1034", "Neptune", "Holst"),
                Song("1035", "Also Sprach Zarathustra", "Strauss"),
                Song("1036", "Don Juan", "Strauss"),
                Song("1037", "Till Eulenspiegel", "Strauss"),
                Song("1038", "The Blue Danube", "Strauss"),
                Song("1039", "Radetzky March", "Strauss"),
                Song("1040", "Hungarian Dances", "Brahms"),
                Song("1041", "Lullaby", "Brahms"),
                Song("1042", "Symphony No. 4", "Brahms"),
                Song("1043", "Academic Festival Overture", "Brahms"),
                Song("1044", "Tragic Overture", "Brahms"),
                Song("1045", "Carmen", "Bizet"),
                Song("1046", "L'Arlésienne", "Bizet"),
                Song("1047", "Peer Gynt", "Grieg"),
                Song("1048", "In the Hall of the Mountain King", "Grieg"),
                Song("1049", "Morning Mood", "Grieg"),
                Song("1050", "The Beautiful Blue Danube", "Strauss II")
            )
        )
    )

    fun toggleLibraryView(viewType: LibraryViewType) {
        val current = userPreferences.libraryViews.toMutableSet()
        if (current.contains(viewType)) {
            current.remove(viewType)
        } else {
            current.add(viewType)
        }
        userPreferences = userPreferences.copy(libraryViews = current)
    }

    fun toggleStatsVisibility() {
        userPreferences = userPreferences.copy(showStats = !userPreferences.showStats)
    }

    fun setPlaylistViewMode(mode: PlaylistViewMode) {
        userPreferences = userPreferences.copy(playlistViewMode = mode)
    }

    fun setPlaylistGridColumns(columns: Int) {
        if (columns in 2..3) {
            userPreferences = userPreferences.copy(playlistGridColumns = columns)
        }
    }
}

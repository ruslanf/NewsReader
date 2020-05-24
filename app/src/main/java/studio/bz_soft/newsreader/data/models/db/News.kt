package studio.bz_soft.newsreader.data.models.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
class News(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "newsId") val newsId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String
)
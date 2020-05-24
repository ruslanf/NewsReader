package studio.bz_soft.newsreader.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import studio.bz_soft.newsreader.data.models.db.News

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: News)

    @Update
    suspend fun update(news: News)

    @Delete
    suspend fun delete(news: News)

    @Query("Select * from news")
    fun getAllFromNews(): LiveData<List<News>>

    @Query("Select * from news where newsId = :newsId")
    suspend fun getRecords(newsId: String): List<News>
}
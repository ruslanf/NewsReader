package studio.bz_soft.newsreader.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import studio.bz_soft.newsreader.data.db.converters.Converters
import studio.bz_soft.newsreader.data.models.db.News
import studio.bz_soft.newsreader.root.Constants.DB_NAME

@Database(entities = [News::class],
    version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RoomDB : RoomDatabase() {

    abstract fun newsDao() : NewsDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDataBase(context: Context): RoomDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: createDB(context).also { INSTANCE = it }
            }

        private fun createDB(context: Context) = Room
            .databaseBuilder(context.applicationContext, RoomDB::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}
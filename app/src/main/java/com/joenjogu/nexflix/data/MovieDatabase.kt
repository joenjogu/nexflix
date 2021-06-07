package com.joenjogu.nexflix.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.joenjogu.nexflix.models.Movie

@Database(entities = [Movie::class], version = 6, exportSchema = false)
@TypeConverters(Converter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao
}

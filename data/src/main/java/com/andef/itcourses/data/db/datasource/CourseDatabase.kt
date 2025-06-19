package com.andef.itcourses.data.db.datasource

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andef.itcourses.data.db.dbmodel.CourseDbModel

@Database(entities = [CourseDbModel::class], version = 1, exportSchema = false)
abstract class CourseDatabase : RoomDatabase() {
    abstract val dao: CourseDao

    companion object {
        private const val DB_NAME = "course.db"
        private var instance: CourseDatabase? = null
        fun getInstance(application: Application): CourseDatabase {
            if (instance != null) return instance!!
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        application,
                        CourseDatabase::class.java,
                        DB_NAME
                    ).build()
                }
                return instance!!
            }
        }
    }
}
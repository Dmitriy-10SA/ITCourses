package com.andef.itcourses.data.db.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.andef.itcourses.data.db.dbmodel.CourseDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Insert(onConflict = IGNORE)
    suspend fun addFavoriteCourse(courseDbModel: CourseDbModel)

    @Query("DELETE FROM course WHERE id = :courseId")
    suspend fun removeFavoriteCourse(courseId: Int)

    @Query("SELECT * FROM course")
    fun favoriteCourses(): Flow<List<CourseDbModel>>
}
package com.example.riya.finalassignment.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.riya.finalassignment.entity.Comicss

@Dao
interface ComicssDAO {

    @Insert
    suspend fun registerProduct(product: Comicss)

    @Query("select * from Comicss")
    suspend fun getAllComics(): List<Comicss>
}


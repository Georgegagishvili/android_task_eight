package com.example.taskeight.dao

import androidx.room.*
import com.example.taskeight.models.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM GAMES")
    fun getAll(): List<Game>

    @Query("SELECT * FROM GAMES WHERE id == (:id)")
    fun getById(id: Int): Game

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: Game)

    @Query("DELETE FROM GAMES")
    fun deleteEverything()

    @Delete
    fun deleteItemById(game: Game)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGame(game: Game)

}
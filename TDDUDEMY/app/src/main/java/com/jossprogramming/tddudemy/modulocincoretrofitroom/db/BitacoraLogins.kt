package com.jossprogramming.tddudemy.modulocincoretrofitroom.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity(tableName = "bitacora_logins")
data class BitacoraLogins(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email:String,
    val password:String
)

@Dao
interface BitacoraLoginsDao {
    @Insert
    suspend fun insert(login: BitacoraLogins)

    @Query("SELECT * FROM bitacora_logins ORDER BY id DESC LIMIT 1")
    suspend fun getLastLogin(): BitacoraLogins?
}

@Database(entities = [BitacoraLogins::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bitacoraLoginsDao(): BitacoraLoginsDao
}
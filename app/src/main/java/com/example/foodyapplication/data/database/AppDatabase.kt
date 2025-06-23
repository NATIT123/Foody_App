package com.example.foodyapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodyapplication.data.database.entities.UserEntity


@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
}
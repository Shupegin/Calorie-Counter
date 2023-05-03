package com.example.caloriecounter.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.caloriecounter.dialog.FoodModel

@Database(entities = [FoodModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object{
        val migration_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE eating ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL")
            }

        }
        private var db : AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()
        fun getInstance(context : Context): AppDatabase{
            synchronized(LOCK){
                db?.let { return  it}
                val instance = Room.databaseBuilder(context,
                    AppDatabase::class.java,
                    DB_NAME)
                    .addMigrations(migration_1_2)
                    .build()
                db = instance
                return instance
            }
        }
    }
    abstract fun foodsInfoDao() : FoodsInfoDao
}
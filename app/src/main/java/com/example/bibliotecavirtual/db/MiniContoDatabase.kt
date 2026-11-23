package com.example.bibliotecavirtual.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bibliotecavirtual.dao.MiniContoDAO
import com.example.bibliotecavirtual.model.MiniConto

@Database(entities = [MiniConto::class], version = 1)
abstract class MiniContoDatabase : RoomDatabase(){

    abstract fun miniContoDao(): MiniContoDAO

    companion object{

        @Volatile
        private var INSTANCE: MiniContoDatabase? =  null

        fun getInstance(context: Context): MiniContoDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MiniContoDatabase::class.java,
                    "mini_conto_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}
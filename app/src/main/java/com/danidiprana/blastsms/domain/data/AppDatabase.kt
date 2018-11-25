package com.danidiprana.blastsms.domain.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import com.danidiprana.blastsms.domain.entity.CustomerDao
import com.danidiprana.blastsms.domain.entity.CustomerEntity

@Database(entities = [CustomerEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase(){

  abstract fun customerDao(): CustomerDao

  companion object {
    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
      override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE customer( "
            + "id LONG primary key, "
            + "name TEXT, "
            + "gender TEXT, "
            + "phone TEXT, "
            + ")")
      }
    }

  }
}
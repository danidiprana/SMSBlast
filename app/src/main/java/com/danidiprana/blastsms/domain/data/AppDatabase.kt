package com.danidiprana.blastsms.domain.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.danidiprana.blastsms.domain.entity.CustomerDao
import com.danidiprana.blastsms.domain.entity.CustomerEntity

@Database(entities = [CustomerEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase(){

  abstract fun customerDao(): CustomerDao

}
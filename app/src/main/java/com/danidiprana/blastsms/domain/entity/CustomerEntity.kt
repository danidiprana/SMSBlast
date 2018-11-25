package com.danidiprana.blastsms.domain.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "customer")
data class CustomerEntity(
  @PrimaryKey(autoGenerate = true) var id: Long = 0L,
  @ColumnInfo(name = "name") var name: String = "",
  @ColumnInfo(name = "gender") var gender: String = "",
  @ColumnInfo(name = "phone") var phone: String = ""
)
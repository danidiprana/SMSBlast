package com.danidiprana.blastsms.domain.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "customer")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "gender") val gender: String = "",
    @ColumnInfo(name = "phone") val phone: String = ""
)
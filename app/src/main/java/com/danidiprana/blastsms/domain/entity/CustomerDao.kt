package com.danidiprana.blastsms.domain.entity

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface CustomerDao {

  @Query("SELECT * FROM customer")
  fun getAllCustomer(): Single<List<CustomerEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertCustomer(value: CustomerEntity)
}
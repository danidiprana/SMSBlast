package com.danidiprana.blastsms.presentation.customer

import com.danidiprana.blastsms.domain.entity.CustomerEntity

interface CustomerContract {
  interface View {
    fun showListCustomer(listCustomer: ArrayList<CustomerEntity>)
  }

  interface Presenter {
    fun attachView(view: View)
    fun detachView()
    fun getAllListCustomer()
  }
}
package com.danidiprana.blastsms.presentation.listCustomer

import com.danidiprana.blastsms.domain.entity.CustomerViewObject

interface ListCustomerContract{
  interface View{
    fun showListEmployee(listCustomerViewObject: List<CustomerViewObject>)
  }

  interface Presenter{
    fun attachView(view: View)
    fun detachView()
    fun loadAll()
  }
}
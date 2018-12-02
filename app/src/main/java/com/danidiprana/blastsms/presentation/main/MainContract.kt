package com.danidiprana.blastsms.presentation.main

import com.danidiprana.blastsms.domain.entity.CustomerViewObject

interface MainContract{
  interface View {
    fun showToastMessage(listCustomer: String)
  }

  interface Presenter {
    fun attachView(view: View)
    fun detachView()
    fun syncCustomerData()
  }

  interface CustomerClickListener {
    fun onCustomerSelected(customerViewObject: CustomerViewObject, position: Int)
  }
}
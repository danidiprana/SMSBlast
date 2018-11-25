package com.danidiprana.blastsms.presentation.customer

interface CustomerContract {
  interface View {
    fun showToastMessage(listCustomer: String)
  }

  interface Presenter {
    fun attachView(view: View)
    fun detachView()
    fun syncCustomerData()
  }
}
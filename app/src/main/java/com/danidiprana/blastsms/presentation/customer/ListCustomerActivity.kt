package com.danidiprana.blastsms.presentation.customer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.danidiprana.blastsms.R
import com.danidiprana.blastsms.R.layout
import com.danidiprana.blastsms.domain.entity.CustomerEntity
import com.danidiprana.blastsms.presentation.customer.CustomerContract.Presenter
import com.danidiprana.blastsms.presentation.customer.CustomerContract.View
import dagger.android.AndroidInjection
import javax.inject.Inject

class ListCustomerActivity : AppCompatActivity(), View {

  @Inject
  lateinit var presenter: Presenter

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    setContentView(layout.activity_list_customer)

    presenter.attachView(this)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_list_customer, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when(item.itemId){
      R.id.sync_customer -> {
        presenter.getAllListCustomer()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun showListCustomer(listCustomer: ArrayList<CustomerEntity>) {
  }
}

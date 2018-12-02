package com.danidiprana.blastsms.presentation.listCustomer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.danidiprana.blastsms.R
import com.danidiprana.blastsms.domain.entity.CustomerViewObject
import com.danidiprana.blastsms.presentation.listCustomer.ListCustomerContract.Presenter
import com.danidiprana.blastsms.presentation.listCustomer.ListCustomerContract.View
import com.danidiprana.blastsms.presentation.main.MainActivity
import com.danidiprana.blastsms.presentation.main.MainContract.CustomerClickListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_list_customer.rv_customer
import javax.inject.Inject

class ListCustomerActivity : AppCompatActivity(), View, CustomerClickListener {

  @Inject
  lateinit var presenter: Presenter

  lateinit var adapter: ListCustomerAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_list_customer)

    adapter = ListCustomerAdapter(this)
    initRecyclerView()

    presenter.attachView(this)
    presenter.loadAll()
  }

  override fun showListEmployee(listCustomerViewObject: List<CustomerViewObject>) {
    adapter.listCustomer = listCustomerViewObject
  }

  override fun onCustomerSelected(customerViewObject: CustomerViewObject, position: Int) {
    val isSelected = adapter.listCustomer[position].isSelected

    adapter.listCustomer[position].isSelected = !isSelected
    adapter.notifyItemChanged(position)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_list_customer, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when(item.itemId){
      R.id.menu_ok -> {
        val listCustomerSelected = adapter.listCustomer.filter { it.isSelected }
        val intent = Intent(this, MainActivity::class.java).apply {
          putExtra("total_customer", listCustomerSelected.size)
        }
        setResult(1212, intent)
        finish()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  private fun initRecyclerView() {
    rv_customer.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    rv_customer.layoutManager = LinearLayoutManager(this)
    rv_customer.adapter = adapter
  }
}
package com.danidiprana.blastsms.presentation.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.danidiprana.blastsms.R
import com.danidiprana.blastsms.R.layout
import com.danidiprana.blastsms.presentation.listCustomer.ListCustomerActivity
import com.danidiprana.blastsms.presentation.main.MainContract.Presenter
import com.danidiprana.blastsms.presentation.main.MainContract.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.btn_select_customer
import kotlinx.android.synthetic.main.activity_main.tv_count_customer
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View {

  @Inject
  lateinit var presenter: Presenter

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    setContentView(layout.activity_main)

    presenter.attachView(this)

    btn_select_customer.setOnClickListener {
      val intent = Intent(this, ListCustomerActivity::class.java)
      startActivityForResult(intent, 1212)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when(item.itemId){
      R.id.sync_customer -> {
        presenter.syncCustomerData()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun showToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
  }

  override fun onActivityResult(
    requestCode: Int,
    resultCode: Int,
    data: Intent
  ) {
    Log.e("ketai", "Activity result")
    if (requestCode == 1212 ){
      val totalCustomer = data.getLongExtra("total_customer", 0L)
      tv_count_customer.text = totalCustomer.toString()
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.detachView()
  }
}

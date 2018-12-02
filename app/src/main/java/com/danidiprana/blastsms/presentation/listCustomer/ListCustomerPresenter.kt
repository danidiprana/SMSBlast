package com.danidiprana.blastsms.presentation.listCustomer

import android.util.Log
import com.danidiprana.blastsms.domain.entity.CustomerViewObject
import com.danidiprana.blastsms.domain.usecase.GetAllCustomerFromDBUseCase
import com.danidiprana.blastsms.presentation.listCustomer.ListCustomerContract.Presenter
import com.danidiprana.blastsms.presentation.listCustomer.ListCustomerContract.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListCustomerPresenter(
  val getAllCustomerFromDBUseCase: GetAllCustomerFromDBUseCase
): Presenter {

  private val disposable = CompositeDisposable()
  private lateinit var view: View

  override fun attachView(view: View) {
    this.view = view
  }

  override fun loadAll() {
    getAllCustomerFromDBUseCase.execute()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ listCustomerEntity ->

          val listCustomerViewObject = listCustomerEntity.map { entity ->
            val prefix = if (entity.gender.equals("L", true)) "Pak" else "Bu"

            val firstName = entity.name.split(" ")[0]

            val customerName = prefix + " " + firstName

            CustomerViewObject(customerName, entity.phone)
          }

          view.showListEmployee(listCustomerViewObject)
        }, {
          Log.e("ketai", "Error when load customer from DB")
        })
        .let { disposable.add(it) }
  }

  override fun detachView() {
    disposable.clear()
  }

}
package com.danidiprana.blastsms.presentation.customer

import android.util.Log
import com.danidiprana.blastsms.domain.usecase.GetAllCustomerUseCase
import com.danidiprana.blastsms.presentation.customer.CustomerContract.Presenter
import com.danidiprana.blastsms.presentation.customer.CustomerContract.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListCustomerPresenter(
    private val getAllCustomerUseCase: GetAllCustomerUseCase
): Presenter {

  private val disposable = CompositeDisposable()
  private lateinit var view: View

  override fun attachView(view: View) {
    this.view = view
  }

  override fun syncCustomerData() {
    getAllCustomerUseCase.execute()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          view.showToastMessage("Synchronize to Google Sheet Success ")
        }, {
          view.showToastMessage("Synchronize to Google Sheet Failed ")
        })
        .let { disposable.add(it) }
  }

  override fun detachView() {
    disposable.clear()
  }
}
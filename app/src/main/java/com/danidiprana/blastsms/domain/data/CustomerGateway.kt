package com.danidiprana.blastsms.domain.data

import com.danidiprana.blastsms.domain.entity.CustomerEntity
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

interface CustomerGateway {
  fun getAllCustomerFromSpreadSheet(): Observable<JSONObject>
}

class CustomerGatewayImpl : CustomerGateway {

  override fun getAllCustomerFromSpreadSheet(): Observable<JSONObject> {
    return Observable.fromCallable {
      val client = OkHttpClient()
      val request = Request.Builder()
          .url(MAIN_URL)
          .build()

      val response = client.newCall(request).execute()
      val responseBody = response.body().string()

      JSONObject(responseBody)
    }.subscribeOn(Schedulers.io())
  }

  companion object {

    private const val SHEET_ID = "14s7VGOxUGGR5jEH7eYJ1NIUQhirXBK1yvb53PMfHbTY"
    const val KEY_SHEET = "customer"
    const val MAIN_URL = "https://script.google.com/macros/s/" +
        "AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=$SHEET_ID&sheet=$KEY_SHEET"
    const val KEY_NAME = "Name"
    const val KEY_GENDER = "Gender"
    const val KEY_PHONE = "Phone"
  }
}

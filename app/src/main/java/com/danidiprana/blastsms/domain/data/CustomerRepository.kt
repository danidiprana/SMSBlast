package com.danidiprana.blastsms.domain.data

import com.danidiprana.blastsms.domain.data.CustomerGatewayImpl.Companion.KEY_GENDER
import com.danidiprana.blastsms.domain.data.CustomerGatewayImpl.Companion.KEY_NAME
import com.danidiprana.blastsms.domain.data.CustomerGatewayImpl.Companion.KEY_PHONE
import com.danidiprana.blastsms.domain.entity.CustomerDao
import com.danidiprana.blastsms.domain.entity.CustomerEntity
import io.reactivex.Single

interface CustomerRepository {
  fun getAllCustomer(): Single<ArrayList<CustomerEntity>>
}

class CustomerRepositoryImpl(
    private val customerGateway: CustomerGateway,
    private val customerDao: CustomerDao
) : CustomerRepository {

  override fun getAllCustomer(): Single<ArrayList<CustomerEntity>> {
    return customerGateway.getAllCustomerFromSpreadSheet()
        .map { json ->
          val jsonArray = json.getJSONArray(CustomerGatewayImpl.KEY_SHEET)
          val listCustomerEntity = arrayListOf<CustomerEntity>()

          for (i in 0 until jsonArray.length()){
            val jsonObject = jsonArray.getJSONObject(i)

            val name = jsonObject.getString(KEY_NAME)
            val gender = jsonObject.getString(KEY_GENDER)
            val phone = jsonObject.getString(KEY_PHONE)
            val customerEntity = CustomerEntity(name, gender, phone)

            customerDao.insertCustomer(customerEntity)
            listCustomerEntity.add(customerEntity)
          }

          listCustomerEntity
        }
        .firstOrError()
  }
}
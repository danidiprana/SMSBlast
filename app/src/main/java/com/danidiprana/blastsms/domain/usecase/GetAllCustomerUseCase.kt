package com.danidiprana.blastsms.domain.usecase

import com.danidiprana.blastsms.domain.data.CustomerRepository
import com.danidiprana.blastsms.domain.entity.CustomerEntity
import io.reactivex.Single

interface GetAllCustomerUseCase {
  fun execute(): Single<ArrayList<CustomerEntity>>
}

class GetAllCustomerUseCaseImpl(
    private val repository: CustomerRepository
): GetAllCustomerUseCase {

  override fun execute(): Single<ArrayList<CustomerEntity>> {
    return repository.getAllCustomer()
  }
}
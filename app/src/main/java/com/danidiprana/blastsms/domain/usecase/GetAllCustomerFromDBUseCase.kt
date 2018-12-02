package com.danidiprana.blastsms.domain.usecase

import com.danidiprana.blastsms.domain.data.CustomerRepository
import com.danidiprana.blastsms.domain.entity.CustomerEntity
import io.reactivex.Single

interface GetAllCustomerFromDBUseCase{
  fun execute(): Single<List<CustomerEntity>>
}

class GetAllCustomerFromDBUseCaseImpl(
  val repository: CustomerRepository
): GetAllCustomerFromDBUseCase {

  override fun execute(): Single<List<CustomerEntity>> {
    return repository.getAllCustomerFromDB()
  }
}
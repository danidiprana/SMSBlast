package com.danidiprana.blastsms.di

import com.danidiprana.blastsms.domain.data.CustomerGateway
import com.danidiprana.blastsms.domain.data.CustomerGatewayImpl
import com.danidiprana.blastsms.domain.data.CustomerRepository
import com.danidiprana.blastsms.domain.data.CustomerRepositoryImpl
import com.danidiprana.blastsms.domain.usecase.GetAllCustomerUseCase
import com.danidiprana.blastsms.domain.usecase.GetAllCustomerUseCaseImpl
import com.danidiprana.blastsms.presentation.customer.CustomerContract.Presenter
import com.danidiprana.blastsms.presentation.customer.ListCustomerPresenter
import dagger.Module
import dagger.Provides

@Module
class AppModule {

  @Provides
  fun provideCustomerGateway(): CustomerGateway = CustomerGatewayImpl()

  @Provides
  fun provideCustomerRepository(customerGateway: CustomerGateway): CustomerRepository {
    return CustomerRepositoryImpl(customerGateway)
  }

  @Provides
  fun provideGetAllCustomerUseCase(repository: CustomerRepository): GetAllCustomerUseCase {
    return GetAllCustomerUseCaseImpl(repository)
  }

  @Provides
  fun provideListCustomerPresenter(getAllCustomerUseCase: GetAllCustomerUseCase): Presenter {
    return ListCustomerPresenter(getAllCustomerUseCase)
  }
}
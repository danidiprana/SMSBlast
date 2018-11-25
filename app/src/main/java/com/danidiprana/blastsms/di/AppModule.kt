package com.danidiprana.blastsms.di

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase.JournalMode.TRUNCATE
import android.content.Context
import com.danidiprana.blastsms.domain.data.AppDatabase
import com.danidiprana.blastsms.domain.data.CustomerGateway
import com.danidiprana.blastsms.domain.data.CustomerGatewayImpl
import com.danidiprana.blastsms.domain.data.CustomerRepository
import com.danidiprana.blastsms.domain.data.CustomerRepositoryImpl
import com.danidiprana.blastsms.domain.entity.CustomerDao
import com.danidiprana.blastsms.domain.usecase.GetAllCustomerUseCase
import com.danidiprana.blastsms.domain.usecase.GetAllCustomerUseCaseImpl
import com.danidiprana.blastsms.presentation.customer.CustomerContract.Presenter
import com.danidiprana.blastsms.presentation.customer.ListCustomerPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

  @Provides
  @Singleton
  fun provideContext(): Context = context

  @Provides
  fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "sms-blast-db")
        .setJournalMode(TRUNCATE).addMigrations(AppDatabase.MIGRATION_1_2).build()
  }

  @Provides
  fun provideCustomerDao(database: AppDatabase): CustomerDao = database.customerDao()

  @Provides
  fun provideCustomerGateway(): CustomerGateway = CustomerGatewayImpl()

  @Provides
  fun provideCustomerRepository(customerGateway: CustomerGateway, customerDao: CustomerDao)
      : CustomerRepository {
    return CustomerRepositoryImpl(customerGateway, customerDao)
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
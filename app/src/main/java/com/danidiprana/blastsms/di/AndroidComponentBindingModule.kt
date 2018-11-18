package com.danidiprana.blastsms.di

import com.danidiprana.blastsms.presentation.customer.ListCustomerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidComponentBindingModule {

  @ContributesAndroidInjector
  internal abstract fun bindListCustomerActivity(): ListCustomerActivity
}
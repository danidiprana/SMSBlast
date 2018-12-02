package com.danidiprana.blastsms.di

import com.danidiprana.blastsms.presentation.listCustomer.ListCustomerActivity
import com.danidiprana.blastsms.presentation.main.MainActivity
import com.danidiprana.blastsms.presentation.main.MainActivity_MembersInjector
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidComponentBindingModule {

  @ContributesAndroidInjector
  abstract fun bindMainActivity(): MainActivity

  @ContributesAndroidInjector
  abstract fun bindListCustomerActivity(): ListCustomerActivity
}
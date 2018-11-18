package com.danidiprana.blastsms.di

import com.danidiprana.blastsms.SmsBlast
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AndroidComponentBindingModule::class, AppModule::class])
@Singleton
interface AppComponent {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: SmsBlast): Builder

    fun build(): AppComponent

    fun setApplicationContext(appModule: AppModule): Builder
  }

  fun inject(application: SmsBlast)
}
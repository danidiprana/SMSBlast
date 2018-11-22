package com.danidiprana.blastsms

import android.app.Activity
import android.app.Application
import com.danidiprana.blastsms.di.AppModule
import com.danidiprana.blastsms.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class SmsBlast: Application(), HasActivityInjector {

  @Inject
  lateinit var activityInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()

    DaggerAppComponent.builder().application(this)
        .setApplicationContext(AppModule(this))
        .build()
        .inject(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> {
    return activityInjector
  }
}
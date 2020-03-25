package jp.co.kotlintemplate.presentation

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import jp.co.kotlintemplate.framework.dagger.AppComponent
import jp.co.kotlintemplate.framework.dagger.DaggerAppComponent
import javax.inject.Inject

class App : Application(), HasAndroidInjector {
    companion object {
        lateinit var instance: App private set
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    lateinit var appComponent: AppComponent private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeDaggerComponent()
    }

    private fun initializeDaggerComponent() {
        this.appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()
        this.appComponent.inject(this)
    }

    override fun androidInjector() = this.androidInjector
}
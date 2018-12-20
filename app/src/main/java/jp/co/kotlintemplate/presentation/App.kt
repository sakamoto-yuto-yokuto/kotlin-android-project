package jp.co.kotlintemplate.presentation

import dagger.android.support.DaggerApplication
import jp.co.kotlintemplate.framework.dagger.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector()
            = DaggerAppComponent.builder()
            .create(this)
}
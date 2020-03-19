package jp.co.kotlintemplate.framework.dagger

import dagger.Module
import jp.co.kotlintemplate.presentation.App
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: App) = application.applicationContext!!
}
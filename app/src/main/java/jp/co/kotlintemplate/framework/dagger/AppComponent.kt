package jp.co.kotlintemplate.framework.dagger

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import jp.co.kotlintemplate.presentation.App
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityModule::class,
    FrameworkModule::class,
    RepositoryModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    // application
    fun inject(application: App)
}
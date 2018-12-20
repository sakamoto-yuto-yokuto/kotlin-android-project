package jp.co.kotlintemplate.framework.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.co.kotlintemplate.presentation.view.activity.MainActivity
import jp.co.kotlintemplate.presentation.view.activity.SecondActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ SecondActivityModule::class ])
    abstract fun contributeSecondActivity(): SecondActivity
}
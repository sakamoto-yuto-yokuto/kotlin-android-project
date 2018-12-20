package jp.co.kotlintemplate.framework.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.co.kotlintemplate.presentation.view.fragment.GalleryFragment
import jp.co.kotlintemplate.presentation.view.fragment.ToolsFragment

@Module
abstract class SecondActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeGalleryFragment(): GalleryFragment

    @ContributesAndroidInjector
    abstract fun contributeToolsFragment(): ToolsFragment
}
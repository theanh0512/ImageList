package pham.honestbee.imagelist.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pham.honestbee.imagelist.ui.detail.DetailActivity
import pham.honestbee.imagelist.ui.imagelist.MainActivity

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun detailActivity(): DetailActivity
}
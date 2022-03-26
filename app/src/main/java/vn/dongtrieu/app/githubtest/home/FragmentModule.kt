package vn.dongtrieu.app.githubtest.home

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import vn.dongtrieu.app.githubtest.FragmentScope
import vn.dongtrieu.app.githubtest.fragments.HomeFragment
import vn.dongtrieu.app.githubtest.fragments.SearchFragment
import javax.inject.Singleton

@Module
interface FragmentModule {

    @ContributesAndroidInjector(
        modules = [
            Provision::class
        ]
    )
    @FragmentScope
    fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    @FragmentScope
    fun contributeSearchFragment(): SearchFragment

    @Module
    object Provision {

        @Provides
        @JvmStatic
        @FragmentScope
        fun homViewModel(fragment: HomeFragment): Lazy<HomeViewModel> {
            return lazy { ViewModelProvider(fragment).get(HomeViewModel::class.java) }
        }
    }
}
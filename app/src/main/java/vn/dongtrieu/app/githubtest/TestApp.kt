package vn.dongtrieu.app.githubtest

import android.view.View
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

open class TestApp: DaggerApplication() {

    @Inject
    lateinit var viewInjector: DispatchingAndroidInjector<View>

    private lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.factory().create(this)
        return appComponent
    }
}
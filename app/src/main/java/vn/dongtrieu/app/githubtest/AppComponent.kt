package vn.dongtrieu.app.githubtest

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import vn.dongtrieu.app.githubtest.home.FragmentModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    FragmentModule::class,
  ]
)
interface AppComponent: AndroidInjector<DaggerApplication> {
  @Component.Factory
  interface Factory {
    fun create(
      @BindsInstance application: Application,
    ): AppComponent
  }
}

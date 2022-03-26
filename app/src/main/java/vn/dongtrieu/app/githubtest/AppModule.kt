package vn.dongtrieu.app.githubtest

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import vn.dongtrieu.app.githubtest.domain.repository.GithubRepository
import vn.dongtrieu.app.githubtest.domain.repository.GithubRepositoryImp
import vn.dongtrieu.app.githubtest.service.GithubAPI
import vn.dongtrieu.app.githubtest.service.ServiceGenerator
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return ServiceGenerator().createService()
    }

    @Singleton
    @Provides
    fun providesPricesAPI(retrofit: Retrofit): GithubAPI {
        return retrofit.create(GithubAPI::class.java)
    }

    @Provides
    fun providesRepository(api: GithubAPI): GithubRepository {
        return GithubRepositoryImp(api)
    }

    @Singleton
    @Provides
    fun providesContext(application: Application): Context {
        return application
    }
}
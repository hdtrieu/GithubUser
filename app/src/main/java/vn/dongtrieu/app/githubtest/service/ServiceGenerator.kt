package vn.dongtrieu.app.githubtest.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import vn.dongtrieu.app.githubtest.BuildConfig


class ServiceGenerator {

    fun createService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
            .client(getClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        val headerInterceptor = RequestInterceptor()
        val changeAccessToken = AccessTokenInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(changeAccessToken)
            .build()
    }
}
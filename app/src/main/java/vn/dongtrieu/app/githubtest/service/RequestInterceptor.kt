package vn.dongtrieu.app.githubtest.service

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import vn.dongtrieu.app.githubtest.BuildConfig

class RequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        val newRequest: Request = request.newBuilder()
            .addHeader("Authorization", BuildConfig.TOKEN)
            .addHeader("Accept", "application/vnd.github.v3+json")
            .build()
        return chain.proceed(newRequest)
    }
}
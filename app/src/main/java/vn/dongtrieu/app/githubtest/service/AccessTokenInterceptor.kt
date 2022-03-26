package vn.dongtrieu.app.githubtest.service

import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val checkResponse = chain.proceed(originalRequest)
        if (checkResponse.code == 401) {
            synchronized(this) {
                val requestBuilder = originalRequest
                    .newBuilder()
                    .method(originalRequest.method, originalRequest.body)
                    .removeHeader("Authorization")
                    .build()
                checkResponse.close()
                return chain.proceed(requestBuilder)
            }
        } else {
            return checkResponse
        }
    }
}
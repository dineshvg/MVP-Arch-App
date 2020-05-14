package de.animebook.core.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class RetrofitClient : Interceptor {

    //todo: logger through DI
    private val logger: okhttp3.logging.HttpLoggingInterceptor
        get() {
            val logging = okhttp3.logging.HttpLoggingInterceptor()
            logging.level = okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS
            logging.level = okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
            logging.level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
            return logging
        }

    //todo: okHttpClient through DI
    private val okHttpClient: OkHttpClient.Builder
        get() = OkHttpClient().newBuilder()

    fun createRetrofitInstance(): Retrofit {

        okHttpClient.addInterceptor(this)
        okHttpClient.addInterceptor(logger)
        return retrofitBuilder.apply {
            baseUrl(BASE_URL)
            addConverterFactory(
                MoshiConverterFactory
                    .create(
                        //todo : inject with DI
                        Moshi.Builder()
                            .add(KotlinJsonAdapterFactory())
                            .build()
                    )
            )
            addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            client(
                okHttpClient
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()
            )
        }.build()
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder: Request.Builder
        builder = request.newBuilder()
        val nextRequest = builder.build()
        return chain.proceed(nextRequest)
    }

    companion object {
        private val retrofitBuilder = Retrofit.Builder()//todo: through DI
    }
}
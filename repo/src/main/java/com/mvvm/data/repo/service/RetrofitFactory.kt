package com.mvvm.data.repo.service

import com.mvvm.data.repo.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi


class RetrofitFactory {

    private val interceptor = Interceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url()
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", AppConstants.API_KEY)
            .build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    val client = OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .addInterceptor(loggingInterceptor)
        .build()


    inline fun <reified T> retrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))//MoshiConverterFactory.create())
        .build()


    /*var customDateAdapter: Any = object : Any() {
        internal val dateFormat: DateFormat

        init {
            dateFormat = SimpleDateFormat(AppConstants.YYYYMMDD)
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"))
        }

        @ToJson
        @Synchronized
        internal fun dateToJson(d: Date): String {
            return dateFormat.format(d)
        }

        @FromJson
        @Synchronized
        @Throws(ParseException::class)
        internal fun dateToJson(s: String): Date {
            return dateFormat.parse(s)
        }
    }*/

    var moshi = Moshi.Builder()
        .add(CustomDateAdapter())
        .build()


}
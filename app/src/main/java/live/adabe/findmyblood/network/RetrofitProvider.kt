package live.adabe.findmyblood.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitProvider {

    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttp = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .writeTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .addInterceptor(Interceptor { chain -> chain.proceed(chain.request().newBuilder().build()) })
        .addInterceptor(logger)
        .build()

    private val instance =
        Retrofit.Builder()
            .baseUrl("https://find-my-blood.herokuapp.com/hospital/")
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create()).build()

    val authApi: AuthApi = instance.create(AuthApi::class.java)
    val bloodApi: BloodApi = instance.create(BloodApi::class.java)
    val requestApi: RequestApi = instance.create(RequestApi::class.java)
}
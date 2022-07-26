package saityan.misc.basedjack.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("top/.json")
    fun getTopPosts(@Query("limit") limit: String): Call<ServerResponseData>
}

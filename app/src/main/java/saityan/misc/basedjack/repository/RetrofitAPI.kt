package saityan.misc.basedjack.repository

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("top")
    fun getTopPosts(limit: String): Call<ServerResponseData>
}

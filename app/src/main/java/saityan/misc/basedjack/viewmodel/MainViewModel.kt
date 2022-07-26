package saityan.misc.basedjack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import saityan.misc.basedjack.repository.RetrofitImplementation
import saityan.misc.basedjack.repository.ServerResponseData

class MainViewModel : ViewModel() {

    private val dataObservable: MutableLiveData<PostsData> = MutableLiveData()

    private val retrofit: RetrofitImplementation = RetrofitImplementation()

    fun getPostsLiveData(): LiveData<PostsData> = dataObservable

    fun sendServerRequest() {
        dataObservable.value = PostsData.Loading
        val limit = "100"
        retrofit.getTopPosts(limit, callback)
    }

    private val callback = object : Callback<ServerResponseData> {
        override fun onResponse(
            call: Call<ServerResponseData>,
            response: Response<ServerResponseData>
        ) {
            if (response.isSuccessful && response.body() != null)
                dataObservable.value = PostsData.Success(response.body()!!)
            else {
                val message = response.message()
                if (message.isNullOrEmpty())
                    dataObservable.value = PostsData.Error(Throwable("Unknown error"))
                else
                    dataObservable.value = PostsData.Error(Throwable(message))
            }
        }

        override fun onFailure(call: Call<ServerResponseData>, t: Throwable) {
            dataObservable.value = PostsData.Error(t)
        }
    }
}

package saityan.misc.basedjack.viewmodel

import saityan.misc.basedjack.repository.ServerResponseData

sealed class PostsData {
    data class Success (val serverResponseData: ServerResponseData) : PostsData()
    data class Error(val error: Throwable) : PostsData()
    object Loading : PostsData()
}

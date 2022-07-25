package saityan.misc.basedjack.repository

import com.google.gson.annotations.SerializedName

data class ServerResponseData (
    @field:SerializedName("data") val data: Data?,
)

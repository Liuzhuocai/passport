package elf.m.passportsimple.ui.http.data.request

import com.google.gson.annotations.SerializedName

/**

 * author：liuzuo on 19-5-17 09:40

 *

 */
data class BaseHttpRequestData(
    @SerializedName("x-api-key")
    var x_api_key: String?,

    @SerializedName("Content-Type")
    var content_type: String?,

    @SerializedName("Authorization")
    var authorization: String?,

    @SerializedName("Accept-Language")
    var accept_language: String?,

    @SerializedName("Request")
    var body: Any?
) {
    constructor() : this(null,null,null,null,null)
}
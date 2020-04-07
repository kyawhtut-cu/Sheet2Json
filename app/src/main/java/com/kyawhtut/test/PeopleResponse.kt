package com.kyawhtut.test

import com.google.gson.annotations.SerializedName

/**
 * @author kyawhtut
 * @date 06/04/2020
 */
data class PeopleResponse(
    val data: List<People>
)

data class People(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("address")
    val address: String,
    @SerializedName("remark")
    val remark: String
)

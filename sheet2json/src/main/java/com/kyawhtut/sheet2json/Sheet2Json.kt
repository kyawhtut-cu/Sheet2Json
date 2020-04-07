package com.kyawhtut.sheet2json

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import io.reactivex.Single
import org.json.JSONObject
import java.util.*

/**
 * @author kyawhtut
 * @date 06/04/2020
 */
class Sheet2Json {

    companion object {
        private var instance: Sheet2Json? = null

        fun init() {
            if (instance != null) return
            instance = Sheet2Json()
        }

        fun get(
            sheetID: String,
            sheetNumber: Int = 1,
            query: String = ""
        ): Single<String> {
            return Single.create {
                if (instance == null) it.onError(RuntimeException("Sheet2Json.init() in application level."))
                if (sheetID.isEmpty()) it.onError(RuntimeException("sheetID must not empty."))
                instance?.let { sheet ->
                    sheet.fetchData(
                        sheetID,
                        sheetNumber,
                        query,
                        success = { data ->
                            if (data.isNotEmpty()) {
                                it.onSuccess(data)
                            } else {
                                it.onError(RuntimeException("Response is empty"))
                            }
                        },
                        error = { error ->
                            it.onError(error)
                        }
                    )
                }
            }
        }
    }

    var sheetID: String = ""
    var sheetNumber: Int = 1

    fun fetchData(
        sheetID: String,
        sheetNumber: Int,
        query: String,
        success: (String) -> Unit,
        error: (Exception) -> Unit
    ) {
        this.sheetID = sheetID
        this.sheetNumber = sheetNumber
        AndroidNetworking.get("https://spreadsheets.google.com/feeds/list/{sheetID}/{sheetNumber}/public/values")
            .addPathParameter("sheetID", sheetID)
            .addPathParameter("sheetNumber", sheetNumber.toString())
            .addQueryParameter("alt", "json")
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    if (response.has("feed") && response.getJSONObject("feed").has("entry")) {
                        val entryArray = response.getJSONObject("feed").getJSONArray("entry")
                        val resp = mutableListOf<Map<String, Any>>()
                        for (row in 0 until entryArray.length()) {
                            val entry = entryArray.getJSONObject(row)
                            val keys = entry.keys()
                            var queried = false
                            val rowEntry = mutableMapOf<String, Any>()
                            keys.forEach {
                                val gsxCheck = it.indexOf("gsx$")
                                if (gsxCheck > -1) {
                                    val key = it
                                    val name = key.substring(4)
                                    val content = entry.getJSONObject(key)
                                    val value = content.getString("\$t")
                                    if (query.toLowerCase(Locale.ENGLISH) == value.toLowerCase(
                                            Locale.ENGLISH
                                        )
                                    ) {
                                        queried = true
                                    }
                                    rowEntry[name] = value
                                }
                            }
                            if (queried || query.isEmpty()) {
                                resp.add(rowEntry)
                            }
                        }
                        success(Gson().toJson(resp))
                    } else success("")
                }

                override fun onError(anError: ANError) {
                    error(anError)
                }
            })
    }
}
package me.fonix232.peopleapp.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson


@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class StringToLong

internal class StringToLongAdapter {
    @FromJson
    @StringToLong
    fun fromJson(value: String): Long = value.toLong()

    @ToJson
    fun toJson(@StringToLong value: Int): String = value.toString()
}
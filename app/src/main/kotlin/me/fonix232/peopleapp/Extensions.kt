package me.fonix232.peopleapp

import org.koin.standalone.KoinComponent
import retrofit2.Retrofit

inline fun <reified T: Any> KoinComponent.retrofit() = lazy {
    getKoin().get<Retrofit>().create(T::class.java)
}
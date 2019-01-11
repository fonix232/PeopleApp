package me.fonix232.peopleapp.api

import kotlinx.coroutines.Deferred
import me.fonix232.peopleapp.model.People
import retrofit2.Response

interface IPeopleClient {
    fun getUsers(): Deferred<Response<People>>
}
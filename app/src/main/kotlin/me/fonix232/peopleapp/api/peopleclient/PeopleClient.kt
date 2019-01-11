package me.fonix232.peopleapp.api.peopleclient

import kotlinx.coroutines.Deferred
import me.fonix232.peopleapp.api.IPeopleClient
import me.fonix232.peopleapp.model.People
import me.fonix232.peopleapp.retrofit
import org.koin.standalone.KoinComponent
import retrofit2.Response
import retrofit2.http.GET

class PeopleClient : IPeopleClient, KoinComponent {
    private val api: PeopleApi by retrofit()

    override fun getUsers(): Deferred<Response<People>> = api.getUsers()

    interface PeopleApi {
        @GET("/users")
        fun getUsers(): Deferred<Response<People>>
    }
}
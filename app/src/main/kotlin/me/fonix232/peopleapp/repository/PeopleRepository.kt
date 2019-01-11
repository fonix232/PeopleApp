package me.fonix232.peopleapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import me.fonix232.peopleapp.api.IPeopleClient
import me.fonix232.peopleapp.db.AppDatabase
import me.fonix232.peopleapp.model.People
import me.fonix232.peopleapp.model.Person
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import kotlin.coroutines.CoroutineContext

class PeopleRepository : KoinComponent {
    private val client: IPeopleClient by inject()
    private val db: AppDatabase by inject()

    val isUpdating: LiveData<Boolean> = MutableLiveData()

    suspend fun clearData() = coroutineScope {
        setUpdating(true)
        withContext(Dispatchers.IO) {
            db.people().deleteAll()
        }
        setUpdating(false)
    }

    suspend fun downloadData() = coroutineScope {
        setUpdating(true)
        withContext(Dispatchers.IO) {
            val response = client.getUsers().await()
            if (response.isSuccessful) {
                saveData(response.body())
            } else {

                setUpdating(false)
            }
        }
    }

    suspend fun saveData(data: People?) = coroutineScope {
        withContext(Dispatchers.IO) {
            data?.forEach { db.people().upsert(it) }
        }
        setUpdating(false)
    }

    fun getPerson(id: Int) = db.people().get(id)

    fun getPeople(): LiveData<People> = db.people().getAll()

    private fun setUpdating(value: Boolean) = (isUpdating as MutableLiveData).postValue(value)
}
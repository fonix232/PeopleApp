package me.fonix232.peopleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.fonix232.common.viewmodel.BaseViewModel
import me.fonix232.peopleapp.model.Person
import me.fonix232.peopleapp.repository.PeopleRepository
import org.koin.standalone.inject

class PersonDetailViewModel : BaseViewModel() {
    private val repo: PeopleRepository by inject()
    private val id = MutableLiveData<Int>()
    val person: LiveData<Person> = MediatorLiveData()

    init {
        (person as MediatorLiveData).addSource(id) { id ->
            CoroutineScope(Dispatchers.IO).launch {
                val newPerson = repo.getPerson(id)
                CoroutineScope(Dispatchers.Main).launch {
                    person.postValue(newPerson)
                }
            }
        }
    }

    fun init(id: Int) {
        this.id.postValue(id)
    }
}
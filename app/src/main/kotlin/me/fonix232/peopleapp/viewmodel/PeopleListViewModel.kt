package me.fonix232.peopleapp.viewmodel

import androidx.lifecycle.LiveData
import me.fonix232.common.viewmodel.BaseViewModel
import me.fonix232.peopleapp.model.People
import me.fonix232.peopleapp.repository.PeopleRepository
import org.koin.standalone.inject
import kotlinx.coroutines.*

class PeopleListViewModel: BaseViewModel() {
    private val repo: PeopleRepository by inject()
    val people: LiveData<People> = repo.getPeople()
    val isUpdating: LiveData<Boolean> = repo.isUpdating

    fun update() {
        CoroutineScope(Dispatchers.IO).launch {
            repo.clearData()
            repo.downloadData()
        }
    }
}
package me.fonix232.peopleapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import me.fonix232.common.db.UpsertDao
import me.fonix232.peopleapp.model.People
import me.fonix232.peopleapp.model.Person

@Dao
abstract class PeopleDao : UpsertDao<Person> {
    @Query("select * from people order by id asc")
    abstract fun getAll(): LiveData<People>

    @Query("select * from people where id is :id")
    abstract fun get(id: Int): Person

    @Query("delete from people")
    abstract fun deleteAll()
}
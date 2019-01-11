package me.fonix232.peopleapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import me.fonix232.peopleapp.db.dao.PeopleDao
import me.fonix232.peopleapp.model.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "people.db"
        val MIGRATIONS: List<Migration> = listOf()
    }

    abstract fun people(): PeopleDao

}
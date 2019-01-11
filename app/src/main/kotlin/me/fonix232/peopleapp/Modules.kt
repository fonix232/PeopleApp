package me.fonix232.peopleapp

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import me.fonix232.peopleapp.api.IPeopleClient
import me.fonix232.peopleapp.api.peopleclient.PeopleClient
import me.fonix232.peopleapp.db.AppDatabase
import me.fonix232.peopleapp.repository.PeopleRepository
import me.fonix232.peopleapp.viewmodel.MainViewModel
import me.fonix232.peopleapp.viewmodel.PeopleListViewModel
import me.fonix232.peopleapp.viewmodel.PersonDetailViewModel
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.single
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Keys {
    const val BASE_URL = "baseUrl"
}

val appModule = module {
    single<PeopleRepository>()

    viewModel<MainViewModel>()
    viewModel<PeopleListViewModel>()
    viewModel<PersonDetailViewModel>()
}

val dbModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(get<Context>(), AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .addMigrations(*AppDatabase.MIGRATIONS.toTypedArray())
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single<Moshi> { Moshi.Builder().build() }
    single<Converter.Factory> { MoshiConverterFactory.create(get<Moshi>()) }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<String>(Keys.BASE_URL))
            .addConverterFactory(get<Converter.Factory>())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}

// By replacing this module with another, the implementation and base URL of the client can be easily changed
val clientModule = module {
    single(Keys.BASE_URL) { "https://jsonplaceholder.typicode.com" }
    single<IPeopleClient> { PeopleClient() }
}
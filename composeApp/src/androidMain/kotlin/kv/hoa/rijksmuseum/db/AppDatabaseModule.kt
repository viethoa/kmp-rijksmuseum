package kv.hoa.rijksmuseum.db

import android.app.Activity
import hoa.kv.rijksmuseum.repository.db.DBFactory
import hoa.kv.rijksmuseum.repository.db.AppDatabase
import org.koin.dsl.module

val appDatabaseModule = module {
    single<AppDatabase> { DBFactory(get<Activity>().application).createRoomDatabase() }
}
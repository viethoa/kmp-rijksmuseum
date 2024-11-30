package kv.hoa.rijksmuseum.db

import hoa.kv.rijksmuseum.repository.db.DBFactory
import hoa.kv.rijksmuseum.repository.db.AppDatabase
import org.koin.dsl.module

val appDataBaseModule = module {
    single<AppDatabase> { DBFactory().createRoomDatabase() }
}
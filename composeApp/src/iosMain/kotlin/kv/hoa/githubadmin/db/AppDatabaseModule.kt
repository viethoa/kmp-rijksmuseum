package kv.hoa.githubadmin.db

import hoa.kv.githubadmin.repository.db.DBFactory
import hoa.kv.githubadmin.repository.db.AppDatabase
import org.koin.dsl.module

val appDataBaseModule = module {
    single<AppDatabase> { DBFactory().createRoomDatabase() }
}
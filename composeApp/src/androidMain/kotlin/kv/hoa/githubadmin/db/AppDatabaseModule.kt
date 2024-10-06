package kv.hoa.githubadmin.db

import android.app.Activity
import hoa.kv.githubadmin.repository.db.DBFactory
import hoa.kv.githubadmin.repository.db.AppDatabase
import org.koin.dsl.module

val appDatabaseModule = module {
    single<AppDatabase> { DBFactory(get<Activity>().application).createRoomDatabase() }
}
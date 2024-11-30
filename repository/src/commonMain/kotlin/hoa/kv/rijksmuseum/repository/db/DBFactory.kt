package hoa.kv.rijksmuseum.repository.db

import hoa.kv.rijksmuseum.repository.db.AppDatabase

expect class DBFactory {
    fun createRoomDatabase(): AppDatabase
}
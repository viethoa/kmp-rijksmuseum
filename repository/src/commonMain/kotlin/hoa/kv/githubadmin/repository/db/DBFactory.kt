package hoa.kv.githubadmin.repository.db

expect class DBFactory {
    fun createRoomDatabase(): AppDatabase
}
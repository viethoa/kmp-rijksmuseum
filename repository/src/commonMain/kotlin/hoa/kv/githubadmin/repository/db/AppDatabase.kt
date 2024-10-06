package hoa.kv.githubadmin.repository.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import hoa.kv.githubadmin.repository.model.UserEntity
import hoa.kv.githubadmin.repository.user.UserDao

internal const val DB_FILE_NAME = "fruits.db"

@Database(entities = [UserEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

// The Room compiler generates the actual implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
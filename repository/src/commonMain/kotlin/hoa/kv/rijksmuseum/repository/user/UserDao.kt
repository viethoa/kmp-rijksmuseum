package hoa.kv.rijksmuseum.repository.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hoa.kv.rijksmuseum.repository.model.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg users: UserEntity)

    @Query("SELECT * FROM userentity WHERE rank >= :since LIMIT :perPage")
    suspend fun getUsers(perPage: Int, since: Int): List<UserEntity>

    @Query("SELECT * FROM userentity WHERE id=:userId LIMIT 1")
    suspend fun getUser(userId: Int): UserEntity
}
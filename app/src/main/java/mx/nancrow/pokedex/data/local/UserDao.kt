package mx.nancrow.pokedex.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import mx.com.satoritech.festlovers.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)

    @Query(
        """
        SELECT * FROM user LIMIT 1
        """
    )
    fun getUser(): Flow<UserEntity>

    @Update
    fun updateUser(userEntity: UserEntity)

}
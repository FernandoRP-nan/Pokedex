package mx.nancrow.pokedex.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import mx.com.satoritech.festlovers.data.local.entity.RecoverAccountEntity

@Dao
interface RecoverAccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecoverAccount(recoverAccountEntity: RecoverAccountEntity)

    @Delete
    fun deleteRecoverAccount(recoverAccountEntity: RecoverAccountEntity)

    @Query(
        """
        SELECT * FROM recover_account LIMIT 1
        """
    )
    fun getRecoverAccount(): Flow<RecoverAccountEntity>
}
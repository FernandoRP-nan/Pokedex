package mx.nancrow.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.com.satoritech.festlovers.data.local.entity.RecoverAccountEntity
import mx.com.satoritech.festlovers.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class, RecoverAccountEntity::class],
    version = 2,
)
abstract class FestLoversDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun recoverAccountDao(): RecoverAccountDao

}
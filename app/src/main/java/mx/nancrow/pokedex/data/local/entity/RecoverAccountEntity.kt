package mx.com.satoritech.festlovers.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recover_account")
data class RecoverAccountEntity(
    val email: String,
    val password: String,
    @PrimaryKey val id: Int? = null
)

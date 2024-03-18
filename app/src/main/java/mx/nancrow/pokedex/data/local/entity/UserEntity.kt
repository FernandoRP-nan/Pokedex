package mx.com.satoritech.festlovers.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val lastName: String,
    val secondLastName: String,
    val email: String,
    val phone: String,
    val urlImage: String? = null,
)

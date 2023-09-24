package com.example.kleine.model

import android.os.Parcelable
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.parcelize.Parcelize

enum class PartnershipStatus {
    pending, approved, rejected, quit
}
@Parcelize
data class Partnership(
    var id: String = "",
    var userId: String,
    var instiName: String,
    var instiType: String,
    var location: String,
    var contactNum: String,
    var reason: String,
    var status: PartnershipStatus = PartnershipStatus.pending,
    var rejectReason: String = "",
    var documentation: String = "",
    var documentationName: String = ""
): Parcelable {
    constructor() : this("","", "", "", "", "", "", PartnershipStatus.pending, "", "", "")
    fun toEntity(): PartnershipEntity {
        return PartnershipEntity(
            id = this.id,
            instiName = this.instiName,
            instiType = this.instiType,
            location = this.location,
            contactNum = this.contactNum,
            reason = this.reason,
            documentation = this.documentation,
            documentationName = this.documentationName,
            userId = this.userId,
            status = this.status.toString(),
            documentationLocalPath = null // Initially, there is no local path
        )
    }
}

@Entity(tableName = "partnership")
data class PartnershipEntity(
    @PrimaryKey
    val id: String,
    val instiName: String,
    val instiType: String,
    val location: String,
    val contactNum: String,
    val reason: String,
    val documentation: String,
    val documentationName: String,
    var documentationLocalPath: String? = null, // Added this field to store local file path
    val userId: String,
    val status: String
)



@Dao
interface PartnershipDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(partnership: PartnershipEntity)

    @Query("SELECT * FROM partnership WHERE userId = :userId")
    suspend fun getPartnershipByUserId(userId: String): List<PartnershipEntity>

    @Query("UPDATE partnership SET documentationLocalPath = :filePath WHERE id = :id")
    suspend fun updateDocumentationLocalPath(id: String, filePath: String)
}


@Database(entities = [PartnershipEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun partnershipDao(): PartnershipDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE partnership ADD COLUMN documentationLocalPath TEXT")
    }

}



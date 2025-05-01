package vcmsa.projects.pocketchange_v3.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryId: Int,
    val amount: Double,
    val description: String,
    val imageUri: String?,         // Optional photo
    val date: String,              // Format: "yyyy-MM-dd"
    val startTime: String,         // Format: "HH:mm"
    val endTime: String? = null,   // Optional end time, can be null            // Format: "HH:mm"
)

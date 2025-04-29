package vcmsa.projects.pocketchange_v3.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budget_table")
data class Budget(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val minAmount: Double,
    val maxAmount: Double
)

package vcmsa.projects.pocketchange_v3.model


data class Expense(
    val id: Long,
    val title: String,
    val amount: Double,
    val date: String,
    val category: String
)

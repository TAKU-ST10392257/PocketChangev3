package vcmsa.projects.pocketchange_v3.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BudgetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudget(budget: Budget)

    @Query("SELECT * FROM budget_table LIMIT 1")
    suspend fun getLatestBudget(): Budget?

    @Query("SELECT * FROM budget_table LIMIT 1")
    fun getBudget(): LiveData<Budget?>

    @Query("DELETE FROM budget_table")
    suspend fun deleteBudget()

}

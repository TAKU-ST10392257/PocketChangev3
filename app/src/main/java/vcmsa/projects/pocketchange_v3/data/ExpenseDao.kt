package vcmsa.projects.pocketchange_v3.data

import androidx.lifecycle.LiveData
import androidx.room.*
import vcmsa.projects.pocketchange_v3.model.Expense

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("DELETE FROM expenses")
    suspend fun deleteAll()

    @Query("SELECT * FROM expenses ORDER BY startTime DESC")
    fun getAllExpenses(): LiveData<List<Expense>>
}

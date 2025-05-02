package vcmsa.projects.pocketchange_v3.data

import androidx.lifecycle.LiveData
import vcmsa.projects.pocketchange_v3.model.Expense
import vcmsa.projects.pocketchange_v3.data.Category

class ExpenseRepository(
    private val expenseDao: ExpenseDao,
    private val categoryDao: CategoryDao // ✅ Add this line
) {

    fun getAllExpenses(): LiveData<List<Expense>> {
        return expenseDao.getAllExpenses()
    }

    fun getAllCategories(): LiveData<List<Category>> { // ✅ Add this function
        return categoryDao.getAllCategories()
    }

    suspend fun insert(expense: Expense) {
        expenseDao.insert(expense)
    }

    suspend fun delete(expense: Expense) {
        expenseDao.delete(expense)
    }

    suspend fun update(expense: Expense) {
        expenseDao.update(expense)
    }
}

package vcmsa.projects.pocketchange_v3.data

import androidx.lifecycle.LiveData
import vcmsa.projects.pocketchange_v3.data.ExpenseDao
import vcmsa.projects.pocketchange_v3.model.Expense


class ExpenseRepository(private val expenseDao: ExpenseDao) {

    fun getAllExpenses(): LiveData<List<Expense>> {
        return expenseDao.getAllExpenses()
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


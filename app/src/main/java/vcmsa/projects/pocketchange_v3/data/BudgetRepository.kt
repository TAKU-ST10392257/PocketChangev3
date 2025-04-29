package vcmsa.projects.pocketchange_v3.data

import androidx.lifecycle.LiveData
import vcmsa.projects.pocketchange_v3.data.Budget

class BudgetRepository(private val budgetDao: BudgetDao) {

    suspend fun insert(budget: Budget) {
        budgetDao.insertBudget(budget)
    }

    fun getBudget(): LiveData<Budget?> {
        return budgetDao.getBudget()
    }

    suspend fun deleteBudget() {
        budgetDao.deleteBudget()
    }

}

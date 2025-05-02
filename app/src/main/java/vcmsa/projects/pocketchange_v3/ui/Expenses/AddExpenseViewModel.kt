package vcmsa.projects.pocketchange_v3.ui.Expenses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vcmsa.projects.pocketchange_v3.data.AppDatabase
import vcmsa.projects.pocketchange_v3.data.ExpenseRepository
import vcmsa.projects.pocketchange_v3.model.Expense

class AddExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ExpenseRepository

    init {
        val db = AppDatabase.getDatabase(application)
        val expenseDao = db.expenseDao()
        val categoryDao = db.categoryDao() // ✅ Include categoryDao
        repository = ExpenseRepository(expenseDao, categoryDao) // ✅ Pass both DAOs
    }

    fun addExpense(expense: Expense) = viewModelScope.launch {
        repository.insert(expense)
    }
}

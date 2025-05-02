package vcmsa.projects.pocketchange_v3.ui.Expenses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vcmsa.projects.pocketchange_v3.data.AppDatabase
import vcmsa.projects.pocketchange_v3.data.ExpenseRepository
import vcmsa.projects.pocketchange_v3.model.Expense
import vcmsa.projects.pocketchange_v3.data.Category

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ExpenseRepository
    val allExpenses: LiveData<List<Expense>>
    val allCategories: LiveData<List<Category>> // ✅ Add this line

    init {
        val db = AppDatabase.getDatabase(application)
        val expenseDao = db.expenseDao()
        val categoryDao = db.categoryDao() // ✅ Add this line
        repository = ExpenseRepository(expenseDao, categoryDao) // ✅ Update constructor
        allExpenses = repository.getAllExpenses()
        allCategories = repository.getAllCategories() // ✅ Add this line
    }

    fun insert(expense: Expense) = viewModelScope.launch {
        repository.insert(expense)
    }

    fun delete(expense: Expense) = viewModelScope.launch {
        repository.delete(expense)
    }

    fun update(expense: Expense) = viewModelScope.launch {
        repository.update(expense)
    }
}

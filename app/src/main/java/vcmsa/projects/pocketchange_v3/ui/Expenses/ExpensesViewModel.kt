package vcmsa.projects.pocketchange_v3.ui.Expenses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vcmsa.projects.pocketchange_v3.model.Expense

class ExpensesViewModel : ViewModel() {

    private val _expenses = MutableLiveData<List<Expense>>()
    val expenses: LiveData<List<Expense>> = _expenses

    init {
        loadDummyExpenses()
    }

    private fun loadDummyExpenses() {
        _expenses.value = listOf(
            Expense(id = 1, title = "Groceries", amount = 500.0, date = "2025-04-25", category = "Food"),
            Expense(id = 2, title = "Transport", amount = 200.0, date = "2025-04-26", category = "Travel"),
            Expense(id = 3, title = "Coffee", amount = 50.0, date = "2025-04-27", category = "Food"),
            Expense(id = 4, title = "Books", amount = 300.0, date = "2025-04-28", category = "Education")
        )
    }
}

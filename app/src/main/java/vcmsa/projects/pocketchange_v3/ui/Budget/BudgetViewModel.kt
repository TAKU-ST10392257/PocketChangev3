package vcmsa.projects.pocketchange_v3.ui.Budget

//==========================================================================//
// Daniel Gorin                 ST10438307                                  //
// Moegammad-Yaseen Salie       ST10257795                                  //
// Jason Daniel Isaacs          ST10039248                                  //
// Takudzwa Denis Murwira       ST10392257      (Group Leader)              //
//                                                                          //
// PROG7313 GROUP 2                                                         //
//==========================================================================//

//==========================================================================//
// References:
//             https://www.youtube.com/playlist?list=PLSrm9z4zp4mEPOfZNV9O-crOhoMa0G2-o
//             https://chatgpt.com/
//==========================================================================//

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import vcmsa.projects.pocketchange_v3.data.Budget
import vcmsa.projects.pocketchange_v3.data.AppDatabase
import vcmsa.projects.pocketchange_v3.data.BudgetRepository

class BudgetViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BudgetRepository
    val currentBudget: LiveData<Budget?>

    init {
        val budgetDao = AppDatabase.getDatabase(application).budgetDao()
        repository = BudgetRepository(budgetDao)
        currentBudget = repository.getBudget()
    }

    fun insertBudget(budget: Budget) = viewModelScope.launch {
        repository.insert(budget)  // ✅ This matches the repository method
    }

    fun deleteBudget() = viewModelScope.launch {
        repository.deleteBudget()
    }

    // Optional: clear or update budget logic can go here
}

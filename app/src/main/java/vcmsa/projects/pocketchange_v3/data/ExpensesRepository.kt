package vcmsa.projects.pocketchange_v3.data

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

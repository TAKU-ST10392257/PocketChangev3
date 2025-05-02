package vcmsa.projects.pocketchange_v3.ui.Category

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
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vcmsa.projects.pocketchange_v3.data.AppDatabase
import vcmsa.projects.pocketchange_v3.data.Category
import vcmsa.projects.pocketchange_v3.data.CategoryRepository

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CategoryRepository
    val allCategories: LiveData<List<Category>>

    init {
        val categoryDao = AppDatabase.getDatabase(application).categoryDao()
        repository = CategoryRepository(categoryDao)
        allCategories = repository.getAllCategories()
    }

    fun insert(category: Category) = viewModelScope.launch {
        repository.insert(category)
    }

    fun delete(category: Category) = viewModelScope.launch {
        repository.delete(category)
    }

    fun update(category: Category) = viewModelScope.launch {
        repository.update(category)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}

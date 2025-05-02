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

class CategoryRepository(private val categoryDao: CategoryDao) {

    fun getAllCategories(): LiveData<List<Category>> {
        return categoryDao.getAllCategories()
    }

    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }

    suspend fun update(category: Category) {
        categoryDao.update(category)
    }

    suspend fun delete(category: Category) {
        categoryDao.delete(category)
    }

    suspend fun deleteAll() {
        categoryDao.deleteAll()
    }
}

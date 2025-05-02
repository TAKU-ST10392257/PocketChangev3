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
import androidx.room.*

@Dao
interface BudgetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudget(budget: Budget)

    @Query("SELECT * FROM budget_table LIMIT 1")
    suspend fun getLatestBudget(): Budget?

    @Query("SELECT * FROM budget_table LIMIT 1")
    fun getBudget(): LiveData<Budget?>

    @Query("DELETE FROM budget_table")
    suspend fun deleteBudget()

}

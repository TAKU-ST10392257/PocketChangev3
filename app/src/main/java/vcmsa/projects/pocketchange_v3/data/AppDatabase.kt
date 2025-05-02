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
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import vcmsa.projects.pocketchange_v3.model.Expense

@Database(
    entities = [User::class, Expense::class, Category::class, Budget::class],
    version = 4, // <--- Increment this
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {



    abstract fun budgetDao(): BudgetDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pocketchange_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

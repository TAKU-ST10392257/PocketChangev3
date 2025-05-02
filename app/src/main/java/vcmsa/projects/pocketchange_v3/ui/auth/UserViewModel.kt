package vcmsa.projects.pocketchange_v3.ui.auth

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
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vcmsa.projects.pocketchange_v3.data.AppDatabase
import vcmsa.projects.pocketchange_v3.data.User
import vcmsa.projects.pocketchange_v3.repository.UserRepository

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getDatabase(application).userDao()
    private val repository = UserRepository(userDao)

    fun registerUser(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val existingUser = repository.getUserByUsername(username)
            if (existingUser != null) {
                onResult(false) // Username already taken
            } else {
                val newUser = User.create(username, password)
                repository.insertUser(newUser)
                onResult(true)
            }
        }
    }

    fun loginUser(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val hashedPassword = User.hashPassword(password)
            val user = repository.authenticate(username, hashedPassword)
            onResult(user != null)
        }
    }
}

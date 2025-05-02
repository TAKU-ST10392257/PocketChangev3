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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import vcmsa.projects.pocketchange_v3.R
import vcmsa.projects.pocketchange_v3.ui.auth.UserViewModel

class LoginFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnGoToRegister: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        etUsername = view.findViewById(R.id.etRegisterUsername)
        etPassword = view.findViewById(R.id.etRegisterPassword)
        btnLogin = view.findViewById(R.id.btnGoToLogin)
        btnGoToRegister = view.findViewById(R.id.btnRegister)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            userViewModel.loginUser(username, password) { success ->
                if (success) {
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                    // Navigate to home or dashboard
                } else {
                    Toast.makeText(requireContext(), "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnGoToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_categoryFragment)

        }
    }
}

package vcmsa.projects.pocketchange_v3.ui.Expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import vcmsa.projects.pocketchange_v3.R
import vcmsa.projects.pocketchange_v3.databinding.FragmentExpensesBinding

class ExpenseFragment : Fragment() {

    private var _binding: FragmentExpensesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExpensesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Set up the button click listener
        binding.btnAddExpense.setOnClickListener {
            // Navigate to the fragment where user can add an expense
            findNavController().navigate(R.id.action_navigation_expenses_to_addExpenseFragment)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

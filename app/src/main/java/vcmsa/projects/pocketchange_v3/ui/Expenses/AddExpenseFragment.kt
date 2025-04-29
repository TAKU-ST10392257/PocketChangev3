package vcmsa.projects.pocketchange_v3.ui.Expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import vcmsa.projects.pocketchange_v3.R
import vcmsa.projects.pocketchange_v3.data.Expense
import vcmsa.projects.pocketchange_v3.databinding.FragmentAddExpenseBinding

class AddExpenseFragment : Fragment() {

    private var _binding: FragmentAddExpenseBinding? = null
    private val binding get() = _binding!!

    // Access the shared ViewModel
    private val addExpenseViewModel: AddExpenseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddExpenseBinding.inflate(inflater, container, false)

        // Handle the save button click
        binding.btnSaveExpense.setOnClickListener {
            val amount = binding.etAmount.text.toString().toDoubleOrNull()
            val description = binding.etDescription.text.toString()
            val category = binding.etCategory.text.toString()

            // Check if all fields are filled
            if (amount != null && description.isNotEmpty() && category.isNotEmpty()) {
                val expense = Expense(
                    amount = amount,
                    description = description,
                    category = category,
                    timestamp = System.currentTimeMillis() // Use the current timestamp for the date
                )
                addExpenseViewModel.addExpense(expense)
                findNavController().navigateUp() // Go back to the previous fragment after adding
            } else {
                // Show error if fields are empty or invalid
                // For example, show a toast or display a Snackbar
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

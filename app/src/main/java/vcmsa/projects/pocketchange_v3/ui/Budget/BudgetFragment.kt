package vcmsa.projects.pocketchange_v3.ui.Budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import vcmsa.projects.pocketchange_v3.data.Budget
import vcmsa.projects.pocketchange_v3.databinding.FragmentBudgetBinding

class BudgetFragment : Fragment() {

    private var _binding: FragmentBudgetBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BudgetViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this)[BudgetViewModel::class.java]

        val inputLayout = binding.inputLayout
        val displayLayout = binding.displayLayout
        val tvBudgetValues = binding.tvBudgetValues

        // Save budget
        binding.btnSaveBudget.setOnClickListener {
            val min = binding.etMinAmount.text.toString().toDoubleOrNull()
            val max = binding.etMaxAmount.text.toString().toDoubleOrNull()

            if (min != null && max != null) {
                val budget = Budget(minAmount = min, maxAmount = max)
                viewModel.insertBudget(budget)
            }
        }

        // Edit button: show input fields again
        binding.btnEditBudget.setOnClickListener {
            inputLayout.visibility = View.VISIBLE
            displayLayout.visibility = View.GONE
        }

        // Delete button: delete and show input again
        binding.btnDeleteBudget.setOnClickListener {
            viewModel.deleteBudget()
            inputLayout.visibility = View.VISIBLE
            displayLayout.visibility = View.GONE
        }

        // Observe budget changes
        viewModel.currentBudget.observe(viewLifecycleOwner) { budget ->
            if (budget != null) {
                val remaining = budget.maxAmount - budget.minAmount
                tvBudgetValues.text = "Budget: R${budget.maxAmount}\nMin: R${budget.minAmount}\nRemaining: R$remaining"

                inputLayout.visibility = View.GONE
                displayLayout.visibility = View.VISIBLE
            } else {
                inputLayout.visibility = View.VISIBLE
                displayLayout.visibility = View.GONE
            }
        }

        return binding.root
    }

}

package vcmsa.projects.pocketchange_v3.ui.Budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import vcmsa.projects.pocketchange_v3.R
import vcmsa.projects.pocketchange_v3.data.Budget

class BudgetFragment : Fragment() {

    private lateinit var viewModel: BudgetViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_budget, container, false)

        val inputLayout = view.findViewById<LinearLayout>(R.id.inputLayout)
        val displayLayout = view.findViewById<LinearLayout>(R.id.displayLayout)
        val tvBudgetValues = view.findViewById<TextView>(R.id.tvBudgetValues)
        val etMinAmount = view.findViewById<TextInputEditText>(R.id.etMinAmount)
        val etMaxAmount = view.findViewById<TextInputEditText>(R.id.etMaxAmount)
        val btnSaveBudget = view.findViewById<Button>(R.id.btnSaveBudget)
        val btnEditBudget = view.findViewById<Button>(R.id.btnEditBudget)
        val btnDeleteBudget = view.findViewById<Button>(R.id.btnDeleteBudget)

        viewModel = ViewModelProvider(this)[BudgetViewModel::class.java]

        btnSaveBudget.setOnClickListener {
            val min = etMinAmount.text.toString().toDoubleOrNull()
            val max = etMaxAmount.text.toString().toDoubleOrNull()
            if (min != null && max != null) {
                val budget = Budget(minAmount = min, maxAmount = max)
                viewModel.insertBudget(budget)
            } else {
                Toast.makeText(context, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            }
        }

        btnEditBudget.setOnClickListener {
            inputLayout.visibility = View.VISIBLE
            displayLayout.visibility = View.GONE
        }

        btnDeleteBudget.setOnClickListener {
            viewModel.deleteBudget()
            inputLayout.visibility = View.VISIBLE
            displayLayout.visibility = View.GONE
        }

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

        return view
    }
}

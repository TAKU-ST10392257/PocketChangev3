package vcmsa.projects.pocketchange_v3.ui.Expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import vcmsa.projects.pocketchange_v3.R

class ExpenseFragment : Fragment() {

    private val expenseViewModel: ExpenseViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ExpensesAdapter
    private lateinit var btnAddExpense: FloatingActionButton

    private var currentCategories = emptyList<vcmsa.projects.pocketchange_v3.data.Category>()
    private var currentExpenses = emptyList<vcmsa.projects.pocketchange_v3.model.Expense>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_expenses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvExpenses)
        btnAddExpense = view.findViewById(R.id.btnAddExpense)

        adapter = ExpensesAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Observe categories first
        expenseViewModel.allCategories.observe(viewLifecycleOwner) { categories ->
            currentCategories = categories
            adapter.updateCategories(categories)

            // Re-submit expenses if we already have them
            if (currentExpenses.isNotEmpty()) {
                adapter.submitList(currentExpenses)
            }
        }

        // Observe expenses
        expenseViewModel.allExpenses.observe(viewLifecycleOwner) { expenses ->
            currentExpenses = expenses

            // Submit only when categories are available
            if (currentCategories.isNotEmpty()) {
                adapter.submitList(expenses)
            }
        }

        btnAddExpense.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_expenses_to_addExpenseFragment)
        }
    }
}

package vcmsa.projects.pocketchange_v3.ui.Expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import vcmsa.projects.pocketchange_v3.R
import vcmsa.projects.pocketchange_v3.ui.Expenses.ExpenseViewModel
import vcmsa.projects.pocketchange_v3.ui.Expenses.ExpensesAdapter

class ExpenseFragment : Fragment() {

    private val expenseViewModel: ExpenseViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ExpensesAdapter
    private lateinit var btnAddExpense: FloatingActionButton


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

        expenseViewModel.allExpenses.observe(viewLifecycleOwner) { expenses ->
            adapter.submitList(expenses)
        }

        btnAddExpense.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_expenses_to_addExpenseFragment)
        }
    }
}

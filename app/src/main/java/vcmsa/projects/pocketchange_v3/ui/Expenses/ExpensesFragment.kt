package vcmsa.projects.pocketchange_v3.ui.Expenses

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
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import vcmsa.projects.pocketchange_v3.R
import vcmsa.projects.pocketchange_v3.data.Category
import vcmsa.projects.pocketchange_v3.model.Expense

class ExpenseFragment : Fragment() {

    private val expenseViewModel: ExpenseViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ExpensesAdapter
    private lateinit var btnAddExpense: FloatingActionButton
    private lateinit var spinnerSort: Spinner

    private var currentCategories = emptyList<Category>()
    private var currentExpenses = emptyList<Expense>()

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
        spinnerSort = view.findViewById(R.id.spinnerSort)

        adapter = ExpensesAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        btnAddExpense.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_expenses_to_addExpenseFragment)
        }

        // Category observer
        expenseViewModel.allCategories.observe(viewLifecycleOwner) { categories ->
            currentCategories = categories
            adapter.updateCategories(categories)
            updateSortedList()
        }

        // Expense observer
        expenseViewModel.allExpenses.observe(viewLifecycleOwner) { expenses ->
            currentExpenses = expenses
            updateSortedList()
        }

        // Spinner logic
        spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                updateSortedList()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        adapter.onItemClick = { expense ->
            val bundle = Bundle().apply {
                putParcelable("expense", expense) // or putSerializable()
            }
            findNavController().navigate(R.id.action_expenseFragment_to_expenseDetailFragment, bundle)

        }
    }

    private fun updateSortedList() {
        if (currentCategories.isEmpty() || currentExpenses.isEmpty()) return

        val sortedList = when (spinnerSort.selectedItemPosition) {
            0 -> currentExpenses.sortedByDescending { it.date } // Newest First
            1 -> currentExpenses.sortedBy { it.date }            // Oldest First
            else -> currentExpenses
        }

        adapter.submitList(sortedList)
    }
}

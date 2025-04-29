package vcmsa.projects.pocketchange_v3.ui.Expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import vcmsa.projects.pocketchange_v3.databinding.FragmentExpensesBinding

class ExpensesFragment : Fragment() {

    private var _binding: FragmentExpensesBinding? = null
    private val binding get() = _binding!!
    private lateinit var expensesViewModel: ExpensesViewModel
    private lateinit var expensesAdapter: ExpensesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpensesBinding.inflate(inflater, container, false)

        expensesViewModel = ViewModelProvider(this)[ExpensesViewModel::class.java]

        setupRecyclerView()

        expensesViewModel.expenses.observe(viewLifecycleOwner) { expenses ->
            expensesAdapter.submitList(expenses)
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        expensesAdapter = ExpensesAdapter()
        binding.rvExpenses.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = expensesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

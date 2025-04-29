package vcmsa.projects.pocketchange_v3.ui.Budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import vcmsa.projects.pocketchange_v3.databinding.FragmentBudgetBinding

class BudgetFragment : Fragment() {

    private var _binding: FragmentBudgetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)

        binding.btnSaveBudget.setOnClickListener {
            val minAmount = binding.etMinAmount.text.toString().toDoubleOrNull()
            val maxAmount = binding.etMaxAmount.text.toString().toDoubleOrNull()

            if (minAmount == null || maxAmount == null) {
                Toast.makeText(requireContext(), "Please enter valid amounts", Toast.LENGTH_SHORT).show()
            } else if (minAmount > maxAmount) {
                Toast.makeText(requireContext(), "Min amount cannot be greater than Max amount", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Budget saved: Min = $minAmount, Max = $maxAmount", Toast.LENGTH_SHORT).show()
                // You can add save-to-database logic here later
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

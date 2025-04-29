package vcmsa.projects.pocketchange_v3.ui.Category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import vcmsa.projects.pocketchange_v3.databinding.FragmentCategoryBinding
import vcmsa.projects.pocketchange_v3.model.Category

class CategoryFragment : Fragment(), CategoryAdapter.OnItemClickListener {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)

        val sampleCategories = listOf(
            Category("Food", "🍔"),
            Category("Transport", "🚌"),
            Category("Utilities", "💡")
        )

        val adapter = CategoryAdapter(sampleCategories, this)
        binding.rvCategories.adapter = adapter

        return binding.root
    }

    override fun onItemClick(category: Category) {
        // Do something on category click
        Toast.makeText(requireContext(), "Clicked: ${category.name}", Toast.LENGTH_SHORT).show()
        // You could also navigate or open a dialog here
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

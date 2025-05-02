package vcmsa.projects.pocketchange_v3.ui.Category

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

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import vcmsa.projects.pocketchange_v3.databinding.FragmentCategoryBinding
import vcmsa.projects.pocketchange_v3.data.Category


class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoryViewModel: CategoryViewModel

    private lateinit var adapter: CategoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CategoryAdapter()
        binding.rvCategories.adapter = adapter

        categoryViewModel.allCategories.observe(viewLifecycleOwner) { categories ->
            if (categories.isEmpty()) {
                binding.tvEmptyState.visibility = View.VISIBLE
                binding.rvCategories.visibility = View.GONE
            } else {
                binding.tvEmptyState.visibility = View.GONE
                binding.rvCategories.visibility = View.VISIBLE
                adapter.submitList(categories)
            }
        }

        binding.fabAddCategory.setOnClickListener {
            showAddCategoryDialog()
        }
    }

    private fun showAddCategoryDialog() {
        val input = EditText(requireContext()).apply {
            hint = "Enter category name"
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Add Category")
            .setView(input)
            .setPositiveButton("Add") { _, _ ->
                val name = input.text.toString().trim()
                if (name.isNotEmpty()) {
                    val newCategory = Category(categoryName = name )
                    categoryViewModel.insert(newCategory)
                } else {
                    Toast.makeText(context, "Category name cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


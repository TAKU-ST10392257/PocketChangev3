package vcmsa.projects.pocketchange_v3.ui.Expense

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import vcmsa.projects.pocketchange_v3.R
import vcmsa.projects.pocketchange_v3.model.Expense
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController

import vcmsa.projects.pocketchange_v3.ui.Category.CategoryViewModel
import vcmsa.projects.pocketchange_v3.ui.Expenses.ExpenseViewModel
import java.util.*

class AddExpenseFragment : Fragment() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private var selectedCategoryId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_expense, container, false)


        val btnSelectDate = view.findViewById<Button>(R.id.btnSelectDate)
        val btnStartTime = view.findViewById<Button>(R.id.btnStartTime)
        val btnEndTime = view.findViewById<Button>(R.id.btnEndTime)
        val spinnerCategories = view.findViewById<Spinner>(R.id.spinnerCategories)
        val etAmount = view.findViewById<EditText>(R.id.etAmount)
        val etDescription = view.findViewById<EditText>(R.id.etDescription)
        val ivExpenseImage = view.findViewById<ImageView>(R.id.ivExpenseImage)
        val btnAddPhoto = view.findViewById<Button>(R.id.btnAddPhoto)
        val btnSaveExpense = view.findViewById<Button>(R.id.btnSaveExpense)


        // ViewModels
        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        expenseViewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]

        // Observe and populate category spinner
        categoryViewModel.allCategories.observe(viewLifecycleOwner) { categories ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories.map { it.categoryName })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCategories.adapter = adapter

            spinnerCategories.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    selectedCategoryId = categories[position].categoryId
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    selectedCategoryId = null
                }
            }
        }

        // Date picker
        btnSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    btnSelectDate.text = getString(R.string.date_format, dayOfMonth, month + 1, year)

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        // Time pickers
        btnStartTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(requireContext(), { _, hour, minute ->
                btnStartTime.text = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }

        btnEndTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(requireContext(), { _, hour, minute ->
                btnEndTime.text = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }

        // Image picker
        pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri: Uri? = result.data?.data
                if (imageUri != null) {
                    ivExpenseImage.setImageURI(imageUri)
                    ivExpenseImage.tag = imageUri.toString()
                    ivExpenseImage.visibility = View.VISIBLE
                }
            }
        }

        btnAddPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickImageLauncher.launch(intent)
        }

        // Save expense
        btnSaveExpense.setOnClickListener {
            val date = btnSelectDate.text.toString()
            val startTime = btnStartTime.text.toString()
            val endTime = btnEndTime.text.toString()
            val amountText = etAmount.text.toString()
            val description = etDescription.text.toString()

            if (amountText.isEmpty() || selectedCategoryId == null || date == "Select Date" || startTime == "Start Time" || endTime == "End Time") {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toDoubleOrNull()
            if (amount == null) {
                Toast.makeText(requireContext(), "Invalid amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val imageUrl = ivExpenseImage.tag as? String

            val expense = Expense(
                date = date,
                startTime = startTime,
                endTime = endTime,
                categoryId = selectedCategoryId!!,
                amount = amount,
                description = description,
                imageUri = imageUrl
            )

            findNavController().navigate(R.id.action_addExpenseFragment_to_navigation_expenses)

            expenseViewModel.insert(expense)
            Toast.makeText(requireContext(), "Expense saved", Toast.LENGTH_SHORT).show()

            // Optionally clear fields or navigate back
        }

        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

}

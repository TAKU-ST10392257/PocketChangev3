package vcmsa.projects.pocketchange_v3.ui.Expenses

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import vcmsa.projects.pocketchange_v3.R
import vcmsa.projects.pocketchange_v3.model.Expense
import vcmsa.projects.pocketchange_v3.ui.Expenses.ExpensesAdapter

class ExpenseDetailFragment : Fragment() {

    private val expenseViewModel: ExpenseViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_expense_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val expense = arguments?.getParcelable("expense", Expense::class.java)

        val categoryTextView = view.findViewById<TextView>(R.id.tvDetailCategory)
        val amountTextView = view.findViewById<TextView>(R.id.tvDetailAmount)
        val dateTimeTextView = view.findViewById<TextView>(R.id.tvDetailDateTime)
        val descriptionTextView = view.findViewById<TextView>(R.id.tvDetailDescription)
        val imageView = view.findViewById<ImageView>(R.id.ivDetailPhoto)

        amountTextView.text = "R${expense?.amount}"
        dateTimeTextView.text = "${expense?.date} ${expense?.startTime} - ${expense?.endTime}"
        descriptionTextView.text = expense?.description

        if (!expense?.imageUri.isNullOrEmpty()) {
            imageView.visibility = View.VISIBLE
            imageView.setImageURI(Uri.parse(expense?.imageUri))
        }

        // Observe categories and match the name
        expenseViewModel.allCategories.observe(viewLifecycleOwner) { categories ->
            val categoryName = categories.find { it.categoryId == expense?.categoryId }?.categoryName ?: "Unknown"
            categoryTextView.text = categoryName
        }
    }

}

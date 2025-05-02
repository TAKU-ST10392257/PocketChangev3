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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import vcmsa.projects.pocketchange_v3.R
import vcmsa.projects.pocketchange_v3.data.Category
import vcmsa.projects.pocketchange_v3.model.Expense

class ExpensesAdapter : RecyclerView.Adapter<ExpensesAdapter.ExpenseViewHolder>() {

    private var expenses: List<Expense> = emptyList()
    private var categories: List<Category> = emptyList()

    // 👇 New click listener
    var onItemClick: ((Expense) -> Unit)? = null

    fun submitList(newExpenses: List<Expense>) {
        expenses = newExpenses
        notifyDataSetChanged()
    }

    fun updateCategories(newCategories: List<Category>) {
        categories = newCategories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        val categoryName = categories.find { it.categoryId == expense.categoryId }?.categoryName ?: "Unknown"
        holder.bind(expense, categoryName)

        // 👇 Attach click handler
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(expense)
        }
    }

    override fun getItemCount(): Int = expenses.size

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.tvExpenseCategory)
        private val amountTextView: TextView = itemView.findViewById(R.id.tvExpenseAmount)
        private val dateTimeTextView: TextView = itemView.findViewById(R.id.tvExpenseDateTime)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tvExpenseDescription)
        private val photoImageView: ImageView = itemView.findViewById(R.id.ivExpensePhoto)

        fun bind(expense: Expense, categoryName: String) {
            categoryTextView.text = categoryName
            amountTextView.text = itemView.context.getString(R.string.expense_amount_format, expense.amount)
            dateTimeTextView.text = itemView.context.getString(
                R.string.expense_datetime_format,
                expense.date,
                expense.startTime,
                expense.endTime
            )
            descriptionTextView.text = expense.description

            if (!expense.imageUri.isNullOrEmpty()) {
                photoImageView.visibility = View.VISIBLE
                photoImageView.setImageURI(expense.imageUri.toUri())
            } else {
                photoImageView.visibility = View.GONE
            }
        }
    }
}

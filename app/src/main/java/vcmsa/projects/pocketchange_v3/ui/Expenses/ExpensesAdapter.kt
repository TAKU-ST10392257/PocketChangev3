package vcmsa.projects.pocketchange_v3.ui.expense

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import vcmsa.projects.pocketchange_v3.R
import vcmsa.projects.pocketchange_v3.model.Expense

class ExpensesAdapter : RecyclerView.Adapter<ExpensesAdapter.ExpenseViewHolder>() {

    private var expenses: List<Expense> = emptyList()

    fun submitList(newExpenses: List<Expense>) {
        expenses = newExpenses
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(expenses[position])
    }

    override fun getItemCount(): Int = expenses.size

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val category: TextView = itemView.findViewById(R.id.tvExpenseCategory)
        private val amount: TextView = itemView.findViewById(R.id.tvExpenseAmount)
        private val dateTime: TextView = itemView.findViewById(R.id.tvExpenseDateTime)
        private val description: TextView = itemView.findViewById(R.id.tvExpenseDescription)
        private val image: ImageView = itemView.findViewById(R.id.ivExpensePhoto)

        fun bind(expense: Expense) {
            category.text = expense.categoryId.toString()

            amount.text = itemView.context.getString(R.string.expense_amount_format, expense.amount)
            dateTime.text = itemView.context.getString(
                R.string.expense_datetime_format,
                expense.date,
                expense.startTime,
                expense.endTime
            )
            description.text = expense.description

            if (!expense.imageUri.isNullOrEmpty()) {
                image.visibility = View.VISIBLE
                image.setImageURI(expense.imageUri.toUri())
            } else {
                image.visibility = View.GONE
            }
        }
    }
}

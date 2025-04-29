package vcmsa.projects.pocketchange_v3.ui.Category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vcmsa.projects.pocketchange_v3.R
import vcmsa.projects.pocketchange_v3.model.Category

class CategoryAdapter(
    private var categories: List<Category>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(category: Category)
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvCategoryName)

        fun bind(category: Category) {
            tvName.text = category.name
            itemView.setOnClickListener {
                listener.onItemClick(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount() = categories.size
}

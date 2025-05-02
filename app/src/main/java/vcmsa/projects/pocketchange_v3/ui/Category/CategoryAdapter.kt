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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import vcmsa.projects.pocketchange_v3.R
import vcmsa.projects.pocketchange_v3.data.Category

class CategoryAdapter : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(DiffCallback()) {

    class CategoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(category: Category) {
            view.findViewById<TextView>(R.id.tvCategoryName).text = category.categoryName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category) = oldItem.categoryId == newItem.categoryId
        override fun areContentsTheSame(oldItem: Category, newItem: Category) = oldItem == newItem
    }
}

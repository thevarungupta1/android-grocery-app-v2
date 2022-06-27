package com.thevarungupta.grocery_app.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.thevarungupta.grocery_app.R
import com.thevarungupta.grocery_app.activities.SubCategoryActivity
import com.thevarungupta.grocery_app.app.Config
import com.thevarungupta.grocery_app.models.Category
import kotlinx.android.synthetic.main.row_category_adapter.view.*

class CategoryAdapter(var mContext: Context) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var mList: ArrayList<Category> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(mContext).inflate(R.layout.row_category_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var category = mList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(data: ArrayList<Category>) {
        mList = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) {
            itemView.text_view.text = category.catName

            Picasso
                .get()
                .load(Config.IMAGE_URL + category.catImage)
                .into(itemView.image_view)

            itemView.setOnClickListener {
                var intent = Intent(mContext, SubCategoryActivity::class.java)
                intent.putExtra(Category.KEY_CAT_ID, category.catId)
                mContext.startActivity(intent)
            }
        }
    }
}
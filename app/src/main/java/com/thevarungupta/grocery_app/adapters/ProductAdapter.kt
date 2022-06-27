package com.thevarungupta.grocery_app.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.thevarungupta.grocery_app.R
import com.thevarungupta.grocery_app.activities.ProductDetailActivity
import com.thevarungupta.grocery_app.app.Config
import com.thevarungupta.grocery_app.models.Product
import kotlinx.android.synthetic.main.row_product_adapter.view.*

class ProductAdapter(var mContext: Context) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var mList: ArrayList<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(mContext).inflate(R.layout.row_product_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var product = mList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(list: ArrayList<Product>) {
        mList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) {

            Picasso
                .get()
                .load("${Config.IMAGE_URL + product.image}")
                .into(itemView.image_view_product)

            itemView.text_view_product_name.text = product.productName
            itemView.text_view_unit.text = product.unit
            itemView.text_view_mrp.text = mContext.getString(R.string.Rs)+ product.mrp.toString()
            itemView.text_view_price.text = mContext.getString(R.string.Rs)+product.price.toString()
            itemView.text_view_mrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            itemView.setOnClickListener {
                var intent = Intent(mContext, ProductDetailActivity::class.java)
                intent.putExtra(Product.KEY_PRODUCT, product)
                mContext.startActivity(intent)
            }
        }

    }
}
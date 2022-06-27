package com.thevarungupta.grocery_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thevarungupta.grocery_app.R
import com.thevarungupta.grocery_app.models.Address
import kotlinx.android.synthetic.main.row_address_adapter.view.*

class AddressAdapter(var mContext: Context) : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {

    var mList: ArrayList<Address> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.row_address_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var address = mList[position]
        holder.bind(address)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(list: ArrayList<Address>) {
        mList = list
        notifyDataSetChanged()

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(address: Address) {
            itemView.text_view_address_1.text = "${address.houseNo}, ${address.streetName}"
            itemView.text_view_address_2.text = "${address.city} - ${address.pincode}"
        }
    }


}
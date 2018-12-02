package com.danidiprana.blastsms.presentation.listCustomer

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.NO_POSITION
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.danidiprana.blastsms.R
import com.danidiprana.blastsms.domain.entity.CustomerViewObject
import com.danidiprana.blastsms.presentation.main.MainContract.CustomerClickListener
import kotlinx.android.synthetic.main.item_list_customer.view.tv_name
import kotlinx.android.synthetic.main.item_list_customer.view.tv_phone

class ListCustomerAdapter(val customerClickCustomer: CustomerClickListener) : RecyclerView.Adapter<ListCustomerViewHolder>() {

  var listCustomer: List<CustomerViewObject> = emptyList()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ListCustomerViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_list_customer, parent, false) as LinearLayout

    return ListCustomerViewHolder(view).apply {
      itemView.setOnClickListener {
        val position = this.adapterPosition
        if (position != NO_POSITION){
          customerClickCustomer.onCustomerSelected(listCustomer[position], position)
        }
      }
    }
  }

  override fun getItemCount(): Int = listCustomer.size

  override fun onBindViewHolder(
    holder: ListCustomerViewHolder,
    position: Int
  ) {
    holder.bind(listCustomer[position])
  }


}

class ListCustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(customerViewObject: CustomerViewObject) {
    if (customerViewObject.isSelected){
      itemView.setBackgroundColor(Color.RED)
    }else{
      itemView.setBackgroundColor(Color.TRANSPARENT)
    }

    itemView.tv_name.text = customerViewObject.name
    itemView.tv_phone.text = customerViewObject.phone
  }

}
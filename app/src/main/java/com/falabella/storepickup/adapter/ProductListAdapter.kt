package com.falabella.storepickup.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.falabella.storepickup.R
import com.falabella.storepickup.databinding.LayoutProductItemBinding
import com.falabella.storepickup.model.Product

class ProductListAdapter(
    private val context: Context,
    private val productList: List<Product>,
) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    var loadingComplete = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false).root
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.layoutProductItemBinding.apply {
            ivProductImage.apply {
                Glide.with(context).load(product.image).into(this)
                loadingComplete = true
            }
            tvProductName.text = product.name.toString()
            tvProductPrice.text = context.getString(R.string.item_price, product.price)
        }
    }

    inner class ProductViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var layoutProductItemBinding: LayoutProductItemBinding = LayoutProductItemBinding.bind(itemView)
    }

}
package com.example.mengov2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mengov2.databinding.ItemItemBinding

class AllitemAdapter(private val MenuItemName:ArrayList<String>, private val MenuItemPrice:ArrayList<String>, private val MenuItemImage:ArrayList<Int>) : RecyclerView.Adapter<AllitemAdapter.AddItemViewHolder>() {
    private val itemQuantities = IntArray(MenuItemName.size){1}

    inner class AddItemViewHolder(private val binding : ItemItemBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int)
        {
            binding.apply {
                val quantity = itemQuantities[position]
                foodNameTextView.text=MenuItemName[position]
                PriceTextView.text= MenuItemPrice[position]
                foodimageview.setImageResource(MenuItemImage[position])
                quantityTextView.text=quantity.toString()
                minusButton.setOnClickListener{
                    decreaseQuantitiy(position)
                }
                plusleButtom.setOnClickListener{
                    increaseQuantitiy(position)
                }
                deleteButton.setOnClickListener{
                    deleteQuantitiy(position)
                }
            }
        }
        private fun increaseQuantitiy(position: Int)
        {
            if(itemQuantities[position]<10)
            {
                itemQuantities[position]++
                binding.quantityTextView.text=itemQuantities[position].toString()
            }
        }
        private fun decreaseQuantitiy(position: Int)
        {
            if(itemQuantities[position]>1)
            {
                itemQuantities[position]--
                binding.quantityTextView.text=itemQuantities[position].toString()
            }
        }
        private fun deleteQuantitiy(position: Int)
        {
            MenuItemName.removeAt(position)
            MenuItemPrice.removeAt(position)
            MenuItemImage.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, MenuItemName.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return AddItemViewHolder(binding)
    }

    override fun getItemCount(): Int = MenuItemName.size

    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }
}
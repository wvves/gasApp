package com.example.gasapp.screens.adapters


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gasapp.R
import com.example.gasapp.databinding.ItemTypeOilBinding
import com.example.gasapp.model.stations.entities.Oil



class OilTypeAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<OilTypeAdapter.OilTypeViewHolder>(), View.OnClickListener {

    interface Listener {
        fun enableOrder(oil: Oil)
    }

    private var oils: List<Oil> = emptyList()
    override fun onClick(v: View) {
        val oil = v.tag as Oil
        listener.enableOrder(oil)
    }

    class OilTypeViewHolder(
        val binding: ItemTypeOilBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OilTypeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTypeOilBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return OilTypeViewHolder(binding)
    }

    override fun getItemCount(): Int = oils.size

    override fun onBindViewHolder(holder: OilTypeViewHolder, position: Int) {
        val oil = oils[position]

        with(holder.binding) {
            holder.itemView.tag = oil

            textView5.text = oil.type
            priceTextView.text = oil.price.toString()

//            cardView.setOnClickListener {
////                cardView.setCardBackgroundColor(Color.GREEN)
////                Toast.makeText(
////                    holder.itemView.context,
////                    "${oil.type}",
////                    Toast.LENGTH_SHORT
////                ).show()
////                listener.enableOrder(oil)
////            }
        }
    }
    fun renderSettings(oils: List<Oil>) {
        val diffResult = DiffUtil.calculateDiff(CreateOrderDiffCallback(this.oils, oils))
        this.oils = oils
        diffResult.dispatchUpdatesTo(this)
    }
}
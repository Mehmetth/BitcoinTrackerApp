package com.mehmetth.bitcointickerapp.presentation.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mehmetth.bitcointickerapp.R
import com.mehmetth.bitcointickerapp.domain.service.entity.ServiceEntity
import com.mehmetth.bitcointickerapp.utils.Constant
import kotlinx.android.synthetic.main.coin_rv_item.view.*

class HomeAdapter  (var context: Context,
                    var items: List<ServiceEntity>?,
                    val onItemClick: (view: View,coinId: ServiceEntity,item: Boolean) -> Unit):
    RecyclerView.Adapter<HomeAdapter.DataViewHolder> () {
    class DataViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.coin_rv_item,parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    fun updateDataList(newDataList: List<ServiceEntity>) {
        items = newDataList
        notifyDataSetChanged()
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        Glide.with(context)
            .load(items!![position].image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .error(android.R.drawable.ic_menu_report_image)
            .into(holder.view.coin_image)

        holder.view.coin_name.text = items!![position].name
        holder.view.coin_symbol.text = items!![position].symbol

        if(items!![position].price_change_24h!!.toDouble() > 0){
            holder.view.coin_status.background = context.getDrawable(R.drawable.coin_up)
        }
        else{
            holder.view.coin_status.background = context.getDrawable(R.drawable.coin_down)
        }
        holder.view.coin_status.text = "${Constant.VSCURRENCYSYMBOL} ${items!![position].current_price.toString()}"

        holder.view.highest_value.text = items!![position].high_24h.toString()
        holder.view.lowest_value.text = items!![position].low_24h.toString()
        holder.view.change_value.text = items!![position].price_change_percentage_24h.toString() + "%"

        holder.view.more_text.setOnClickListener {
            onItemClick(holder.view.more_layout,items!![position],false)
        }

        holder.view.setOnClickListener {
            onItemClick(it,items!![position],true)
        }
    }
}
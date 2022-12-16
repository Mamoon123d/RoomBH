package com.e.roomexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.e.roomexample.data.model
import com.e.roomexample.databinding.ListItemBinding
import com.moon.baselibrary.base.BaseRvAdapter
import com.moon.baselibrary.base.BaseRvViewHolder

class ItemAdapter(
    val context: Context,
    list: List<model>
) : BaseRvAdapter<model, ItemAdapter.MyHolder>(context, list) {

    protected var onItemDeleteListener: OnItemDeleteListener? = null

    class MyHolder(val binding: ListItemBinding) : BaseRvViewHolder(binding) {
        fun bind(context: Context, t: model, onItemDeleteListener: OnItemDeleteListener?) {
            binding.td.text = "TD : ${t.td}"
            binding.md.text = "MD : ${t.md}"
            if (onItemDeleteListener != null) {
                binding.delete.setOnClickListener {
                    onItemDeleteListener.onItemDelete(position)
                }
            }
        }

    }

    override fun onBindData(holder: MyHolder, t: model) {
        holder.bind(context, t, onItemDeleteListener)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    public interface OnItemDeleteListener {
        public fun onItemDelete(position: Int)
    }
    @JvmName("setOnItemDeleteListener1")
    public fun setOnItemDeleteListener(onItemDeleteListener: OnItemDeleteListener) {
        this.onItemDeleteListener = onItemDeleteListener
    }

}
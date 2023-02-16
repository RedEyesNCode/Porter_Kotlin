package com.ushatech.porter.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ushatech.porter.databinding.ShiftDateItemBinding

class ShiftDateAdapter(var context:Context):RecyclerView.Adapter<ShiftDateAdapter.MyViewHolder>() {

    lateinit var binding:ShiftDateItemBinding




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ShiftDateItemBinding.inflate(LayoutInflater.from(context))


        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvDateNumber.setText("16 Feb")
    }

    override fun getItemCount(): Int {
        return 10
    }

    public class MyViewHolder(var binding:ShiftDateItemBinding):RecyclerView.ViewHolder(binding.root)
}
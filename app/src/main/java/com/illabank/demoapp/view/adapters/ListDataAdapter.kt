package com.illabank.demoapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.illabank.demoapp.R
import com.illabank.demoapp.databinding.RowListingDataBinding
import com.illabank.demoapp.model.ImageListData.ListData
import java.util.*


class ListDataAdapter(var listData: List<ListData>) :
    RecyclerView.Adapter<ListDataAdapter.ViewHolder>(), Filterable {

    private var mListData = listData
    private var filteredListData = listData

    class ViewHolder(var mBinding: RowListingDataBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun setData(listData: ListData) {
            mBinding.listData = listData
            mBinding.imgListIcon.setImageResource(R.drawable.ic_checklist_svg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinding = DataBindingUtil.inflate<RowListingDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_listing_data,
            parent,
            false
        )

        return ViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(filteredListData[position])
    }

    override fun getItemCount() = filteredListData.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    filteredListData = mListData
                } else {
                    val filteredList: MutableList<ListData> = ArrayList()
                    for (data in mListData) {
                        if (data.listText.toLowerCase(Locale.getDefault()).contains(
                                charString.toLowerCase(
                                    Locale.getDefault()
                                )
                            )
                        ) {
                            filteredList.add(data)
                        }
                    }
                    filteredListData = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredListData
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: FilterResults
            ) {
                filteredListData = filterResults.values as List<ListData>
                notifyDataSetChanged()
            }
        }
    }
}
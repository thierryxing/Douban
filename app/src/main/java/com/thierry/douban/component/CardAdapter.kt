package com.thierry.douban.component

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.thierry.douban.BR
import com.thierry.douban.model.Card
import com.thierry.douban.module.common.BaseViewModel
import kotlinx.android.synthetic.main.partial_recycler_view.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * Created by Thierry on 2017/7/1.
 */
class CardAdapter(val viewModel: RecyclerViewModel, val itemClickCallback: RecyclerFragment.OnItemClickListener? = null) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    val TAG = CardAdapter::class.java.canonicalName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layoutId = viewModel.getCardLayoutId(viewType)
        val itemBinding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, parent, false)
        return CardViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int)
            = holder.bind(viewModel.getCardViewModel(position), viewModel.cardList[position], itemClickCallback)

    override fun getItemCount(): Int
            = viewModel.cardList.size

    override fun getItemViewType(position: Int): Int
            = viewModel.cardList[position].layout

    class CardViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cardViewModel: BaseViewModel, card: Card, listener: RecyclerFragment.OnItemClickListener?) = with(itemView) {
            binding.setVariable(BR.viewModel, cardViewModel)
            binding.root.setOnClickListener { listener?.onItemClick(card) }
            if (cardViewModel::class.java.superclass.equals(CardViewModel::class.java)) {
                buildChildCardAdapter(cardViewModel as CardViewModel)
            }
            return@with
        }

        fun buildChildCardAdapter(viewModel: CardViewModel) = with(itemView) {
            viewModel.fetchData()
            partialRecyclerView.layoutManager = viewModel.getLayoutManager(context)
            partialRecyclerView.adapter = CardAdapter(viewModel)
            partialRecyclerView.adapter.notifyDataSetChanged()
        }

    }


}




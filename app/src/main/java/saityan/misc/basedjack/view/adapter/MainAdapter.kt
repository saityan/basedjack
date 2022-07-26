package saityan.misc.basedjack.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import saityan.misc.basedjack.databinding.MainFragmentItemBinding
import saityan.misc.basedjack.repository.Highpost
import saityan.misc.basedjack.view.MainFragment

class MainAdapter(
    private var data: List<Highpost>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapter.MainViewHolder =
        MainViewHolder(
            MainFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: List<Highpost>) {
        this.data = data
    }

    inner class MainViewHolder (private val binding: MainFragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind (position: Int) = with(binding) {
            mainTitle.text = data[position].data.title
        }

        override fun onClick(p0: View?) { }
    }
}

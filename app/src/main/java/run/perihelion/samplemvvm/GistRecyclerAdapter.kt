package run.perihelion.samplemvvm

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_gist.view.*
import run.perihelion.models.Gist

class GistRecyclerAdapter : RecyclerView.Adapter<GistRecyclerAdapter.GistViewHolder>() {
    var data = emptyList<Gist>()

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        return GistViewHolder(View.inflate(parent.context, R.layout.list_item_gist, parent))
    }

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        holder.author.text = data[position].user
    }

    class GistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val author: TextView = itemView.gist_author
    }
}
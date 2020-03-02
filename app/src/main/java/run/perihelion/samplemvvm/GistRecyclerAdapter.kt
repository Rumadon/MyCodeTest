package run.perihelion.samplemvvm

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GistRecyclerAdapter : RecyclerView.Adapter<GistRecyclerAdapter.GistViewHolder>() {
    var data = emptyList<GistViewHolder>()

    override fun getItemCount(): Int = data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        return GistViewHolder(View.inflate(parent.context, R.layout.content_main, parent))
    }


    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class GistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
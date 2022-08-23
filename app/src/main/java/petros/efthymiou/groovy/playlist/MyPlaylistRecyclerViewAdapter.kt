package petros.efthymiou.groovy.playlist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import petros.efthymiou.groovy.R
import java.util.*

class MyPlaylistRecyclerViewAdapter(
    private val values: List<Playlist>,
    private val listener : (String) -> Unit

    ) : RecyclerView.Adapter<MyPlaylistRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.playlist_item, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.playlistName.text = item.name
        holder.playlistCategory.text = item.category
        holder.playlistImage.setImageResource(item.image)
        holder.root.setOnClickListener{listener(item.id)}
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playlistName: TextView = view.findViewById(R.id.playlist_name)
        val playlistCategory: TextView = view.findViewById(R.id.playlist_category)
        val playlistImage: ImageView = view.findViewById(R.id.playlist_image)

        val root : View = view.findViewById(R.id.playlist_item_root)
        }
}


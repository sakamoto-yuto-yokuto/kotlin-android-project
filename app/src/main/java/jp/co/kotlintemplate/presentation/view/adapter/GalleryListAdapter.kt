package jp.co.kotlintemplate.presentation.view.adapter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.co.kotlintemplate.R
import jp.co.kotlintemplate.data.entity.Gallery
import jp.co.kotlintemplate.databinding.ItemGalleryListBinding
import jp.co.kotlintemplate.extension.default
import jp.co.kotlintemplate.extension.map
import jp.co.kotlintemplate.presentation.viewmodel.adapter.GalleryListItemViewModel

class GalleryListAdapter constructor(@NonNull val owner: LifecycleOwner) : RecyclerView.Adapter<GalleryListAdapter.ViewHolder>() {
    val entities = MutableLiveData<List<Gallery>>().default(mutableListOf())
    private val viewModels = this.entities.map {
        if (it.isEmpty()) {
            mutableListOf()
        } else {
            it.map { entity -> GalleryListItemViewModel(entity.getName()) }.toList()
        }
    }

    init {
        this.viewModels.observe(this.owner, Observer {
            this.notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_list, parent, false)
        return GalleryListAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryListAdapter.ViewHolder, position: Int) {
        holder.update(this.viewModels.value!![position])
    }

    override fun getItemCount() = this.viewModels.value?.count() ?: 0

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemGalleryListBinding = DataBindingUtil.bind(view)!!

        fun update(viewModel: GalleryListItemViewModel) {
            this.binding.viewModel = viewModel
        }
    }
}
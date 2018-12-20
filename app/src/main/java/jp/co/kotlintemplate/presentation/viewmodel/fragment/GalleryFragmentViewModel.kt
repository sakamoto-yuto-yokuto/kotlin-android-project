package jp.co.kotlintemplate.presentation.viewmodel.fragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import jp.co.kotlintemplate.data.entity.Gallery
import jp.co.kotlintemplate.extension.addAll
import jp.co.kotlintemplate.extension.default
import jp.co.kotlintemplate.extension.map
import jp.co.kotlintemplate.presentation.viewmodel.BaseViewModel
import java.util.ArrayList
import javax.inject.Inject

class GalleryFragmentViewModel @Inject constructor() : BaseViewModel() {
    private val _galleries = MutableLiveData<MutableList<Gallery>>().default(mutableListOf())
    val galleries: LiveData<List<Gallery>> = _galleries.map { it.toList() }

    fun getGalleries() {
        val list = ArrayList<Gallery>()
        for (index in 0..99) {
            list.add(Gallery(index.toString(), "Gallery $index"))
        }
        _galleries.addAll(list)
    }
}
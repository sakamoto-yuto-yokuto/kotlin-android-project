package jp.co.kotlintemplate.presentation.viewmodel.adapter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import jp.co.kotlintemplate.presentation.viewmodel.BaseViewModel
import javax.inject.Inject

class GalleryListItemViewModel @Inject constructor() : BaseViewModel() {
    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?> = _name

    constructor(defName: String?): this() { _name.postValue(defName) }
}
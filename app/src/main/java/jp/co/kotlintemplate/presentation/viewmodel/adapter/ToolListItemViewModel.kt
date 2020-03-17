package jp.co.kotlintemplate.presentation.viewmodel.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ToolListItemViewModel @Inject constructor() : ViewModel() {
    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?> = _name

    constructor(defName: String?): this() { _name.postValue(defName) }
}
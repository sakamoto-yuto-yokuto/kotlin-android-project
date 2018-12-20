package jp.co.kotlintemplate.presentation.view.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import jp.co.kotlintemplate.presentation.lifecycle.DisposableObserver

abstract class BaseFragment : Fragment() {
    protected val disableObserver = DisposableObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.lifecycle.addObserver(this.disableObserver)
    }
}
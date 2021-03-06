package jp.co.kotlintemplate.presentation.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class DisposableObserver : LifecycleObserver {
    val subscriptions: CompositeDisposable = CompositeDisposable()

    fun addAll(vararg disposable: Disposable) = subscriptions.addAll(*disposable)

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopped() { if (!subscriptions.isDisposed) subscriptions.dispose() }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyed() { if (!subscriptions.isDisposed) subscriptions.dispose() }
}
package jp.co.kotlintemplate.presentation.viewmodel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import jp.co.kotlintemplate.data.repository.IUserRepository
import jp.co.kotlintemplate.domain.model.LoginChallenge
import jp.co.kotlintemplate.extension.map
import jp.co.kotlintemplate.extension.switchMap
import jp.co.kotlintemplate.presentation.viewmodel.BaseViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val userRepository: IUserRepository) : BaseViewModel() {
    private val _loginChallenge = MutableLiveData<LoginChallenge>()

    val userId = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginChallenge: LiveData<LoginChallenge> = _loginChallenge
    val loginResult = _loginChallenge.switchMap { this.userRepository.login(it) }
    val result = loginResult.map { it.result }
    val message = loginResult.map { it.message }
    val token = loginResult.map { it.token }

    val skipCommand: Subject<Boolean> = PublishSubject.create()

    fun login() = this._loginChallenge.postValue(LoginChallenge(this.userId.value, this.password.value))
    fun skip() = this.skipCommand.onNext(true)
}
package jp.co.kotlintemplate.data.repository

import androidx.lifecycle.LiveData
import jp.co.kotlintemplate.data.entity.User
import jp.co.kotlintemplate.domain.model.LoginChallenge
import jp.co.kotlintemplate.domain.model.LoginResult
import jp.co.kotlintemplate.domain.model.SaveUserResult

interface IUserRepository : IRepository {
    fun login(model: LoginChallenge): LiveData<LoginResult>
    fun saveUser(user: User): LiveData<SaveUserResult>
}
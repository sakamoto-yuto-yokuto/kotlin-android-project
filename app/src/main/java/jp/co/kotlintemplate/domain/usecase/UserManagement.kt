package jp.co.kotlintemplate.domain.usecase

import jp.co.kotlintemplate.data.repository.IUserRepository
import javax.inject.Inject

class UserManagement @Inject constructor(private val userRepository: IUserRepository) : Usecase() {
}
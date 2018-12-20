package jp.co.kotlintemplate.domain.model

import jp.co.kotlintemplate.data.entity.User

data class SaveUserChallenge(val entity: User)
data class SaveUserResult(val isSuccess: Boolean, val message: String? = null)
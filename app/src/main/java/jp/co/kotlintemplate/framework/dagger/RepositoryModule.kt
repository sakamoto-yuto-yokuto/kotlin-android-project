package jp.co.kotlintemplate.framework.dagger

import dagger.Module
import dagger.Provides
import jp.co.kotlintemplate.data.entity.OrmaDatabase
import jp.co.kotlintemplate.data.repository.IUserRepository
import jp.co.kotlintemplate.data.repository.impl.UserRepository
import retrofit2.Retrofit

@Module
class RepositoryModule {
    @Provides
    fun provideIUserRepository(retrofit: Retrofit, orma: OrmaDatabase) : IUserRepository = UserRepository(retrofit, orma)
}
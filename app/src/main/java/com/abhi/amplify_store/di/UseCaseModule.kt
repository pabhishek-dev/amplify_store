package com.abhi.amplify_store.di

import com.abhi.amplify_store.domain.repository.AuthRepository
import com.abhi.amplify_store.domain.repository.FavouritesRepository
import com.abhi.amplify_store.domain.repository.HomeRepository
import com.abhi.amplify_store.domain.repository.ProductDetailsRepository
import com.abhi.amplify_store.domain.repository.SSLPinningRepository
import com.abhi.amplify_store.domain.usecase.FavouriteUseCase
import com.abhi.amplify_store.domain.usecase.HomeUseCase
import com.abhi.amplify_store.domain.usecase.LoginUseCase
import com.abhi.amplify_store.domain.usecase.ProductDetailsUseCase
import com.abhi.amplify_store.domain.usecase.RegisterUseCase
import com.abhi.amplify_store.domain.usecase.SSLValidateUseCase
import com.abhi.amplify_store.domain.usecase.util.EmailValidator
import com.abhi.amplify_store.domain.usecase.util.PasswordValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideLoginUseCase(
        repository: AuthRepository,
        emailValidator: EmailValidator,
        passwordValidator: PasswordValidator
    ): LoginUseCase = LoginUseCase(repository, emailValidator, passwordValidator)

    @Provides
    fun provideRegisterUseCase(
        repository: AuthRepository,
        emailValidator: EmailValidator,
        passwordValidator: PasswordValidator
    ): RegisterUseCase = RegisterUseCase(repository, emailValidator, passwordValidator)

    @Provides
    fun provideSSLUseCase(
        repository: SSLPinningRepository
    ): SSLValidateUseCase = SSLValidateUseCase(repository)

    @Provides
    fun provideHomeUseCase(
        homeRepository: HomeRepository
    ): HomeUseCase = HomeUseCase(homeRepository)

    @Provides
    fun provideProductDetailsUseCase(
        productDetailsRepository: ProductDetailsRepository
    ): ProductDetailsUseCase = ProductDetailsUseCase(productDetailsRepository)

    @Provides
    fun provideFavouriteUseCase(
        favouritesRepository: FavouritesRepository
    ): FavouriteUseCase = FavouriteUseCase(
        favouritesRepository
    )
}

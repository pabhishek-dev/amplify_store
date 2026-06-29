package com.abhi.trackinfo.di

import com.abhi.trackinfo.domain.repository.AuthRepository
import com.abhi.trackinfo.domain.repository.FavouritesRepository
import com.abhi.trackinfo.domain.repository.HomeRepository
import com.abhi.trackinfo.domain.repository.ProductDetailsRepository
import com.abhi.trackinfo.domain.repository.SSLPinningRepository
import com.abhi.trackinfo.domain.usecase.FavouriteUseCase
import com.abhi.trackinfo.domain.usecase.HomeUseCase
import com.abhi.trackinfo.domain.usecase.LoginUseCase
import com.abhi.trackinfo.domain.usecase.ProductDetailsUseCase
import com.abhi.trackinfo.domain.usecase.RegisterUseCase
import com.abhi.trackinfo.domain.usecase.SSLValidateUseCase
import com.abhi.trackinfo.domain.usecase.util.EmailValidator
import com.abhi.trackinfo.domain.usecase.util.PasswordValidator
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

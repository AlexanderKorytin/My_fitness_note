package com.example.myfitnessnote.di

import com.example.myfitnessnote.data.repository.DaysRepositoryImpl
import com.example.myfitnessnote.domain.api.DaysInteractor
import com.example.myfitnessnote.domain.api.DaysRepository
import com.example.myfitnessnote.domain.impl.DaysInteractorImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val domainModule = module {
    single<DaysRepository> {
        DaysRepositoryImpl(storage = get())
    }
    factory<DaysInteractor> {
        DaysInteractorImpl(repository = get())
    }
}
package com.example.foodyapplication.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonPlaceHolderSite(
)

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class StackOverFlowSite(
)

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class FatherOfApps()
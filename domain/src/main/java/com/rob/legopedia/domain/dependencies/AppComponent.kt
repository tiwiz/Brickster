package com.rob.legopedia.domain.dependencies

import android.app.Activity
import android.app.Application
import com.rob.legopedia.domain.ui.LegoSetViewModel
import dagger.Component
import javax.inject.Provider

@Component(modules = [NetworkModule::class])
interface AppComponent {
    val provider: Provider<LegoSetViewModel>

    @Component.Factory
    interface Factory {
        fun create() : AppComponent
    }
}

val Application.appComponent : AppComponent
    get() = DaggerAppComponent.factory().create()

val Activity.appComponent : AppComponent
    get() = application.appComponent


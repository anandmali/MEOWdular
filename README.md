# AisleDesign

> A demo of Aisle app designs, given [here](https://public.3.basecamp.com/p/YsPNgSeRhUqLs64GpSgkjjB7)

## Built with 🛠

* Java
* [Dagger Hilt](https://dagger.dev/hilt/) - dependency injection
* [Jetpack](https://developer.android.com/jetpack)
    * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - in-app navigation
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - an observable data holder class
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
    * [Data binding](https://developer.android.com/topic/libraries/data-binding) - bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically
* [Retrofit](https://square.github.io/retrofit/) - networking
* [RxJava 3](https://github.com/ReactiveX/RxJava) - Reactive Extensions for the JVM
* [Glide](https://github.com/bumptech/glide) - a fast and efficient open source media management and image loading framework for Android


## Architecture - MVVM

Modularized code providing benefits of better [separation of concerns](https://en.wikipedia.org/wiki/Separation_of_concerns). Demonstrating [recommended app architecture](https://developer.android.com/jetpack/guide#recommended-app-arch) with structuring into separate modules, that can be grouped as `activity/fragment`, `view models`, `repositories`, `data source`, and `dependecies`.

[Download debug APK](statics/app-debug.apk)

### Test requirements
* Phone number : `9876543212`
* OTP : `1234`

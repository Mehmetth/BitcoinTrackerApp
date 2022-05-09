

<!-- ABOUT THE PROJECT -->
## BitcoinTrackerApp
<p float="left">
<img src="https://user-images.githubusercontent.com/18207490/167412018-5b2882f0-d11e-4f4e-8c70-74e15d66a8d6.png"  height="275">
<img src="https://user-images.githubusercontent.com/18207490/167412007-589cbee0-139e-4fca-bef7-4b5fa7166afa.png"  height="275">
<img src="https://user-images.githubusercontent.com/18207490/167411997-acb614e6-f0c4-4fa5-b796-983730542b25.png"  height="275">
<img src="https://user-images.githubusercontent.com/18207490/167411988-a794c758-2540-4732-9898-f3edeaecf686.png"  height="275">
<img src="https://user-images.githubusercontent.com/18207490/167412043-ea284b5b-13b5-4e40-ac46-5175d9a5d74c.png"  height="275">
<img src="https://user-images.githubusercontent.com/18207490/167412041-8f5266c2-6dfb-4332-9943-ef9b27e61e6a.png"  height="275">
<img src="https://user-images.githubusercontent.com/18207490/167412033-c09408f7-1be5-4f39-ac5f-5e40e9355e1a.png"  height="275">
<img src="https://user-images.githubusercontent.com/18207490/167412021-c61cb2ca-6ed4-4985-82e4-cd355512ac38.png"  height="275">
<img src="https://user-images.githubusercontent.com/18207490/167412020-47a9e286-8f5e-46b1-b00f-c85d81ab778e.png"  height="275">
<img src="https://user-images.githubusercontent.com/18207490/167412012-54d45249-99b0-47e6-82d9-7883ebb03bc2.png"  height="275">
<img src="https://user-images.githubusercontent.com/18207490/167412000-901c5cc5-b530-4d79-954c-899aa2044bba.png"  height="275">
<img src="https://user-images.githubusercontent.com/18207490/167412004-65ae0e12-adf1-4335-8a6a-bebd7f352bcf.png"  height="275">
</p>

This application is written using the api.coingecko. The application, MVVM, Coroutines, Hilt, Navigation Component, Room Database, Firebse(Authentication,Cloud Firestore) features were used.

 * Architecture is written in MVVM. 
 * Asynchronous transactions were made with Coroutines. 
 * StateFlow was used to control the values returned in the Retrofit and to perform operations according to the returned values.
 * Glide is used to display the pictures.
 * Hilt is used for Dependency Injection.
 * Room Database was used to store local data.
 * Firebase Authentication is used for login processes.
 * Firestore Database is used to add to favorites and read.

### Built With

Libraries used in the application.

* [Coroutines](https://developer.android.com/kotlin/coroutines)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
* [View Binding](https://developer.android.com/topic/libraries/view-binding)
* [Retrofit](https://square.github.io/retrofit/)
* [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
* [Glide](https://github.com/bumptech/glide)
* [RoomDB](https://developer.android.com/training/data-storage/room)
* [Firebase Authentication](https://firebase.google.com/docs/auth)
* [Firestore Database](https://firebase.google.com/docs/firestore)

<!-- GETTING STARTED -->
## Getting Started

### Installation

1. Get a free API
2. Enter your API in `build.gradle`
   ```js
   buildConfigField 'String', 'API_BASE_URL', '"https://api.coingecko.com/api/v3/coins/"'
   ```


# 📱 InoviMap

**Mobile Development Test — Inovisec**

This project meets the technical test requirements for developing a **functional login form** using Kotlin and Jetpack Compose, which upon successful login displays a **Google Maps** centered on a predefined location.

---

## 🧩 General Objective

- Implement a modern and functional login form.
- Validate credentials using a backend in Node.js (Express).
- Display an interactive map with the Google Maps API after a successful login.

---

## 🎥 Demo in Action

<p align="center">
  <img src="assets/demo.gif" alt="InoviMap Demo" width="270"/>
</p>

---

## 🧩 Source code

<pre> ```text
InoviMap/
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties          
├── secret.properties
├── settings.gradle.kts
├── estructura_proyecto.txt   
│
├── app/
│   ├── .gitignore
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   ├── src/
│   │   ├── androidTest/
│   │   │   └── java/com/example/inovimap/ExampleInstrumentedTest.kt
│   │   ├── test/
│   │   │   └── java/com/example/inovimap/ExampleUnitTest.kt
│   │   └── main/
│   │       ├── AndroidManifest.xml
│   │       ├── java/
│   │       │   └── com/example/inovimap/
│   │       │       ├── HiltApplication.kt
│   │       │       ├── MainActivity.kt
│   │       │       │
│   │       │       ├── datasource/
│   │       │       │   ├── LoginDataSource.kt
│   │       │       │   └── LoginDataSourceImpl.kt
│   │       │       │
│   │       │       ├── domain/
│   │       │       │   ├── server/
│   │       │       │   │   └── models/
│   │       │       │   │       ├── LoginResponse.kt  (o .class en build)
│   │       │       │   │       └── UserInfo.kt
│   │       │       │   └── login/
│   │       │       │       └── usecases/
│   │       │       │           ├── GetServerResponse.kt
│   │       │       │           └── ValidateEmail.kt
│   │       │       │
│   │       │       ├── feature/
│   │       │       │   ├── login/
│   │       │       │   │   ├── LoginScreen.kt  (composable)
│   │       │       │   │   └── LoginState / LoginRoute / LoginViewModel (Kotlin)
│   │       │       │   └── map/
│   │       │       │       └── MapScreen.kt
│   │       │       │
│   │       │       ├── navigation/
│   │       │       │   └── NavigationHost.kt
│   │       │       │
│   │       │       ├── remote/
│   │       │       │   ├── ServerApi.kt     (interfaz Retrofit)
│   │       │       │   └── dtos/
│   │       │       │       ├── LoginRequestDto.kt
│   │       │       │       ├── LoginResponseDto.kt
│   │       │       │       ├── UserInfoDto.kt
│   │       │       │       └── ErrorResponseDto.kt
│   │       │       │
│   │       │       ├── repository/
│   │       │       │   ├── LoginRepository.kt
│   │       │       │   └── LoginRepositoryImpl.kt
│   │       │       │
│   │       │       └── ui/
│   │       │           └── theme/
│   │       │               ├── Color.kt (ColorKt.class in builds)
│   │       │               ├── Theme.kt (ThemeKt.class)
│   │       │               └── Type.kt (TypeKt.class)
│   │       │
│   │       └── res/
│   │           ├── drawable/
│   │           │   ├── ic_launcher_background.xml
│   │           │   └── ic_launcher_foreground.xml
│   │           ├── mipmap-*/ (ic_launcher.webp, etc.)
│   │           ├── values/
│   │           │   ├── colors.xml
│   │           │   ├── strings.xml
│   │           │   └── themes.xml
│   │           └── xml/
│   │               ├── backup_rules.xml
│   │               └── data_extraction_rules.xml
│   │
│   └── (others)
│
└── backend/
    ├── .gitignore
    ├── package.json
    ├── package-lock.json           
    └── server.js     
``` </pre>

## ⚙️ Instructions for running the project locally

### 🧱 1. Clone the repository

```bash
git clone https://github.com/daniospina9/InoviMap.git
cd InoviMap
```

### 🧱 2. Before running, you need a **Google Maps API Key**.
   Follow these steps to obtain it:

##### 🔑 How to get your API Key
   1. Go to [https://console.cloud.google.com/](https://console.cloud.google.com/).  
   2. Sign in with your Google account.
   3. Create a new project or select an existing one. 
   4. In the side menu, go to **APIs & Services → Library**.  
   5. Search for and enable the **Maps SDK for Android**. 
   6. Then, go to **Credentials → Create Credential → API Key**.  
   7. Copy the generated key (it will look something like `AIzaSyC-xxxxxxxxxxxxxxxx`).
   8. (Optional) Under “Restrict Key,” limit its use to Android only if you want greater security.

### 3. ⚙️ Add the key to the project

  1. You need to go to the "Project Files" of your local project and create a general-level "Resource Bundle" called "secret.properties." This way, your API_KEY will be protected.
  2. Inside "secret.properties" you must place the following line of code:
        API_KEY_MAPS=Tu_API_KEY

### 4. 🌐 Expose the server with Ngrok

    1. Go to (https://dashboard.ngrok.com) and create an account to get your free authtoken.
    2. Once you have your authtoken, enter the following command in the terminal:

```bash
npm install g ngrok
```

and then this
    
```bash
ngrok config add authtoken YOUR_AUTHTOKEN
ngrok http 3000
```

    3. At this point, your server should already be running, so go to the terminal response and copy the public URL, which is in the Forwarding section.
    4. Once you have your URL, go to the "ServeModule" module in the project and paste this URL into the Retrofit injection:
    
```bash
@Singleton
@Provides
fun provideRetrofit(): Retrofit = Retrofit.Builder()
.baseUrl("Tu_URL")
.addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
.build()
```

### 5. 🌐 Launch the server

Okay, with this, you're ready to get your local server up and running, but remember that you must first have node.js installed on your computer. If you already have it, go to the terminal in the project root and run the following commands:

```bash
cd backend
node server.js
```

And with this you can run the application normally.


### 6. Users already created by default

In the project's server.js, there are two default users configured to verify a successful login for existing users. However, if we add a new user, it will be created and stored on our server. New users are assigned a default location when they navigate to the map. This location appears with a marker within the default city, which in this case is Bogotá. Below are the two existing users:

- email: 'danielos@gmail.com', password: '123456', latitude: 48.8566, longitude: 2.3522
- email: 'androidev@hotmail.com', password: 'new_project', latitude: 4.6097, longitude: -74.0817

If you want to have a little fun, try entering the username "danielos@gmail.com" to see how the default setting appears in France.

## 🛠️ Technologies Used
- **Kotlin** → Main development language.  
- **Android SDK** → Mobile development framework.  
- **Coroutines** → Asynchronous and reactive programming.
- **Dagger Hilt** → Dependency Injection
- **Retrofit** → REST API client for fetching exchange rates.  
- **ViewModel (Jetpack)** → Lifecycle-aware state management.
- **NavigationHost** → Navigation between Screens.
- **Clean Architecture + MVVM** → Clear separation of concerns and modular design.
- **Google Maps SDK for Android (Compose)** → Interactive map with marker and camera control.
- **Node.js + Express.js** → Simple REST API backend for authentication and coordinate response.
- **Ngrok** → Tunneling tool for testing backend connectivity from Android devices.




















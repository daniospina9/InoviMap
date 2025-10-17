# 📱 InoviMap

**Prueba de Desarrollo Móvil — Inovisec**

Este proyecto cumple los requerimientos de la prueba técnica para desarrollar un **formulario de inicio de sesión funcional** con Kotlin y Jetpack Compose, que tras un login exitoso muestra un **mapa de Google Maps** centrado en una ubicación predefinida.

---

## 🧩 Objetivo General

- Implementar un formulario de inicio de sesión moderno y funcional.
- Validar las credenciales mediante un backend en Node.js (Express).
- Mostrar un mapa interactivo con la API de Google Maps tras un inicio de sesión exitoso.

---

## 🧩 Código fuente

<pre> ```text
InoviMap/
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties          # (contiene MAPS_API_KEY u otras props locales)
├── secret.properties
├── settings.gradle.kts
├── estructura_proyecto.txt   # archivo que generaste con tree. :contentReference[oaicite:1]{index=1}
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

## ⚙️ Instrucciones para ejecutar el proyecto localmente

### 🧱 1. Clonar el repositorio

```bash
git clone https://github.com/daniospina9/InoviMap.git
cd InoviMap
```

### 🧱 2. Antes de ejecutar, necesitas una **API Key de Google Maps**.  
   Sigue estos pasos para obtenerla:

##### 🔑 Cómo obtener tu API Key
   1. Ve a [https://console.cloud.google.com/](https://console.cloud.google.com/).  
   2. Inicia sesión con tu cuenta de Google.  
   3. Crea un nuevo proyecto o selecciona uno existente.  
   4. En el menú lateral, entra a **API y servicios → Biblioteca**.  
   5. Busca y habilita la **"Maps SDK for Android"**.  
   6. Luego, ve a **Credenciales → Crear credencial → Clave de API**.  
   7. Copia la clave generada (se verá algo como `AIzaSyC-xxxxxxxxxxxxxxxx`).  
   8. (Opcional) En “Restringir clave”, limita su uso solo para Android si deseas mayor seguridad.

### 3. ⚙️ Agregar la clave al proyecto

  1. Tienes que ir al "Project Files" de tu proyecto local y allí tienes que crear un "Resource Bundle" a nivel general llamado "secret.properties". De esta manera tu API_KEY va a estar protegida
  2. dentro de "secret.properties" debes colocar la siguiente línea  de código:
        API_KEY_MAPS=Tu_API_KEY

### 4. 🌐 Exponer el servidor con Ngrok

    1. Ve a (https://dashboard.ngrok.com) y crea una cuenta para obtenmer gratis tu authtoken
    2. una vez tengas tu authtoken pon el siguiente comando en la terminal:
    
```bash
ngrok config add authtoken YOUR_AUTHTOKEN
ngrok http 3000
```

    3. En este punto tu servidor ya debería estar lanzado, así que tienes que ir a la respuesta de la terminal y copiar la URL pública, que está en la sección de Forwarding
    4. Una vez tengas tu URL, debes dirigirte di (de inyección de dependencias) dentro del proyecto al módulo "ServeModule" y pegar esta URL en la inyección de Retrofit:
    
```bash
@Singleton
@Provides
fun provideRetrofit(): Retrofit = Retrofit.Builder()
.baseUrl("Tu_URL")
.addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
.build()
```

### 5. 🌐 Lanzar el servidor

Bien, con esto ya estás listo para poner a funcionar el servidor local, pero recuerda que antes, debes tener instalado node.js en tu computador. Si ya lo tienes, debes ir a la terminal en la raíz del proyecto y correr los siguintes comandos:

    ```bash
cd backend
node server.js
```

Y listo ya con esto puedes correr la aplicación normalmente


### 6. Usuarios ya creados por defecto

En el server.js del proyecto hay 2 usuarios configurados por defecto, esto con el objeivo de que se compruebe un login exitoso de usuarios existentes, pero si colocásemos un usuario nuevo, este se va a crear y se va a almacenar dentro de nuestro servidor. A los nuevos usuarios se les establece una ubicación por defecto al navegar al mapa, la cual aparece con un marcador dentro de la ciudad configurada por defecto que en este caso es Bogotá. A continuación los 2 usuarios ya existentes:

- email: 'danielos@gmail.com', password: '123456', latitude: 48.8566, longitude: 2.3522
- email: 'androidev@hotmail.com', password: 'new_project', latitude: 4.6097, longitude: -74.0817

Si te quieres divertir un poco, prueba a poner el ususario "danielos@gmail.com" para que veas cómo su configuración por defecto aparece en Francia











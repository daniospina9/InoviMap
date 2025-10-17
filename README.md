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

## ⚙️ Instrucciones para ejecutar el proyecto localmente

### 🧱 1. Clonar el repositorio

```bash
git clone https://github.com/daniospina9/InoviMap.git
cd InoviMap

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
  3. 

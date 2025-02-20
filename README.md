# 📚 Gestión de Libros y Autores

Este proyecto es una aplicación de escritorio para la gestión de libros y autores, desarrollada en Java utilizando SWT para la interfaz gráfica y JPA para la persistencia de datos.

## ✨ Características

- **📖 Gestión de Libros**: Permite añadir, editar, eliminar y buscar libros por título.
- **👨‍🏫 Gestión de Autores**: Permite añadir, editar, eliminar y buscar autores por nombre.
- **🔗 Relación Libro-Autor**: Al añadir un libro, se puede especificar el ID del autor correspondiente.

## 🛠️ Requisitos

- Java 11 o superior
- Gradle
- Base de datos compatible con JPA

## 🚀 Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/kellyss1/grupal_JPA_SWT.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd grupal_JPA_SWT
    ```
3. Compila el proyecto con Gradle:
    ```sh
    gradle build
    ```

## ▶️ Ejecución

Para ejecutar la aplicación, utiliza el siguiente comando:
```sh
gradle run
```
## 📂 Estructura del Proyecto

- `src/main/java/programacion`: Contiene el código fuente de la aplicación.
    - `db`: Clases de entidad JPA.
    - `repositorio/interfaces`: Interfaces de los repositorios JPA.
    - `ui`: Clases de la interfaz de usuario con SWT.
- `src/main/resources`: Archivos de configuración y recursos.

## 📋 Uso

### 📚 Gestión de Libros

1. **Añadir Libro**: Haz clic en el botón "Añadir" y completa el formulario.
2. **Editar Libro**: Selecciona un libro de la lista y haz clic en "Editar".
3. **Eliminar Libro**: Selecciona un libro de la lista y haz clic en "Eliminar".
4. **Buscar Libro**: Ingresa el título del libro en el campo de búsqueda y haz clic en "Buscar".

### 👨‍🏫 Gestión de Autores

1. **Añadir Autor**: Haz clic en el botón "Añadir" y completa el formulario.
2. **Editar Autor**: Selecciona un autor de la lista y haz clic en "Editar".
3. **Eliminar Autor**: Selecciona un autor de la lista y haz clic en "Eliminar".
4. **Buscar Autor**: Ingresa el nombre del autor en el campo de búsqueda y haz clic en "Buscar".

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request para discutir cualquier cambio que desees realizar.

## 📄 Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
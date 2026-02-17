En el frontend hemos tenido que hacer algo un poco más complejo:

Usar una imagen de Node para poder instalar dependencias y generar el build.

Copiar el proyecto React al contenedor.

Ejecutar npm install.

Ejecutar npm run build para generar la carpeta final de producción.

Después hemos usado una segunda imagen basada en Nginx.

Hemos copiado el build generado dentro de la carpeta que usa Nginx para servir archivos estáticos.

Hemos copiado también nuestro nginx.conf personalizado.

Hemos expuesto el puerto 80.

En el Dockerfile del backend

En el Dockerfile del backend hemos tenido que configurar varias cosas importantes para que Spring Boot funcione dentro de un contenedor:

Primero hemos elegido una imagen base de Java compatible con nuestra versión del proyecto (Java 17).

Hemos definido un WORKDIR para indicar en qué carpeta del contenedor se va a trabajar.

Hemos copiado el archivo .jar generado por Spring (que se crea al hacer mvn clean package).

Hemos expuesto el puerto 8080, que es el puerto donde corre el backend.

Hemos configurado el ENTRYPOINT para que cuando el contenedor arranque, ejecute el .jar.
En el nginx.conf

En el archivo nginx.conf hemos tenido que configurar dos cosas clave:

1. Servir el frontend

Configurar el servidor para que escuche en el puerto 80.

Indicar la carpeta donde están los archivos del build.

Añadir try_files para que funcionen las rutas de React Router.

Esto es necesario porque React es una SPA (Single Page Application).

2. Configurar el proxy al backend

También hemos tenido que:

Crear un location /api.

Configurar un proxy_pass apuntando al servicio del backend.

Usar el nombre del servicio definido en docker-compose como host.

En el docker-compose.yml

Aquí es donde hemos conectado todo.

Hemos tenido que:

Definir el servicio backend y decirle que se construya desde su carpeta.

Definir el servicio frontend.

Mapear los puertos (8080 y 80).

Añadir depends_on para que el frontend espere al backend.

Dejar que Docker cree una red interna automáticamente.
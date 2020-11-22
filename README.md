# Despaching
Despaching es una aplicación para la gestión de citas entre alumnos y profesores en los despachos.

## Version 0.1
En esta version 0.1 si haces click en el boton de "Entrar" entras como alumno y si le das a "Olvidaste tu coontrasena?" entras como profesor.

## Version 0.5
En esta version 0.5 para iniciar sesión se requiere un usuario y contraseña, tanto si se es alumno como profesor, ya que el sistema detectará automaticamente el tipo de usuario y lo tratara acorde con este. Actualmente el botón de recuperar contraseña no se encuentra operativo, para la proxima entrega lo estará.

Las credenciales para poder acceder se encuntran en el archivo "credenciales.txt" adjunto a la entrega.

En esta entrega el botón para recuperar la contraseña no tiene ninguna funcionalidad.

### Alumno
En esta pantalla se le muestra una lista con sus profesores, los cuales han sido obtenidos de la base de datos. Pudiendo seleccionar uno de ellos para poder acceder a la cola virtual de este. Actualmente, la información de la cola, hay que actualizarla manualmente, en un futuro se agregara un thread que la ira actualizandola en segundo plano. Por tanto el alumno, puede ver cuantos alumnos hay en la cola de un profesor, si esta está abierta. A su vez, podrá ingresar o salir de ella si el alumno lo desea. Si el alumno ya se encuentra en la cola, el sistema le mostrará su posición.

La funcionalidad de "Reservar" se agregará en la proxima entrega.

### Profesor
En esta pantalla se le muestra a mano izquierda el horario de el día actual, salvo los findes de semana, que se le mostrará el de los Lunes. En este horario divido por franjas de una hora, se representará de color rojo si en esa franja el profesor está ocupado 50 minutos o más. En verde, si no hay ninguna actividad en dicha franja. En amarillo si hay alguna actividad que abarque 50 minutos o menos.

A mano derecha se encuentra la cola virual, esta por defecto se encuentra cerrada, pero el profesor con un solo click podrá abrirla para empezar a aceptar alumnos. Dichos alumnos aparecerán en orden descendente, siendo el de más arriba el primero en apuntarse. El profesor trás atender a un alumno, lo podrá borrar de esta. A su vez en cualquier momento, el profesor podrá cerrar la cola, cuya accion deberá confirmar, ya que cerrarla expulsrá a todos los alumnos de su cola.

## Objetivo
El objetivo de despaching es mejorar la organización de las reuniones con profesores y permitir una mejor gestión del tiempo tanto de profesores como de alumnos.

## Autores
Esta aplicación está realizada por Raúl, Félix, Juan Antonio y Javier como parte de la asignatura de Ingeniería del Software en la Universidad Pontificia de Comillas.
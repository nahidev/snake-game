# snake-game

## Características

- **Juego clásico de Snake**: Controla la serpiente con las teclas de flecha y devora las manzanas.
- **Manzanas doradas**: Ocasionalmente, aparecerán manzanas doradas que aumentarán tu tamaño en 2 partes y tendrán el valor de 2 manzanas rojas. 
- **Niveles de dificultad**: Mensajes de nivel basados en tu puntuaciíon:
  - 0-10: "You have reached the Easy Level. Try again!"
  - 11-20: "You have reached the Medium Level. Not bad!"
  - 21-49: "You have reached Hard Level. Congrats!"
  - 50+: "You have reached the Legendary Level" (en color dorado)
- **Incremento de velocidad**: La velocidad del juego aumenta cada 10 manzanas comidas.

## Requisitos

- **Java**: Asegúrate de tener instalado JDK 8 o superior.
- **Eclipse IDE**: Necesitarás Eclipse para importar y ejecutar el proyecto.

## Instrucciones

### Configuración del entorno

1. **Clonar el repositorio.**

2. **Abrir Eclipse**: Inicia Eclipse IDE.

3. **Importar el proyecto**:
    - Ve a `File` > `Import`.
    - Selecciona `Existing Projects into Workspace` y haz clic en `Next`.
    - Navega hasta la ubicación del repositorio clonado y selecciona la carpeta del proyecto.
    - Haz clic en `Finish`.

### Ejecutar el juego
#### Opción 1
1. Descargar el ejecutable Snake.jar.
2. Hacer clic en el archivo y abrir.

#### Opción 2
1. En el Explorador de proyectos, navega hasta `src/snake`.
2. Haz clic derecho en `SnakeGame.java` y selecciona `Run As` > `Java Application` o `Run SnakeGame`.

El panel del juego Snake se abrirá y podrás comenzar a jugar.

## Controles del juego

- **Flecha Arriba**: Mover hacia arriba.
- **Flecha Abajo**: Mover hacia abajo.
- **Flecha Izquierda**: Mover hacia la izquierda.
- **Flecha Derecha**: Mover hacia la derecha.
- **Game Over**: El juego terminará cuando la serpiente se muerda así misma o colisione con la pared.

1. Diferencia entre @{...} y @={...}
   @{...}: Los datos viajan en un solo sentido, del Modelo hacia la Vista.

@={...}: Los datos viajan en ambos sentidos. La vista lee el valor inicial del modelo y, cuando el usuario interactúa con ella

2. Recorrido paso a paso: desde pulsar Calcular hasta ver 42

   1.Clic en Vista: El usuario pulsa el botón btn_calc. El atributo android:onClick="@{() -> controlador.calcular()}" avisa al evento y 
   llama al método calcular() del objeto controlador.
    2.Delegación en el Controlador: EdadCaninaControlador.calcular() recibe la recibe ysin hacer ninguna validación ni tocar vistas, pasando la acción llamando a modelo.calcular().

   3.Procesamiento y cálculo en el Modelo: Dentro de EdadCaninaModelo.calcular(): 
    -Limpia espacios y comprueba que no esté vacío. 
    -Convierte el texto a entero y valida que esté entre edad mínima y máxima.
    -Realiza la operación matemática: edadHumana = 6 * 7.   Cambia el estado interno: validacion = Validacion.OK.   
   4.Notificación del Modelo a DataBinding: Justo antes de terminar calcular(), el modelo ejecuta notifyChange(), avisando al sistema de DataBinding de Android de que sus propiedades @Bindable han cambiado.
3. Cambiar el factor a 6 en vez de 7
   En el archivo EdadCaninaModelo.java, modificarías únicamente el valor de la constante:
public static final int FACTOR_CANINO = 6;.Porque   Al haber definido factor_canino como una constante en lugar de escribir el número mágico 7 disperso por el código, cualquier cálculo que dependa de ese factor pone el valor actualizado automáticamente desde ese único lugar.

**RETO REALIZADO: Botón inteligente**

Bastante sencillo, se solucionaba agregando un atributo más al xml del btn_guardar:
android:enabled="@{!modelo.edadTexto.trim().isEmpty()}"
modelo avisa en cada tecla, y la expresión se recalcula sola.
con la condición .trim().isEmpty(), El botón queda activo solo si hay algo escrito.


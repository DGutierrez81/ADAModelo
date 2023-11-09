package Vista
 import Modelo.*

class Vista {

    /**
     * Representa la pantalla principal de la aplicación por consola.
     * @return devuelve un entero que indica la opción deseada.
     */
    fun inicio(): Int?{
        println("Elija que opción desea:\n" +
                "1. Ingresar datos\n" +
                "2. Consultar todos los datos\n" +
                "3. Consultar un dato\n" +
                "4. Actualizar dato\n" +
                "5. Eliminar dato\n" +
                "0. Salir")

        return readln().toIntOrNull()
    }

    /**
     * Imprime un mensaje
     * @param mensaje recibe la confirmación o el error en la conexión de la BBDD.
     */
    fun ConectarBBDD(mensaje: String){
        println(mensaje)
    }

    /**
     * Permite la inserción de datos
     * @return Devuelve un objeto de la clase producto.
     */
    fun IngresarDatos(): Producto{
        println("Escriba el id del producto: ")
        val id = readln().toInt()
        println("Escriba el nombre del producto: ")
        val nombre = readln()
        println("Escriba el precio del producto: ")
        val precio = readln().toFloat()
        return Producto(id, nombre, precio)
    }

    /**
     * Muestra toda la información referente a la tabla.
     * @param mensaje recibe toda la información del contenido de la tabla.
     */
    fun ConsultarDatos(mensaje: MutableList<Producto>?){
        for(i in mensaje?: mutableListOf()){
            println(i)
            }
        println("\n  \n  ")
    }

    /**
     * Muestra la consulta de una dato específico.
     * @param mensaje recibe la información del dato a mostrar.
     */
    fun ConsultarDatoEspecifico(mensaje: String){
        println(mensaje)
        println("\n  \n  ")
    }

    /**
     * Solicita la información del identificador del producto.
     * @return Devuelve un String con la información solicitada.
     */
    fun ObtenerId(): String{
        println("Escriba el identificador del producto a buscar: ")
        val id = readln()
        return id
    }


    /**
     * Muestra los mensajes de desconexión.
     * @param mensaje Recibe el mensaje de conformidad o de error.
     */
    fun salir(mensaje: String){
        println(mensaje)
        println("Fin de la aplicación.")
    }
}
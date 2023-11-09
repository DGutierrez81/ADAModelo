package Vista
 import Modelo.*

class Vista {

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

    fun ConectarBBDD(mensaje: String){
        println(mensaje)
    }
    fun IngresarDatos(): Producto{
        println("Escriba el id del producto: ")
        val id = readln().toInt()
        println("Escriba el nombre del producto: ")
        val nombre = readln()
        println("Escriba el precio del producto: ")
        val precio = readln().toFloat()
        return Producto(id, nombre, precio)
    }

    fun ConsultarDatos(mensaje: MutableList<Producto>?){
        for(i in mensaje?: mutableListOf()){
            println(i)
            }
        println("\n  \n  ")
    }

    fun ConsultarDatoEspecifico(mensaje: String){
        println(mensaje)
        println("\n  \n  ")
    }
    fun ObtenerId(): String{
        println("Escriba el identificador del producto a buscar: ")
        val id = readln()
        return id
    }


    fun salir(mensaje: String){
        println(mensaje)
        println("Fin de la aplicación.")
    }
}
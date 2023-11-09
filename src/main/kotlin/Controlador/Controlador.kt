package Controlador
import Vista.*
import Modelo.*
class Controlador(private val vista: Vista, private val gestor: GestorBBDD) {

    /**
     * Realiza todas conexiones entre el GestorBBDD y la vista
     */
    fun Iniciar(){
        vista.ConectarBBDD(gestor.ConectarBBDD())
        var estado = true
        while(estado){
            when(vista.inicio()){
                // Opción que manda la desconexión de la BBDD.
                0 -> {
                    vista.salir(gestor.desconectarBBDD())
                    estado = false
                }
                1 -> {
                    // Opción que permite la isercción de los datos.
                    val producto = vista.IngresarDatos()
                    gestor.Insertar(producto.id, producto.nombre, producto.precio)
                }
                // Opción que permite la consulta global de los datos.
                2 -> {
                   vista.ConsultarDatos(gestor.SelecTodo())
                }
                // Opción que permite realizar una consulta específica.
                3 -> {
                    val id = vista.ObtenerId()
                    val consulta = gestor.ConsultaEspecifica(id)
                    vista.ConsultarDatoEspecifico(consulta)
                }
                // Opción modificar los datos.
                4 -> {
                    val producto = vista.IngresarDatos()
                    val mensaje = gestor.ModidificarDatos(producto.id, producto.nombre, producto.precio)
                    vista.ConsultarDatoEspecifico(mensaje)
                }
                // Opción que permite eliminar los datos.
                5 -> {
                    val id = vista.ObtenerId()
                    val mensaje = gestor.BorrarFila(id)
                    vista.ConsultarDatoEspecifico(mensaje)
                }
            }
        }
    }
}
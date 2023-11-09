package Controlador
import Vista.*
import Modelo.*
class Controlador(private val vista: Vista, private val gestor: GestorBBDD) {

    fun Iniciar(){
        vista.ConectarBBDD(gestor.ConectarBBDD())
        var estado = true
        while(estado){
            when(vista.inicio()){
                0 -> {
                    vista.salir(gestor.desconectarBBDD())
                    estado = false
                }
                1 -> {

                    val producto = vista.IngresarDatos()
                    gestor.Insertar(producto.id, producto.nombre, producto.precio)
                }
                2 -> {
                   vista.ConsultarDatos(gestor.SelecTodo())
                }
                3 -> {
                    val id = vista.ObtenerId()
                    val consulta = gestor.ConsultaEspecifica(id)
                    vista.ConsultarDatoEspecifico(consulta)
                }
                4 -> {
                    val producto = vista.IngresarDatos()
                    val mensaje = gestor.ModidificarDatos(producto.id, producto.nombre, producto.precio)
                    vista.ConsultarDatoEspecifico(mensaje)
                }
                5 -> {
                    val id = vista.ObtenerId()
                    val mensaje = gestor.BorrarFila(id)
                    vista.ConsultarDatoEspecifico(mensaje)
                }
            }
        }
    }
}
import Modelo.*
import Vista.*
import Controlador.*

fun main(args: Array<String>) {
    val gestor = GestorBBDD()
    val vista = Vista()
    /*
    gestor.ConectarBBDD()
    gestor.CrearTabla()
    gestor.Insertar(1, "Nescafe", 4.5f)
    gestor.Insertar(2,"palangana", 6f)
    gestor.desconectarBBDD()

     */




    val controlador = Controlador(vista, gestor)

    controlador.Iniciar()



}
package Modelo

import java.lang.Exception
import java.nio.channels.SelectableChannel
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

class GestorBBDD {
    private var url: String? = null
    private var username: String? = null
    private var password: String? = null
    private var connection: Connection? = null
    private var statement: Statement? = null



    fun ConectarBBDD(): String{
        return try{
            url = "jdbc:oracle:thin:@localhost:1521:XE"
            username = "nomina"
            password = "nomina"
            connection = DriverManager.getConnection(url, username, password)
            "Conexión realizada a la BBDD con éxito.\nBienvenido a la BBDD.\n   \n    "

        }catch (e: SQLException) {
            "Error en la conexión: ${e.message}"
        } catch (e: ClassNotFoundException) {
            "No se encontró el driver JDBC: ${e.message}"
        }
    }


    fun CrearTabla(){
        try{
            statement = connection!!.createStatement()
            val statemnt2 = connection!!.createStatement()
            val comprobar = "DROP TABLE Producto CASCADE CONSTRAINTS"
            val crearTabla = Sentencia.crearTabla

            statement!!.execute(comprobar)
            statemnt2!!.execute(crearTabla)

        } catch (e: SQLException) {
            println("Error en la conexión: ${e.message}")
        } catch (e: ClassNotFoundException) {
            println("No se encontró el driver JDBC: ${e.message}")
        }
        catch (e: NullPointerException) {
            println("No se inició la BBDD: ${e.message}")
        }
    }

    fun ModidificarDatos(id: Int, nombre: String, precio: Float): String{
        var actualizar = 0
        return try{
            val producto = connection!!.prepareStatement(Sentencia.modficaNombre)
            producto.setString(1, nombre)
            producto.setFloat(2, precio)
            producto.setInt(3, id)

            actualizar = producto.executeUpdate()

            if (actualizar > 0) {
                 "El registro ha sido actualizado exitosamente."
            } else {
                 "No se ha actualizado ningún registro."
            }
        } catch (e: SQLException) {
             "Error en la conexión: ${e.message}"
        } catch (e: ClassNotFoundException) {
             "No se encontró el driver JDBC: ${e.message}"
        }
    }

    fun BorrarFila(id: String): String{
        return try{
            val producto = connection!!.prepareStatement(Sentencia.borrarFila)
            producto.setString(1, id)

            val columna = producto.executeUpdate()

            if(columna > 0){
                "Registro borrado correctamente."
            }else{
                "No existe el registro."
            }

        }catch (e: SQLException) {
             "Error en la conexión: ${e.message}"
        }
    }

    fun Insertar(id: Int, nombre: String, precio: Float){
        try{
            val insertar = connection!!.prepareStatement(Sentencia.insertar)
            insertar.setInt(1, id)
            insertar.setString(2, nombre)
            insertar.setFloat(3, precio)
            insertar.executeUpdate()
        }catch (e: SQLException) {
            println("Error al ingresar el registro: ${e.message}")
        } catch (e: ClassNotFoundException) {
            println("No se encontró el driver JDBC: ${e.message}")
        }
    }

    fun SelecTodo(): MutableList<Producto>?{
        return try{
            val resultado = mutableListOf<Producto>()
            statement = connection!!.createStatement()
            val consulta = statement!!.executeQuery(Sentencia.selectTodo)
            val result = mutableListOf<Map<String, Any>>()
            while(consulta.next()){
                val id = consulta.getInt("Id")
                val nombre = consulta.getString("Nombre")
                val precio = consulta.getFloat("Precio")
                resultado.add(Producto(id, nombre, precio))

            }
            if(resultado.isEmpty()) throw Exception()  else resultado
        }catch (e: Exception) {
            null
        }
    }

    fun ConsultaEspecifica(id: String): String{
        return try{
            var resultado = ""

            val consulta = connection!!.prepareStatement(Sentencia.selectDato)
            consulta.setString(1, id)
            val state = consulta.executeQuery()
            while(state.next()){
                val _id = state.getInt("Id")
                val nombre = state.getString("Nombre")
                val precio = state.getFloat("Precio")
                val producto = Producto(_id, nombre, precio)
                resultado = producto.toString()
            }
            if(resultado.isEmpty()) throw Exception()  else resultado
        }catch (e: Exception) {
            return "No se encuentra en la tabla"
        }
    }

    fun desconectarBBDD(): String{
        var resultado = ""

            connection!!.close()
            resultado = "Desconectado correctamente de la BBDD."

        return resultado
    }
}


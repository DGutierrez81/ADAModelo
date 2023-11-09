package Modelo

data class Producto(val id: Int, val nombre: String, var precio: Float) {

    override fun toString(): String {
        return "El producto con id: $id es $nombre con precio $precio€"
    }
}
package com.ap.cartly.model

class Carrito(
    val usuario: User
){

    //Lista interna protegida

    private val elementos = mutableListOf<TransactionItem>()

    fun agregarProducto(
        producto: Product,
        cantidad: Int
    ): Boolean{
        //Product valida la cantidad y descuenta el stock

        if (!producto.descontarStock(cantidad)){
            return false
        }

        //Busca si el producto ya esta dentro del carrito
        val indiceExistente = elementos.indexOfFirst{ item ->
            item.product.id.equals(
                producto.id,
                ignoreCase = true
            )
        }

        if (indiceExistente >= 0){
            val itemActual = elementos[indiceExistente]

            elementos[indiceExistente] = itemActual.copy(
                quantity = itemActual.quantity + cantidad
            )
        } else {
            elementos.add(
                TransactionItem(
                    product = producto,
                    quantity =  cantidad
                )
            )
        }
        return true
    }

    fun estaVacio(): Boolean{
        return  elementos.isEmpty()
    }

    fun obtenerElementos(): List<TransactionItem> {
        // Devuelve una copia para proteger la lista original
        return elementos.toList()
    }

    fun calcularSubtotal(): Double {
        return elementos.sumOf { item ->
            item.product.price * item.quantity
        }
    }

    fun cantidadDeProductos(): Int {
        return elementos.sumOf { item ->
            item.quantity
        }
    }
}
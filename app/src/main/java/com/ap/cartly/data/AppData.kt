package com.ap.cartly.data

import com.ap.cartly.model.MembershipLevel
import com.ap.cartly.model.Product
import com.ap.cartly.model.User

object AppData {

    val usuarios = listOf(
        User(
            id = "U001",
            name = "Emma Johnson",
            email = "emma.johnson@cartly.com",
            membershipLevel = MembershipLevel.BASIC
        ),
        User(
            id = "U002",
            name = "Liam Anderson",
            email = "liam.anderson@cartly.com",
            membershipLevel = MembershipLevel.SILVER
        ),
        User(
            id = "U003",
            name = "Sophie Williams",
            email = "sophie.williams@cartly.com",
            membershipLevel = MembershipLevel.GOLD
        ),
        User(
            id = "U004",
            name = "Noah Thompson",
            email = "noah.thompson@cartly.com",
            membershipLevel = MembershipLevel.BASIC
        ),
        User(
            id = "U005",
            name = "Olivia Brown",
            email = "olivia.brown@cartly.com",
            membershipLevel = MembershipLevel.SILVER
        ),
        User(
            id = "U006",
            name = "Lucas Martin",
            email = "lucas.martin@cartly.com",
            membershipLevel = MembershipLevel.GOLD
        )
    )

    // Usuario que aparecerá inicialmente en la pantalla de perfil.
    val usuarioActual: User = usuarios.first { usuario ->
        usuario.id == "U003"
    }

    val categorias = listOf(
        "Todos",
        "Computadoras",
        "Accesorios",
        "Almacenamiento",
        "Audio"
    )

    val productos = listOf(
        Product(
            id = "P001",
            name = "Laptop",
            price = 1200.00,
            stockDisponible = 5,
            category = "Computadoras",
            description = "Laptop moderna para estudiar, trabajar y realizar tareas de productividad.",
            specifications = listOf(
                "16 GB de memoria RAM",
                "Unidad SSD de 512 GB",
                "Pantalla Full HD"
            ),
            imageUrl = "https://images.unsplash.com/photo-1538503529202-7a0e79cbb6f6?auto=format&fit=crop&w=900&q=80"
        ),
        Product(
            id = "P002",
            name = "Mouse",
            price = 25.50,
            stockDisponible = 20,
            category = "Accesorios",
            description = "Mouse ergonómico con conexión inalámbrica y diseño compacto.",
            specifications = listOf(
            "Conexión inalámbrica",
            "Sensor óptico",
            "Diseño ergonómico"
            ),
            imageUrl = "https://images.unsplash.com/photo-1647755814392-fd071a3fcb4b?auto=format&fit=crop&w=900&q=80"
        ),
        Product(
            id = "P003",
            name = "Teclado",
            price = 45.90,
            stockDisponible = 15,
            category = "Accesorios",
            description = "Teclado de tamaño completo para trabajo, estudio y entretenimiento.",
            specifications = listOf(
            "Distribución en español",
            "Teclas multimedia",
            "Conexión USB"
            ),
            imageUrl = "https://images.unsplash.com/photo-1543271082-81b28dfc0551?auto=format&fit=crop&w=900&q=80"
        ),
        Product(
            id = "P004",
            name = "Monitor",
            price = 350.00,
            stockDisponible = 8,
            category = "Computadoras",
            description = "Monitor de alta definición con una imagen clara y colores intensos.",
            specifications = listOf(
            "Resolución Full HD",
            "Pantalla de 24 pulgadas",
            "Conexiones HDMI y DisplayPort"
            ),
            imageUrl = "https://images.unsplash.com/photo-1501780392773-287d506245a5?auto=format&fit=crop&w=900&q=80"
        ),
        Product(
            id = "P005",
            name = "Audífonos",
            price = 89.99,
            stockDisponible = 12,
            category = "Audio",
            description = "Audífonos cómodos con sonido envolvente para música y videollamadas.",
            specifications = listOf(
            "Micrófono incorporado",
            "Sonido estéreo",
            "Almohadillas acolchadas"
            ),
            imageUrl = "https://images.unsplash.com/photo-1520170350707-b2da59970118?auto=format&fit=crop&w=900&q=80"
        ),
        Product(
            id = "P006",
            name = "Cámara Web HD",
            price = 65.00,
            stockDisponible = 10,
            category = "Accesorios",
            description = "Cámara web de alta definición para reuniones, clases y transmisiones.",
            specifications = listOf(
            "Resolución HD",
            "Micrófono integrado",
            "Conexión USB"
            ),
            imageUrl = "https://images.unsplash.com/photo-1750975314977-374f2290db53?auto=format&fit=crop&w=900&q=80"
        ),
        Product(
            id = "P007",
            name = "Disco SSD 1 TB",
            price = 110.50,
            stockDisponible = 7,
            category = "Almacenamiento",
            description = "Unidad de almacenamiento rápida para mejorar el rendimiento del equipo.",
            specifications = listOf(
            "Capacidad de 1 TB",
            "Tecnología SSD",
            "Interfaz SATA"
            ),
            imageUrl = "https://images.unsplash.com/photo-1518547606470-00ac2ae882af?auto=format&fit=crop&w=900&q=80"
        ),
        Product(
            id = "P008",
            name = "Memoria USB 128 GB",
            price = 28.75,
            stockDisponible = 25,
            category = "Almacenamiento",
            description = "Memoria portátil para guardar documentos, imágenes y archivos personales.",
            specifications = listOf(
            "Capacidad de 128 GB",
            "Conexión USB 3.0",
            "Diseño compacto"
            ),
            imageUrl = "https://images.unsplash.com/photo-1585163218097-43d81b016202?auto=format&fit=crop&w=900&q=80"
        ),
        Product(
            id = "P009",
            name = "Parlante Portátil",
            price = 55.90,
            stockDisponible = 14,
            category = "Audio",
            description = "Parlante inalámbrico portátil con sonido claro y batería recargable.",
            specifications = listOf(
            "Conexión Bluetooth",
            "Batería recargable",
            "Diseño portátil"
            ),
            imageUrl = "https://images.unsplash.com/photo-1548949974-5a40c9a4de55?auto=format&fit=crop&w=900&q=80"
        ),
        Product(
            id = "P010",
            name = "Base Refrigerante",
            price = 32.40,
            stockDisponible = 18,
            category = "Accesorios",
            description = "Base diseñada para mejorar la ventilación y comodidad de una laptop.",
            specifications = listOf(
            "Ventiladores integrados",
            "Altura ajustable",
            "Alimentación mediante USB"
            ),
            imageUrl = "https://images.unsplash.com/photo-1658869402195-9f0ea192ecd5?auto=format&fit=crop&w=900&q=80"
        )
    )
}
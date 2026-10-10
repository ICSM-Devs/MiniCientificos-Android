package com.icsm.minicientificos_android.models;

import com.icsm.minicientificos_android.R;
import java.util.ArrayList;
import java.util.List;

public class ProductoCatalogData {

    public static List<Producto> getCatalog() {
        List<Producto> productos = new ArrayList<>();

        // 1. Peluches y Agendas (Agenda va 3ra)
        productos.add(new Producto("P001", "Amigurumi Grande", "Personaje principal tejido a mano con hilo de algodón hipoalergénico. Tamaño 30cm.", 149.90, "Peluches", 10, R.drawable.amiguru_grande, "Hecho a mano", "Coleccionable"));
        productos.add(new Producto("P002", "Amigurumi Pequeño", "Personaje tejido ideal para llevar en la mochila o como llavero grande. Tamaño 15cm.", 79.90, "Peluches", 15, R.drawable.amiguru_pequeno, null, "Coleccionable"));
        
        // 4. Papelería (Moviendo Agenda aquí para que salga después de los Amigurumis)
        productos.add(new Producto("S001", "Agenda", "Agenda escolar sin fechas con stickers, curiosidades científicas y espacio para notas de experimentos.", 39.90, "Papeleria", 50, R.drawable.agenda, null, "Educativo"));

        // 2. Accesorios
        productos.add(new Producto("A001", "Lámpara", "Lámpara de noche con motivos espaciales y científicos. Luz LED cálida recargable USB.", 54.90, "Accesorios", 20, R.drawable.lampara_pequena, null, "Ideal para regalar"));
        productos.add(new Producto("A002", "Neceser", "Estuche organizador impermeable con diseño de constelaciones para guardar útiles o experimentos.", 34.90, "Accesorios", 30, R.drawable.neceser, null, "Ideal para regalar"));
        productos.add(new Producto("A003", "Bolsa de Tocuyo", "Bolsa ecológica reutilizable y resistente, perfecta para compras o llevar materiales educativos.", 29.90, "Accesorios", 50, R.drawable.bolsa_de_tocuyo, "Eco", "Sostenible"));
        productos.add(new Producto("A004", "Brújula", "Brújula de bolsillo de alta precisión para exploraciones al aire libre y campamentos científicos.", 24.90, "Accesorios", 40, R.drawable.brujula, null, "Exploración"));
        productos.add(new Producto("A005", "Lupa", "Lupa de aumento 5x con mango ergonómico antideslizante para pequeños detectives de la naturaleza.", 21.90, "Accesorios", 60, R.drawable.lupa, "Nuevo", "Exploración"));
        productos.add(new Producto("A006", "Lentes de Protección", "Gafas de seguridad ajustables para realizar experimentos químicos en casa con total tranquilidad.", 19.90, "Accesorios", 100, R.drawable.lentes, null, "Exploración"));
        productos.add(new Producto("A007", "Llavero", "Llavero metálico esmaltado con la forma de nuestros personajes Mini Científicos.", 12.90, "Accesorios", 80, R.drawable.llavero, null, "Coleccionable"));

        // 3. Ropa
        productos.add(new Producto("R001", "Polo", "Polo 100% algodón orgánico con estampado científico. Disponible en tallas 4 a 14.", 44.90, "Ropa", 45, R.drawable.polo, null, "Unisex"));
        productos.add(new Producto("R002", "Gorro", "Gorro tipo bucket hat con protección UV y bordado de astronauta. Talla estándar para niños.", 32.90, "Ropa", 35, R.drawable.gorro, null, "Unisex"));

        // 4. Papelería (Continuación)
        productos.add(new Producto("S002", "Tabla Periódica", "Tabla periódica ilustrada y a todo color, material plastificado de alta durabilidad.", 29.90, "Papeleria", 100, R.drawable.tabla_periodica, null, "Educativo"));
        productos.add(new Producto("S003", "Credencial", "Gafete plástico oficial de Mini Científico. Incluye lanyard y espacio para el nombre del niño.", 16.90, "Papeleria", 150, R.drawable.credenciales, null, "Personalizable"));
        productos.add(new Producto("S004", "Miniposter", "Póster decorativo tamaño A3 con infografía del sistema solar. Ideal para el cuarto de los niños.", 14.90, "Papeleria", 80, R.drawable.miniposter_minicientifico, null, "Decorativo"));
        productos.add(new Producto("S005", "Tarjeta Motivacional", "Set de tarjetas con frases inspiradoras de científicos famosos para motivar el aprendizaje.", 12.90, "Papeleria", 200, R.drawable.tarjeta_motivacional, null, "Ideal para regalar"));
        productos.add(new Producto("S006", "Certificado", "Certificado de Participación Científica impreso en cartulina fina, ideal para premiar descubrimientos.", 9.90, "Papeleria", 300, R.drawable.certificado_minicientifico, null, "Personalizable"));

        return productos;
    }
}
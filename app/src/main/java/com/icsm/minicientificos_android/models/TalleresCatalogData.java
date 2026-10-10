package com.icsm.minicientificos_android.models;

import com.icsm.minicientificos_android.R;
import java.util.ArrayList;
import java.util.List;

public class TalleresCatalogData {
    public static List<Taller> getTalleres() {
        List<Taller> lista = new ArrayList<>();

        lista.add(new Taller(1, "Astronomía", "Ciencia", "Presencial/Virtual",
                "Viaja al espacio sin salir de casa",
                "Comprender el sistema solar y construir modelos a escala.",
                new String[]{"Constelaciones", "Cohetes de agua", "El sistema solar"},
                new String[]{"Telescopio didáctico", "Material impreso", "Kit cohete"},
                "Sábados 4:00 PM - 6:00 PM", "10 vacantes", 45.0, R.drawable.astronomia));

        lista.add(new Taller(2, "Bobina Tesla", "Tecnologia", "Híbrido",
                "Rayo y magnetismo seguro",
                "Aprender sobre magnetismo y armar una bobina tesla segura.",
                new String[]{"Electromagnetismo básico", "Armado de bobina", "Experimentos seguros"},
                new String[]{"Kit bobina tesla", "Lentes de seguridad"},
                "Viernes 4:00 PM - 6:00 PM", "5 vacantes", 45.0, R.drawable.bobina));

        lista.add(new Taller(3, "Arqueología", "Ciencia", "Presencial",
                "Descubre fósiles y reliquias",
                "Explorar como arqueólogos y extraer fósiles.",
                new String[]{"Fósiles de dinosaurios", "Herramientas de excavación", "Réplicas"},
                new String[]{"Bloque de yeso con fósil", "Kit de herramientas", "Guantes"},
                "Sábados 3:00 PM - 5:00 PM", "10 vacantes", 45.0, R.drawable.arqueologia));

        lista.add(new Taller(4, "Geología Divertida", "Ciencia", "Presencial",
                "Rocas mágicas y minerales",
                "Estudiar los minerales y reacciones volcánicas.",
                new String[]{"Formación de rocas", "Volcanes de reacción", "Cristales"},
                new String[]{"Muestras de minerales", "Kit volcán"},
                "Sábados 9:00 AM - 10:30 AM", "6 vacantes", 45.0, R.drawable.geologia));

        lista.add(new Taller(5, "Física Loca", "Ciencia", "Presencial/Virtual",
                "Cohetes y mezclas divertidas",
                "Aprender leyes físicas mediante fluidos y movimiento.",
                new String[]{"Fluidos no newtonianos", "Giroscopios", "Leyes del movimiento"},
                new String[]{"Materiales del hogar", "Bata de laboratorio"},
                "Miércoles 5:00 PM - 6:30 PM", "20 vacantes", 45.0, R.drawable.expe_fisi));

        lista.add(new Taller(6, "Inteligencia Artificial", "Robotica", "Virtual",
                "Aprende cómo piensan las máquinas",
                "Lógica de computación y programación por bloques.",
                new String[]{"Pensamiento lógico", "Programación en bloques", "Asistente virtual"},
                new String[]{"Acceso a plataforma", "Guía digital"},
                "Domingos 11:00 AM - 12:30 PM", "15 vacantes", 45.0, R.drawable.ia));

        lista.add(new Taller(7, "Microbiología", "Ciencia", "Presencial",
                "El mundo invisible al microscopio",
                "Uso de microscopios para observar microorganismos.",
                new String[]{"Uso del microscopio", "Células vegetales", "Bacterias buenas"},
                new String[]{"Microscopio (préstamo)", "Láminas", "Bata"},
                "Lunes 4:00 PM - 5:30 PM", "8 vacantes", 45.0, R.drawable.microbiologia));

        lista.add(new Taller(8, "Minería del Futuro", "Ecologia", "Híbrido",
                "Extracción limpia y sostenible",
                "Procesos de cristalización y minería amigable.",
                new String[]{"Cristales y sales", "Minería limpia", "Reciclaje de metales"},
                new String[]{"Kit de cristales", "Guía ilustrada"},
                "Sábados 4:00 PM - 5:30 PM", "15 vacantes", 45.0, R.drawable.mineria));

        lista.add(new Taller(9, "Reciclaje", "Ecologia", "Presencial",
                "Convierte residuos en inventos",
                "Transformar plásticos en obras de arte y juguetes.",
                new String[]{"Economía circular", "Eco-arte", "Organizadores útiles"},
                new String[]{"Pinturas", "Insumos artísticos"},
                "Domingos 10:00 AM - 11:30 AM", "12 vacantes", 45.0, R.drawable.reciclaje));

        lista.add(new Taller(10, "Mini Huerto", "Ecologia", "Presencial",
                "Siembra, cuida y cosecha",
                "Aprender sobre germinación y cuidado de plantas.",
                new String[]{"Plantado en macetas", "Compostaje infantil", "Cosecha"},
                new String[]{"Semillas", "Tierra viva", "Maceta"},
                "Sábados 10:00 AM - 12:00 PM", "8 vacantes", 45.0, R.drawable.compostaje));

        lista.add(new Taller(11, "Hidroponía", "Ecologia", "Presencial/Híbrido",
                "Plantas que flotan en nutrientes",
                "Cultivo de vegetales en agua sin necesidad de tierra.",
                new String[]{"Agricultura del futuro", "Sistema hidropónico", "Nutrientes"},
                new String[]{"Soporte hidropónico", "Muestras de plantas"},
                "Viernes 3:00 PM - 4:30 PM", "9 vacantes", 45.0, R.drawable.hidroponia));

        lista.add(new Taller(12, "Energías Renovables", "Tecnologia", "Presencial/Virtual",
                "Molinos y energía solar",
                "Armar mini turbinas y paneles funcionales.",
                new String[]{"Energía solar", "Energía eólica", "Electromagnetismo"},
                new String[]{"Panel solar de juguete", "Mini motor"},
                "Martes 4:00 PM - 5:30 PM", "7 vacantes", 45.0, R.drawable.energias_reno));

        return lista;
    }
}
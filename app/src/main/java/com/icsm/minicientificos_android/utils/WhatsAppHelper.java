package com.icsm.minicientificos_android.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import com.icsm.minicientificos_android.cart.CartItem;
import com.icsm.minicientificos_android.models.Taller;

import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;

public class WhatsAppHelper {

    private static final String WHATSAPP_NUMBER = "51916313634";

    public static void abrirInscripcionTaller(Context context, Taller taller) {
        String mensaje = "¡Hola Mini Científicos! 🔬 Deseo inscribirme en el taller de *" + taller.getTitulo() + "*.\n" +
                "Modalidad: " + taller.getModalidad() + "\n" +
                "Horario: " + taller.getHorario() + "\n" +
                "¿Me podrían brindar los medios de pago y requisitos?";
        enviarMensajeWhatsApp(context, mensaje);
    }

    public static void abrirPedidoCarrito(Context context, List<CartItem> items, double total, String notas) {
        StringBuilder sb = new StringBuilder();
        sb.append("¡Hola Mini Científicos! 🛒 Deseo realizar el siguiente pedido:\n\n");
        for (CartItem item : items) {
            sb.append("- ").append(item.getProducto().getNombre())
              .append(" x").append(item.getCantidad())
              .append(" (S/ ").append(String.format(Locale.US, "%.2f", item.getSubtotal())).append(")\n");
        }
        sb.append("\n*Total:* S/ ").append(String.format(Locale.US, "%.2f", total));
        if (notas != null && !notas.trim().isEmpty()) {
            sb.append("\n*Notas:* ").append(notas);
        }
        enviarMensajeWhatsApp(context, sb.toString());
    }

    public static void abrirConsultaContacto(Context context, String nombre, String email, String celular, String motivo, String mensajeLocal) {
        String mensaje = "🚀 *Transmisión Espacial de:* " + nombre + "\n" +
                "📧 *Email:* " + email + "\n" +
                "📱 *Celular:* " + celular + "\n" +
                "🎯 *Asunto:* " + motivo + "\n\n" +
                "📝 *Mensaje:* " + mensajeLocal;
        enviarMensajeWhatsApp(context, mensaje);
    }

    private static void enviarMensajeWhatsApp(Context context, String mensaje) {
        try {
            String url = "https://wa.me/" + WHATSAPP_NUMBER + "?text=" + URLEncoder.encode(mensaje, "UTF-8");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            
            boolean isWhatsappInstalled = isPackageInstalled(context, "com.whatsapp") || isPackageInstalled(context, "com.whatsapp.w4b");
            
            if (isWhatsappInstalled) {
                intent.setPackage(isPackageInstalled(context, "com.whatsapp") ? "com.whatsapp" : "com.whatsapp.w4b");
            }
            
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "No se pudo abrir WhatsApp", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private static boolean isPackageInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
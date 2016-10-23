package modelo;

import android.net.Uri;

/**
 * Created by ASUS on 20/10/2016.
 */
public class Contrato {

    interface ColumnasAlmacen {

        String IDWEB = "id";
        String RAZONSOCIAL = "razonsocial";
        String NIT = "nit";
        String DESCRIPCION = "descripcion";
        String DIRECCION = "direccion";
        String TELEFONO = "telefono";
        String CORREO = "correo";
        String POSICIONGPS = "posiciongps";
        String LOGO = "logo";
        String MARCADOR = "marcador";
        String REGISTRO = "registro";
        String MODIFICADOR = "modificador";
        String VISIBLE = "visible";
        String ACTIVO = "activo";
        String TAGS = "tags";
        String X = "x";
        String Y = "y";
    }

    // Autoridad del Content Provider
    public final static String AUTORIDAD = "com.feedhenry.armark";

    // Uri base
    public final static Uri URI_CONTENIDO_BASE = Uri.parse("content://" + AUTORIDAD);

    // String de USUARIOS
    public static class Armark_almacen implements ColumnasAlmacen {

        public static final Uri URI_CONTENIDO =
                URI_CONTENIDO_BASE.buildUpon().appendPath(RECURSO_ALMACEN).build();

        public final static String MIME_RECURSO =
                "vnd.android.cursor.item/vnd." + AUTORIDAD + "/" + RECURSO_ALMACEN;

        public final static String MIME_COLECCION =
                "vnd.android.cursor.dir/vnd." + AUTORIDAD + "/" + RECURSO_ALMACEN;


        /**
         * Construye una {@link Uri} para el {@link #} solicitado.
         */
        public static Uri construirUriUsuarios(String idTorneo) {
            return URI_CONTENIDO.buildUpon().appendPath(idTorneo).build();
        }

        /*public static String generarIdAlquiler() {
            return "A-" + UUID.randomUUID();
        }*/

        public static String obtenerIdAlmacen(Uri uri) {
            return uri.getLastPathSegment();
        }
    }

    // Recursosp
    public final static String RECURSO_ALMACEN = "almacen";


}

package modelo;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

public class MyContentProvider extends ContentProvider {

    // Comparador de URIs
    public static final UriMatcher uriMatcher;


    // Casos
    public static final int ALMACEN = 100;
    public static final int ALMACEN_ID = 101;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(Contrato.AUTORIDAD, "almacen", ALMACEN);
        uriMatcher.addURI(Contrato.AUTORIDAD, "almacen/*", ALMACEN_ID);
    }

    private BaseDatos bd;
    private ContentResolver resolver;
    SQLiteDatabase SQLDB;


    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int affected;
        switch (uriMatcher.match(uri)) {

            // Eliminar Almacenes
            case ALMACEN:
                affected = SQLDB.delete(BaseDatos.Tabla.ALMACEN,
                        selection, selectionArgs);

                break;
            case ALMACEN_ID:
                String idActividad = uri.getPathSegments().get(1);
                affected = SQLDB.delete(BaseDatos.Tabla.ALMACEN,
                        BaseColumns._ID + "=" + idActividad
                                + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""),
                        selectionArgs);
                break;



            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case ALMACEN:
                return Contrato.Armark_almacen.MIME_COLECCION;
            case ALMACEN_ID:
                return Contrato.Armark_almacen.MIME_RECURSO;
            default:
                throw new IllegalArgumentException("Tipo desconocido: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // inicio insercion de Almacen
        if(uriMatcher.match(uri)==ALMACEN) {
            ContentValues contentValues;
            if (values != null) {
                contentValues = new ContentValues(values);
            } else {
                contentValues = new ContentValues();
            }

            // Inserción de nueva fila
            SQLDB = bd.getWritableDatabase();
            long rowId = SQLDB.insert(BaseDatos.Tabla.ALMACEN,
                    null, contentValues);
            if (rowId > 0) {
                Uri uri_actividad =
                        ContentUris.withAppendedId(
                                Contrato.Armark_almacen.URI_CONTENIDO, rowId);
                getContext().getContentResolver().
                        notifyChange(uri_actividad, null);
                return uri_actividad;
            }
        } //  fin de insercione de Almacen


        // error de insercion
        throw new SQLException("Falla al insertar fila en : " + uri);

    }

    @Override
    public boolean onCreate() {
        bd = new BaseDatos(getContext());
        resolver = getContext().getContentResolver();
        SQLDB = bd.getWritableDatabase();
        return SQLDB != null && SQLDB.isOpen();
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = bd.getWritableDatabase();
        // Comparar Uri
        int match = uriMatcher.match(uri);

        Cursor c;

        switch (match) {

            // para consultar Torneos
            case ALMACEN:


                // Consultando todos los registros
                c = db.query(BaseDatos.Tabla.ALMACEN, projection,
                        selection, selectionArgs,
                        null, null, sortOrder);
                c.setNotificationUri(resolver, Contrato.Armark_almacen.URI_CONTENIDO);
                break;
            case ALMACEN_ID:
                // Consultando un solo registro basado en el Id del Uri
                String idTorneo = Contrato.Armark_almacen.obtenerIdAlmacen(uri);
                c = db.query(BaseDatos.Tabla.ALMACEN, projection,
                        BaseColumns._ID + "=" + "\'" + idTorneo + "\'"
                                + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""),
                        selectionArgs, null, null, sortOrder);
                c.setNotificationUri(resolver, uri);
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }
        return c;

        }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int affected;
        switch (uriMatcher.match(uri)) {
            case ALMACEN:
                affected = SQLDB.update(BaseDatos.Tabla.ALMACEN, values,
                        selection, selectionArgs);
                break;
            case ALMACEN_ID:
                String idActividad = uri.getPathSegments().get(1);
                affected = SQLDB.update(BaseDatos.Tabla.ALMACEN, values,
                        BaseColumns._ID + "=" + idActividad
                                + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""),
                        selectionArgs);
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;
    }
}

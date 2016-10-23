package modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by ASUS on 20/10/2016.
 */
public class BaseDatos extends SQLiteOpenHelper {

    private static final String name_bd = "Armark2";
    private static final int version = 1;

    public BaseDatos(Context context) {
        super(context, name_bd, null, version);
    }

    public interface Tabla{

        String ALMACEN = "almacen";
        String PROMOCIONES = "promociones";
        String PRODUCTOS = "productos";

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        CrearTablaAlmacen(db);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            db.execSQL("DROP TABLE IF EXISTS " + Tabla.ALMACEN);

        } catch (SQLiteException e) {
            // Manejo de excepciones
        }
        onCreate(db);
    }
    private void CrearTablaAlmacen(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE " + Tabla.ALMACEN + "("
                        + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + Contrato.Armark_almacen.IDWEB + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.RAZONSOCIAL + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.NIT + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.DESCRIPCION + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.DIRECCION + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.TELEFONO + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.CORREO + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.POSICIONGPS + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.LOGO + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.MARCADOR + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.REGISTRO + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.MODIFICADOR + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.VISIBLE + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.ACTIVO + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.TAGS + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.X + " TEXT NOT NULL,"
                        + Contrato.Armark_almacen.Y + " TEXT NOT NULL)");

        // REGISTRO INICIALES

        ContentValues values = new ContentValues();
        values.put(Contrato.Armark_almacen.IDWEB,"1");
        values.put(Contrato.Armark_almacen.RAZONSOCIAL,"El vivito");
        values.put(Contrato.Armark_almacen.NIT,"18425417-1");
        values.put(Contrato.Armark_almacen.DESCRIPCION,"Ingeniero Vivito");
        values.put(Contrato.Armark_almacen.DIRECCION,"CL80 #11b-35");
        values.put(Contrato.Armark_almacen.TELEFONO,"3107078");
        values.put(Contrato.Armark_almacen.CORREO,"culevivo@hotmail.com");
        values.put(Contrato.Armark_almacen.POSICIONGPS,"--");
        values.put(Contrato.Armark_almacen.LOGO,"http://torneofacil.serticiossp.co/imagenes/torneo2.jpg");
        values.put(Contrato.Armark_almacen.MARCADOR,"--");
        values.put(Contrato.Armark_almacen.REGISTRO,"--");
        values.put(Contrato.Armark_almacen.MODIFICADOR,"--");
        values.put(Contrato.Armark_almacen.VISIBLE,"--");
        values.put(Contrato.Armark_almacen.ACTIVO,"--");
        values.put(Contrato.Armark_almacen.TAGS,"--");
        values.put(Contrato.Armark_almacen.X,"--");
        values.put(Contrato.Armark_almacen.Y,"--");
        db.insertOrThrow(Tabla.ALMACEN,null,values);

        values.put(Contrato.Armark_almacen.IDWEB,"2");
        values.put(Contrato.Armark_almacen.RAZONSOCIAL,"El vivito2");
        values.put(Contrato.Armark_almacen.NIT,"5555417-1");
        values.put(Contrato.Armark_almacen.DESCRIPCION,"Ingeniero poseidon");
        values.put(Contrato.Armark_almacen.DIRECCION,"CL80 #11b-35");
        values.put(Contrato.Armark_almacen.TELEFONO,"3107078");
        values.put(Contrato.Armark_almacen.CORREO,"culevivo@hotmail.com");
        values.put(Contrato.Armark_almacen.POSICIONGPS,"--");
        values.put(Contrato.Armark_almacen.LOGO,"http://torneofacil.serticiossp.co/imagenes/torneo2.jpg");
        values.put(Contrato.Armark_almacen.MARCADOR,"--");
        values.put(Contrato.Armark_almacen.REGISTRO,"--");
        values.put(Contrato.Armark_almacen.MODIFICADOR,"--");
        values.put(Contrato.Armark_almacen.VISIBLE,"--");
        values.put(Contrato.Armark_almacen.ACTIVO,"--");
        values.put(Contrato.Armark_almacen.TAGS,"--");
        values.put(Contrato.Armark_almacen.X,"--");
        values.put(Contrato.Armark_almacen.Y,"--");
        db.insertOrThrow(Tabla.ALMACEN,null,values);

    }
}

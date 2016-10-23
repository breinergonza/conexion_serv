package com.feedhenry.armark;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.feedhenry.sdk.FH;
import com.feedhenry.sdk.FHActCallback;
import com.feedhenry.sdk.FHResponse;
import com.feedhenry.sdk.api.FHCloudRequest;

import org.json.fh.JSONArray;
import org.json.fh.JSONObject;

import modelo.Contrato;

/**
 * Created by ASUS on 22/10/2016.
 */
public class CloudCall_Post {


    int IDWEB ;
    public String RAZONSOCIAL,NIT,DESCRIPCION,DIRECCION,TELEFONO,CORREO,POSICIONGPS,LOGO ,
            MARCADOR,REGISTRO,MODIFICADOR,VISIBLE,ACTIVO,TAGS,X,Y;


    private static final String TAG =""; //InitFragment.class.getName();
    public CloudCall_Post() {
    }

    public void Post_Almacen( final Context context){
        try {
            JSONObject params = new JSONObject("{fecha: 2015-09-11}");


            FHCloudRequest request = FH.buildCloudRequest("almacenes", "POST", null, params);
            request.executeAsync(new FHActCallback() {
                @Override
                public void success(FHResponse fhResponse) {
                    Log.d(TAG, "cloudCall - success");
                    //v.setEnabled(true);
                    //response.setText(fhResponse.getJson().toString());

                    int Id;
                    String RazonSocial;
                    String resp = "";


                    JSONArray array = fhResponse.getArray();

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject row = array.getJSONObject(i);


                         IDWEB = row.getInt("Id");
                         RAZONSOCIAL = row.getString("RazonSocial");
                         NIT = row.getString("Nit");
                         DESCRIPCION = row.getString("Descripcion");
                         DIRECCION = row.getString("Direccion");
                         TELEFONO = row.getString("Telefono");
                         CORREO = row.getString("Correo");
                         POSICIONGPS = row.getString("PosicionGps");
                         LOGO = row.getString("Logo");
                         MARCADOR = row.getString("Marcador");
                         REGISTRO = row.getString("Registro");
                         MODIFICADOR = row.getString("Modificacion");
                         VISIBLE = row.getString("Visible");
                         ACTIVO = row.getString("Activo");
                         TAGS = row.getString("Tags");
                         X = row.getString("X");
                         Y = row.getString("Y");

                     //   resp += "Id : " + Id + ", Razon Social : " + RazonSocial + "\n";
                        insertar_registros_almacen(context);

                    }
                  //  response.setText(resp);
                }

                @Override
                public void fail(FHResponse fhResponse) {
                    Log.d(TAG, "cloudCall - fail");
                    Log.e(TAG, fhResponse.getErrorMessage(), fhResponse.getError());
                    //v.setEnabled(true);
                   // response.setText(fhResponse.getErrorMessage());
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e.getCause());
        }
    }

    private void insertar_registros_almacen(Context context) {

        // el content provider genera todos los cambios en un content resolver
        ContentResolver contentResolver = context.getContentResolver();

        // creamos un contenedor de valores nuevos
        ContentValues values = new ContentValues();
        values.put(Contrato.Armark_almacen.IDWEB,IDWEB);
        values.put(Contrato.Armark_almacen.RAZONSOCIAL,RAZONSOCIAL);
        values.put(Contrato.Armark_almacen.NIT,NIT);
        values.put(Contrato.Armark_almacen.DESCRIPCION,DESCRIPCION);
        values.put(Contrato.Armark_almacen.DIRECCION,DIRECCION);

        values.put(Contrato.Armark_almacen.TELEFONO,TELEFONO);
        values.put(Contrato.Armark_almacen.CORREO,CORREO);
        values.put(Contrato.Armark_almacen.POSICIONGPS,POSICIONGPS);
        values.put(Contrato.Armark_almacen.LOGO,LOGO);
        values.put(Contrato.Armark_almacen.MARCADOR,MARCADOR);

        values.put(Contrato.Armark_almacen.REGISTRO,REGISTRO);
        values.put(Contrato.Armark_almacen.MODIFICADOR,MODIFICADOR);
        values.put(Contrato.Armark_almacen.VISIBLE,VISIBLE);
        values.put(Contrato.Armark_almacen.ACTIVO,ACTIVO);
        values.put(Contrato.Armark_almacen.TAGS,TAGS);

        values.put(Contrato.Armark_almacen.X,X);
        values.put(Contrato.Armark_almacen.Y,Y);

        // insertamos la nueva fila con sus nuevos valores
        contentResolver.insert(Uri.parse(String.valueOf(Contrato.Armark_almacen.URI_CONTENIDO)),values);

    }
}

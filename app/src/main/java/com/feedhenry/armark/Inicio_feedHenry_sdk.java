package com.feedhenry.armark;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.feedhenry.sdk.FH;
import com.feedhenry.sdk.FHActCallback;
import com.feedhenry.sdk.FHResponse;

/**
 * Created by ASUS on 20/10/2016.
 */
public class Inicio_feedHenry_sdk  {

    Context context;

    public Inicio_feedHenry_sdk(Context context) {
        this.context = context;
    }

    public void  InicializarFH (final Context context){



        FH.init(context, new FHActCallback() {
            @Override
            public void success(FHResponse pResponse) {



                  Log.d("dato","exito");
                    MainActivity activity = new MainActivity();

                    CloudCall_Post cloudCall_post = new CloudCall_Post();
                    cloudCall_post.Post_Almacen(context);


            }

            @Override
            public void fail(FHResponse pResponse) {
            Log.d("dato","fail");
                Log.e("error", pResponse.getErrorMessage(), pResponse.getError());
            }
        });

    }
    private void goLogin() {
        Intent intent = new Intent(context,Loggin.class);

        // AÑADIMOS banderas que nos permite limpiar el recorrido anterior, cuando presionemos atras no nos devuelve al MainActivity.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}

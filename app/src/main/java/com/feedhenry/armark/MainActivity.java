/**
 * Copyright 2015 Red Hat, Inc., and individual contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feedhenry.armark;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.feedhenry.armark.fragmentos.Almacen_fragment;
import com.feedhenry.armark.fragmentos.Productos_fragment;
import com.feedhenry.armark.fragmentos.Promociones_fragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //try {
        //    PackageInfo info = getPackageManager().getPackageInfo(
        //            "com.feedhenry.armark", PackageManager.GET_SIGNATURES);
        //    for (Signature signature : info.signatures) {
        //        MessageDigest md = MessageDigest.getInstance("SHA");
        //        md.update(signature.toByteArray());
        //        Log.e("MY KEY HASH:",
        //                Base64.encodeToString(md.digest(), Base64.DEFAULT));
        //    }
        //} catch (PackageManager.NameNotFoundException e) {
        //    Log.e("Error 1", e.getMessage());
        //} catch (NoSuchAlgorithmException e) {
        //    Log.e("Error 2", e.getMessage());
        //}

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //inicializador de api de facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        if(AccessToken.getCurrentAccessToken()== null) {
            goLogin();
        }





        //  adaptador para los fragmentos
       SeccionPageradapter seccionPageradapter1 = new SeccionPageradapter(getSupportFragmentManager(),
                MainActivity.this);


        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        assert mViewPager != null;
        mViewPager.setAdapter(seccionPageradapter1);
        mViewPager.setOffscreenPageLimit(0); //  Guarda la cantidad (int 0)de fragment que esta en el viewPager.  este caso es 0, las destruye de una todas las posiciones de fragment

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        assert tabLayout != null;
        tabLayout.setupWithViewPager(mViewPager);


        Inicio_feedHenry_sdk iniciar = new Inicio_feedHenry_sdk(getApplicationContext());
        iniciar.InicializarFH(getApplicationContext());


    }



    @Override
    public void onStart() {
        super.onStart();
       // showInitFragment();



    }



    @Override
    public void onStop() {
        super.onStop();
       // FH.stop();
    }

    public void onclick_cerrar_sesion(View view) {
        // cerramos cesion con cuenta de facebook
        if (AccessToken.getCurrentAccessToken() != null) { //  si esto es null quiere decir que no tenemos la seccion iniciada
            LoginManager.getInstance().logOut();
            goLogin();

        }
    }
    private void goLogin() {
        Intent intent = new Intent(this,Loggin.class);

        // AÑADIMOS banderas que nos permite limpiar el recorrido anterior, cuando presionemos atras no nos devuelve al MainActivity.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


  //**********************INICIO************************************************************************************************
  public class SeccionPageradapter extends FragmentStatePagerAdapter {
      final int PAGE_COUNT = 3;
      private Context context;

      public SeccionPageradapter(FragmentManager fm, Context context) {
          super(fm);
          this.context = context;
      }

      @Override
      public Fragment getItem(int position) {
          //PaginaActual = position;

          switch (position){
              case 0:

                  return Promociones_fragment.newInstance(position);

              case 1:

                  return Almacen_fragment.newInstance(position);

              case 2:
                  return Productos_fragment.newInstance(position);

              default:
                  return null;

          }

      }

      @Override
      public int getCount() {
          return PAGE_COUNT;
      }
      @Override
      public CharSequence getPageTitle(int position) {
          switch (position) {
              case 0:
                  return "Promociones";
              case 1:
                  return "Almacen";
              case 2:
                  return "Productos";
          }
          return null;
      }
  }
    //*************************************************************************************************************************



}

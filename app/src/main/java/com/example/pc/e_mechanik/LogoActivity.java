package com.example.pc.e_mechanik;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class LogoActivity extends AppCompatActivity {


    String loginStaly;
    EditText login;
    EditText haslo;
    EditText email;
    EditText workTime;
    EditText adres;
    EditText tel;
    EditText silnik;
    EditText typ_Nadwozia;
    EditText model;
    EditText marka;
    RadioButton radioKlient;
    RadioButton radioMechanic;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void Zapom_hasla(View view) {
        setContentView(R.layout.activity_restore);
    }

    public void customerEditClick(View view){
        setContentView(R.layout.activity_customer_profile_edit);
    }

    public void mechanicEditClick(View view) {
        setContentView(R.layout.activity_mechanic_profile_edit);
    }

    public void rejestruj(View view) {
        setContentView(R.layout.activity_register);
    }

    public void rejestracjaMechanika(View view) {
        setContentView(R.layout.activity_mechanic_register);
    }

    public void rejestracjaKlienta(View view) {
        setContentView(R.layout.activity_customer_register);
    }

    public void rejestracjaMechanikaWlasciwa(View view) {
        login = (EditText) findViewById(R.id.tv_login);
        haslo = (EditText) findViewById(R.id.tv_haslo);
        email = (EditText) findViewById(R.id.tv_email);
        workTime = (EditText) findViewById(R.id.tv_czas);
        adres = (EditText) findViewById(R.id.tv_adres);
        tel = (EditText) findViewById(R.id.tv_tel);

        final String login_mechanika = login.getText().toString().trim().toLowerCase();
        final String haslo_mechanika = haslo.getText().toString().trim().toLowerCase();
        final String email_mechanika = email.getText().toString().trim().toLowerCase();
        final String godz_pracy = workTime.getText().toString().trim().toLowerCase();
        final String adres_firmy = adres.getText().toString().trim().toLowerCase();
        final String nr_tel = tel.getText().toString().trim().toLowerCase();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("login_mechanika", login_mechanika);
                params.put("haslo_mechanika", haslo_mechanika);
                params.put("email_mechanika", email_mechanika);
                params.put("nr_tel", nr_tel);
                params.put("godz_pracy", godz_pracy);
                params.put("adres_firmy", adres_firmy);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void rejestracjKlientaWlasciwa(View view) {
        login = (EditText) findViewById(R.id.customerLogin);
        haslo = (EditText) findViewById(R.id.customerPassword);
        email = (EditText) findViewById(R.id.customerEmail);

        final String login_klienta = login.getText().toString().trim().toLowerCase();
        final String haslo_klienta = haslo.getText().toString().trim().toLowerCase();
        final String email_klienta = email.getText().toString().trim().toLowerCase();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER_CLIENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("login_klienta", login_klienta);
                params.put("haslo_klienta", haslo_klienta);
                params.put("email_klienta", email_klienta);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void Zaloguj(View view) {
        radioKlient = (RadioButton) findViewById(R.id.radioButtonKlient);
        radioMechanic =(RadioButton) findViewById(R.id.radioMechanic);
        EditText loginn = (EditText) findViewById(R.id.loginLogin);
        EditText hasloo = (EditText) findViewById(R.id.loginHaslo);
        final String login = loginn.getText().toString().trim().toLowerCase();
        loginStaly = login;
        final String haslo = hasloo.getText().toString().trim().toLowerCase();
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        RadioButton rb = (RadioButton) findViewById(selectedId);

        if(radioKlient.isChecked()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN_CLIENT, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }

            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }

            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("login_klienta", login);
                    params.put("haslo_klienta", haslo);

                    return params;
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
            setContentView(R.layout.activity_customer_menu);
        }

        else if(radioMechanic.isChecked()){

            StringRequest stringRequestt = new StringRequest(Request.Method.POST, Constants.URL_LOGIN_MECHANIC, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }

            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("login_mechanika", login);
                    params.put("haslo_mechanika", haslo);

                    return params;
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequestt);
                setContentView(R.layout.activity_mechanic_menu);

        }
    }

    public void klientEdytuj(View view){
        EditText edit = (EditText)findViewById(R.id.customerHasloEdit) ;
        email = (EditText) findViewById(R.id.emailKlientas);
        final String haslo = edit.getText().toString();
        final String mail = email.getText().toString();

        ////email
            StringRequest stringRequestt = new StringRequest(Request.Method.POST, Constants.URL_CUSTOMER_EMAIL_EDIT, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }

            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("login_klienta", loginStaly);
                    params.put("email_klienta", mail);

                    return params;
                }
            };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequestt);

        /////////haslooooooooo
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CUSTOMER_PASSWORD_EDIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("login_klienta", loginStaly);
                params.put("haslo_klienta", haslo);

                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        }



    public void mechanikEdytuj(View view){

        EditText pass = (EditText)findViewById(R.id.hasloMech) ;
        EditText email = (EditText)findViewById(R.id.emailMech) ;
        EditText adres = (EditText)findViewById(R.id.adresMech) ;
        EditText phone = (EditText)findViewById(R.id.nazwaMech) ;
        EditText godz = (EditText)findViewById(R.id.godzMech) ;
        final String haslo = pass.getText().toString();
        final String mail = email.getText().toString();
        final String telefon = phone.getText().toString();
        final String adress = adres.getText().toString();
        final String hours = adres.getText().toString();

        StringRequest stringRequestt = new StringRequest(Request.Method.POST, Constants.URL_MECHANIC_EMAIL_EDIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("login_mechanika", loginStaly);
                params.put("email_mechanika", mail);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequestt);



        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_MECHANIC_PASSWORD_EDIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("login_mechanika", loginStaly);
                params.put("haslo_mechanika", haslo);

                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        StringRequest stringRequestq = new StringRequest(Request.Method.POST, Constants.URL_MECHANIC_PHONE_EDIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("login_mechanika", loginStaly);
                params.put("nr_tel", telefon);

                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequestq);

        StringRequest stringRequestqwX = new StringRequest(Request.Method.POST, Constants.URL_MECHANIC_ADRESS_EDIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("login_mechanika", loginStaly);
                params.put("adres_firmy", adress);

                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequestqwX);

        StringRequest stringRequestqwR = new StringRequest(Request.Method.POST, Constants.URL_MECHANIC_HOURS_EDIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("login_mechanika", loginStaly);
                params.put("godz_pracy", hours);

                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequestqwR);
    }

}

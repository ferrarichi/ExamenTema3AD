package com.example.juanjo.examentema3ad;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variables
    private LinearLayout lyPrincipal, lyBtns, lyCrear;
    private boolean panelGuardar;

    //Crear datos
    private EditText editNombre, editDia, editMes;

    //Mostrar datos
    private TextView txtName, txtPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        panelGuardar = false;

        //Asignacion de variables a la vista
        lyCrear = (LinearLayout) findViewById(R.id.lyCrear);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editDia = (EditText) findViewById(R.id.editDia);
        editMes = (EditText) findViewById(R.id.editMes);

        txtName = (TextView) findViewById(R.id.txtName);
        txtPago = (TextView) findViewById(R.id.txtPago);

        lyCrear.setVisibility(View.GONE);
        cargarPreferencias();


    }


    //Guardar preferencias
    public void guardarPreferencias() {
        SharedPreferences misPreferencias = getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();

        //Cargamos valores de la vista
        String nombre = editNombre.getText().toString();
        String dia = editDia.getText().toString();
        String mes = editMes.getText().toString();

        //Guardamos valores
        editor.putString("Nombre", nombre);
        editor.putString("Dia", dia);
        editor.putString("Mes", mes);

        editor.commit();

        Toast.makeText(getBaseContext(), "Se han Guardado las preferencias", Toast.LENGTH_LONG).show();
        cargarPreferencias();
    }


    //Cargar preferencias
    public void cargarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);

        //Asignamos valores a pantalla
        txtName.setText("Hola " + misPreferencias.getString("Nombre","Anonimo"));
        txtPago.setText("Tu ultimo pago fue el dia " + misPreferencias.getString("Dia","xx") + " de "+misPreferencias.getString("Mes", "xxxxxx"));

        Toast.makeText(getBaseContext(),"Se han Cargado las preferencias",Toast.LENGTH_LONG).show();
    }




    //Eliminar preferencias
    public void eliminarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();

        editor.clear().commit();
        Toast.makeText(getBaseContext(),"Preferencias eliminadas",Toast.LENGTH_LONG).show();

        cargarPreferencias();
    }



    //Clics botones
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCrear:
                if (panelGuardar) {
                    lyCrear.setVisibility(View.GONE);
                    panelGuardar = false;
                } else {
                    lyCrear.setVisibility(View.VISIBLE);
                    panelGuardar = true;
                }
                break;
            case R.id.btnCargar:
                cargarPreferencias();
                break;
            case R.id.btnGuardarDatos:
                guardarPreferencias();
                lyCrear.setVisibility(View.GONE);
                panelGuardar = true;
                break;
            case R.id.BtnBorrar:
                eliminarPreferencias();
                break;
        }
    }





}

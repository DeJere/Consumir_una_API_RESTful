package com.example.myapplication.models;

public class LugarTuristico {
    private String Nombre;
       private String Informacion;
       public LugarTuristico (String nombre, String info){
           Nombre = nombre;
           Informacion = info;
       }
       public String getNombre(){
           return Nombre;
       }
       public String getInformacion(){
           return Informacion;
       }
}

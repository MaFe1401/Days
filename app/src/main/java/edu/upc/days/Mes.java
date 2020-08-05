package edu.upc.days;

public class Mes {
    String nombre;
    int valor;
    int orden;
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setValor(int valor){
        this.valor=valor;
    }
    public String getNombre(){
        return this.nombre;
    }
    public int getValor(){
        return this.valor;
    }
    public Mes(String nombre, int valor, int orden){
        this.nombre=nombre;
        this.valor=valor;
        this.orden = orden;
    }

}


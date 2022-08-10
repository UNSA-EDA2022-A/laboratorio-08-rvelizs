package com.example.project;

public class Persona {
    public String DNI, nombre;

    public Persona(String DNI, String nombre){
        this.DNI = DNI;
        this.nombre = nombre;
    }
    
    public void setDNI(String dni) {
		this.DNI = dni;
	}
}

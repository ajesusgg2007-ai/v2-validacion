package es.davante.edadcanina;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import modelo.EdadCaninaModelo;
import modelo.Validacion;

public class EdadCaninaModeloTest {
    private EdadCaninaModelo modelo;

    @Before public void prepare(){
        modelo = new EdadCaninaModelo();
    }

    @Test public void vacio()
    {
        modelo.setEdadTexto("");
        assertEquals(Validacion.VACIO, modelo.calcular());
    }

    @Test public void soloEspacios_esVacio()
    {
        modelo.setEdadTexto("   ");
        assertEquals(Validacion.VACIO, modelo.calcular());
    }

    @Test public void texto()
    {
        modelo.setEdadTexto("abc");
        assertEquals(Validacion.NO_NUMERO, modelo.calcular());
    }

    @Test public void numeroGrande()
    {
        modelo.setEdadTexto("849594");
        assertEquals(Validacion.FUERA_DE_RANGO, modelo.calcular());
    }

    @Test public void MayorFueraRango()
    {
        modelo.setEdadTexto("31");
        assertEquals(Validacion.FUERA_DE_RANGO, modelo.calcular());

    }

   @Test public void seisSon42(){
        modelo.setEdadTexto("6");
        assertEquals(Validacion.OK, modelo.calcular());
        assertEquals(42, modelo.getEdadHumana());
   }

   @Test public void cambiarTexto(){
        modelo.setEdadTexto("6");
        modelo.calcular();
        modelo.setEdadTexto("7");
        assertEquals(Validacion.SIN_CALCULAR,modelo.getValidacion());
    }



}


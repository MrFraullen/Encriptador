package Codificador;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

public class Codificador{

    public static class CodificadorException extends Exception{
        public CodificadorException(String mensaje){
            super(mensaje);
        }
    }

    private static final int ORDEN_CPV = 123; //Clave(1) Posicion(2) Valor(3).
    private static final int ORDEN_CVP = 132; //Clave(1) Valor(3) Posicion(2).
    private static final int ORDEN_PCV = 213; //Posicion(2) Clave(1) Valor(3).
    private static final int ORDEN_PVC = 231; //Posicion(2) Valor(3) Clave(1).
    private static final int ORDEN_VCP = 312; //Valor(3) Clave(1) Posicion(2)
    private static final int ORDEN_VPC = 321; //Valor(3) Posicion(2) Clave(1)
    private static final int DIGITOS_POSICION = 4;
    private static final int DIGITOS_VALOR = 4;

    private static final int[] ORDENES={ORDEN_CPV,ORDEN_CVP,ORDEN_PCV,ORDEN_PVC,ORDEN_VCP,ORDEN_VPC};

    private static StringBuilder construirElemento(int orden, String clave, String valor, String posicion){
        StringBuilder elemento = new StringBuilder();
        elemento.append(orden);
        switch (orden) {
            case ORDEN_CPV -> { elemento.append(clave); elemento.append(posicion); elemento.append(valor); }
            case ORDEN_CVP -> { elemento.append(clave); elemento.append(valor); elemento.append(posicion); }
            case ORDEN_PCV -> { elemento.append(posicion); elemento.append(clave); elemento.append(valor); }
            case ORDEN_PVC -> { elemento.append(posicion); elemento.append(valor); elemento.append(clave); }
            case ORDEN_VCP -> { elemento.append(valor); elemento.append(clave); elemento.append(posicion); }
            case ORDEN_VPC -> { elemento.append(valor); elemento.append(posicion); elemento.append(clave); }
        }
        return elemento;
    }

    public static void escribirl(Object texto){System.out.println(texto);}
    public static void escribir(Object texto){System.out.print(texto);}

    private static String agregarCerosIzquierda(String texto, int cantidadTotal){
        StringBuilder textoFinal = new StringBuilder();
        int cantidadCeros = cantidadTotal - texto.length();
        textoFinal.append("0".repeat(Math.max(0, cantidadCeros))); //agrega el 0 repetido la cantidad de 0's necesaria
        textoFinal.append(texto);
        return textoFinal.toString();
    }

    // Copiar al portapapeles
    private static void copiarAlPortapapeles(String texto){
        StringSelection seleccion = new StringSelection(texto);
        Clipboard portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
        portapapeles.setContents(seleccion, null);
    }

    public static void codificar(){
        try {
            Random rand = new Random();
            String texto;

            Scanner scanner = new Scanner(System.in);
            escribir("Ingrese el texto a codificar (Sin caracteres fuera del ASCII imprimible): ");
            texto = scanner.nextLine().trim();
            while(texto.isEmpty()){
                escribirl("No se ingreso un texto a codificar");
                escribir("Ingrese el texto a codificar: ");
                texto = scanner.nextLine().trim();
            }

            StringBuilder secuencia = new StringBuilder();
            StringBuilder[] valores = new StringBuilder[texto.length()];

            for(int i=0; i < texto.length(); i++){
                String clave = String.valueOf(rand.nextInt(22, 100));
                int valorASCII = texto.charAt(i);
                if (valorASCII<32 || valorASCII>126) throw new CodificadorException("Error: Caracter ASCII no imprimible"+valorASCII);
                int multiplicador = Character.getNumericValue(clave.charAt(0));
                int suma = Character.getNumericValue(clave.charAt(1));

                int valorCodificado = (valorASCII * multiplicador) + suma;
                String valorFinal = agregarCerosIzquierda(String.valueOf(valorCodificado), DIGITOS_VALOR);
                String posicionStr = agregarCerosIzquierda(String.valueOf(i), DIGITOS_POSICION);
                int orden = ORDENES[rand.nextInt(ORDENES.length)];

                valores[i] = construirElemento(orden, clave, valorFinal, posicionStr);
            }

            List<StringBuilder> lista = Arrays.asList(valores);
            Collections.shuffle(lista);

            for(StringBuilder elemento : lista) secuencia.append(elemento);

            escribirl("Texto codificado:");
            escribirl(secuencia.toString());

            // Copia la secuencia al portapapeles
            copiarAlPortapapeles(secuencia.toString());
            escribirl("La secuencia codificada se ha copiado automáticamente al portapapeles.");
        } catch (CodificadorException e) {
            escribirl(e.getMessage());
        }
    }
}

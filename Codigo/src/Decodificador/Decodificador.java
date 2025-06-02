package Decodificador;

import java.util.Scanner;

public class Decodificador {

    public static class DecodificadorException extends Exception{
        public DecodificadorException(String mensaje){
            super(mensaje);
        }
    }

    public static void escribirl(Object texto){System.out.println(texto);}

    public static void escribir(Object texto){System.out.print(texto);}

    private static final int LONGITUD_ELEMENTO=13;

    private static int[] desconstruirElemento(String elemento, int orden){
        int[] elementos=new int[3];
        int claveElem = 0;
        int pos = 0;
        int valor = 0;
        switch (orden) {
            case 123 -> {
                claveElem = Integer.parseInt(elemento.substring(3, 5));
                pos = Integer.parseInt(elemento.substring(5, 9));
                valor = Integer.parseInt(elemento.substring(9));
            }
            case 132 -> {
                claveElem = Integer.parseInt(elemento.substring(3, 5));
                valor = Integer.parseInt(elemento.substring(5, 9));
                pos = Integer.parseInt(elemento.substring(9));
            }
            case 213 -> {
                pos = Integer.parseInt(elemento.substring(3, 7));
                claveElem = Integer.parseInt(elemento.substring(7, 9));
                valor = Integer.parseInt(elemento.substring(9));
            }
            case 231 -> {
                pos = Integer.parseInt(elemento.substring(3, 7));
                valor = Integer.parseInt(elemento.substring(7, 11));
                claveElem = Integer.parseInt(elemento.substring(11));
            }
            case 312 -> {
                valor = Integer.parseInt(elemento.substring(3, 7));
                claveElem = Integer.parseInt(elemento.substring(7, 9));
                pos = Integer.parseInt(elemento.substring(9));
            }
            case 321 -> {
                valor = Integer.parseInt(elemento.substring(3, 7));
                pos = Integer.parseInt(elemento.substring(7, 11));
                claveElem = Integer.parseInt(elemento.substring(11));
            }
        }
        elementos[0]=claveElem;
        elementos[1]=pos;
        elementos[2]=valor;
        return elementos;
    }

    public static void decodificar(){
        try {
            //Entrada
            String secuencia;
            Scanner scanner=new Scanner(System.in);
            escribir("Ingrese una secuencia a decodificar:");
            secuencia=scanner.nextLine().trim();
            while(secuencia.isEmpty()){
                escribirl("No se ingreso una secuencia a decodificar.");
                escribir("Ingrese la secuencia a decodificar");
                secuencia=scanner.nextLine().trim();
            }

            //Verifica que sea correcta
            final int CANTIDAD=secuencia.length();
            for(int i=0;i<CANTIDAD;i++)if(!(Character.isDigit(secuencia.charAt(i))))throw new DecodificadorException("Error: la secuencia ingresada no es numerica o genero un caracter ascii invalido."); //Si contiene algo no numerico.
            if(CANTIDAD%LONGITUD_ELEMENTO!=0)throw new DecodificadorException("Error: la cantidad de digitos de la secuencia/subsecuencia no corresponde a la cantidad de digitos que deberia tener una secuencia."); //Si no es 13 x n de longitud

            //Prepara el texto final
            StringBuilder textoFinal=new StringBuilder();
            while(textoFinal.length()!=CANTIDAD/LONGITUD_ELEMENTO)textoFinal.append("a"); //lo rellena de a's

            for(int i=0;i<CANTIDAD;i+=LONGITUD_ELEMENTO){
                String elementoActual=secuencia.substring(i,i+LONGITUD_ELEMENTO);

                //Halla los valores
                int orden=Integer.parseInt(elementoActual.substring(0,3));
                int[] elementoDesconstruido=desconstruirElemento(elementoActual,orden);
                int claveElem=elementoDesconstruido[0];
                int pos=elementoDesconstruido[1];
                int valorInt=elementoDesconstruido[2];


                //Decodifica
                String claveStr=String.valueOf(claveElem);
                int divisor=Character.getNumericValue(claveStr.charAt(0));
                int resta=Character.getNumericValue(claveStr.charAt(1));
                char valorASCII= (char) ((valorInt-resta)/divisor);
                if( valorASCII<32 || valorASCII>126) throw new DecodificadorException("Error: Caracter ASCII no imprimible.");

                textoFinal.setCharAt(pos, valorASCII); //Coloca el caracter correspondiente en su posiciÃ³n
            }
            escribirl("El texto decodificado es:");
            escribirl(textoFinal);
        } catch (DecodificadorException e) {
            escribirl(e.getMessage());
        }
    }
}

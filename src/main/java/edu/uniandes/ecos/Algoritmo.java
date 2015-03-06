/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos;

public class Algoritmo {

    public static Double error = 0.0001;
    private static Double valorD = 0.5;
    public static Double valorX = 0.0;
    public static Double valorDof = 0.0;
    public static Double valorP = 0.0;
    public static App app = new App();
    
//     public static void main(String[] args) {
//        calcularX();
//    }
     
     /**
      * Metodo que carga las variables de pantalla
      * @param p
      * @param dof
      * @param x 
      */
     public void cargarDatos(Double p, Double dof, Double x){
         valorP = p;
         valorDof = dof;
         valorX = x;
     }
    
     /**
      * Metodo que va calculando el valor de X hasta encontrar 
      * la mayor aproximación para que obtener el resultado P dado
      * con la regla de simpson.
      */
    public static void calcularX() {
        boolean valorSigno = true; //true positivo, false negativo
        Double pTemporal = 0.0;
        while (!pTemporal.equals(valorP)) {
            app.calcularVariables(valorX, 10.0, valorDof);
            app.calcularP();
            pTemporal = app.getP();
            if (pTemporal - valorP == error || valorP - pTemporal == error || valorP.equals(pTemporal)) {
                System.out.println("valor p" + pTemporal +"valor X" + valorX);
            } else {
                if (pTemporal > valorP) {
                    valorX = valorX - valorD;
                } else if (pTemporal < valorP) {
                    valorX = valorX + valorD;
                }
                if (pTemporal - valorP > 0) {
                    if (!valorSigno) {
                        valorD = valorD / 2;
                    }
                    valorSigno = true;
                } else {
                    if (valorSigno) {
                        valorD = valorD / 2;
                    }
                    valorSigno = false;
                }
            }
        }
        System.out.println("FIN!");

    }

    public static Double getError() {
        return error;
    }

    public static void setError(Double error) {
        Algoritmo.error = error;
    }

    public static Double getValorD() {
        return valorD;
    }

    public static void setValorD(Double valorD) {
        Algoritmo.valorD = valorD;
    }

    public static Double getValorX() {
        return valorX;
    }

    public static void setValorX(Double valorX) {
        Algoritmo.valorX = valorX;
    }

    public static Double getValorDof() {
        return valorDof;
    }

    public static void setValorDof(Double valorDof) {
        Algoritmo.valorDof = valorDof;
    }

    public static Double getValorP() {
        return valorP;
    }

    public static void setValorP(Double valorP) {
        Algoritmo.valorP = valorP;
    }
        
}

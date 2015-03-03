package edu.uniandes.ecos;

/**
 * Esta clase controla las variables para la ecuación de simpson
 * calcula la formula
 * @author Sergio Forero
 */
public class App {

    private static Double w;
    private static Double dof;
    private static Double x;
    private static Double E;
    private static Double numSeg;
    private static Double P;

//    public static void main(String[] args) {
//        calcularVariables(1.1,10.0,9.0);
//        calcularP();
//    }

    /**
     * Metodo que guarda las variables que se ingresaron por pantalla
     * y calcula W
     * @param xvar
     * @param numSegvar
     * @param dofvar 
     */
    public static void calcularVariables(Double xvar, Double numSegvar, Double dofvar) {
        x = xvar;
        numSeg = numSegvar;
        dof = dofvar;
        w = x/numSeg;
        E = 0.00001;
    }
    
    /**
     * Este metodo calcula P
     * este usa los metodos de calcularF y sumatoria para pares e impares
     */
    public static void calcularP(){
        P= (w/3) * (calulcarF(0.0) + sumatoriaImpares(numSeg) + sumatoriaPares(numSeg)+ calulcarF(x));
    }
    
    /**
     * Metodo que calcula la Funcion F con el valor que recibe
     * @param val
     * @return  retorna el resultado de la funcion calculada
     */
    public static Double calulcarF(Double val){
        Double result = 0.0;
        Double cabecera = (dof + 1) / 2;
        cabecera = factorial(cabecera);
        Double divide = (Math.pow((dof*Math.PI),0.5)) * factorial((dof/2));
        Double segunda = 1+(Math.pow(val, 2)/dof);
        segunda = Math.pow(segunda, -(dof+1)/2);
        result = cabecera/divide;
        result = result * segunda;
        return result;
    }
    
    /**
     * Metodo que realiza la sumatoria segun el valor ingresado para los numeros impares de i
     * Este metodo utiliza el metodo de calcular Funcion
     * @param s
     * @return retorna el resultado de la sumatoria
     */
    public static Double sumatoriaImpares(Double s){
        Double sumatoria = 0.0;
        for(int i=1; i<= s-1; i=i+2){
            sumatoria += 4*(calulcarF(w*i));
        }
        return sumatoria;
    }
    /**
     * Metodo que realiza la sumatoria segun el valor ingresado para los numeros pares de i
     * Este metodo utiliza el metodo de calcular Funcion
     * @param s
     * @return retorna el resultado de la sumatoria
     */
     public static Double sumatoriaPares(Double s){
         Double sumatoria = 0.0;
        for(int i=2; i<= s-2; i = i+2){
             sumatoria += 2*(calulcarF(w*i));
        }
        return sumatoria;
    }
    
     /**
      * Metodo que calcula el factorial de un entero o un quebrado
      * @param N
      * @return retorna el resultado del factorial
      */
    public static Double factorial(Double N){
        Double multi = 1.0;
        for (Double i = N-1; i >= 0.5; i--) {
            multi = multi * i;
        }
        if(multi %2 ==0){
            return multi;
        }else{
            return multi * Math.sqrt(Math.PI);
        }
        
    }

    public static Double getP() {
        return P;
    }

    public static void setP(Double P) {
        App.P = P;
    }    
}

package edu.uniandes.ecos;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Hello world!
 *
 */
public class Main extends HttpServlet {

    public static String texto = "";
    public Algoritmo alg = new Algoritmo();
    /**
     * Metodo que inicia el servlet
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new Main()), "/*");
        server.start();
        server.join();
    }
    
    /**
     * Metodo post que se ejecuta cuando hay un submit
     * obtiene los valores digitados en pantalla y llama los metodos que calculan la regla de Simpson
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        String xvar = req.getParameter("xvar");        
        String pvar = req.getParameter("pvar");
        String dofvar = req.getParameter("dofvar");
        alg.cargarDatos(Double.parseDouble(pvar), Double.parseDouble(dofvar), Double.parseDouble(xvar));
        alg.calcularX();
        
        PrintWriter out = resp.getWriter();
        out.print("P obtenido =" + alg.getValorP()+"\n");
        out.print("X calculado =" + alg.getValorX() +"\n");
        out.print("Error =" + alg.getError() +"\n");
        
        
        
    }
    
    /**
     * Metodo que carga la pantalla en html que pedira los datos a ingresar 
     * para realizar el calculo de la regla de Simpson
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if(req.getParameter("xvar") != null){
            showHome(req, resp);
        }else{
        PrintWriter out = resp.getWriter();
        out.print("<html>\n"
                + "<body>\n"
                + "<form action=\"/Main\" method=\"POST\">\n"
                +"Ingrese el valor de X :\n Datos: <input type=\"text\" name=\"xvar\">\n"
                + "<br/>\n"
                + "<br />\n"
                +"Ingrese el valor de P:\n Datos: <input type=\"text\" name=\"pvar\">\n"
                + "<br/>\n"
                + "<br />\n"
                +"Ingrese el valor de dof :\n Datos: <input type=\"text\" name=\"dofvar\">\n"
                + "<br/>\n"
                + "<br />\n"
                + "<input type=\"submit\" value=\"Submit\" />\n"
                + "</form>\n"
                + "</body>\n"
                + "</html>");
        }        
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        
    }
}

package servlets;

import domain.conversores.CelsiusParaFahrenheit;
import domain.conversores.ConversorGenerico;
import domain.conversores.FahrenheitParaCelsius;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/conversorTemperatura")
public class ConversorTemperaturaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double temperatura = Integer.parseInt(request.getParameter("temperatura"));
        String opcao = request.getParameter("conversor");
        
        double temperaturaConvertida = new ConversorGenerico(opcao).converter(temperatura);
        
        enviarResposta(response, temperaturaConvertida);
    }
    
    private void enviarResposta(HttpServletResponse response, double temperaturaConvertida) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Conversor de temperaturas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + temperaturaConvertida + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}

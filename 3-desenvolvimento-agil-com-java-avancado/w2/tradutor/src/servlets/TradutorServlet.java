package servlets;

import dominio.Tradutor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tradutor")
public class TradutorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String palavra = req.getParameter("palavra");

        String resposta = new Tradutor().traduzir(palavra);
        req.setAttribute("resposta", resposta);

        req.getRequestDispatcher("resposta.jsp").forward(req, resp);
    }
}

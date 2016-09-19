package servlets;

import servicos.ServicoExibicaoTopico;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exibirTopico")
public class ExibicaoTopicoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        req.setAttribute("topico", new ServicoExibicaoTopico().recuperarTopico(id));
        req.getRequestDispatcher("exibe-topico.jsp").forward(req, resp);
    }
}

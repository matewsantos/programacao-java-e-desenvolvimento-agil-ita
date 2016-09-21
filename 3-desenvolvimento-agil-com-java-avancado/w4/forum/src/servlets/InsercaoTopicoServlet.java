package servlets;

import servicos.ServicoCriacaoTopico;
import servicos.ServicoListagemTopicos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/inserirTopico")
public class InsercaoTopicoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getSession().getAttribute("usuarioLogado");
        String titulo = req.getParameter("titulo");
        String conteudo= req.getParameter("conteudo");

        new ServicoCriacaoTopico().criar(login, titulo, conteudo);

        req.setAttribute("topicos", new ServicoListagemTopicos().getTopicos());
        req.getRequestDispatcher("topicos.jsp").forward(req, resp);
    }
}

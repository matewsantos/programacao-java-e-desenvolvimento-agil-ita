package servlets;

import servicos.ServicoCriacaoComentario;
import servicos.ServicoExibicaoTopico;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/inserirComentario")
public class InsercaoComentarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getSession().getAttribute("usuarioLogado");
        String comentario = req.getParameter("comentario");
        int idTopico = Integer.parseInt(req.getParameter("idTopico"));

        new ServicoCriacaoComentario().criar(comentario, idTopico, login);

        req.setAttribute("topico", new ServicoExibicaoTopico().recuperarTopico(idTopico));
        req.getRequestDispatcher("exibe-topico.jsp").forward(req, resp);
    }
}

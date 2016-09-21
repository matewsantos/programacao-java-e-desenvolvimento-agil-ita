package servlets;

import servicos.ServicoRankingUsuarios;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exibirRanking")
public class ExibicaoRankingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("usuarios", new ServicoRankingUsuarios().getRanking());

        req.getRequestDispatcher("ranking.jsp").forward(req, resp);
    }
}

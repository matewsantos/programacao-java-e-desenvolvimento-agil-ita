package servlets;

import servicos.ServicoCadastroUsuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cadastrar")
public class CadastroServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        new ServicoCadastroUsuario().cadastrar(nome, login, email, senha);

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}

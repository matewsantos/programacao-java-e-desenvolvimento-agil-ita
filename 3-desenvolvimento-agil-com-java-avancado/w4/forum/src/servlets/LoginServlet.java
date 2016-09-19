package servlets;

import dominio.entidades.Usuario;
import servicos.ServicoLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logar")
public class LoginServlet extends HttpServlet {
    private String PAGINA_INSUCESSO = "index.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");
        Usuario usuarioLogado = new ServicoLogin().logar(login, senha);

        if (usuarioLogado != null) {
            req.getSession().setAttribute("usuarioLogado", usuarioLogado.getLogin());
            resp.sendRedirect("/listagemTopicos");
        } else {
            req.setAttribute("erro", "login e/ou senha inválido(s)");
            req.getRequestDispatcher(PAGINA_INSUCESSO).forward(req, resp);
        }
    }
}

package controlador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ansap
 */

import dao.UsuarioDAO;
import modelo.Usuario;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rolSeleccionado = request.getParameter("rol");
        
        UsuarioDAO dao = new UsuarioDAO();
        
        Usuario usuarios = dao.validar(username, password);
        
        if (usuarios != null && usuarios.getRol().equals(rolSeleccionado)) {
            HttpSession session = request.getSession();
            session.setAttribute("UsuarioLogueado", usuarios);
            session.setAttribute("rol", usuarios.getRol());

            if ("admin".equals(usuarios.getRol())) {
                response.sendRedirect("perfilAdmin.jsp");
            } else if ("usuario".equals(usuarios.getRol())) {
                response.sendRedirect("perfilUsuario.jsp");
            }
        } else {
            response.sendRedirect("sesion.jsp?error=1");
        }
    }
}


package co.gov.mintrabajo.encuesta.servlet;

import co.gov.mintrabajo.encuesta.dto.UsuarioDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/SessionServlet"})
public class SessionServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      UsuarioDTO admon = (UsuarioDTO)request.getSession().getAttribute("userLogin");
      System.out.println("Cerrando Sesion");
      if (admon != null) {
         System.out.println("Eliminando Usuario y atributos");
         request.getSession().removeAttribute("userLogin");
      }

      response.sendRedirect(request.getParameter("rutaPadre"));
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
      UsuarioDTO admon = (UsuarioDTO)request.getSession().getAttribute("userLogin");
      System.out.println("Cerrando Sesion");
      if (admon != null) {
         request.getSession().removeAttribute("userLogin");
         response.sendRedirect("HTTP://localhost:9080/Administracion/");
      }

   }
}

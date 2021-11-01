package co.gov.mintrabajo.encuesta.beans;

import co.gov.mintrabajo.encuesta.beans.base.BeanBase;
import co.gov.mintrabajo.encuesta.dto.UsuarioDTO;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

public class LoginBean extends BeanBase {
   private static final long serialVersionUID = 1L;
   private UsuarioDTO usuario;

   @PostConstruct
   public void cargarDatos() {
      this.usuario = new UsuarioDTO();
   }

   public void enviarDatos() {
      System.out.println("[FLAG][LoginBean][enviarDatos][FIN]");
   }

   public boolean getSessionValida() {
      UsuarioDTO usuario = (UsuarioDTO)this.getParametroSession("userLogin");
      if (usuario == null) {
         usuario = new UsuarioDTO();
         String idFuncionario = this.getParametros("idFuncionario");
         if (idFuncionario == null) {
            return false;
         }

         usuario.setCodigo(Long.parseLong(this.getParametros("idFuncionario")));
         usuario.setEsAdmon(true);
         this.setParametroSession("userLogin", usuario);
      }

      return true;
   }

   public void validaUsuario(ActionEvent action) {
      if (this.validarUsuarioLDAP(this.usuario.getUsuario(), this.usuario.getContrasena())) {
         this.usuario.setCodigo(1L);
         this.usuario.setNombreCompleto("Prueba");
         this.usuario.setEsAdmon(true);
         this.setParametroSession("userLogin", this.usuario);
         System.out.println("[FLAG][LoginBean][validaUsuario][USUARIO CORRECTO]");
         this.redirigirA("paginas/encuesta/encuestaList.xhtml");
      } else {
         System.out.println("[FLAG][LoginBean][validaUsuario][USUARIO INCORRECTO]");
         this.addMensajeError(getMensaje("mintrabajo.message.noInicioSesion", "mintrabajo"));
      }

   }

   public UsuarioDTO getUsuario() {
      return this.usuario;
   }

   public void setUsuario(UsuarioDTO usuario) {
      this.usuario = usuario;
   }
}

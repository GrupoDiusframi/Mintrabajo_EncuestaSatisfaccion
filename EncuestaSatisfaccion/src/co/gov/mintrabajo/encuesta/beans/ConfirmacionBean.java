package co.gov.mintrabajo.encuesta.beans;

import co.gov.mintrabajo.encuesta.beans.base.BeanBase;
import co.gov.mintrabajo.encuesta.dto.MensajeDTO;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(
   name = "confirmacionBean"
)
public class ConfirmacionBean extends BeanBase {
   private static final long serialVersionUID = 1L;
   private MensajeDTO mensaje;
   private Boolean confirmarMensajeInformacion;

   @PostConstruct
   public void cargarDatos() {
      this.mensaje = (MensajeDTO)this.getParametroSession("confirmacion");
      if (this.mensaje.getIdentificador() != null) {
         this.confirmarMensajeInformacion = Boolean.TRUE;
         this.invalidateSession();
      } else {
         this.redirigirA("paginas/errores/paginaNoEncontrada.xhtml");
      }

   }

   public void enviarDatos() {
   }

   public MensajeDTO getMensaje() {
      return this.mensaje;
   }

   public void setMensaje(MensajeDTO mensaje) {
      this.mensaje = mensaje;
   }

   public Boolean getConfirmarMensajeInformacion() {
      return this.confirmarMensajeInformacion;
   }

   public void setConfirmarMensajeInformacion(Boolean confirmarMensajeInformacion) {
      this.confirmarMensajeInformacion = confirmarMensajeInformacion;
   }
}

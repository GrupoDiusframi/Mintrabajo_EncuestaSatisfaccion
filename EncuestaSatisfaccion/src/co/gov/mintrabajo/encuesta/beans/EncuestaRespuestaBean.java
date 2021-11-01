package co.gov.mintrabajo.encuesta.beans;

import co.gov.mintrabajo.encuesta.beans.base.BeanBase;
import co.gov.mintrabajo.encuesta.dto.EncuestaDTO;
import co.gov.mintrabajo.encuesta.dto.MensajeDTO;
import co.gov.mintrabajo.encuesta.dto.RespuestaEncuestaDTO;
import co.gov.mintrabajo.encuesta.dto.UsuarioDTO;
import co.gov.mintrabajo.encuesta.servicios.impl.IAdministradorEncuesta;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(
   name = "encuestaRespuestaBean"
)
public class EncuestaRespuestaBean extends BeanBase {
   private static final long serialVersionUID = 1L;
   @EJB
   IAdministradorEncuesta administradorEncuesta;
   private EncuestaDTO encuesta;
   private String numeroRadicado;
   private String _idEncuesta;
   private String _idReg;
   private String _idUsr;
   private Boolean mostrarEncuesta;

   @PostConstruct
   public void cargarDatos() {
      this._idEncuesta = this.getParametros("idEnc");
      this._idReg = this.getParametros("idReg");
      this._idUsr = this.getParametros("idUsr");
      this._idEncuesta = this._idEncuesta != null ? this._idEncuesta.trim() : "";
      this._idReg = this._idReg != null ? this._idReg.trim() : "";
      this._idUsr = this._idUsr != null ? this._idUsr.trim() : "";
      this.mostrarDialogo(this._idReg, this._idEncuesta, "dialogo");
      if (this._idEncuesta.length() != 0 && this._idReg.length() != 0 && this._idUsr.length() != 0) {
         this.encuesta = this.administradorEncuesta.consultarEncuestaPorCodigo(Long.parseLong(this._idEncuesta));
         this.buscarNoRadicado(this._idReg, this._idEncuesta);
         this.mostrarEncuesta = Boolean.TRUE;
      } else {
         this.redirigirA("paginas/errores/paginaNoEncontrada.xhtml");
      }

   }

   public void enviarDatos() {
      this.mostrarEncuesta = Boolean.TRUE;
      MensajeDTO mensaje = null;
      RespuestaEncuestaDTO respuestaEncuesta = this.administradorEncuesta.enviarRespuestaEncuesta(new RespuestaEncuestaDTO(Long.parseLong(this._idReg), Long.parseLong(this._idUsr), this.encuesta));
      if (respuestaEncuesta.getCodigo() != null) {
         this.mostrarEncuesta = Boolean.FALSE;
         this.addMensajeInfo(getMensaje("mintrabajo.message.siRegistroRespuestaEncuesta", "mintrabajo"));
         System.out.println("BANDERA EN: " + this.mostrarEncuesta);
         mensaje = new MensajeDTO("0000", getMensaje("mintrabajo.message.mensajeConfirmacion", "mintrabajo"), (Exception)null);
      } else {
         mensaje = new MensajeDTO("0001", (String)null, new Exception(getMensaje("mintrabajo.message.mensajeError", "mintrabajo")));
      }

      this.setParametroSession("confirmacion", mensaje);
      this.redirigirA("paginas/errores/confirmacion.xhtml");
   }

   public void mostrarDialogo(String idRegp, String idEncp, String idDialogo) {
      boolean a = this.administradorEncuesta.buscarRespuesta(idRegp, idEncp);
      if (a) {
         this.mostrarDialogo(idDialogo);
      }

   }

   public String buscarNoRadicado(String idReg, String idEncu) {
      if (idEncu.equals("2050")) {
         this.numeroRadicado = this.administradorEncuesta.buscarRadicadoPQRS(idReg);
      } else {
         this.numeroRadicado = this.administradorEncuesta.buscarRadicadoTYS(idReg);
      }

      return this.numeroRadicado;
   }

   public EncuestaDTO getEncuesta() {
      return this.encuesta;
   }

   public void setEncuesta(EncuestaDTO encuesta) {
      this.encuesta = encuesta;
   }

   public Boolean getMostrarEncuesta() {
      return this.mostrarEncuesta;
   }

   public void setMostrarEncuesta(Boolean mostrarEncuesta) {
      this.mostrarEncuesta = mostrarEncuesta;
   }

   public boolean esAdmon() {
      return ((UsuarioDTO)this.getParametroSession("userLogin")).isEsAdmon();
   }

   public String getNumeroRadicado() {
      return this.numeroRadicado;
   }

   public void setNumeroRadicado(String numeroRadicado) {
      this.numeroRadicado = numeroRadicado;
   }
}

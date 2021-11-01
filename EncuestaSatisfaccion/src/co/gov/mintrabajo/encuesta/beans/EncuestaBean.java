package co.gov.mintrabajo.encuesta.beans;

import co.gov.mintrabajo.encuesta.beans.base.BeanBase;
import co.gov.mintrabajo.encuesta.dto.EncuestaDTO;
import co.gov.mintrabajo.encuesta.dto.PreguntaDTO;
import co.gov.mintrabajo.encuesta.dto.RespuestaDTO;
import co.gov.mintrabajo.encuesta.dto.UsuarioDTO;
import co.gov.mintrabajo.encuesta.servicios.impl.IAdministradorEncuesta;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.PersistenceException;

@ViewScoped
@ManagedBean(
   name = "encuestaBean"
)
public class EncuestaBean extends BeanBase {
   private static final long serialVersionUID = 1L;
   @EJB
   IAdministradorEncuesta administradorEncuesta;
   private EncuestaDTO encuesta;
   private PreguntaDTO pregunta;
   private RespuestaDTO respuesta;
   private UsuarioDTO _usuario;
   private String _accion;
   private Boolean editarRespuesta;
   private Boolean editarPregunta;
   private boolean tituloPregunta = true;
   private boolean ordenPregunta = true;
   private boolean valorRespuesta = true;
   private boolean ordenRespuesta = true;
   private boolean camposValidados = true;
   private String closeDialogQuery = "PF('wvPregunta').hide();";

   @PostConstruct
   public void cargarDatos() {
      this._accion = (String)this.getParametroSession("sesionAccion");
      if (this._accion != null && this._accion.length() > 0) {
         this._usuario = (UsuarioDTO)this.getParametroSession("userLogin");
         System.out.println("USUARIO LOGUEADO: " + (this._usuario != null && this._usuario.getCodigo() != null ? this._usuario.getCodigo() : "NULL"));
         if (this._accion.equals("editar")) {
            this.encuesta = (EncuestaDTO)this.getParametroSession("sesionEncuesta");
            this.encuesta = this.administradorEncuesta.consultarEncuestaPorCodigo(this.encuesta.getCodigo());
         } else if (this._accion.equals("nuevo")) {
            this.encuesta = new EncuestaDTO(this._usuario.getCodigo());
         }

         this.pregunta = new PreguntaDTO(this._usuario.getCodigo());
         this.respuesta = new RespuestaDTO(this._usuario.getCodigo());
      } else {
         this.redirigirA("paginas/errores/paginaNoEncontrada.xhtml");
      }

      this.editarPregunta = Boolean.FALSE;
      this.editarRespuesta = Boolean.FALSE;
      System.out.println("[FLAG][EncuestaBean][cargarDatos][FIN]");
   }

   public void enviarDatos() {
      try {
         System.out.println("[FLAG][Encuesta][enviarActionListener][PASO 1]");
         if (this._accion.equals("nuevo")) {
            this.encuesta = this.administradorEncuesta.crearEncuesta(this.encuesta);
         } else if (this._accion.equals("editar")) {
            this.encuesta.setCodigoUsuarioModifico(this._usuario.getCodigo());
            this.encuesta = this.administradorEncuesta.actualizarEncuesta(this.encuesta);
         }
      } catch (PersistenceException var2) {
         var2.printStackTrace();
      }

      System.out.println("[FLAG][EncuestaBean][enviarDatos][FIN]");
   }

   public void abrirDialogAction() {
      this.pregunta = new PreguntaDTO(this._usuario.getCodigo());
      this.respuesta = new RespuestaDTO(this._usuario.getCodigo());
      System.out.println("[FLAG][EncuestaBean][abrirPopupAction][FIN]");
   }

   public void agregarPreguntaActionListener(ActionEvent actionEvent) {
      if (this.validarCamporPregunta()) {
         if (!this.editarPregunta) {
            if (!this.pregunta.getRespuestas().isEmpty()) {
               this.encuesta.addPregunta(this.pregunta);
               System.out.println(getMensaje("mintrabajo.message.siAgregarPregunta", "mintrabajo"));
            } else {
               System.out.println(getMensaje("mintrabajo.message.noAgregarPregunta", "mintrabajo"));
            }
         } else {
            this.pregunta.setCodigoUsuarioCambio(this._usuario.getCodigo());
         }

         this.pregunta = new PreguntaDTO(this._usuario.getCodigo());
         this.editarPregunta = Boolean.FALSE;
      }

   }

   public void agregarEliminarYOEditarRespuestaActionListener(ActionEvent event) {
      if (this.validarCamposRespuesta()) {
         if (!this.editarRespuesta) {
            this.pregunta.addRespuesta(this.respuesta);
         } else {
            this.respuesta.setCodigoUsuarioCambio(this._usuario.getCodigo());
         }

         this.respuesta = new RespuestaDTO(this._usuario.getCodigo());
         this.editarRespuesta = Boolean.FALSE;
         System.out.println(getMensaje("mintrabajo.message.siAgregarRespuesta", "mintrabajo"));
      }

   }

   public boolean validarCamporPregunta() {
      this.camposValidados = true;
      this.closeDialogQuery = "PF('wvPregunta').hide();";
      if (this.pregunta.getTitulo() == null || this.pregunta.getTitulo().isEmpty()) {
         this.camposValidados = false;
         this.tituloPregunta = false;
         this.closeDialogQuery = "";
      }

      if (this.pregunta.getNumeroOrden() == 0) {
         this.camposValidados = false;
         this.ordenPregunta = false;
         this.closeDialogQuery = "";
      }

      System.out.println("closeDialogQuery /// " + this.closeDialogQuery);
      return this.camposValidados;
   }

   public boolean validarCamposRespuesta() {
      this.valorRespuesta = true;
      this.ordenRespuesta = true;
      boolean camposValidados = true;
      if (this.respuesta.getTitulo() == null || this.respuesta.getTitulo().isEmpty()) {
         this.valorRespuesta = false;
         camposValidados = false;
      }

      if (this.respuesta.getOrden() == 0) {
         camposValidados = false;
         this.ordenRespuesta = false;
      }

      return camposValidados;
   }

   public void enviarAction(ActionEvent action) {
      this.enviarDatos();
      System.out.println("[FLAG][Encuesta][enviarActionListener][FIN]");
      this.redirigirA("paginas/encuesta/encuestaList.xhtml");
   }

   public void salirActionListener(ActionEvent actionEvent) {
      try {
         this.logout("paginas/login/login.xhtml?faces-redirect=true");
      } catch (IOException var3) {
         System.out.println("[FLAG][salirAction][CATCH]" + var3.getMessage());
      }

      System.out.println("[FLAG][Encuesta][salirActionListener][FIN]");
   }

   public void regresarAction(ActionEvent action) {
      System.out.println("[FLAG][Encuesta][regresarAction][INIC-FIN]");
      this.redirigirA("paginas/encuesta/encuestaList.xhtml");
   }

   public void eliminarRespuestaAction(ActionEvent event) {
      this.respuesta = (RespuestaDTO)event.getComponent().getAttributes().get("attEliminar");
      this.pregunta.eliminarRespuesta(this.respuesta);
   }

   public void editarRespuesta(ActionEvent event) {
      this.respuesta = (RespuestaDTO)event.getComponent().getAttributes().get("attEditar");
      this.editarRespuesta = Boolean.TRUE;
      System.out.println("EDITAR A: " + this.respuesta.getTitulo());
   }

   public void eliminarPreguntaAction(ActionEvent event) {
      this.pregunta = (PreguntaDTO)event.getComponent().getAttributes().get("attEliminar");
      this.administradorEncuesta.eliminarPregunta(this.pregunta);
      this.redirigirA("paginas/encuesta/encuesta.xhtml");
   }

   public void editarPregunta(ActionEvent event) {
      this.pregunta = (PreguntaDTO)event.getComponent().getAttributes().get("attEditar");
      this.editarPregunta = Boolean.TRUE;
      System.out.println("EDITAR A: " + this.pregunta.getTitulo());
   }

   public EncuestaDTO getEncuesta() {
      return this.encuesta;
   }

   public void setEncuesta(EncuestaDTO encuesta) {
      this.encuesta = encuesta;
   }

   public PreguntaDTO getPregunta() {
      return this.pregunta;
   }

   public void setPregunta(PreguntaDTO pregunta) {
      this.pregunta = pregunta;
   }

   public RespuestaDTO getRespuesta() {
      return this.respuesta;
   }

   public void setRespuesta(RespuestaDTO respuesta) {
      this.respuesta = respuesta;
   }

   public Boolean getEditarRespuesta() {
      return this.editarRespuesta;
   }

   public void setEditarRespuesta(Boolean editarRespuesta) {
      this.editarRespuesta = editarRespuesta;
   }

   public Boolean getEditarPregunta() {
      return this.editarPregunta;
   }

   public void setEditarPregunta(Boolean editarPregunta) {
      this.editarPregunta = editarPregunta;
   }

   public UsuarioDTO getUsuario() {
      return this._usuario;
   }

   public boolean getTituloPregunta() {
      return this.tituloPregunta;
   }

   public boolean getOrdenPregunta() {
      return this.ordenPregunta;
   }

   public boolean getValorRespuesta() {
      return this.valorRespuesta;
   }

   public boolean getOrdenRespuesta() {
      return this.ordenRespuesta;
   }

   public boolean getCamposValidados() {
      return this.camposValidados;
   }

   public String getCloseDialogQuery() {
      return this.closeDialogQuery;
   }
}

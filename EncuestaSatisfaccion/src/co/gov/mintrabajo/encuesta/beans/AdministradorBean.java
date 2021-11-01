package co.gov.mintrabajo.encuesta.beans;

import co.gov.mintrabajo.encuesta.beans.base.BeanBase;
import co.gov.mintrabajo.encuesta.dto.EncuestaDTO;
import co.gov.mintrabajo.encuesta.dto.UsuarioDTO;
import co.gov.mintrabajo.encuesta.servicios.impl.IAdministradorEncuesta;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(
   name = "administradorBean"
)
public class AdministradorBean extends BeanBase {
   private static final long serialVersionUID = 1L;
   @EJB
   IAdministradorEncuesta administradorEncuesta;
   private List<EncuestaDTO> encuestas;
   private EncuestaDTO encuesta;
   private Boolean filaSeleccionada;
   private static boolean esAdmon = false;

   @PostConstruct
   public void cargarDatos() {
      esAdmon = false;

      try {
         if (this.esSesionValida()) {
            this.encuestas = this.administradorEncuesta.consultarEncuestas();
            this.filaSeleccionada = Boolean.FALSE;
         } else {
            this.redirigirA("paginas/login/login.xhtml?faces-redirect=true");
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public void enviarDatos() {
   }

   protected void agregarYOEditarEncuesta(String bandera) {
      System.out.println("IDENTIFICADOR: " + bandera);
      if (bandera != null) {
         if (bandera.equals(getMensaje("mintrabajo.label.agregar", "mintrabajo"))) {
            this.setParametroSession("sesionAccion", "nuevo");
         } else if (bandera.equals(getMensaje("mintrabajo.label.editar", "mintrabajo"))) {
            this.setParametroSession("sesionEncuesta", this.encuesta);
            this.setParametroSession("sesionAccion", "editar");
         }

         this.redirigirA("paginas/encuesta/encuesta.xhtml");
      }

   }

   public void agregarEncuesta(ActionEvent action) {
      this.agregarYOEditarEncuesta(getMensaje("mintrabajo.label.agregar", "mintrabajo"));
   }

   public void editarEncuesta(ActionEvent action) {
      this.agregarYOEditarEncuesta(getMensaje("mintrabajo.label.editar", "mintrabajo"));
   }

   public void onRowSelect(SelectEvent event) {
      this.filaSeleccionada = this.encuesta != null;
   }

   public void salirAction(ActionEvent action) {
      this.invalidateSession();
      this.redirigirA("paginas/login/login.xhtml?faces-redirect=true");
   }

   public List<EncuestaDTO> getEncuestas() {
      return this.encuestas;
   }

   public void setEncuestas(List<EncuestaDTO> encuestas) {
      this.encuestas = encuestas;
   }

   public EncuestaDTO getEncuesta() {
      return this.encuesta;
   }

   public void setEncuesta(EncuestaDTO encuesta) {
      this.encuesta = encuesta;
   }

   public Boolean getFilaSeleccionada() {
      return this.filaSeleccionada;
   }

   public void setFilaSeleccionada(Boolean filaSeleccionada) {
      this.filaSeleccionada = filaSeleccionada;
   }

   public boolean isEsAdmon() {
      return ((UsuarioDTO)this.getParametroSession("userLogin")).isEsAdmon();
   }

   public void setEsAdmon(boolean esAdmon) {
      AdministradorBean.esAdmon = esAdmon;
   }
}

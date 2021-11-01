package co.gov.mintrabajo.encuesta.dto;

import co.gov.mintrabajo.encuesta.common.enums.EstadosEncuesta;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class RespuestaEncuestaDTO implements Serializable {
   private static final long serialVersionUID = 1L;
   private Long codigo;
   private Long codigoPQR;
   private Long codigoUsuarioCreo;
   private Date fechaCreacion;
   private Date fechaRealizacion;
   private Date fechaModificacion;
   private EstadosEncuesta estado;
   private EncuestaDTO encuesta;

   public RespuestaEncuestaDTO() {
      this.encuesta = new EncuestaDTO();
   }

   public RespuestaEncuestaDTO(Long codigoPQR, Long codigoUsuarioCreo, EncuestaDTO encuesta) {
      this.codigoPQR = codigoPQR;
      this.codigoUsuarioCreo = codigoUsuarioCreo;
      this.encuesta = encuesta;
      this.estado = this.asignarEstadoResultadoEncuesta();
      this.fechaCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
   }

   protected EstadosEncuesta asignarEstadoResultadoEncuesta() {
      if (this.encuesta == null) {
         return null;
      } else {
         Iterator var2 = this.encuesta.getPreguntas().iterator();

         while(var2.hasNext()) {
            PreguntaDTO pregunta = (PreguntaDTO)var2.next();
            if (pregunta.getCodigoRespuestaSeleccionada() == null) {
               return EstadosEncuesta.INCOMPLETA;
            }
         }

         return EstadosEncuesta.COMPLETA;
      }
   }

   public Long getCodigo() {
      return this.codigo;
   }

   public void setCodigo(Long codigo) {
      this.codigo = codigo;
   }

   public Long getCodigoPQR() {
      return this.codigoPQR;
   }

   public void setCodigoPQR(Long codigoPQR) {
      this.codigoPQR = codigoPQR;
   }

   public Long getCodigoUsuarioCreo() {
      return this.codigoUsuarioCreo;
   }

   public void setCodigoUsuarioCreo(Long codigoUsuarioCreo) {
      this.codigoUsuarioCreo = codigoUsuarioCreo;
   }

   public Date getFechaCreacion() {
      return this.fechaCreacion;
   }

   public void setFechaCreacion(Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   public Date getFechaModificacion() {
      return this.fechaModificacion;
   }

   public void setFechaModificacion(Date fechaModificacion) {
      this.fechaModificacion = fechaModificacion;
   }

   public EncuestaDTO getEncuesta() {
      return this.encuesta;
   }

   public void setEncuesta(EncuestaDTO encuesta) {
      this.encuesta = encuesta;
   }

   public Date getFechaRealizacion() {
      return this.fechaRealizacion;
   }

   public void setFechaRealizacion(Date fechaRealizacion) {
      this.fechaRealizacion = fechaRealizacion;
   }

   public EstadosEncuesta getEstado() {
      return this.estado;
   }

   public void setEstado(EstadosEncuesta estado) {
      this.estado = estado;
   }
}

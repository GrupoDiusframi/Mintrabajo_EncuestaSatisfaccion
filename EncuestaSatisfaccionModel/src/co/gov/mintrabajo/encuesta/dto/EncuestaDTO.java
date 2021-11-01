package co.gov.mintrabajo.encuesta.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EncuestaDTO implements Serializable {
   private static final long serialVersionUID = 1L;
   private Long codigo;
   private String titulo;
   private String subtitulo;
   private List<PreguntaDTO> preguntas;
   private Long codigoUsuarioCreador;
   private Long codigoUsuarioModifico;
   private Date fechaCreacion;
   private Date fechaModificacion;

   public EncuestaDTO() {
      this.preguntas = new ArrayList(0);
   }

   public EncuestaDTO(Long codigoUsuarioCreador) {
      this.codigoUsuarioCreador = codigoUsuarioCreador;
   }

   public EncuestaDTO(Long codigoEncuesta, Date fechaCreacion, Date fechaModificacion, String titulo) {
      this.codigo = codigoEncuesta;
      this.fechaCreacion = fechaCreacion;
      this.fechaModificacion = fechaModificacion;
      this.titulo = titulo;
   }

   public EncuestaDTO(Long codigoEncuesta, String titulo, String subTitulo, Long codigoUsuarioCreador, Date fechaCreacion, Long codigoUsuarioCambio, Date fechaModificacion, List<PreguntaDTO> preguntas) {
      this.codigo = codigoEncuesta;
      this.titulo = titulo;
      this.subtitulo = subTitulo;
      this.codigoUsuarioCreador = codigoUsuarioCreador;
      this.fechaCreacion = fechaCreacion;
      this.codigoUsuarioModifico = codigoUsuarioCambio;
      this.fechaModificacion = fechaModificacion;
      this.preguntas = preguntas;
   }

   public void addPregunta(PreguntaDTO pregunta) {
      if (this.preguntas == null) {
         this.preguntas = new ArrayList(0);
      }

      this.preguntas.add(pregunta);
   }

   public void removePregunta() {
   }

   public Long getCodigo() {
      return this.codigo;
   }

   public void setCodigo(Long codigo) {
      this.codigo = codigo;
   }

   public String getTitulo() {
      return this.titulo;
   }

   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }

   public List<PreguntaDTO> getPreguntas() {
      return this.preguntas;
   }

   public String getNumeroRespuesta(int numero) {
      String informacion = ((RespuestaDTO)((PreguntaDTO)this.preguntas.get(numero)).getRespuestas().get(numero)).getTitulo();
      return informacion;
   }

   public void setPreguntas(List<PreguntaDTO> preguntas) {
      this.preguntas = preguntas;
   }

   public Long getCodigoUsuarioCreador() {
      return this.codigoUsuarioCreador;
   }

   public void setCodigoUsuarioCreador(Long codigoUsuarioCreador) {
      this.codigoUsuarioCreador = codigoUsuarioCreador;
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

   public Long getCodigoUsuarioModifico() {
      return this.codigoUsuarioModifico;
   }

   public void setCodigoUsuarioModifico(Long codigoUsuarioModifico) {
      this.codigoUsuarioModifico = codigoUsuarioModifico;
   }

   public String getSubtitulo() {
      return this.subtitulo;
   }

   public void setSubtitulo(String subtitulo) {
      this.subtitulo = subtitulo;
   }
}

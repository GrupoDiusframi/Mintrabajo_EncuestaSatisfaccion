package co.gov.mintrabajo.encuesta.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PreguntaDTO implements Serializable {
   private static final long serialVersionUID = 1L;
   private Long codigo;
   private String titulo;
   private Integer numeroOrden;
   private String valorTipoPregunta = "1";
   private String indicadorEstaActivo;
   private Long codigoUsuarioCreador;
   private Long codigoUsuarioCambio;
   private Date fechaCreacion;
   private Date fechaModificacion;
   private List<RespuestaDTO> respuestas;
   private String codigoRespuestaSeleccionada;

   public PreguntaDTO() {
      this.respuestas = new ArrayList(0);
   }

   public PreguntaDTO(Long codigo, String titulo, Integer numeroOrden, String valorTipoPregunta, String indicadorEstaActivo, Long codigoUsuarioCreador, Date fechaCreacion, Date fechaModificacion, List<RespuestaDTO> respuestas) {
      this.codigo = codigo;
      this.titulo = titulo;
      this.numeroOrden = numeroOrden;
      this.valorTipoPregunta = valorTipoPregunta;
      this.indicadorEstaActivo = indicadorEstaActivo;
      this.codigoUsuarioCreador = codigoUsuarioCreador;
      this.respuestas = respuestas;
      this.fechaCreacion = fechaCreacion;
      this.fechaModificacion = fechaModificacion;
   }

   public PreguntaDTO(Long codigoUsuarioCreador) {
      this.codigoUsuarioCreador = codigoUsuarioCreador;
   }

   public void addRespuesta(RespuestaDTO respuesta) {
      if (this.respuestas == null) {
         this.respuestas = new ArrayList(0);
      }

      this.respuestas.add(respuesta);
   }

   public void eliminarRespuesta(RespuestaDTO respuesta) {
      this.respuestas.remove(respuesta);
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

   public List<RespuestaDTO> getRespuestas() {
      return this.respuestas;
   }

   public void setRespuestas(List<RespuestaDTO> respuestas) {
      this.respuestas = respuestas;
   }

   public Long getCodigoUsuarioCreador() {
      return this.codigoUsuarioCreador;
   }

   public void setCodigoUsuarioCreador(Long codigoUsuarioCreador) {
      this.codigoUsuarioCreador = codigoUsuarioCreador;
   }

   public Integer getNumeroOrden() {
      return this.numeroOrden;
   }

   public void setNumeroOrden(Integer numeroOrden) {
      this.numeroOrden = numeroOrden;
   }

   public String getValorTipoPregunta() {
      return this.valorTipoPregunta;
   }

   public void setValorTipoPregunta(String valorTipoPregunta) {
      this.valorTipoPregunta = valorTipoPregunta;
   }

   public String getIndicadorEstaActivo() {
      return this.indicadorEstaActivo;
   }

   public void setIndicadorEstaActivo(String indicadorEstaActivo) {
      this.indicadorEstaActivo = indicadorEstaActivo;
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

   public String getCodigoRespuestaSeleccionada() {
      return this.codigoRespuestaSeleccionada;
   }

   public void setCodigoRespuestaSeleccionada(String codigoRespuestaSeleccionada) {
      this.codigoRespuestaSeleccionada = codigoRespuestaSeleccionada;
   }

   public Long getCodigoUsuarioCambio() {
      return this.codigoUsuarioCambio;
   }

   public void setCodigoUsuarioCambio(Long codigoUsuarioCambio) {
      this.codigoUsuarioCambio = codigoUsuarioCambio;
   }
}

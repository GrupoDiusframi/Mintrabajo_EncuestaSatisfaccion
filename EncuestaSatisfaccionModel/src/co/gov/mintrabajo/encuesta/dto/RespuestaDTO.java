package co.gov.mintrabajo.encuesta.dto;

import java.io.Serializable;
import java.util.Date;

public class RespuestaDTO implements Serializable {
   private static final long serialVersionUID = 1L;
   private Long codigo;
   private String codigoCast;
   private String titulo;
   private Integer orden;
   private Long codigoUsuarioCreador;
   private Long codigoUsuarioCambio;
   private String indicadorEstaActivo;
   private Date fechaCreacion;
   private Date fechaModificacion;

   public RespuestaDTO() {
   }

   public RespuestaDTO(Long codigoUsuarioCreador) {
      this.codigoUsuarioCreador = codigoUsuarioCreador;
   }

   public RespuestaDTO(Long codigo, String titulo, Integer orden, Long codigoUsuarioCreador, Date fechaCreacion, Date fechaModificacion) {
      this.codigo = codigo;
      this.titulo = titulo;
      this.orden = orden;
      this.codigoUsuarioCreador = codigoUsuarioCreador;
      this.fechaCreacion = fechaCreacion;
      this.fechaModificacion = fechaModificacion;
      this.codigoCast = Long.toString(codigo);
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

   public Integer getOrden() {
      return this.orden;
   }

   public void setOrden(Integer orden) {
      this.orden = orden;
   }

   public Long getCodigoUsuarioCreador() {
      return this.codigoUsuarioCreador;
   }

   public void setCodigoUsuarioCreador(Long codigoUsuarioCreador) {
      this.codigoUsuarioCreador = codigoUsuarioCreador;
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

   public String getCodigoCast() {
      return this.codigoCast;
   }

   public void setCodigoCast(String codigoCast) {
      this.codigoCast = codigoCast;
   }

   public Long getCodigoUsuarioCambio() {
      return this.codigoUsuarioCambio;
   }

   public void setCodigoUsuarioCambio(Long codigoUsuarioCambio) {
      this.codigoUsuarioCambio = codigoUsuarioCambio;
   }
}

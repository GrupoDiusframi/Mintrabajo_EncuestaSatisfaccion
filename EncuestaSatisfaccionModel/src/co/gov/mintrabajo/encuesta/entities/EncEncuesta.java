package co.gov.mintrabajo.encuesta.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(
   name = "ENC_ENCUESTA",
   schema = "SGD"
)
@NamedQueries({@NamedQuery(
   name = "EncEncuesta.findAll",
   query = "SELECT e FROM EncEncuesta e ORDER BY e.fecCreacion DESC"
), @NamedQuery(
   name = "EncEncuesta.buscarEncuestaPorId",
   query = "SELECT e FROM EncEncuesta e WHERE e.idEncuesta = ?1 ORDER BY e.fecCreacion"
)})
public class EncEncuesta implements Serializable {
   private static final long serialVersionUID = 1L;
   public static final String JPQL_BUSCAR_TODAS = "EncEncuesta.findAll";
   public static final String JPQL_BUSCAR_ENCUESTA_POR_CODIGO = "EncEncuesta.buscarEncuestaPorId";
   @Id
   @SequenceGenerator(
      name = "ENCUESTA_SEQ",
      sequenceName = "SENC_ENCUESTA"
   )
   @GeneratedValue(
      generator = "ENCUESTA_SEQ",
      strategy = GenerationType.SEQUENCE
   )
   @Basic(
      optional = false
   )
   @Column(
      name = "IDE_ENCUESTA",
      nullable = false
   )
   private Long idEncuesta;
   @Temporal(TemporalType.TIMESTAMP)
   @Column(
      name = "FEC_CAMBIO"
   )
   private Date fecCambio;
   @Temporal(TemporalType.TIMESTAMP)
   @Column(
      name = "FEC_CREACION",
      nullable = false
   )
   private Date fecCreacion;
   @Column(
      name = "IDE_USUARIO_CAMBIO"
   )
   private Long idUsuarioCambio;
   @Column(
      name = "IDE_USUARIO_CREO",
      nullable = false
   )
   private Long idUsuarioCreo;
   @Column(
      name = "TITULO",
      length = 100
   )
   private String titulo;
   @Column(
      name = "SUBTITULO",
      length = 300
   )
   private String subtitulo;
   @Column(
      name = "IDE_UUID"
   )
   private String idUuid = "1";
   @Column(
      name = "NIV_ESCRITURA"
   )
   private Integer nivEscritura = 1;
   @Column(
      name = "NIV_LECTURA"
   )
   private Integer nivLectura = 1;

   public EncEncuesta() {
      this.idEncuesta = null;
      this.titulo = "";
      this.subtitulo = "";
      this.fecCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
   }

   public EncEncuesta(Long idUsuarioCreo) {
      this.idEncuesta = null;
      this.fecCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
      this.idUsuarioCreo = idUsuarioCreo;
   }

   public EncEncuesta(Long idEncuesta, Long idUsuarioCreo) {
      this.idEncuesta = idEncuesta;
      this.fecCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
      this.idUsuarioCreo = idUsuarioCreo;
   }

   public EncEncuesta(Long idUsuarioCreo, String titulo, String subtitulo) {
      this.idEncuesta = null;
      this.titulo = titulo;
      this.subtitulo = subtitulo;
      this.fecCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
      this.idUsuarioCreo = idUsuarioCreo;
   }

   public EncEncuesta(Long idEncuesta, Long idUsuarioCreo, Date fecCreacion, Long idUsuarioCambio, Date fecCambio) {
      this.idEncuesta = idEncuesta;
      this.fecCreacion = (Date)(fecCreacion != null ? fecCreacion : new Timestamp(Calendar.getInstance().getTimeInMillis()));
      this.idUsuarioCambio = idUsuarioCambio;
      this.fecCambio = (Date)(fecCambio != null ? fecCambio : new Timestamp(Calendar.getInstance().getTimeInMillis()));
      this.idUsuarioCreo = idUsuarioCreo;
   }

   public EncEncuesta(Long idEncuesta, String titulo, String subtitulo, Long idUsuarioCreo, Date fecCreacion, Long idUsuarioCambio, Date fecCambio) {
      this.idEncuesta = idEncuesta;
      this.fecCreacion = (Date)(fecCreacion != null ? fecCreacion : new Timestamp(Calendar.getInstance().getTimeInMillis()));
      this.idUsuarioCambio = idUsuarioCambio;
      this.fecCambio = (Date)(fecCambio != null ? fecCambio : new Timestamp(Calendar.getInstance().getTimeInMillis()));
      this.idUsuarioCreo = idUsuarioCreo;
      this.titulo = titulo;
      this.subtitulo = subtitulo;
   }

   public Long getIdEncuesta() {
      return this.idEncuesta;
   }

   public void setIdEncuesta(Long idEncuesta) {
      this.idEncuesta = idEncuesta;
   }

   public Date getFecCambio() {
      return this.fecCambio;
   }

   public void setFecCambio(Date fecCambio) {
      this.fecCambio = fecCambio;
   }

   public Date getFecCreacion() {
      return this.fecCreacion;
   }

   public void setFecCreacion(Date fecCreacion) {
      this.fecCreacion = fecCreacion;
   }

   public Long getIdUsuarioCambio() {
      return this.idUsuarioCambio;
   }

   public void setIdUsuarioCambio(Long idUsuarioCambio) {
      this.idUsuarioCambio = idUsuarioCambio;
   }

   public Long getIdUsuarioCreo() {
      return this.idUsuarioCreo;
   }

   public void setIdUsuarioCreo(Long idUsuarioCreo) {
      this.idUsuarioCreo = idUsuarioCreo;
   }

   public String getIdUuid() {
      return this.idUuid;
   }

   public void setIdUuid(String idUuid) {
      this.idUuid = idUuid;
   }

   public Integer getNivEscritura() {
      return this.nivEscritura;
   }

   public void setNivEscritura(Integer nivEscritura) {
      this.nivEscritura = nivEscritura;
   }

   public Integer getNivLectura() {
      return this.nivLectura;
   }

   public void setNivLectura(Integer nivLectura) {
      this.nivLectura = nivLectura;
   }

   public String getTitulo() {
      return this.titulo;
   }

   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }

   public String getSubtitulo() {
      return this.subtitulo;
   }

   public void setSubtitulo(String subtitulo) {
      this.subtitulo = subtitulo;
   }
}

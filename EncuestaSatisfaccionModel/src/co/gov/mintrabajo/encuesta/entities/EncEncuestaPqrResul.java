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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(
   name = "ENC_ENCUESTA_PQR_RESUL",
   schema = "SGD"
)
@NamedQueries({@NamedQuery(
   name = "EncEncuestaPqrResul.findAll",
   query = "SELECT e FROM EncEncuestaPqrResul e"
), @NamedQuery(
   name = "EncEncuestaPqrResul.findOne",
   query = "SELECT e FROM EncEncuestaPqrResul e WHERE e.registro = ?1"
)})
public class EncEncuestaPqrResul implements Serializable {
   private static final long serialVersionUID = 1L;
   public static final String JPQL_BUSCAR_TODAS = "EncEncuestaPqrResul.findAll";
   public static final String JPQL_BUSCAR_POR_CODIGO = "EncEncuestaPqrResul.findOne";
   @Id
   @SequenceGenerator(
      name = "ENCUESTA_PQR_RESUL_SEQ",
      sequenceName = "SENC_ENCUESTA_PQR_RESUL"
   )
   @GeneratedValue(
      generator = "ENCUESTA_PQR_RESUL_SEQ",
      strategy = GenerationType.SEQUENCE
   )
   @Basic(
      optional = false
   )
   @Column(
      name = "IDE_ENCUESTA_PQR_RESUL"
   )
   private Long idEncuestaPqrResul;
   @Temporal(TemporalType.TIMESTAMP)
   @Column(
      name = "FEC_ENCUESTA"
   )
   private Date fecEncuesta;
   @Temporal(TemporalType.TIMESTAMP)
   @Column(
      name = "FEC_CAMBIO"
   )
   private Date fecCambio;
   @Temporal(TemporalType.TIMESTAMP)
   @Column(
      name = "FEC_CREACION"
   )
   private Date fecCreacion;
   @Column(
      name = "IDE_USUARIO_CAMBIO"
   )
   private Long idUsuarioCambio;
   @Column(
      name = "IDE_USUARIO_CREO"
   )
   private Long idUsuarioCreo;
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
   @Column(
      name = "IDE_REGISTRO"
   )
   private Long registro;
   @JoinColumn(
      name = "IDE_ENCUESTA",
      referencedColumnName = "IDE_ENCUESTA"
   )
   @ManyToOne
   private EncEncuesta encuesta;
   @JoinColumn(
      name = "IDE_CONS_ESTADO",
      referencedColumnName = "IDE_CON_CONSTANTE"
   )
   @ManyToOne
   private ConConstante estado;

   public EncEncuestaPqrResul() {
   }

   public EncEncuestaPqrResul(EncEncuesta encuesta, ConConstante estado, Long idUsuarioCreo, Long idPQR, Date fechaCreacion) {
      this.idEncuestaPqrResul = null;
      this.encuesta = encuesta;
      this.estado = estado;
      this.idUsuarioCreo = idUsuarioCreo;
      this.registro = idPQR;
      this.fecCreacion = fechaCreacion;
      this.fecEncuesta = new Timestamp(Calendar.getInstance().getTimeInMillis());
   }

   public Long getIdEncuestaPqrResul() {
      return this.idEncuestaPqrResul;
   }

   public void setIdEncuestaPqrResul(Long idEncuestaPqrResul) {
      this.idEncuestaPqrResul = idEncuestaPqrResul;
   }

   public Date getFecEncuesta() {
      return this.fecEncuesta;
   }

   public void setFecEncuesta(Date fecEncuesta) {
      this.fecEncuesta = fecEncuesta;
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

   public Long getRegistro() {
      return this.registro;
   }

   public void setRegistro(Long registro) {
      this.registro = registro;
   }

   public EncEncuesta getEncuesta() {
      return this.encuesta;
   }

   public void setEncuesta(EncEncuesta encuesta) {
      this.encuesta = encuesta;
   }

   public ConConstante getEstado() {
      return this.estado;
   }

   public void setEstado(ConConstante estado) {
      this.estado = estado;
   }
}

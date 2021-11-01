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
   name = "ENC_ENC_PQR_RES_RTA"
)
@NamedQueries({@NamedQuery(
   name = "EncEncPqrResRta.findAll",
   query = "SELECT e FROM EncEncPqrResRta e"
)})
public class EncEncPqrResRta implements Serializable {
   private static final long serialVersionUID = 1L;
   public static final String JPQL_BUSCAR_TODAS = "EncEncPqrResRta.findAll";
   @Id
   @SequenceGenerator(
      name = "ENC_ENC_PQR_RES_RTA_SEQ",
      sequenceName = "SENC_ENC_PQR_RES_RTA"
   )
   @GeneratedValue(
      generator = "ENC_ENC_PQR_RES_RTA_SEQ",
      strategy = GenerationType.SEQUENCE
   )
   @Basic(
      optional = false
   )
   @Column(
      name = "IDE_ENC_PQR_RES_RTA"
   )
   private Long idEncPqrResRta;
   @Column(
      name = "VAL_REPUESTA"
   )
   private String valRespuesta;
   @Temporal(TemporalType.DATE)
   @Column(
      name = "FEC_CAMBIO"
   )
   private Date fecCambio;
   @Temporal(TemporalType.DATE)
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
   @JoinColumn(
      name = "IDE_ENCUESTA_PREGUN",
      referencedColumnName = "IDE_ENCUESTA_PREGUN"
   )
   @ManyToOne
   private EncEncuestaPregunta pregunta;
   @JoinColumn(
      name = "IDE_ENCUESTA_PQR_RESUL",
      referencedColumnName = "IDE_ENCUESTA_PQR_RESUL"
   )
   @ManyToOne
   private EncEncuestaPqrResul resultado;
   @JoinColumn(
      name = "IDE_ENCUESTA_PRE_RTA",
      referencedColumnName = "IDE_ENCUESTA_PRE_RTA"
   )
   @ManyToOne
   private EncEncuestaPreRta respuesta;

   public EncEncPqrResRta() {
   }

   public EncEncPqrResRta(EncEncuestaPregunta pregunta, EncEncuestaPqrResul resultado, EncEncuestaPreRta respuesta, Long idUsuarioCreo) {
      this.idEncPqrResRta = null;
      this.pregunta = pregunta;
      this.resultado = resultado;
      this.respuesta = respuesta;
      this.idUsuarioCreo = idUsuarioCreo;
      this.fecCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
   }

   public Long getIdEncPqrResRta() {
      return this.idEncPqrResRta;
   }

   public void setIdEncPqrResRta(Long idEncPqrResRta) {
      this.idEncPqrResRta = idEncPqrResRta;
   }

   public String getValRespuesta() {
      return this.valRespuesta;
   }

   public void setValRespuesta(String valRespuesta) {
      this.valRespuesta = valRespuesta;
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

   public EncEncuestaPregunta getPregunta() {
      return this.pregunta;
   }

   public void setPregunta(EncEncuestaPregunta pregunta) {
      this.pregunta = pregunta;
   }

   public EncEncuestaPqrResul getResultado() {
      return this.resultado;
   }

   public void setResultado(EncEncuestaPqrResul resultado) {
      this.resultado = resultado;
   }

   public EncEncuestaPreRta getRespuesta() {
      return this.respuesta;
   }

   public void setRespuesta(EncEncuestaPreRta respuesta) {
      this.respuesta = respuesta;
   }
}

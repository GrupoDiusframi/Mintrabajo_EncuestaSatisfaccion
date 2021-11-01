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
   name = "ENC_ENCUESTA_PRE_RTA"
)
@NamedQueries({@NamedQuery(
   name = "EncEncuestaPreRta.findAll",
   query = "SELECT e FROM EncEncuestaPreRta e WHERE e.indEstaActiva = '1' ORDER BY e.nroOrden DESC"
), @NamedQuery(
   name = "EncEncuestaPreRta.buscarRespuestaPorIdPregunta",
   query = "SELECT e FROM EncEncuestaPreRta e WHERE e.pregunta.idEncuestaPregunta = ?1 AND e.indEstaActiva = '1' ORDER BY e.nroOrden ASC"
), @NamedQuery(
   name = "EncEncuestaPreRta.eliminarRespuestaPorIdPregunta",
   query = "DELETE FROM EncEncuestaPreRta e WHERE e.pregunta.idEncuestaPregunta = ?1"
)})
public class EncEncuestaPreRta implements Serializable {
   private static final long serialVersionUID = 1L;
   public static final String JPQL_BUSCAR_TODAS = "EncEncuestaPreRta.findAll";
   public static final String JPQL_BUSCAR_RESPUESTA_POR_CODIGO_PREGUNTA = "EncEncuestaPreRta.buscarRespuestaPorIdPregunta";
   public static final String JPQL_ELIMINAR_RESPUESTA_POR_CODIGO_PREGUNTA = "EncEncuestaPreRta.eliminarRespuestaPorIdPregunta";
   @Id
   @SequenceGenerator(
      name = "ENCUESTA_PRE_RTA_SEQ",
      sequenceName = "SENC_ENCUESTA_PRE_RTA"
   )
   @GeneratedValue(
      generator = "ENCUESTA_PRE_RTA_SEQ",
      strategy = GenerationType.SEQUENCE
   )
   @Basic(
      optional = false
   )
   @Column(
      name = "IDE_ENCUESTA_PRE_RTA",
      nullable = false
   )
   private Long idEncuestaPreRta;
   @Column(
      name = "IND_ESTA_ACTIVA",
      nullable = false,
      length = 1
   )
   private String indEstaActiva;
   @Column(
      name = "NRO_ORDEN",
      nullable = false
   )
   private Integer nroOrden;
   @Column(
      name = "VAL_PREGUNTA",
      nullable = false,
      length = 500
   )
   private String valPregunta;
   @Column(
      name = "IDE_USUARIO_CAMBIO"
   )
   private Long ideUsuarioCambio;
   @Column(
      name = "IDE_USUARIO_CREO",
      nullable = false
   )
   private Long ideUsuarioCreo;
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
      name = "IDE_UUID"
   )
   private String ideUuid = "1";
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

   public EncEncuestaPreRta() {
      this.idEncuestaPreRta = null;
      this.indEstaActiva = "1";
      this.fecCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
   }

   public EncEncuestaPreRta(String valPregunta, Long ideUsuarioCreo, EncEncuestaPregunta pregunta, Integer nroOrden) {
      this.idEncuestaPreRta = null;
      this.indEstaActiva = "1";
      this.ideUsuarioCreo = ideUsuarioCreo;
      this.valPregunta = valPregunta;
      this.pregunta = pregunta;
      this.nroOrden = nroOrden;
      this.fecCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
   }

   public EncEncuestaPreRta(Long idEncuestaPreRta, String valPregunta, Integer nroOrden, Long ideUsuarioCreo, Date fechaCreo, Long ideUsuarioCambio, Date fechaCambio, EncEncuestaPregunta pregunta) {
      this.idEncuestaPreRta = idEncuestaPreRta;
      this.ideUsuarioCreo = ideUsuarioCreo;
      this.ideUsuarioCambio = ideUsuarioCambio;
      this.valPregunta = valPregunta;
      this.pregunta = pregunta;
      this.nroOrden = nroOrden;
      this.fecCreacion = (Date)(fechaCreo != null ? fechaCreo : new Timestamp(Calendar.getInstance().getTimeInMillis()));
      this.fecCambio = (Date)(fechaCambio != null ? fechaCambio : new Timestamp(Calendar.getInstance().getTimeInMillis()));
      this.indEstaActiva = "1";
   }

   public Long getIdEncuestaPreRta() {
      return this.idEncuestaPreRta;
   }

   public void setIdEncuestaPreRta(Long idEncuestaPreRta) {
      this.idEncuestaPreRta = idEncuestaPreRta;
   }

   public String getIndEstaActiva() {
      return this.indEstaActiva;
   }

   public void setIndEstaActiva(String indEstaActiva) {
      this.indEstaActiva = indEstaActiva;
   }

   public Integer getNroOrden() {
      return this.nroOrden;
   }

   public void setNroOrden(Integer nroOrden) {
      this.nroOrden = nroOrden;
   }

   public String getValPregunta() {
      return this.valPregunta;
   }

   public void setValPregunta(String valPregunta) {
      this.valPregunta = valPregunta;
   }

   public Long getIdeUsuarioCambio() {
      return this.ideUsuarioCambio;
   }

   public void setIdeUsuarioCambio(Long ideUsuarioCambio) {
      this.ideUsuarioCambio = ideUsuarioCambio;
   }

   public Long getIdeUsuarioCreo() {
      return this.ideUsuarioCreo;
   }

   public void setIdeUsuarioCreo(Long ideUsuarioCreo) {
      this.ideUsuarioCreo = ideUsuarioCreo;
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

   public String getIdeUuid() {
      return this.ideUuid;
   }

   public void setIdeUuid(String ideUuid) {
      this.ideUuid = ideUuid;
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
}

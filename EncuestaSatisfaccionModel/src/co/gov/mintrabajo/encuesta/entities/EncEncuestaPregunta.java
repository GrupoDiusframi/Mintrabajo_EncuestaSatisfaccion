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
   name = "ENC_ENCUESTA_PREGUNTA",
   schema = "SGD"
)
@NamedQueries({@NamedQuery(
   name = "EncEncuestaPregunta.findAll",
   query = "SELECT e FROM EncEncuestaPregunta e WHERE e.indEstaActiva = '1' ORDER BY e.nroOrden DESC"
), @NamedQuery(
   name = "EncEncuestaPregunta.buscarPreguntaPorIdEncuesta",
   query = "SELECT e FROM EncEncuestaPregunta e WHERE e.encuesta.idEncuesta = ?1 AND e.indEstaActiva = '1' ORDER BY e.nroOrden DESC"
), @NamedQuery(
   name = "EncEncuestaPregunta.eliminarPregunta",
   query = "DELETE FROM EncEncuestaPregunta e WHERE e.idEncuestaPregunta = ?1"
)})
public class EncEncuestaPregunta implements Serializable {
   private static final long serialVersionUID = 1L;
   public static final String JPQL_BUSCAR_TODAS = "EncEncuestaPregunta.findAll";
   public static final String JPQL_BUSCAR_PREGUNTA_POR_CODIGO_ENCUESTA = "EncEncuestaPregunta.buscarPreguntaPorIdEncuesta";
   public static final String JPQL_ELIMINAR_PREGUNTA = "EncEncuestaPregunta.eliminarPregunta";
   @Id
   @SequenceGenerator(
      name = "ENCUESTA_PREGUNTA_SEQ",
      sequenceName = "SENC_ENCUESTA_PREGUNTA"
   )
   @GeneratedValue(
      generator = "ENCUESTA_PREGUNTA_SEQ",
      strategy = GenerationType.SEQUENCE
   )
   @Basic(
      optional = false
   )
   @Column(
      name = "IDE_ENCUESTA_PREGUN",
      nullable = false
   )
   private Long idEncuestaPregunta;
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
      name = "VAL_TIP_PREGUNTA",
      nullable = false,
      length = 1
   )
   private String valTipoPregunta = "1";
   @Column(
      name = "VAL_PREGUNTA",
      nullable = false,
      length = 500
   )
   private String valPregunta;
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
      name = "IDE_ENCUESTA",
      referencedColumnName = "IDE_ENCUESTA"
   )
   @ManyToOne
   private EncEncuesta encuesta;

   public EncEncuestaPregunta() {
      this.idEncuestaPregunta = null;
      this.indEstaActiva = "1";
      this.fecCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
   }

   public EncEncuestaPregunta(Integer nroOrden, String valTipoPregunta, String indEstaActiva, EncEncuesta encuesta, String valPregunta, Long idUsuarioCreo) {
      System.out.println("[FLAG][EncEncuestaPregunta][VALORES][nroOrden: " + nroOrden + "]\n" + "[valTipoPregunta: " + valTipoPregunta + "]\n" + "[indEstaActiva: " + indEstaActiva + "]\n" + "[encuesta: " + encuesta.getIdEncuesta() + "]\n" + "[valPregunta: " + valPregunta + "]\n" + "[idUsuarioCreo: " + idUsuarioCreo + "]");
      this.idEncuestaPregunta = null;
      this.nroOrden = nroOrden;
      this.valTipoPregunta = valTipoPregunta;
      this.indEstaActiva = indEstaActiva;
      this.fecCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
      this.encuesta = encuesta;
      this.valPregunta = valPregunta;
      this.idUsuarioCreo = idUsuarioCreo;
   }

   public EncEncuestaPregunta(Long idEncuestaPregunta, Integer nroOrden, String valTipoPregunta, String valPregunta, Long idUsuarioCreo, Date fechaCreacion, Long idUsuarioCambio, Date fechaCambio, EncEncuesta encuesta) {
      System.out.println("[FLAG][EncEncuestaPregunta][VALORES][nroOrden: " + nroOrden + "]\n" + "[valTipoPregunta: " + valTipoPregunta + "]\n" + "[indEstaActiva: " + this.indEstaActiva + "]\n" + "[encuesta: " + encuesta.getIdEncuesta() + "]\n" + "[valPregunta: " + valPregunta + "]\n" + "[idUsuarioCreo: " + idUsuarioCreo + "]" + "[idUsuarioCambio: " + idUsuarioCambio + "]");
      this.idEncuestaPregunta = idEncuestaPregunta;
      this.nroOrden = nroOrden;
      this.valTipoPregunta = valTipoPregunta;
      this.fecCreacion = (Date)(fechaCreacion != null ? fechaCreacion : new Timestamp(Calendar.getInstance().getTimeInMillis()));
      this.encuesta = encuesta;
      this.valPregunta = valPregunta;
      this.idUsuarioCreo = idUsuarioCreo;
      this.idUsuarioCambio = idUsuarioCambio;
      this.fecCambio = (Date)(fechaCambio != null ? fechaCambio : new Timestamp(Calendar.getInstance().getTimeInMillis()));
      this.indEstaActiva = "1";
   }

   public EncEncuestaPregunta(Long idUsuarioCreo) {
      this.idEncuestaPregunta = null;
      this.indEstaActiva = "1";
      this.fecCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
      this.idUsuarioCreo = idUsuarioCreo;
   }

   public EncEncuestaPregunta(Long idEncuestaPregunta, Long idUsuarioCreo) {
      this.idEncuestaPregunta = idEncuestaPregunta;
      this.indEstaActiva = "1";
      this.fecCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
      this.idUsuarioCreo = idUsuarioCreo;
   }

   public Long getIdEncuestaPregunta() {
      return this.idEncuestaPregunta;
   }

   public void setIdEncuestaPregunta(Long idEncuestaPregunta) {
      this.idEncuestaPregunta = idEncuestaPregunta;
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

   public String getValTipoPregunta() {
      return this.valTipoPregunta;
   }

   public void setValTipoPregunta(String valTipoPregunta) {
      this.valTipoPregunta = valTipoPregunta;
   }

   public String getValPregunta() {
      return this.valPregunta;
   }

   public void setValPregunta(String valPregunta) {
      this.valPregunta = valPregunta;
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

   public EncEncuesta getEncuesta() {
      return this.encuesta;
   }

   public void setEncuesta(EncEncuesta encuesta) {
      this.encuesta = encuesta;
   }
}

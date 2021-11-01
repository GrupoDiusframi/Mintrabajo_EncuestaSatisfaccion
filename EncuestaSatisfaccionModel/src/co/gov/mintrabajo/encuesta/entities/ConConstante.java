package co.gov.mintrabajo.encuesta.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(
   name = "CON_CONSTANTE"
)
@NamedQuery(
   name = "ConConstante.findAll",
   query = "SELECT c FROM ConConstante c"
)
public class ConConstante implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @Column(
      name = "IDE_CON_CONSTANTE"
   )
   private Long idConConstante;
   @Column(
      name = "CODIGO"
   )
   private String codigo;
   @Column(
      name = "NOMBRE"
   )
   private String nombre;
   @Temporal(TemporalType.DATE)
   @Column(
      name = "FEC_CAMBIO"
   )
   private Date fecCambio;
   @Column(
      name = "IDE_CON_CONS_ID"
   )
   private Long idConConsId;
   @Column(
      name = "IDE_USUARIO_CAMBIO"
   )
   private Long idUsuarioCambio;
   @Column(
      name = "IDE_UUID"
   )
   private String idUuid;
   @Column(
      name = "NIV_ESCRITURA"
   )
   private Integer nivEscritura;
   @Column(
      name = "NIV_LECTURA"
   )
   private Integer nivLectura;

   public ConConstante() {
   }

   public ConConstante(Long idConConstante) {
      this.idConConstante = idConConstante;
   }

   public long getIdConConstante() {
      return this.idConConstante;
   }

   public void setIdeConConstante(long idConConstante) {
      this.idConConstante = idConConstante;
   }

   public String getCodigo() {
      return this.codigo;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   public Date getFecCambio() {
      return this.fecCambio;
   }

   public void setFecCambio(Date fecCambio) {
      this.fecCambio = fecCambio;
   }

   public Long getIdConConsId() {
      return this.idConConsId;
   }

   public void setIdConConsId(Long idConConsId) {
      this.idConConsId = idConConsId;
   }

   public Long getIdUsuarioCambio() {
      return this.idUsuarioCambio;
   }

   public void setIdUsuarioCambio(Long idUsuarioCambio) {
      this.idUsuarioCambio = idUsuarioCambio;
   }

   public String getIdUuid() {
      return this.idUuid;
   }

   public void setIdeUuid(String idUuid) {
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

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }
}

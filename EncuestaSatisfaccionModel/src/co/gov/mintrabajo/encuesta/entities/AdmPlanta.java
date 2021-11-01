package co.gov.mintrabajo.encuesta.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(
   name = "ADM_PLANTA"
)
@NamedQueries({@NamedQuery(
   name = "AdmPlanta.findAll",
   query = "SELECT a FROM AdmPlanta a"
), @NamedQuery(
   name = "AdmPlanta.findLogin",
   query = "SELECT a FROM AdmPlanta a WHERE upper(a.valLogin) = upper(:login)"
)})
public class AdmPlanta implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @Column(
      name = "IDE_PLANTA"
   )
   private long idePlanta;
   @Column(
      name = "COD_NIVE_KACTUS"
   )
   private String codNiveKactus;
   @Column(
      name = "FEC_CAMBIO"
   )
   private Timestamp fecCambio;
   @Column(
      name = "FEC_CREACION"
   )
   private Timestamp fecCreacion;
   @Column(
      name = "IDE_CARGO"
   )
   private BigDecimal ideCargo;
   @Column(
      name = "IDE_CONS_TIP_FUNCIONARIO"
   )
   private BigDecimal ideConsTipFuncionario;
   @Column(
      name = "IDE_ORGA_ADMI"
   )
   private BigDecimal ideOrgaAdmi;
   @Column(
      name = "IDE_TIP_DOC_IDENT"
   )
   private BigDecimal ideTipDocIdent;
   @Column(
      name = "IDE_USUARIO_CAMBIO"
   )
   private BigDecimal ideUsuarioCambio;
   @Column(
      name = "IDE_USUARIO_CREO"
   )
   private BigDecimal ideUsuarioCreo;
   @Column(
      name = "IDE_UUID"
   )
   private String ideUuid;
   @Column(
      name = "IND_ES_ACTIVO"
   )
   private String indEsActivo;
   @Column(
      name = "NIV_ESCRITURA"
   )
   private BigDecimal nivEscritura;
   @Column(
      name = "NIV_LECTURA"
   )
   private BigDecimal nivLectura;
   @Column(
      name = "NOM_FUNCIONARIO"
   )
   private String nomFuncionario;
   @Column(
      name = "NUM_IDENTIFICACION"
   )
   private String numIdentificacion;
   @Column(
      name = "NUM_NIVE_KACTUS"
   )
   private BigDecimal numNiveKactus;
   @Column(
      name = "VAL_APELLIDO1"
   )
   private String valApellido1;
   @Column(
      name = "VAL_APELLIDO2"
   )
   private String valApellido2;
   @Column(
      name = "VAL_CORR_ELECTRONICO"
   )
   private String valCorrElectronico;
   @Column(
      name = "VAL_LOGIN"
   )
   private String valLogin;

   public long getIdePlanta() {
      return this.idePlanta;
   }

   public void setIdePlanta(long idePlanta) {
      this.idePlanta = idePlanta;
   }

   public String getCodNiveKactus() {
      return this.codNiveKactus;
   }

   public void setCodNiveKactus(String codNiveKactus) {
      this.codNiveKactus = codNiveKactus;
   }

   public Timestamp getFecCambio() {
      return this.fecCambio;
   }

   public void setFecCambio(Timestamp fecCambio) {
      this.fecCambio = fecCambio;
   }

   public Timestamp getFecCreacion() {
      return this.fecCreacion;
   }

   public void setFecCreacion(Timestamp fecCreacion) {
      this.fecCreacion = fecCreacion;
   }

   public BigDecimal getIdeCargo() {
      return this.ideCargo;
   }

   public void setIdeCargo(BigDecimal ideCargo) {
      this.ideCargo = ideCargo;
   }

   public BigDecimal getIdeConsTipFuncionario() {
      return this.ideConsTipFuncionario;
   }

   public void setIdeConsTipFuncionario(BigDecimal ideConsTipFuncionario) {
      this.ideConsTipFuncionario = ideConsTipFuncionario;
   }

   public BigDecimal getIdeOrgaAdmi() {
      return this.ideOrgaAdmi;
   }

   public void setIdeOrgaAdmi(BigDecimal ideOrgaAdmi) {
      this.ideOrgaAdmi = ideOrgaAdmi;
   }

   public BigDecimal getIdeTipDocIdent() {
      return this.ideTipDocIdent;
   }

   public void setIdeTipDocIdent(BigDecimal ideTipDocIdent) {
      this.ideTipDocIdent = ideTipDocIdent;
   }

   public BigDecimal getIdeUsuarioCambio() {
      return this.ideUsuarioCambio;
   }

   public void setIdeUsuarioCambio(BigDecimal ideUsuarioCambio) {
      this.ideUsuarioCambio = ideUsuarioCambio;
   }

   public BigDecimal getIdeUsuarioCreo() {
      return this.ideUsuarioCreo;
   }

   public void setIdeUsuarioCreo(BigDecimal ideUsuarioCreo) {
      this.ideUsuarioCreo = ideUsuarioCreo;
   }

   public String getIdeUuid() {
      return this.ideUuid;
   }

   public void setIdeUuid(String ideUuid) {
      this.ideUuid = ideUuid;
   }

   public String getIndEsActivo() {
      return this.indEsActivo;
   }

   public void setIndEsActivo(String indEsActivo) {
      this.indEsActivo = indEsActivo;
   }

   public BigDecimal getNivEscritura() {
      return this.nivEscritura;
   }

   public void setNivEscritura(BigDecimal nivEscritura) {
      this.nivEscritura = nivEscritura;
   }

   public BigDecimal getNivLectura() {
      return this.nivLectura;
   }

   public void setNivLectura(BigDecimal nivLectura) {
      this.nivLectura = nivLectura;
   }

   public String getNomFuncionario() {
      return this.nomFuncionario;
   }

   public void setNomFuncionario(String nomFuncionario) {
      this.nomFuncionario = nomFuncionario;
   }

   public String getNumIdentificacion() {
      return this.numIdentificacion;
   }

   public void setNumIdentificacion(String numIdentificacion) {
      this.numIdentificacion = numIdentificacion;
   }

   public BigDecimal getNumNiveKactus() {
      return this.numNiveKactus;
   }

   public void setNumNiveKactus(BigDecimal numNiveKactus) {
      this.numNiveKactus = numNiveKactus;
   }

   public String getValApellido1() {
      return this.valApellido1;
   }

   public void setValApellido1(String valApellido1) {
      this.valApellido1 = valApellido1;
   }

   public String getValApellido2() {
      return this.valApellido2;
   }

   public void setValApellido2(String valApellido2) {
      this.valApellido2 = valApellido2;
   }

   public String getValCorrElectronico() {
      return this.valCorrElectronico;
   }

   public void setValCorrElectronico(String valCorrElectronico) {
      this.valCorrElectronico = valCorrElectronico;
   }

   public String getValLogin() {
      return this.valLogin;
   }

   public void setValLogin(String valLogin) {
      this.valLogin = valLogin;
   }
}

package co.gov.mintrabajo.encuesta.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
   private static final long serialVersionUID = 1L;
   private Long codigo;
   private String nombreCompleto;
   private String usuario;
   private String contrasena;
   private boolean esAdmon;

   public String getUsuario() {
      return this.usuario;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   public String getContrasena() {
      return this.contrasena;
   }

   public void setContrasena(String contrasena) {
      this.contrasena = contrasena;
   }

   public Long getCodigo() {
      return this.codigo;
   }

   public void setCodigo(Long codigo) {
      this.codigo = codigo;
   }

   public String getNombreCompleto() {
      return this.nombreCompleto;
   }

   public void setNombreCompleto(String nombreCompleto) {
      this.nombreCompleto = nombreCompleto;
   }

   public boolean isEsAdmon() {
      return this.esAdmon;
   }

   public void setEsAdmon(boolean esAdmon) {
      this.esAdmon = esAdmon;
   }
}

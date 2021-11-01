package co.gov.mintrabajo.encuesta.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class MensajeDTO implements Serializable {
   private static final long serialVersionUID = 1L;
   private String identificador;
   private String cuerpo;
   private Exception exception;

   public MensajeDTO() {
   }

   public MensajeDTO(String identificador, String cuerpo, Exception exception) {
      this.identificador = identificador;
      this.cuerpo = this.asociarfechaFormateadaAlMensaje(cuerpo);
      this.exception = exception;
   }

   protected String asociarfechaFormateadaAlMensaje(String cuerpo) {
      Date date = new Date();
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      int year = cal.get(1);
      int month = cal.get(2);
      int day = cal.get(5);
      int hour = cal.get(11);
      int min = cal.get(12);
      int sec = cal.get(13);
      String amin = String.valueOf(min);
      String ahour = String.valueOf(hour);
      String asec = String.valueOf(sec);
      ++month;
      if (min < 10) {
         amin = "0" + amin;
      }

      if (hour < 10) {
         ahour = "0" + ahour;
      }

      if (sec < 10) {
         asec = "0" + asec;
      }

      return cuerpo + "Se registró en el sistema a las " + ahour + ":" + amin + ":" + asec + " del " + day + "/" + month + "/" + year + ".";
   }

   public String getIdentificador() {
      return this.identificador;
   }

   public void setIdentificador(String identificador) {
      this.identificador = identificador;
   }

   public String getCuerpo() {
      return this.cuerpo;
   }

   public void setCuerpo(String cuerpo) {
      this.cuerpo = cuerpo;
   }

   public Exception getException() {
      return this.exception;
   }

   public void setException(Exception exception) {
      this.exception = exception;
   }
}

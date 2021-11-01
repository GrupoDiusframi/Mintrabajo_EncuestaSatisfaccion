package co.gov.mintrabajo.encuesta.beans.converters;

import co.gov.mintrabajo.encuesta.dto.RespuestaDTO;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("testConverter")
public class RespuestaConverter implements Converter, Serializable {
   private static final long serialVersionUID = 1L;

   public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigoRespuesta) throws ConverterException {
      System.out.println("INIC getAsObject");
      if (codigoRespuesta != null && codigoRespuesta.trim().length() > 0) {
         System.out.println("INIC PASO 1 CODIGO SELECCIONADO: " + codigoRespuesta);
         Long codigo = Long.parseLong(codigoRespuesta);
         RespuestaDTO respuesta = new RespuestaDTO();
         respuesta.setCodigo(codigo);
         System.out.println("INIC PASO 2 " + respuesta.getCodigo());
         return respuesta;
      } else {
         return null;
      }
   }

   public String getAsString(FacesContext arg0, UIComponent arg1, Object respuestaObject) throws ConverterException {
      System.out.println("INIC getAsString");
      System.out.println("INIC PASO 1" + (respuestaObject != null ? ((RespuestaDTO)respuestaObject).getCodigo().toString() : "NULL"));
      return respuestaObject != null ? ((RespuestaDTO)respuestaObject).getCodigo().toString() : null;
   }
}

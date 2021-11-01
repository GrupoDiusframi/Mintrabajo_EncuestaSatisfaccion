package co.gov.mintrabajo.encuesta.common.servicios.impl;

import co.gov.mintrabajo.encuesta.dto.EncuestaDTO;
import co.gov.mintrabajo.encuesta.entities.EncEncuestaPregunta;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.PersistenceException;

@Local
public interface IEntidadServicio {
   <T> T guardar(T var1) throws PersistenceException;

   <T> List<T> buscarPorConsultaNombrada(String var1) throws PersistenceException;

   <T> List<T> buscarPorConsultaNombrada(String var1, Object... var2) throws PersistenceException;

   void eliminaEncuesta(EncuestaDTO var1) throws PersistenceException;

   void eliminaPregunta(EncEncuestaPregunta var1) throws PersistenceException;

   String buscarRadicadoPQRS(String var1);

   String buscarRadicadoTYS(String var1);

   boolean buscarRespuesta(String var1, String var2);
}

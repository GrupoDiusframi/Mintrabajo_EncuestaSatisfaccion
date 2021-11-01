package co.gov.mintrabajo.encuesta.servicios.impl;

import co.gov.mintrabajo.encuesta.dto.EncuestaDTO;
import co.gov.mintrabajo.encuesta.dto.PreguntaDTO;
import co.gov.mintrabajo.encuesta.dto.RespuestaEncuestaDTO;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.PersistenceException;

@Local
public interface IAdministradorEncuesta {
   EncuestaDTO crearEncuesta(EncuestaDTO var1) throws PersistenceException;

   EncuestaDTO actualizarEncuesta(EncuestaDTO var1) throws PersistenceException;

   List<EncuestaDTO> consultarEncuestas() throws PersistenceException;

   EncuestaDTO consultarEncuestaPorCodigo(Long var1) throws PersistenceException;

   RespuestaEncuestaDTO enviarRespuestaEncuesta(RespuestaEncuestaDTO var1) throws PersistenceException;

   void eliminarPregunta(PreguntaDTO var1) throws PersistenceException;

   String buscarRadicadoPQRS(String var1);

   String buscarRadicadoTYS(String var1);

   boolean buscarRespuesta(String var1, String var2);
}

package co.gov.mintrabajo.encuesta.servicios;

import co.gov.mintrabajo.encuesta.common.servicios.impl.IEntidadServicio;
import co.gov.mintrabajo.encuesta.dto.EncuestaDTO;
import co.gov.mintrabajo.encuesta.dto.PreguntaDTO;
import co.gov.mintrabajo.encuesta.dto.RespuestaDTO;
import co.gov.mintrabajo.encuesta.dto.RespuestaEncuestaDTO;
import co.gov.mintrabajo.encuesta.entities.ConConstante;
import co.gov.mintrabajo.encuesta.entities.EncEncPqrResRta;
import co.gov.mintrabajo.encuesta.entities.EncEncuesta;
import co.gov.mintrabajo.encuesta.entities.EncEncuestaPqrResul;
import co.gov.mintrabajo.encuesta.entities.EncEncuestaPreRta;
import co.gov.mintrabajo.encuesta.entities.EncEncuestaPregunta;
import co.gov.mintrabajo.encuesta.servicios.impl.IAdministradorEncuesta;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

@Stateless(
   mappedName = "AdministradorEncuesta"
)
public class AdministradorEncuesta implements IAdministradorEncuesta {
   @EJB
   private IEntidadServicio entidadServicio;

   public EncuestaDTO crearEncuesta(EncuestaDTO encuesta) throws PersistenceException {
      EncEncuesta encEncuesta = new EncEncuesta(encuesta.getCodigoUsuarioCreador(), encuesta.getTitulo(), encuesta.getSubtitulo());
      encEncuesta = (EncEncuesta)this.entidadServicio.guardar(encEncuesta);
      encuesta.setCodigo(encEncuesta.getIdEncuesta());
      this.crearPreguntas(encuesta);
      System.out.println("[FLAG][AdministradorEncuesta][crearEncuesta][FIN]");
      return encuesta;
   }

   public void eliminarEncuesta(EncuestaDTO encuesta) throws PersistenceException {
      try {
         this.entidadServicio.eliminaEncuesta(encuesta);
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public void eliminarPregunta(PreguntaDTO pregunta) throws PersistenceException {
      EncEncuestaPregunta encuestaPregunta = new EncEncuestaPregunta();
      encuestaPregunta.setIdEncuestaPregunta(pregunta.getCodigo());

      try {
         this.entidadServicio.eliminaPregunta(encuestaPregunta);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   public EncuestaDTO actualizarEncuesta(EncuestaDTO encuesta) throws PersistenceException {
      EncEncuesta encEncuesta = new EncEncuesta(encuesta.getCodigo(), encuesta.getTitulo(), encuesta.getSubtitulo(), encuesta.getCodigoUsuarioCreador(), encuesta.getFechaCreacion(), encuesta.getCodigoUsuarioModifico(), encuesta.getFechaModificacion());
      this.entidadServicio.guardar(encEncuesta);
      this.actualizarPreguntas(encuesta);
      System.out.println("[FLAG][AdministradorEncuesta][actualizarEncuesta][FIN]");
      return null;
   }

   public List<EncuestaDTO> consultarEncuestas() throws PersistenceException {
      List<EncuestaDTO> encuestas = new ArrayList(0);
      List<EncEncuesta> encEncuestas = this.entidadServicio.buscarPorConsultaNombrada("EncEncuesta.findAll");
      if (encEncuestas != null && !encEncuestas.isEmpty()) {
         Iterator var4 = encEncuestas.iterator();

         while(var4.hasNext()) {
            EncEncuesta encEncuesta = (EncEncuesta)var4.next();
            encuestas.add(new EncuestaDTO(encEncuesta.getIdEncuesta(), encEncuesta.getFecCreacion(), encEncuesta.getFecCambio(), encEncuesta.getTitulo()));
         }
      }

      System.out.println("[FLAG][AdministradorEncuesta][consultarEncuestas][FIN]");
      return encuestas;
   }

   public EncuestaDTO consultarEncuestaPorCodigo(Long codigo) throws PersistenceException {
      List<EncEncuesta> encEncuestas = this.entidadServicio.buscarPorConsultaNombrada("EncEncuesta.buscarEncuestaPorId", codigo);
      if (encEncuestas != null && encEncuestas.size() > 0) {
         EncEncuesta encEncuesta = (EncEncuesta)encEncuestas.get(0);
         List<PreguntaDTO> preguntas = this.obtenerPreguntasPorEncuesta(encEncuesta.getIdEncuesta());
         return new EncuestaDTO(encEncuesta.getIdEncuesta(), encEncuesta.getTitulo(), encEncuesta.getSubtitulo(), encEncuesta.getIdUsuarioCreo(), encEncuesta.getFecCreacion(), encEncuesta.getIdUsuarioCambio(), encEncuesta.getFecCambio(), preguntas);
      } else {
         System.out.println("[FLAG][AdministradorEncuesta][consultarEncuestaPorCodigo][FIN]");
         return new EncuestaDTO();
      }
   }

   public RespuestaEncuestaDTO enviarRespuestaEncuesta(RespuestaEncuestaDTO encuestaRespuesta) throws PersistenceException {
      EncEncuesta encEncuesta = new EncEncuesta();
      encEncuesta.setIdEncuesta(encuestaRespuesta.getEncuesta().getCodigo());
      EncEncuestaPqrResul encEncuestaPqrResul = (EncEncuestaPqrResul)this.entidadServicio.guardar(new EncEncuestaPqrResul(encEncuesta, new ConConstante(encuestaRespuesta.getEstado().getCodigo()), encuestaRespuesta.getCodigoUsuarioCreo(), encuestaRespuesta.getCodigoPQR(), encuestaRespuesta.getFechaCreacion()));
      encuestaRespuesta.setCodigo(encEncuestaPqrResul.getIdEncuestaPqrResul());
      Iterator var5 = encuestaRespuesta.getEncuesta().getPreguntas().iterator();

      while(var5.hasNext()) {
         PreguntaDTO pregunta = (PreguntaDTO)var5.next();
         EncEncuestaPregunta encEncuestaPregunta = new EncEncuestaPregunta();
         encEncuestaPregunta.setIdEncuestaPregunta(pregunta.getCodigo());
         EncEncuestaPreRta encEncuestaPreRta = new EncEncuestaPreRta();
         encEncuestaPreRta.setIdEncuestaPreRta(Long.parseLong(pregunta.getCodigoRespuestaSeleccionada()));
         this.entidadServicio.guardar(new EncEncPqrResRta(encEncuestaPregunta, encEncuestaPqrResul, encEncuestaPreRta, encuestaRespuesta.getCodigoUsuarioCreo()));
      }

      return encuestaRespuesta;
   }

   protected void crearPreguntas(EncuestaDTO encuesta) throws PersistenceException {
      EncEncuesta encEncuesta = new EncEncuesta(encuesta.getCodigo(), encuesta.getCodigoUsuarioCreador());
      List<PreguntaDTO> preguntas = encuesta.getPreguntas();
      if (!preguntas.isEmpty()) {
         Iterator var5 = preguntas.iterator();

         while(var5.hasNext()) {
            PreguntaDTO pregunta = (PreguntaDTO)var5.next();
            EncEncuestaPregunta encEncuestaPregunta = new EncEncuestaPregunta(pregunta.getNumeroOrden(), pregunta.getValorTipoPregunta(), "1", encEncuesta, pregunta.getTitulo().trim(), pregunta.getCodigoUsuarioCreador());
            encEncuestaPregunta = (EncEncuestaPregunta)this.entidadServicio.guardar(encEncuestaPregunta);
            pregunta.setCodigo(encEncuestaPregunta.getIdEncuestaPregunta());
            this.crearRespuestas(pregunta);
         }
      }

   }

   protected void actualizarPreguntas(EncuestaDTO encuesta) throws PersistenceException {
      EncEncuesta encEncuesta = new EncEncuesta(encuesta.getCodigo(), encuesta.getCodigoUsuarioCreador());
      List<PreguntaDTO> preguntas = encuesta.getPreguntas();
      if (!preguntas.isEmpty()) {
         Iterator var5 = preguntas.iterator();

         while(var5.hasNext()) {
            PreguntaDTO pregunta = (PreguntaDTO)var5.next();
            EncEncuestaPregunta encEncuestaPregunta = new EncEncuestaPregunta(pregunta.getCodigo(), pregunta.getNumeroOrden(), pregunta.getValorTipoPregunta(), pregunta.getTitulo(), pregunta.getCodigoUsuarioCreador(), pregunta.getFechaCreacion(), pregunta.getCodigoUsuarioCambio(), pregunta.getFechaModificacion(), encEncuesta);
            encEncuestaPregunta = (EncEncuestaPregunta)this.entidadServicio.guardar(encEncuestaPregunta);
            pregunta.setCodigo(encEncuestaPregunta.getIdEncuestaPregunta());
            this.actualizarRespuestas(pregunta);
         }
      }

   }

   protected void crearRespuestas(PreguntaDTO pregunta) {
      EncEncuestaPregunta encEncuestaPregunta = new EncEncuestaPregunta(pregunta.getCodigo(), pregunta.getCodigoUsuarioCreador());
      List<RespuestaDTO> respuestas = pregunta.getRespuestas();
      if (!respuestas.isEmpty()) {
         Iterator var5 = respuestas.iterator();

         while(var5.hasNext()) {
            RespuestaDTO respuesta = (RespuestaDTO)var5.next();
            EncEncuestaPreRta encEncuestaPreRta = new EncEncuestaPreRta(respuesta.getTitulo(), respuesta.getCodigoUsuarioCreador(), encEncuestaPregunta, respuesta.getOrden());
            encEncuestaPreRta = (EncEncuestaPreRta)this.entidadServicio.guardar(encEncuestaPreRta);
            respuesta.setCodigo(encEncuestaPreRta.getIdEncuestaPreRta());
         }
      }

   }

   protected void actualizarRespuestas(PreguntaDTO pregunta) {
      EncEncuestaPregunta encEncuestaPregunta = new EncEncuestaPregunta(pregunta.getCodigo(), pregunta.getCodigoUsuarioCreador());
      List<RespuestaDTO> respuestas = pregunta.getRespuestas();
      EncEncuestaPreRta encEncuestaPreRta;
      if (!respuestas.isEmpty()) {
         for(Iterator var5 = respuestas.iterator(); var5.hasNext(); encEncuestaPreRta = (EncEncuestaPreRta)this.entidadServicio.guardar(encEncuestaPreRta)) {
            RespuestaDTO respuesta = (RespuestaDTO)var5.next();
            encEncuestaPreRta = new EncEncuestaPreRta(respuesta.getCodigo(), respuesta.getTitulo(), respuesta.getOrden(), respuesta.getCodigoUsuarioCreador(), respuesta.getFechaCreacion(), respuesta.getCodigoUsuarioCambio(), respuesta.getFechaModificacion(), encEncuestaPregunta);
         }
      }

   }

   protected List<PreguntaDTO> obtenerPreguntasPorEncuesta(Long codigoEncuesta) {
      List<PreguntaDTO> preguntas = new ArrayList(0);
      List<EncEncuestaPregunta> encEncuestaPreguntas = this.entidadServicio.buscarPorConsultaNombrada("EncEncuestaPregunta.buscarPreguntaPorIdEncuesta", codigoEncuesta);
      if (encEncuestaPreguntas != null && !encEncuestaPreguntas.isEmpty()) {
         Iterator var5 = encEncuestaPreguntas.iterator();

         while(var5.hasNext()) {
            EncEncuestaPregunta encEncuestaPregunta = (EncEncuestaPregunta)var5.next();
            List<RespuestaDTO> respuestas = this.obtenerRespuestaPorPregunta(encEncuestaPregunta.getIdEncuestaPregunta());
            preguntas.add(new PreguntaDTO(encEncuestaPregunta.getIdEncuestaPregunta(), encEncuestaPregunta.getValPregunta(), encEncuestaPregunta.getNroOrden(), encEncuestaPregunta.getValTipoPregunta(), encEncuestaPregunta.getIndEstaActiva(), encEncuestaPregunta.getIdUsuarioCreo(), encEncuestaPregunta.getFecCreacion(), encEncuestaPregunta.getFecCambio(), respuestas));
         }
      }

      return preguntas;
   }

   protected List<RespuestaDTO> obtenerRespuestaPorPregunta(Long codigoPregunta) {
      List<RespuestaDTO> respuestas = new ArrayList(0);
      List<EncEncuestaPreRta> encEncuestaPreRtas = this.entidadServicio.buscarPorConsultaNombrada("EncEncuestaPreRta.buscarRespuestaPorIdPregunta", codigoPregunta);
      if (encEncuestaPreRtas != null && !encEncuestaPreRtas.isEmpty()) {
         Iterator var5 = encEncuestaPreRtas.iterator();

         while(var5.hasNext()) {
            EncEncuestaPreRta encEncuestaPreRta = (EncEncuestaPreRta)var5.next();
            respuestas.add(new RespuestaDTO(encEncuestaPreRta.getIdEncuestaPreRta(), encEncuestaPreRta.getValPregunta(), encEncuestaPreRta.getNroOrden(), encEncuestaPreRta.getIdeUsuarioCreo(), encEncuestaPreRta.getFecCreacion(), encEncuestaPreRta.getFecCambio()));
         }
      }

      return respuestas;
   }

   public boolean buscarRespuesta(String idReg, String idEnc) {
      boolean result = this.entidadServicio.buscarRespuesta(idReg, idEnc);
      return result;
   }

   public String buscarRadicadoPQRS(String idPQRS) {
      try {
         String result = this.entidadServicio.buscarRadicadoPQRS(idPQRS);
         return result;
      } catch (Exception var3) {
         return "Error en la Consulta";
      }
   }

   public String buscarRadicadoTYS(String idRegistro) {
      try {
         String result = this.entidadServicio.buscarRadicadoTYS(idRegistro);
         return result;
      } catch (Exception var3) {
         return "Error en la Consulta";
      }
   }
}

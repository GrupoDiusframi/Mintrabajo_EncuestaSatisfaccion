package co.gov.mintrabajo.encuesta.common.servicios;

import co.gov.mintrabajo.encuesta.common.servicios.impl.IEntidadServicio;
import co.gov.mintrabajo.encuesta.dto.EncuestaDTO;
import co.gov.mintrabajo.encuesta.entities.EncEncuestaPregunta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@Stateless
public class EntidadServicio implements IEntidadServicio {
   @PersistenceContext(
      unitName = "EncuestaSatisfaccionModel"
   )
   private EntityManager manejadorEntidad;

   public <T> T guardar(T entidad) throws PersistenceException {
      try {
         System.out.println("[FLAG][EntidadServicio][guardar] " + entidad.getClass().getName());
         T o = this.manejadorEntidad.merge(entidad);
         return o;
      } catch (Exception var3) {
         throw new PersistenceException(var3);
      }
   }

   public synchronized <T> List<T> buscarPorConsultaNombrada(String nombreConsulta) throws PersistenceException {
      try {
         Query query = this.manejadorEntidad.createNamedQuery(nombreConsulta);
         List<T> o = query.getResultList();
         return o;
      } catch (Exception var4) {
         throw new PersistenceException(var4);
      }
   }

   public <T> List<T> buscarPorConsultaNombrada(String nombreConsulta, Object... parametros) throws PersistenceException {
      try {
         Query consulta = this.manejadorEntidad.createNamedQuery(nombreConsulta);
         if (parametros != null) {
            int i = 1;
            Object[] var8 = parametros;
            int var7 = parametros.length;

            for(int var6 = 0; var6 < var7; ++var6) {
               Object parametro = var8[var6];
               if (parametro != null) {
                  consulta.setParameter(i, parametro);
               }

               ++i;
            }
         }

         List<T> o = consulta.getResultList();
         return o;
      } catch (Exception var9) {
         throw new PersistenceException(var9);
      }
   }

   public void eliminaEncuesta(EncuestaDTO encuesta) throws PersistenceException {
      try {
         this.manejadorEntidad.remove(encuesta);
      } catch (Exception var3) {
         System.out.println("No se ha podido eliminar la encuesta");
      }

   }

   public void eliminaPregunta(EncEncuestaPregunta pregunta) throws PersistenceException {
      byte i = 1;

      try {
         Query qRespuesta = this.manejadorEntidad.createNamedQuery("EncEncuestaPreRta.eliminarRespuestaPorIdPregunta");
         qRespuesta.setParameter(i, pregunta.getIdEncuestaPregunta());
         qRespuesta.executeUpdate();
         Query qPregunta = this.manejadorEntidad.createNamedQuery("EncEncuestaPregunta.eliminarPregunta");
         qPregunta.setParameter(i, pregunta.getIdEncuestaPregunta());
         System.out.println("Ingreso eliminaPregunta [try  " + qPregunta);
         qPregunta.executeUpdate();
      } catch (Exception var5) {
         System.out.println("No se ha podido eliminar la pregunta: " + var5);
         var5.printStackTrace();
      }

   }

   public String buscarRadicadoPQRS(String idPQRS) {
      try {
         String cade = "SELECT NRO_RADICADO FROM PQR_REGISTRO WHERE IDE_REGISTRO =" + idPQRS;
         Query query = this.manejadorEntidad.createNativeQuery(cade);
         String result = (String)query.getSingleResult();
         return result;
      } catch (Exception var5) {
         System.out.println("Error en la Consulta" + var5);
         return "No hay valores";
      }
   }

   public String buscarRadicadoTYS(String idRegistro) {
      try {
         String cade = "SELECT NRO_RADICADO FROM TYS_REGISTRO_TRAMITE WHERE IDE_REG_TRAMITE =" + idRegistro;
         Query query = this.manejadorEntidad.createNativeQuery(cade);
         String result = (String)query.getSingleResult();
         return result;
      } catch (Exception var5) {
         System.out.println("Error en la Consulta" + var5);
         return "No hay valores";
      }
   }

   public boolean buscarRespuesta(String idReg, String idEnc) {
      try {
         String cade = "SELECT * FROM ENC_ENCUESTA_PQR_RESUL WHERE IDE_REGISTRO=" + idReg + "AND IDE_ENCUESTA =" + idEnc;
         Query query = this.manejadorEntidad.createNativeQuery(cade);
         Object result = query.getSingleResult();
         return true;
      } catch (Exception var6) {
         return false;
      }
   }
}

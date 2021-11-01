package co.gov.mintrabajo.encuesta.common.servicios;

import co.gov.mintrabajo.encuesta.common.servicios.impl.ISessionControl;
import co.gov.mintrabajo.encuesta.dto.UsuarioDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@Stateless
public class SessionControl implements ISessionControl {
   @PersistenceContext(
      unitName = "EncuestaSatisfaccionModel"
   )
   private EntityManager manejadorEntidad;

   public boolean validacionUsuario(UsuarioDTO user) {
      if (user != null) {
         try {
            Query consulta = this.manejadorEntidad.createQuery("SELECT a FROM AdmPlanta a WHERE upper(a.valLogin) = upper(:login)");
            consulta.setParameter("login", user.getUsuario());
            if (consulta.getMaxResults() > 0) {
               return true;
            }
         } catch (Exception var3) {
            System.err.println("Error al consultar usuario por login");
            var3.printStackTrace();
            throw new PersistenceException(var3);
         }
      }

      return false;
   }
}

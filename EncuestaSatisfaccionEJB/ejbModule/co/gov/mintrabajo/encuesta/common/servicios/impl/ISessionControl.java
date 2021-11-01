package co.gov.mintrabajo.encuesta.common.servicios.impl;

import co.gov.mintrabajo.encuesta.dto.UsuarioDTO;
import javax.ejb.Local;
import javax.persistence.PersistenceException;

@Local
public interface ISessionControl {
   boolean validacionUsuario(UsuarioDTO var1) throws PersistenceException;
}

package co.gov.mintrabajo.encuesta.entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EncEncuestaPregunta.class)
public class EncEncuestaPregunta_ {
   public static volatile SingularAttribute<EncEncuestaPregunta, Long> idEncuestaPregunta;
   public static volatile SingularAttribute<EncEncuestaPregunta, String> indEstaActiva;
   public static volatile SingularAttribute<EncEncuestaPregunta, Integer> nroOrden;
   public static volatile SingularAttribute<EncEncuestaPregunta, String> valTipoPregunta;
   public static volatile SingularAttribute<EncEncuestaPregunta, String> valPregunta;
   public static volatile SingularAttribute<EncEncuestaPregunta, Date> fecCambio;
   public static volatile SingularAttribute<EncEncuestaPregunta, Date> fecCreacion;
   public static volatile SingularAttribute<EncEncuestaPregunta, Long> idUsuarioCambio;
   public static volatile SingularAttribute<EncEncuestaPregunta, Long> idUsuarioCreo;
   public static volatile SingularAttribute<EncEncuestaPregunta, String> idUuid;
   public static volatile SingularAttribute<EncEncuestaPregunta, Integer> nivEscritura;
   public static volatile SingularAttribute<EncEncuestaPregunta, Integer> nivLectura;
   public static volatile SingularAttribute<EncEncuestaPregunta, EncEncuesta> encuesta;
}

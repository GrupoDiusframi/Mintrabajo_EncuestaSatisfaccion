package co.gov.mintrabajo.encuesta.entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EncEncuestaPreRta.class)
public class EncEncuestaPreRta_ {
   public static volatile SingularAttribute<EncEncuestaPreRta, Long> idEncuestaPreRta;
   public static volatile SingularAttribute<EncEncuestaPreRta, String> indEstaActiva;
   public static volatile SingularAttribute<EncEncuestaPreRta, Integer> nroOrden;
   public static volatile SingularAttribute<EncEncuestaPreRta, String> valPregunta;
   public static volatile SingularAttribute<EncEncuestaPreRta, Long> ideUsuarioCambio;
   public static volatile SingularAttribute<EncEncuestaPreRta, Long> ideUsuarioCreo;
   public static volatile SingularAttribute<EncEncuestaPreRta, Date> fecCambio;
   public static volatile SingularAttribute<EncEncuestaPreRta, Date> fecCreacion;
   public static volatile SingularAttribute<EncEncuestaPreRta, String> ideUuid;
   public static volatile SingularAttribute<EncEncuestaPreRta, Integer> nivEscritura;
   public static volatile SingularAttribute<EncEncuestaPreRta, Integer> nivLectura;
   public static volatile SingularAttribute<EncEncuestaPreRta, EncEncuestaPregunta> pregunta;
}

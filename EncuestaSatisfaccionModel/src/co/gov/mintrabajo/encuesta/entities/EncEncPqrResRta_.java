package co.gov.mintrabajo.encuesta.entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EncEncPqrResRta.class)
public class EncEncPqrResRta_ {
   public static volatile SingularAttribute<EncEncPqrResRta, Long> idEncPqrResRta;
   public static volatile SingularAttribute<EncEncPqrResRta, String> valRespuesta;
   public static volatile SingularAttribute<EncEncPqrResRta, Date> fecCambio;
   public static volatile SingularAttribute<EncEncPqrResRta, Date> fecCreacion;
   public static volatile SingularAttribute<EncEncPqrResRta, Long> idUsuarioCambio;
   public static volatile SingularAttribute<EncEncPqrResRta, Long> idUsuarioCreo;
   public static volatile SingularAttribute<EncEncPqrResRta, String> idUuid;
   public static volatile SingularAttribute<EncEncPqrResRta, Integer> nivEscritura;
   public static volatile SingularAttribute<EncEncPqrResRta, Integer> nivLectura;
   public static volatile SingularAttribute<EncEncPqrResRta, EncEncuestaPregunta> pregunta;
   public static volatile SingularAttribute<EncEncPqrResRta, EncEncuestaPqrResul> resultado;
   public static volatile SingularAttribute<EncEncPqrResRta, EncEncuestaPreRta> respuesta;
}

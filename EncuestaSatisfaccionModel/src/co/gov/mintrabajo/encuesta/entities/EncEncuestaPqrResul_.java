package co.gov.mintrabajo.encuesta.entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EncEncuestaPqrResul.class)
public class EncEncuestaPqrResul_ {
   public static volatile SingularAttribute<EncEncuestaPqrResul, Long> idEncuestaPqrResul;
   public static volatile SingularAttribute<EncEncuestaPqrResul, Date> fecEncuesta;
   public static volatile SingularAttribute<EncEncuestaPqrResul, Date> fecCambio;
   public static volatile SingularAttribute<EncEncuestaPqrResul, Date> fecCreacion;
   public static volatile SingularAttribute<EncEncuestaPqrResul, Long> idUsuarioCambio;
   public static volatile SingularAttribute<EncEncuestaPqrResul, Long> idUsuarioCreo;
   public static volatile SingularAttribute<EncEncuestaPqrResul, String> idUuid;
   public static volatile SingularAttribute<EncEncuestaPqrResul, Integer> nivEscritura;
   public static volatile SingularAttribute<EncEncuestaPqrResul, Integer> nivLectura;
   public static volatile SingularAttribute<EncEncuestaPqrResul, Long> registro;
   public static volatile SingularAttribute<EncEncuestaPqrResul, EncEncuesta> encuesta;
   public static volatile SingularAttribute<EncEncuestaPqrResul, ConConstante> estado;
}

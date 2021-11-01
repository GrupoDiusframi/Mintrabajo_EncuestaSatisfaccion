package co.gov.mintrabajo.encuesta.entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EncEncuesta.class)
public class EncEncuesta_ {
   public static volatile SingularAttribute<EncEncuesta, Long> idEncuesta;
   public static volatile SingularAttribute<EncEncuesta, Date> fecCambio;
   public static volatile SingularAttribute<EncEncuesta, Date> fecCreacion;
   public static volatile SingularAttribute<EncEncuesta, Long> idUsuarioCambio;
   public static volatile SingularAttribute<EncEncuesta, Long> idUsuarioCreo;
   public static volatile SingularAttribute<EncEncuesta, String> titulo;
   public static volatile SingularAttribute<EncEncuesta, String> subtitulo;
   public static volatile SingularAttribute<EncEncuesta, String> idUuid;
   public static volatile SingularAttribute<EncEncuesta, Integer> nivEscritura;
   public static volatile SingularAttribute<EncEncuesta, Integer> nivLectura;
}

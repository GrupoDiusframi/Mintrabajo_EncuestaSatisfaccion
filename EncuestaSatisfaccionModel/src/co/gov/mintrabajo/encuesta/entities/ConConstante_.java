package co.gov.mintrabajo.encuesta.entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ConConstante.class)
public class ConConstante_ {
   public static volatile SingularAttribute<ConConstante, Long> idConConstante;
   public static volatile SingularAttribute<ConConstante, String> codigo;
   public static volatile SingularAttribute<ConConstante, String> nombre;
   public static volatile SingularAttribute<ConConstante, Date> fecCambio;
   public static volatile SingularAttribute<ConConstante, Long> idConConsId;
   public static volatile SingularAttribute<ConConstante, Long> idUsuarioCambio;
   public static volatile SingularAttribute<ConConstante, String> idUuid;
   public static volatile SingularAttribute<ConConstante, Integer> nivEscritura;
   public static volatile SingularAttribute<ConConstante, Integer> nivLectura;
}

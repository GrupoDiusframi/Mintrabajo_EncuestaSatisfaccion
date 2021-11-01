package co.gov.mintrabajo.encuesta.common.enums;

public enum EstadosEncuesta {
   COMPLETA("Completa", 10001L),
   INCOMPLETA("Incompleta", 10002L);

   private static final long serialVersionUID = 1L;
   private String valor;
   private long codigo;

   private EstadosEncuesta(String valor, long codigo) {
      this.valor = valor;
      this.codigo = codigo;
   }

   public static EstadosEncuesta getEstadoPorValor(String valor) {
      valor = valor.toLowerCase();
      EstadosEncuesta[] estados = values();
      EstadosEncuesta estadoEncontrado = null;
      EstadosEncuesta[] var6 = estados;
      int var5 = estados.length;

      for(int var4 = 0; var4 < var5; ++var4) {
         EstadosEncuesta estado = var6[var4];
         String valorTemplate = estado.getValor().toLowerCase();
         if (valorTemplate.equals(valor)) {
            estadoEncontrado = estado;
            break;
         }
      }

      return estadoEncontrado;
   }

   public static EstadosEncuesta getEstadoPorCodigo(long codigo) {
      EstadosEncuesta[] estados = values();
      EstadosEncuesta estadoEncontrado = null;
      EstadosEncuesta[] var7 = estados;
      int var6 = estados.length;

      for(int var5 = 0; var5 < var6; ++var5) {
         EstadosEncuesta estado = var7[var5];
         long codigoTemplate = estado.getCodigo();
         if (codigoTemplate == codigo) {
            estadoEncontrado = estado;
            break;
         }
      }

      return estadoEncontrado;
   }

   public void setValor(String valor) {
      this.valor = valor;
   }

   public String getValor() {
      return this.valor;
   }

   public void setCodigo(int codigo) {
      this.codigo = (long)codigo;
   }

   public long getCodigo() {
      return this.codigo;
   }
}

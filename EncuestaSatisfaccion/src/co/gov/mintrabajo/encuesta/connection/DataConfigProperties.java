package co.gov.mintrabajo.encuesta.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class DataConfigProperties {
   private static final String propertiesFileName = "LDAPConnection.prop";
   private static Properties properties;
   private static ClassLoader cl = null;
   private static InputStream is = null;

   public static InputStream getResource(String resource) {
      try {
         cl = Thread.currentThread().getContextClassLoader();
         is = cl.getResourceAsStream(resource);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      return is;
   }

   public static String getPropiedad(String name) {
      if (properties == null) {
         try {
            loadProperties();
         } catch (Exception var10) {
            var10.printStackTrace();
         } finally {
            cl = null;

            try {
               if (is != null) {
                  is.close();
               }
            } catch (IOException var9) {
               var9.printStackTrace();
            }

         }
      }

      return properties.getProperty(name);
   }

   private static void loadProperties() throws Exception {
      try {
         properties = new Properties();
         properties.load(getResource("LDAPConnection.prop"));
      } catch (FileNotFoundException var1) {
         System.out.println("Error, El archivo no existe");
      } catch (IOException var2) {
         System.out.println("Error, No se puede leer el archivo");
      }

   }
}

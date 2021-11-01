package co.gov.mintrabajo.encuesta.connection;

import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LDAPAuthentication {
   private static final String ldapURI = DataConfigProperties.getPropiedad("url");
   private static final String contextFactory = DataConfigProperties.getPropiedad("driver");

   private static DirContext ldapContext() throws Exception {
      Hashtable<String, String> env = new Hashtable();
      return ldapContext(env);
   }

   private static DirContext ldapContext(Hashtable<String, String> env) throws Exception {
      env.put("java.naming.factory.initial", contextFactory);
      env.put("java.naming.provider.url", ldapURI);
      DirContext ctx = new InitialDirContext(env);
      return ctx;
   }

   public static String getUid(String user) throws Exception {
      DirContext ctx = ldapContext();
      String filter = "(uid=" + user + ")";
      SearchControls ctrl = new SearchControls();
      ctrl.setSearchScope(2);
      NamingEnumeration answer = ctx.search("", filter, ctrl);
      String dn;
      if (answer.hasMore()) {
         SearchResult result = (SearchResult)answer.next();
         dn = result.getNameInNamespace();
      } else {
         dn = null;
      }

      answer.close();
      return dn;
   }

   public static boolean autenticar(String dn, String password) throws Exception {
      Hashtable<String, String> env = new Hashtable();
      env.put("java.naming.security.authentication", "simple");
      env.put("java.naming.security.principal", dn);
      env.put("java.naming.security.credentials", password);

      try {
         ldapContext(env);
         return true;
      } catch (AuthenticationException var4) {
         return false;
      }
   }
}

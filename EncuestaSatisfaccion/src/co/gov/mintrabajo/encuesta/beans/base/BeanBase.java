package co.gov.mintrabajo.encuesta.beans.base;

import co.gov.mintrabajo.encuesta.common.servicios.impl.ISessionControl;
import co.gov.mintrabajo.encuesta.connection.DataConfigProperties;
import co.gov.mintrabajo.encuesta.dto.UsuarioDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;

public abstract class BeanBase implements Serializable {
   private static final long serialVersionUID = 1L;
   public static final String BUNDLE = "mintrabajo";
   @EJB
   private ISessionControl sesion;

   public abstract void cargarDatos();

   public abstract void enviarDatos();

   @PostConstruct
   public void inicializar() {
      FacesContext.getCurrentInstance().getExternalContext().getSession(true);
   }

   public boolean esSesionValida() {
      return this.isValidSession();
   }

   protected Map<String, String> getParametros() {
      return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
   }

   protected String getParametros(String llave) {
      String parametro = null;
      if (llave != null && !llave.isEmpty()) {
         Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
         parametro = !parametros.isEmpty() ? (String)parametros.get(llave) : null;
      }

      return parametro;
   }

   protected FacesContext getCurrentContext() {
      return FacesContext.getCurrentInstance();
   }

   public void addMensajeInfo(String msg) {
      this.addMensaje(msg, FacesMessage.SEVERITY_INFO);
   }

   public void addMensajeInfo(String msg, String clientId) {
      this.addMensaje(msg, FacesMessage.SEVERITY_INFO, clientId);
   }

   public void addMensajeError(String msg) {
      this.addMensajeError(msg, (String)null);
   }

   public void addMensajeError(Exception ex) {
      String msg = getMessageException(ex);
      msg = msg != null && !msg.trim().isEmpty() ? msg : "OcurriÃ³ un error interno en el sistema";
      this.addMensajeError(msg, (String)null);
   }

   private static String getMessageException(Throwable ex) {
      if (ex == null) {
         return "ExcepciÃ³n general";
      } else {
         String msg = ex.getMessage();
         return msg != null && !msg.trim().isEmpty() ? msg : getMessageException(ex.getCause());
      }
   }

   public void addMensajeError(String msg, String clientId) {
      this.addMensaje(msg, FacesMessage.SEVERITY_ERROR, clientId);
   }

   public void addMensajeFatal(String msg) {
      this.addMensajeFatal(msg, (String)null);
   }

   public void addMensajeFatal(String msg, String clientId) {
      this.addMensaje(msg, FacesMessage.SEVERITY_ERROR, clientId);
   }

   public void addMensajeWarn(String msg) {
      this.addMensajeWarn(msg, (String)null);
   }

   public void addMensajeWarn(String msg, String clientId) {
      this.addMensaje(msg, FacesMessage.SEVERITY_WARN, clientId);
   }

   private void addMensaje(String msg, Severity severidad) {
      this.addMensaje(msg, severidad, (String)null);
   }

   private void addMensaje(String msg, Severity severidad, String clientId) {
      FacesMessage mensaje = new FacesMessage(msg);
      mensaje.setSeverity(severidad);
      FacesContext ctx = this.getCurrentContext();
      ctx.addMessage(clientId, mensaje);
   }

   public static ResourceBundle getResourceBundle(String variableArchvio) {
      FacesContext context = FacesContext.getCurrentInstance();
      return context.getApplication().getResourceBundle(context, variableArchvio);
   }

   public static String getMensaje(String key, String variableArchvio) {
      return getResourceBundle(variableArchvio).getString(key);
   }

   private Object getExpression(String expression) {
      FacesContext ctx = this.getCurrentContext();
      ExpressionFactory factory = ctx.getApplication().getExpressionFactory();
      ValueExpression ex = factory.createValueExpression(ctx.getELContext(), "#{" + expression + "}", Object.class);
      return ex.getValue(ctx.getELContext());
   }

   public void descargarArchivo(String ruta) {
      FacesContext contextoLocal = FacesContext.getCurrentInstance();
      ExternalContext contextoExterno = contextoLocal.getExternalContext();
      HttpServletResponse response = (HttpServletResponse)contextoExterno.getResponse();

      try {
         File archivo = new File(ruta);
         FileInputStream fis = new FileInputStream(archivo);
         byte[] bytes = new byte[1000];
         int read = 0;
         if (!contextoLocal.getResponseComplete()) {
            String contentType = "application/pdf";
            response.reset();
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment;filename=\"" + archivo.getName() + "\"");
            ServletOutputStream out = response.getOutputStream();

            while((read = fis.read(bytes)) != -1) {
               out.write(bytes, 0, read);
            }

            contextoLocal.responseComplete();
            fis.close();
            out.flush();
            out.close();
         }
      } catch (IOException var11) {
         System.out.println("MENSAJE: " + var11.getMessage() + "CAUSA: " + var11.getCause());
      }

   }

   public void redirigirA(String ruta) {
      try {
         ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
         contexto.redirect(contexto.getRequestContextPath() + "/" + ruta);
      } catch (IOException var3) {
         this.addMensajeError((Exception)var3);
      }

   }

   public static <T> T findBean(String beanName) {
      FacesContext context = FacesContext.getCurrentInstance();
      return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
   }

   public static void ejecutarJavascript(String javascript) {
      RequestContext requestContext = RequestContext.getCurrentInstance();
      requestContext.execute(javascript);
   }

   public static void actualizarAjax(String... idComponentes) {
      RequestContext requestContext = RequestContext.getCurrentInstance();
      requestContext.update(Arrays.asList(idComponentes));
   }

   protected Object getParametroSession(String llave) {
      Object parametro = null;
      if (llave != null && !llave.isEmpty()) {
         Map<String, Object> parametros = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
         parametro = !parametros.isEmpty() ? parametros.get(llave) : null;
      }

      return parametro;
   }

   protected void setParametroSession(String llave, Object valor) {
      if (llave != null && !llave.isEmpty()) {
         Map<String, Object> parametros = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
         parametros.put(llave, valor);
      }

   }

   protected void removerParametroSession(String llave) {
      if (llave != null && !llave.isEmpty()) {
         Map<String, Object> parametros = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
         parametros.remove(llave);
      }

   }

   protected void invalidateSession() {
      this.getCurrentContext().getExternalContext().invalidateSession();
   }

   protected void logout(String pathLogoutPage) throws IOException {
      this.invalidateSession();
      this.redirigirA(pathLogoutPage);
   }

   protected static final String obtenerContextoAppWAR() {
      FacesContext contextoLocal = FacesContext.getCurrentInstance();
      ExternalContext contextoExterno = contextoLocal.getExternalContext();
      return contextoExterno.getRequestContextPath();
   }

   public String getUrl() {
      HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
      String url = req.getRequestURL().toString();
      return url.substring(0, url.length() - req.getRequestURI().length()) + req.getContextPath() + "/";
   }

   private boolean isValidSession() {
      UsuarioDTO user;
      if (this.getParametros("idFuncionario") != null && this.getParametros("contrasena") != null && this.getParametros("nombreCompleto") != null && this.getParametros("usuario") != null) {
         user = new UsuarioDTO();
         user.setContrasena(this.getParametros("contrasena"));
         user.setNombreCompleto(this.getParametros("nombreCompleto"));
         user.setUsuario(this.getParametros("usuario"));
         user.setEsAdmon(Boolean.parseBoolean(this.getParametros("administracion")));
         System.out.println("Usuario Valido");
         this.setParametroSession("userLogin", user);
         return true;
      } else {
         user = (UsuarioDTO)this.getParametroSession("userLogin");
         if (user == null) {
            System.out.println("Usuario Invalido");
            return false;
         } else {
            return true;
         }
      }
   }

   public boolean validarUsuarioLDAP(String username, String password) {
      if (username != null && username != "" && password != null && password != "") {
         System.out.println("[FLAG][LoginBean][validaUsuario] Validando usuario frente al LDAP");
         InitialLdapContext ctx = null;
         Hashtable env = new Hashtable();
         env.put("java.naming.factory.initial", DataConfigProperties.getPropiedad("driver"));
         env.put("java.naming.provider.url", DataConfigProperties.getPropiedad("url"));
         env.put("java.naming.security.principal", username + "@" + DataConfigProperties.getPropiedad("domain"));
         env.put("java.naming.security.authentication", DataConfigProperties.getPropiedad("security_authentication"));
         env.put("java.naming.security.credentials", password);

         try {
            ctx = new InitialLdapContext(env, (Control[])null);
            System.out.println("[FLAG][LoginBean][validaUsuario] Usuario autenticado de forma exitosa");
            ctx.close();
            return true;
         } catch (Exception var6) {
            System.err.println("[FLAG][LoginBean][validaUsuario] Error al autenticar " + var6.getMessage() + " ");
            return false;
         }
      } else {
         return false;
      }
   }

   public void mostrarDialogo(String idDIalogo) {
      String url = "PF('" + idDIalogo + "').show();";
      RequestContext.getCurrentInstance().execute(url);
   }
}

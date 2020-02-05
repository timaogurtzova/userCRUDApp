import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import servlet.AddServlet;
import servlet.DeleteServlet;
import servlet.ServletApi;
import servlet.UpdateServlet;
import java.io.File;

public class Main {

    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
        /* Порт, на котором должны работать, может быть установлен в переменную окружения
        Ищем эту переменную, если её нет, то порт по умолчанию 8080
         */
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        tomcat.setPort(Integer.valueOf(webPort));

        String webappDirLocation = "src/main/webapp/";

        StandardContext ctx = (StandardContext)tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
        tomcat.addServlet(ctx,"getServlet", new ServletApi());
        ctx.addServletMappingDecoded("/get", "getServlet");

        tomcat.addServlet(ctx, "addServlet", new AddServlet());
        ctx.addServletMappingDecoded("/add", "addServlet");

        tomcat.addServlet(ctx, "updateServlet", new UpdateServlet());
        ctx.addServletMappingDecoded("/update", "updateServlet");

        tomcat.addServlet(ctx, "deleteServlet", new DeleteServlet());
        ctx.addServletMappingDecoded("/delete", "deleteServlet");

        tomcat.start();
        tomcat.getServer().await();

    }
}

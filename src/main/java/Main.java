import org.apache.catalina.*;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import servlet.AddServlet;
import servlet.DeleteServlet;
import servlet.UpdateServlet;
import java.io.File;

public class Main {

    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
        /* Порт, на котором должны работать, может быть установлен в переменную окружения.
        Если такой переменной нет, устанавливаем порт по умолчанию 8080.
         */
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        tomcat.setPort(Integer.parseInt(webPort));

        String webappDirLocation = "src/main/webapp/";
        StandardContext context = (StandardContext)tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

        tomcat.addServlet(context, "addServlet", new AddServlet());
        context.addServletMappingDecoded("/add", "addServlet");

        tomcat.addServlet(context, "updateServlet", new UpdateServlet());
        context.addServletMappingDecoded("/update", "updateServlet");

        tomcat.addServlet(context, "deleteServlet", new DeleteServlet());
        context.addServletMappingDecoded("/delete", "deleteServlet");

        tomcat.start();
        tomcat.getServer().await();

    }
}

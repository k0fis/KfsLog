package kfs.kfslog.web;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kfs.kfslog.service.KfsLogService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author pavedrim
 */
public class log extends HttpServlet {
    
    private final String hb[] = new String[]{
        "WSMS:onStartCommand(): alarm heartbeat",
        "WSMS:app heartbeat",
        "WSMS:onStartCommand(): calls heartbeat",};
    
    private Logger log;
    private KfsLogService logService;
    
    @Override
    public void init() throws ServletException {
        log = Logger.getLogger(getClass());
        Arrays.sort(hb);
        ServletContext servletContext = getServletContext();
        ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        logService = ac.getBean(KfsLogService.class);
        super.init();
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getUserPrincipal().getName();
        String p = req.getParameter("data");
        if (p != null) {
            boolean isHb = Arrays.binarySearch(hb, p) >= 0;
            if (isHb) {
                log.info(userId + " -" + p);
            } else {
                logService.log(userId, p);
            }
        }
    }
    
}

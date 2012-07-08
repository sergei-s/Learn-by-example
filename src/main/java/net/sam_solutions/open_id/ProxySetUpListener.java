package net.sam_solutions.open_id;

import org.openid4java.util.HttpClientFactory;
import org.openid4java.util.ProxyProperties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * User: Sergei Savenko
 * Date: 05.07.12
 */
public class ProxySetUpListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProxyProperties proxyProps = new ProxyProperties();
        proxyProps.setProxyHostName("proxy.sam-solutions.net");
        proxyProps.setProxyPort(8080);
        HttpClientFactory.setProxyProperties(proxyProps);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

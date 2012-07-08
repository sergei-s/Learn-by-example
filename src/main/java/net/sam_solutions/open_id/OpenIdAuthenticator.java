package net.sam_solutions.open_id;

import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.MessageException;
import org.openid4java.util.HttpClientFactory;
import org.openid4java.util.ProxyProperties;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 * User: Sergei Savenko
 * Date: 30.06.12
 */
public class OpenIdAuthenticator {
    private ConsumerManager manager = new ConsumerManager();
    private DiscoveryInformation discovered;
    private static final String returnURL = "http://localhost:8080/Task/index.jsf";


    public DiscoveryInformation getDiscovered() {
        return discovered;
    }

    public ConsumerManager getConsumerManager() {
        return manager;
    }

    public AuthRequest proceedRedirect(String providerUrl) {
        List discoveries;
        AuthRequest authReq = null;
        ProxyProperties proxyProps = new ProxyProperties();
        proxyProps.setProxyHostName("proxy.sam-solutions.net");
        proxyProps.setProxyPort(8080);
        HttpClientFactory.setProxyProperties(proxyProps);
        try {
            discoveries = manager.discover(providerUrl);
            discovered = manager.associate(discoveries);
            authReq = manager.authenticate(discovered, returnURL);
        } catch (DiscoveryException e) {
            e.printStackTrace();
        } catch (MessageException e) {
            e.printStackTrace();
        } catch (ConsumerException e) {
            e.printStackTrace();
        }

        return authReq;
    }
}

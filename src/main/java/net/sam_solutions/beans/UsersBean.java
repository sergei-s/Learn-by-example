package net.sam_solutions.beans;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import net.sam_solutions.service.UserService;
import net.sam_solutions.ws.UserExists;
import org.openid4java.association.AssociationException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.MessageException;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
import org.openid4java.util.HttpClientFactory;
import org.openid4java.util.ProxyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * User: Администратор
 * Date: 01.05.12
 */
@Component
@Scope(value = "session")
public class UsersBean implements Serializable {
    private String login;
    private String password;
    private String email;
    private String name;
    private String openIdUrl;
    ConsumerManager manager;
    public DiscoveryInformation discovered;
    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getOpenIdUrl() {
        return openIdUrl;
    }

    public void setOpenIdUrl(String openIdUrl) {
        this.openIdUrl = openIdUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameFromSpring() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public String doLogin() throws IOException, ServletException {
        ProxyProperties proxyProps = new ProxyProperties();
        proxyProps.setProxyHostName("proxy.sam-solutions.net");
        proxyProps.setProxyPort(8080);
        HttpClientFactory.setProxyProperties(proxyProps);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher
                ("/j_spring_security_check");
        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }

    public String checkRegistred() {
        Client client = Client.create();
        if (getLogin() == null) {
            setLogin(getNameFromSpring());
        }
        WebResource webResource = client.resource("http://localhost:8080/Task/rest/user/check").path(getLogin());
        return webResource.post(String.class);
    }

    public String create() {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8080/Task/rest/user").path(getLogin()).path
                (getPassword()).path(getName()).path(getEmail());
        ClientResponse response = webResource.post(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        return "success";
    }

    public void proceedOpenId() throws IOException, ServletException {
        /*Client client = Client.create();
        WebResource webResource = null;
        webResource = client.resource("http://localhost:8080/Task/rest/user/openId").path(getOpenIdUrl());
        String response = webResource.post(String.class);*/
        /*if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }*/
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher
                ("/j_spring_openid_security_check");
        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
        FacesContext.getCurrentInstance().responseComplete();

        /*manager = new ConsumerManager();
        String returnURL = "http://localhost:8080/Task/second.jsf";
        List discoveries;
        AuthRequest authReq = null;
        ProxyProperties proxyProps = new ProxyProperties();
        proxyProps.setProxyHostName("proxy.sam-solutions.net");
        proxyProps.setProxyPort(8080);
        HttpClientFactory.setProxyProperties(proxyProps);
        try {
            discoveries = manager.discover(getOpenIdUrl());
            discovered = manager.associate(discoveries);
            authReq = manager.authenticate(discovered, returnURL);
        } catch (DiscoveryException e) {
            e.printStackTrace();
        } catch (MessageException e) {
            e.printStackTrace();
        } catch (ConsumerException e) {
            e.printStackTrace();
        }
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(authReq.getDestinationUrl(true));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    public String verifyResponse() {
        ExternalContext context = javax.faces.context.FacesContext
                .getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        ParameterList openidResp = new ParameterList(request.getParameterMap());

        StringBuffer receivingURL = request.getRequestURL();
        String queryString = request.getQueryString();
        if (queryString != null && queryString.length() > 0) {
            receivingURL.append("?").append(request.getQueryString());
        }

        VerificationResult verification = null;
        try {
            verification = manager.verify(receivingURL.toString(), openidResp, discovered);
        } catch (MessageException e) {
            e.printStackTrace();
        } catch (DiscoveryException e) {
            e.printStackTrace();
        } catch (AssociationException e) {
            e.printStackTrace();
        }
        if (verification != null) {
            Identifier verified = verification.getVerifiedId();
            if (verified != null) {
                AuthSuccess authSuccess = (AuthSuccess) verification.getAuthResponse();
                String username = "";
                try {
                    username = authSuccess.getIdentity();
                } catch (DiscoveryException e) {
                    e.printStackTrace();
                }
                this.login = username.substring(8);
                return "ok";
            } else {
                return "fail";
            }
        } else {
            return "fail";
        }
    }

    public String getOnLoad() {
        String result;
        if (this.login == null) {
            result = verifyResponse();
        } else {
            result = "ok";
        }
        return result;
    }
}

package net.sam_solutions.open_id;

import org.openid4java.discovery.DiscoveryInformation;

/**
 * User: Sergei Savenko
 * Date: 04.07.12
 */
public class OpenIdResponse {

    private String destinationUrl;
    private DiscoveryInformation discovered;

    public OpenIdResponse(String destinationUrl, DiscoveryInformation discovered) {
        this.destinationUrl = destinationUrl;
        this.discovered = discovered;
    }

    public String getDestinationUrl() {
        return destinationUrl;
    }

    public void setDestinationUrl(String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }

    public DiscoveryInformation getDiscovered() {
        return discovered;
    }

    public void setDiscovered(DiscoveryInformation discovered) {
        this.discovered = discovered;
    }
}

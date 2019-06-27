# sling-proxy-servlet
Http-Proxy for Apache Sling or Adobe Experience Manager (AEM) for development purposes (simulate production proxy)


## Scenario "Local Development"

- [ ] Diagram with production vs. local development setup

Security : local only 

## Scenario "Production Maintenance"

- [ ] Diagram with production setup and maintenance access behind Apache Web-server

Additional Security 

- IP Range only (VPN endpoint)
- Deny IP (load balancer)

##### Read More

Webkomponenten mit OSGi – einfach und elegant (in German) 
https://jaxenter.de/webkomponenten-mit-osgi-einfach-und-elegant-23731


# Testing

### Enable Integration Testing
add Commandline parameter to add addition proxies

### Set Proxy for Test Requests

For using a network debugging proxy (e.g. [Charles](https://www.charlesproxy.com/) or [Fiddler](https://www.telerik.com/fiddler))
the JUnit test can fire all requests via an proxy.

- proxy.use-system-default
- proxy.server


### Set Proxy for servlet


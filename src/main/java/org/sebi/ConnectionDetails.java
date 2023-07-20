package org.sebi;

import io.quarkus.qute.TemplateData;

@TemplateData
public class ConnectionDetails {
    private String host;
    private String port;
    private String username;
    private String password;
    private String database;
    private String sslMode;
    private String serviceName;

    

    public ConnectionDetails(String host, String port, String username, String password, String database,
            String sslMode, String serviceName) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.database = database;
        this.sslMode = sslMode;
        this.serviceName = serviceName;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDatabase() {
        return database;
    }
    public void setDatabase(String database) {
        this.database = database;
    }
    public String getSslMode() {
        return sslMode;
    }
    public void setSslMode(String sslMode) {
        this.sslMode = sslMode;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    
}

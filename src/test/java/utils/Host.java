package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

public class Host {
    private static final Map<String, ServerConfig> hostMap = new HashMap<>();


    static {
        //staging env :
        hostMap.put("stg", new ServerConfig("192.168.100.25", 220 ,"https://stg-cms.ofbusiness.co.in/", "https://stg-sales.ofbusiness.co.in/","https://ofb.stg-oxyzoadmin.ofbusiness.co.in/ofe/","https://stg-api.ofbusiness.co.in/"));
        hostMap.put("stg1", new ServerConfig("192.168.100.25", 221,"https://stg1-cms.ofbusiness.co.in/","https://stg1-sales.ofbusiness.co.in/","https://ofb.stg1-oxyzoadmin.ofbusiness.co.in/ofe/","https://stg1-api.ofbusiness.co.in/"));
        hostMap.put("stg2", new ServerConfig("192.168.100.25", 222,"https://stg2-cms.ofbusiness.co.in/", "https://stg2-sales.ofbusiness.co.in/","https://ofb.stg2-oxyzoadmin.ofbusiness.co.in/ofe/","https://stg2-api.ofbusiness.co.in/"));
        hostMap.put("stg3", new ServerConfig("192.168.100.25", 223,"https://stg3-cms.ofbusiness.co.in/", "https://stg3-sales.ofbusiness.co.in/","https://ofb.stg3-oxyzoadmin.ofbusiness.co.in/ofe/","https://stg3-api.ofbusiness.co.in/"));
        hostMap.put("stg4", new ServerConfig("192.168.100.25", 224,"https://stg4-cms.ofbusiness.co.in/", "https://stg4-sales.ofbusiness.co.in/","https://ofb.stg4-oxyzoadmin.ofbusiness.co.in/ofe/","https://stg4-api.ofbusiness.co.in/"));
        hostMap.put("stg5", new ServerConfig("192.168.100.23", 7501,"https://stg5-cms.ofbusiness.co.in/", "https://stg5-sales.ofbusiness.co.in/","https://ofb.stg5-oxyzoadmin.ofbusiness.co.in/ofe/","https://stg5-api.ofbusiness.co.in/"));
        hostMap.put("stg6", new ServerConfig("192.168.100.23", 7701,"https://stg6-cms.ofbusiness.co.in/", "https://stg6-sales.ofbusiness.co.in/","https://ofb.stg6-oxyzoadmin.ofbusiness.co.in/ofe/","https://stg6-api.ofbusiness.co.in/"));

        //uat env :
        hostMap.put("uat", new ServerConfig("192.168.100.23", 3801,"https://uat-cms.ofbusiness.co.in/","https://uat-sales.ofbusiness.co.in/","https://ofb.uat-oxyzoadmin.ofbusiness.co.in/ofe/","https://uat-ofb-api.ofbusiness.co.in/"));
    }

    public static ServerConfig getServerConfig(String configName) {
        return hostMap.get(configName);
    }

    @Getter
    @ToString
    @AllArgsConstructor
    public static class ServerConfig {
        private final String server;
        private final int port;
        private final String baseUrl;
        private final String salesUrl;
        private final String oxyzoAdminUrl;
        private final String api;
    }
}
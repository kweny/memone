/*
 * Copyright (C) 2018 Apenk.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apenk.memone.application.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.apenk.memone.application.autoconfig.EnableMongoCarefree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Kweny DataSourceConfigurer
 *
 * @author Kweny
 * @since TODO version
 */
//@Configuration("dataSourceConfigurer")
@EnableMongoCarefree
public class DataSourceConfigurer {

    private final DataSourceProperties dataSourceProperties;

    @Autowired
    public DataSourceConfigurer(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean
    public MongoClientOptions mongoClientOptions() {
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.minConnectionsPerHost(dataSourceProperties.getMinConnectionPerHost())
                .connectionsPerHost(dataSourceProperties.getMaxConnectionPerHost())
                .threadsAllowedToBlockForConnectionMultiplier(dataSourceProperties.getThreadsAllowedToBlockForConnectionMultiplier())
                .serverSelectionTimeout(dataSourceProperties.getServerSelectionTimeout())
                .maxWaitTime(dataSourceProperties.getMaxWaitTime())
                .maxConnectionIdleTime(dataSourceProperties.getMaxConnectionIdleTime())
                .maxConnectionLifeTime(dataSourceProperties.getMaxConnectionLifeTime())
                .connectTimeout(dataSourceProperties.getConnectTimeout())
                .socketTimeout(dataSourceProperties.getSocketTimeout())
//                .socketKeepAlive(dataSourceProperties.getSocketKeepAlive())
                .alwaysUseMBeans(dataSourceProperties.getAlwaysUseMBeans())
                .heartbeatFrequency(dataSourceProperties.getHeartbeatFrequency())
                .minHeartbeatFrequency(dataSourceProperties.getMinHeartbeatFrequency())
                .heartbeatConnectTimeout(dataSourceProperties.getHeartbeatConnectTimeout())
                .heartbeatSocketTimeout(dataSourceProperties.getHeartbeatSocketTimeout())
                .localThreshold(dataSourceProperties.getLocalThreshold())
                .sslEnabled(dataSourceProperties.getSslEnabled())
                .sslInvalidHostNameAllowed(dataSourceProperties.getSslInvalidHostNameAllowed());
        if (dataSourceProperties.getReplicaSet() != null) {
            builder.requiredReplicaSetName(dataSourceProperties.getReplicaSet());
        }
        return builder.build();
    }

    public MongoDbFactory mongoDbFactory() {
        List<ServerAddress> serverAddressList = new ArrayList<>();
        for (String address : dataSourceProperties.getAddresses()) {
            String host = address;
            int port = 27017;
            int sepIndex = address.indexOf(":");
            if (sepIndex != -1) {
                host = address.substring(0, sepIndex);
                port = Integer.parseInt(address.substring(sepIndex + 1));
            }
            ServerAddress serverAddress = new ServerAddress(host, port);
            serverAddressList.add(serverAddress);
        }

//        List<MongoCredential> mongoCredentialList = new ArrayList<>();
//        if (dataSourceProperties.getUsername() != null) {
//
//            mongoCredentialList.add(MongoCredential.createScramSha1Credential(dataSourceProperties.getUsername(), ))
//        }

        MongoClient mongoClient = new MongoClient();
        return new SimpleMongoDbFactory(mongoClient, dataSourceProperties.getDatabase());
    }
}
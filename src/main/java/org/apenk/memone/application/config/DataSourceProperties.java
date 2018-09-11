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

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * TODO Kweny DataSourceProperties
 *
 * @author Kweny
 * @since TODO version
 */
@Component("dataSourceProperties")
//@Validated
@PropertySource(value = "classpath:application.yml")
//@ConfigurationProperties(prefix = "mongo")
public class DataSourceProperties {
    @NotEmpty
    private List<String> addresses;
    @NotBlank
    private String database;
    private String username;
    private String password;
    private String authenticationDatabase;
    /**
     * 集群所需的副本集名称
     */
    private String replicaSet;
    /**
     * 每个主机的最小连接数。不能小于 0。
     */
    private Integer minConnectionPerHost = 0;
    /**
     * 每个主机的最大连接数。不能小于 1。
     */
    private Integer maxConnectionPerHost = 100;
    /**
     * 允许阻塞的连接线程数系数，
     * 其与 {@link #maxConnectionPerHost} 相乘的结果就是连接等待队列的最大值。
     * 不能小于 1。
     */
    private Integer threadsAllowedToBlockForConnectionMultiplier = 5;
    /**
     * 线程从连接池中获取可用连接的最长等待时间（毫秒）。
     */
    private Integer maxWaitTime = 120000;
    /**
     * 连接池中的连接最大空闲时间（毫秒）。
     * 必须 >= 0，0 表示无限制。
     */
    private Integer maxConnectionIdleTime = 0;
    /**
     * 连接池中的连接最长寿命（毫秒）。
     * 必须 >= 0，0 表示无限制。
     */
    private Integer maxConnectionLifeTime = 0;
    /**
     * 连接超时时间（毫秒）。必须 > 0。
     */
    private Integer connectTimeout = 10000;
    /**
     * Socket 超时时间（毫秒）。
     */
    private Integer socketTimeout = 0;
    /**
     * 是否启用 socket keep-alive。默认为 true（启用），不建议禁用。
     */
    private Boolean socketKeepAlive = true;
    /**
     * 驱动程序注册的 JMX Beans 是否始终为 MBeans，无论 VM 是不是 Java 6 及更高版本。
     * 当该值为 false 时，驱动程序将在 Java 6 或更高版本时使用 MXBeans；Java 5 中使用 MBeans。
     */
    private Boolean alwaysUseMBeans = false;
    /**
     * 集群心跳检测频率（毫秒）。必须 > 0。默认 10000。
     */
    private Integer heartbeatFrequency = 10000;
    /**
     * 集群心跳检测最小频率（毫秒）。必须 > 0。默认 500。
     * 如果驱动程序需要经常检查服务器的可用性，那么距离上次检测将至少等待这么长时间，以避免资源浪费。
     */
    private Integer minHeartbeatFrequency = 500;
    /**
     * 集群心跳检测的连接超时时间（毫秒）。
     */
    private Integer heartbeatConnectTimeout = 20000;
    /**
     * 集群心跳检测连接的 socket 超时时间（毫秒）。
     */
    private Integer heartbeatSocketTimeout = 20000;
    /**
     * 服务器选择的超时时间（毫秒），指定驱动程序从集群中选择服务器时，无法成功执行而放弃并抛出异常的时间。
     * 可以根据需要（如耐心等待或快速返回错误）来设置该参数的值。
     * 默认为 30000（30秒），这个时间对于在经典的故障恢复阶段中选举出新的主节点来说已经足够。
     * 0 表示无可用服务器，将立即超时；负值意味着无限期等待。
     */
    private Integer serverSelectionTimeout = 30000;
    /**
     * 如果对于某个操作存在多个合适的服务器，则以该参数的值来确定一个基于延迟时间（RTT）的可接受的延迟窗口范围（Latency Window）。
     * 以延迟最小的服务器为基准（最小延迟时间），所有延迟时间和最小延迟时间的差值小于该参数的服务器，都有资格被随机选中。
     * 如果该参数设为 0，则不使用随机算法，而是选择延迟时间最小的服务器。
     * 默认值为 15 毫秒，意味着所有资格服务器之间的延迟时间只有 15 毫秒以内的差异。
     */
    private Integer localThreshold = 15;
    /**
     * 是否使用 SSL。
     * 如果未设置 socketFactory，将该值设为 true 时将同时设置 socketFactory 为 {@link SSLSocketFactory#getDefault()}；
     * 将该值设为 false 时将同时设置 socketFactory 为 {@link SocketFactory#getDefault()}。
     * 如果设置了 socket factory，并且开启了 sslEnabled，则 socket factory 必须创建 {@link javax.net.ssl.SSLSocket} 的实例，
     * 否则 MongoClient 将拒绝连接。
     */
    private Boolean sslEnabled = false;
    /**
     * 是否允许无效的主机名（关闭 SSL 证书域名检测）。设为 true 将使应用容易受到中间人攻击。
     */
    private Boolean sslInvalidHostNameAllowed = false;


    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
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

    public String getReplicaSet() {
        return replicaSet;
    }

    public void setReplicaSet(String replicaSet) {
        this.replicaSet = replicaSet;
    }

    public Integer getMinConnectionPerHost() {
        return minConnectionPerHost;
    }

    public void setMinConnectionPerHost(Integer minConnectionPerHost) {
        this.minConnectionPerHost = minConnectionPerHost;
    }

    public Integer getMaxConnectionPerHost() {
        return maxConnectionPerHost;
    }

    public void setMaxConnectionPerHost(Integer maxConnectionPerHost) {
        this.maxConnectionPerHost = maxConnectionPerHost;
    }

    public Integer getThreadsAllowedToBlockForConnectionMultiplier() {
        return threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(Integer threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }

    public Integer getServerSelectionTimeout() {
        return serverSelectionTimeout;
    }

    public void setServerSelectionTimeout(Integer serverSelectionTimeout) {
        this.serverSelectionTimeout = serverSelectionTimeout;
    }

    public Integer getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(Integer maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public Integer getMaxConnectionIdleTime() {
        return maxConnectionIdleTime;
    }

    public void setMaxConnectionIdleTime(Integer maxConnectionIdleTime) {
        this.maxConnectionIdleTime = maxConnectionIdleTime;
    }

    public Integer getMaxConnectionLifeTime() {
        return maxConnectionLifeTime;
    }

    public void setMaxConnectionLifeTime(Integer maxConnectionLifeTime) {
        this.maxConnectionLifeTime = maxConnectionLifeTime;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Boolean getSslInvalidHostNameAllowed() {
        return sslInvalidHostNameAllowed;
    }

    public void setSslInvalidHostNameAllowed(Boolean sslInvalidHostNameAllowed) {
        this.sslInvalidHostNameAllowed = sslInvalidHostNameAllowed;
    }

    public Boolean getAlwaysUseMBeans() {
        return alwaysUseMBeans;
    }

    public void setAlwaysUseMBeans(Boolean alwaysUseMBeans) {
        this.alwaysUseMBeans = alwaysUseMBeans;
    }

    public Integer getHeartbeatFrequency() {
        return heartbeatFrequency;
    }

    public void setHeartbeatFrequency(Integer heartbeatFrequency) {
        this.heartbeatFrequency = heartbeatFrequency;
    }

    public Integer getMinHeartbeatFrequency() {
        return minHeartbeatFrequency;
    }

    public void setMinHeartbeatFrequency(Integer minHeartbeatFrequency) {
        this.minHeartbeatFrequency = minHeartbeatFrequency;
    }

    public Integer getHeartbeatConnectTimeout() {
        return heartbeatConnectTimeout;
    }

    public void setHeartbeatConnectTimeout(Integer heartbeatConnectTimeout) {
        this.heartbeatConnectTimeout = heartbeatConnectTimeout;
    }

    public Integer getHeartbeatSocketTimeout() {
        return heartbeatSocketTimeout;
    }

    public void setHeartbeatSocketTimeout(Integer heartbeatSocketTimeout) {
        this.heartbeatSocketTimeout = heartbeatSocketTimeout;
    }

    public Integer getLocalThreshold() {
        return localThreshold;
    }

    public void setLocalThreshold(Integer localThreshold) {
        this.localThreshold = localThreshold;
    }

    public Boolean getSocketKeepAlive() {
        return socketKeepAlive;
    }

    public void setSocketKeepAlive(Boolean socketKeepAlive) {
        this.socketKeepAlive = socketKeepAlive;
    }

    public Boolean getSslEnabled() {
        return sslEnabled;
    }

    public void setSslEnabled(Boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
    }

    public String getAuthenticationDatabase() {
        return authenticationDatabase;
    }

    public void setAuthenticationDatabase(String authenticationDatabase) {
        this.authenticationDatabase = authenticationDatabase;
    }
}
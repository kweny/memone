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

package org.apenk.memone.application.autoconfig;

import com.mongodb.*;
import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.connection.ServerSettings;
import com.mongodb.connection.SocketSettings;
import com.mongodb.connection.SslSettings;
import com.mongodb.event.ClusterListener;
import com.mongodb.event.CommandListener;
import com.mongodb.selector.ServerSelector;
import org.bson.codecs.configuration.CodecRegistry;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import java.util.Collections;
import java.util.List;

/**
 * TODO Kweny MongoCarefreeOption
 *
 * @author Kweny
 * @since TODO version
 */
public class MongoCarefreeOption {

    private List<String> addresses;
    private String database;
    private String uri;
    private String username;
    private String password;
    private String authenticationDatabase;

    private Boolean showClass;
    private String gridFsDatabase;


    /**
     * MongoClient 的描述。
     * ---
     * 获取此 MongoClient 的描述。该描述用于日志记录和 JMX 等位置。默认值为 null。
     */
    private final String description;
    /**
     * 应用程序的逻辑名称。
     * 3.4
     * ---
     * 通过此 MongoClient 获取应用程序的逻辑名称。客户端可以据此标识应用程序服务器，以用于服务器日志、慢查询日志及概要收集。默认值为 null。
     */
    private final String applicationName;
    /**
     * 向 MongoDB 服务器收发消息的压缩器列表。
     * 3.4
     * ---
     * 获取用于向服务器收发消息的压缩器列表。驱动程序将使用服务器配置支持的列表中的第一个压缩器。默认值为空列表。
     */
    private final List<MongoCompressor> compressorList;
    /**
     * <p>用于查询、Map-Reduce、聚合、计数的读取首选项。默认 {@link ReadPreference#primary()}</p>
     * <p>
     * MongoDB 有 5 种 ReadPreference 模式——
     * <ul>
     *     <li><b>primary：</b>主节点，默认模式，读操作只在主节点，如果主节点不可用，则报错或抛出异常。</li>
     *     <li><b>primaryPreferred：</b>首选主节点，多数情况下读操作在主节点，如果主节点不可用（如故障转移），则读操作在从节点。</li>
     *     <li><b>secondary：</b>从节点，读操作只在从节点，如果从节点不可用，则报错或抛出异常。</li>
     *     <li><b>secondaryPreferred：</b>首选从节点，多数情况下读操作在从节点，特殊情况（如单主节点架构），则读操作在主节点。</li>
     *     <li><b>nearest：</b>最临街节点，读操作在最邻近的节点，可能是主节点或从节点。</li>
     * </ul>
     * </p>
     */
    private final ReadPreference readPreference;
    /**
     * 写入策略。默认 {@link WriteConcern#ACKNOWLEDGED}。
     *
     * <p>{@code w} - 该选项要求确认操作已经传递到指定数量的节点或指定标签的节点。</p>
     * <ul>
     *     <li>0：非应答式写入，不返回任何响应。无法知道写入是否成功，但对于尝试向已关闭的套接字写入或者网络故障会返回异常。</li>
     *     <li>1：应答式写入，等待确认操作已经传递到指定的单个节点或副本集主节点后进行响应。默认值。</li>
     *     <li>&gt;=2：用于副本集环境，等待多个节点的确认，包括主节点和从节点，该值设定写入节点的数量。</li>
     *     <li>"majority"：大多数，适用于集群架构，要求写入操作已经传递到绝大多数投票节点以及主节点后进行响应。</li>
     *     <li>"&lt;tag set name&gt;"：要求写入操作已经传递到指定 tag 标记的副本集中的成员后进行相应。</li>
     * </ul>
     *
     * <p>{@code wtimeout} - 写入超时时间（毫秒）。用于设定一个时间限制，以防止写操作被无限制阻塞导致无法响应客户端。
     * 该参数仅适用于集群环境，当 {@code w} 值 &gt;=2 时生效。
     * 当某个节点的写入超过 {@code wtimeout} 指定的时间后，将返回一个错误，在捕获到超时之前，不会撤销其它节点已经完成的写入。</p>
     * <ul>
     *   <li>0：无限期。</li>
     *   <li>&gt;0：等待的时间。</li>
     * </ul>
     *
     * <p>其它选项：</p>
     * <ul>
     *   <li>{@code journal}：当确认写操作已经提交到 journal 日志之后进行响应。
     *   该选项要求开启 journal 功能。不能与 {@code fsync} 结合使用。
     *   在 MongoDB 2.6 之前，如果服务器在没有开启 journal 的情况下运行，则忽略此选项。
     *   从 MongoDB 2.6 开始，如果服务器没有开启 journal，使用此选项将失败并抛出异常。
     *   写入 journal 操作必须等到下次提交日志时完成写入，为了降低延迟，可以通过增加 commit journal 的频率来加快 journal 写入。
     * </ul>
     */
    private final WriteConcern writeConcern;
    /**
     * 当由于网络错误而写入失败时，是否重试。
     * 3.6
     */
    private final boolean retryWrites;
    /**
     * 读取策略。
     * 3.2
     * ---
     * 决定从 MongoDB 中读取数据时，能读取到什么样的数据——
     * <ul>
     *     <li>"local"：能读取任意数据，默认值。</li>
     *     <li>"majority"：只能读取到成功写入大多数节点的数据。</li>
     * </ul>
     *
     * 该参数可用于解决“脏读”问题，如从 primary 节点上读取了某条数据，但这条数据并没有同步到大多数节点，然后 primary 节点故障。
     * 重新恢复后 primary 节点会将未同步到大多数节点的数据回滚掉，导致读取到了“脏数据”。
     * 需要注意的是，readConcern 能保证读取到的数据不会发生回滚，但并不能保证读到的数据时最新的。
     * 该参数可以和 {@link #readPreference} 配合使用。
     */
    private final ReadConcern readConcern;
    /**
     *
     */
    private final CodecRegistry codecRegistry;
    /**
     *
     */
    private final ServerSelector serverSelector;

    /**
     *
     */
    private final int minConnectionsPerHost;
    /**
     *
     */
    private final int maxConnectionsPerHost;
    /**
     *
     */
    private final int threadsAllowedToBlockForConnectionMultiplier;
    /**
     *
     */
    private final int serverSelectionTimeout;
    /**
     *
     */
    private final int maxWaitTime;
    /**
     *
     */
    private final int maxConnectionIdleTime;
    /**
     *
     */
    private final int maxConnectionLifeTime;

    /**
     *
     */
    private final int connectTimeout;
    /**
     *
     */
    private final int socketTimeout;
    /**
     *
     */
    private final boolean socketKeepAlive;
    /**
     *
     */
    private final boolean sslEnabled;
    /**
     *
     */
    private final boolean sslInvalidHostNameAllowed;
    /**
     *
     */
    private final SSLContext sslContext;
    /**
     *
     */
    private final boolean alwaysUseMBeans;
    /**
     *
     */
    private final int heartbeatFrequency;
    /**
     *
     */
    private final int minHeartbeatFrequency;
    /**
     *
     */
    private final int heartbeatConnectTimeout;
    /**
     *
     */
    private final int heartbeatSocketTimeout;
    /**
     *
     */
    private final int localThreshold;

    /**
     *
     */
    private final String requiredReplicaSetName;
    /**
     *
     */
    private final DBDecoderFactory dbDecoderFactory;
    /**
     *
     */
    private final DBEncoderFactory dbEncoderFactory;
    /**
     *
     */
    private final SocketFactory socketFactory;
    /**
     *
     */
    private final boolean cursorFinalizerEnabled;
    /**
     *
     */
    private final ConnectionPoolSettings connectionPoolSettings;
    /**
     *
     */
    private final SocketSettings socketSettings;
    /**
     *
     */
    private final ServerSettings serverSettings;
    /**
     *
     */
    private final SocketSettings heartbeatSocketSettings;
    /**
     *
     */
    private final SslSettings sslSettings;

    /**
     *
     */
    private final List<ClusterListener> clusterListeners;
    /**
     *
     */
    private final List<CommandListener> commandListeners;


    public MongoCarefreeOption() {
        this.addresses = Collections.singletonList("127.0.0.1:27017");
        this.database = "test";
        this.uri = "mongodb://localhost/test";
        this.authenticationDatabase = "admin";
    }
}
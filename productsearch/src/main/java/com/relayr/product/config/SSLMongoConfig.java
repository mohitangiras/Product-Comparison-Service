package com.relayr.product.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.Collections;
import java.util.List;

/**
 * Mongo configuration file
 */
@Configuration
public class SSLMongoConfig {
    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Value("${spring.data.mongodb.hostname}")
    private String hostname;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Autowired
    MongoMappingContext mongoMappingContext;

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoClient mongoClient = MongoClients.create(mongoClientSettings());

        final MongoDatabaseFactory mongoDatabaseFactory = new SimpleMongoClientDatabaseFactory(mongoClient, database);

        final DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);

        final MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.afterPropertiesSet();
        return new MongoTemplate(mongoDatabaseFactory, converter);
    }

    @Bean
    public MongoClientSettings mongoClientSettings() {
        List<ServerAddress> serverAddresses = Collections.singletonList(new ServerAddress(hostname, port));
        MongoCredential credential = MongoCredential.createScramSha1Credential(username, database, password.toCharArray());

        return MongoClientSettings.builder()
                .credential(credential)
                .applyToSslSettings(builder -> builder.enabled(false))
                .applyToClusterSettings(builder -> builder.hosts(serverAddresses))
                .build();
    }
}

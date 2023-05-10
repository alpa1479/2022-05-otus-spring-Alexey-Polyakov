package ru.otus.spring.hw11.configuration.db;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ReadConcern;
import com.mongodb.WriteConcern;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import io.mongock.driver.mongodb.reactive.driver.MongoReactiveDriver;
import io.mongock.runner.springboot.MongockSpringboot;
import io.mongock.runner.springboot.base.MongockInitializingBeanRunner;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class MongoDbConfiguration {

    @Bean
    public MongockInitializingBeanRunner mongockInitializingBeanRunner(MongoClient reactiveMongoClient,
                                                                       MongoProperties mongoProperties, ApplicationContext context) {
        MongoReactiveDriver driver = MongoReactiveDriver.withDefaultLock(reactiveMongoClient, mongoProperties.getDatabase());
        driver.setReadConcern(ReadConcern.LOCAL);
        driver.setWriteConcern(WriteConcern.MAJORITY.withJournal(false));
        return MongockSpringboot.builder()
                .setDriver(driver)
                .addMigrationScanPackage("ru.otus.spring.hw11.mongock.changeunits")
                .setSpringContext(context)
                .setTransactionEnabled(false)
                .buildInitializingBeanRunner();
    }

    @Bean
    MongoClient mongoClient(MongoProperties mongoProperties) {
        PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        return MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(String.format("mongodb://%s:%s/", mongoProperties.getHost(), mongoProperties.getPort())))
                .codecRegistry(codecRegistry)
                .build());
    }
}

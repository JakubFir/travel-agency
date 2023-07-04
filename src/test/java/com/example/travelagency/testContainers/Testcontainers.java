package com.example.travelagency.testContainers;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@org.testcontainers.junit.jupiter.Testcontainers
public abstract class Testcontainers {

    protected static Network sharedNetwork = Network.newNetwork();

    @Container
    protected static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:5.7")
            .withDatabaseName("travel_agency")
            .withUsername("root")
            .withPassword("password")
            .withExposedPorts(3306)
            .withNetwork(sharedNetwork);

    @DynamicPropertySource
    public static void smySQLContainerConfig(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Container
    protected static GenericContainer<?> backendContainer = new GenericContainer<>(DockerImageName.parse("travel"))
            .withExposedPorts(8080)
            .withNetwork(mySQLContainer.getNetwork())
            .withNetworkAliases("backend")
            .dependsOn(mySQLContainer)
            .withEnv("spring.datasource.url", "jdbc:mysql://" + mySQLContainer.getNetworkAliases().iterator().next() + ":" + "3306/travel_agency")
            .withEnv("spring.datasource.username", "root")
            .withEnv("spring.datasource.password", "password")
            .withEnv("server.port", "8080");
}

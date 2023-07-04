package com.example.travelagency.testContainers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestcontainerTests extends Testcontainers {
    @Test
    public void canRunMySqlTestcontainer() {
        assertThat(mySQLContainer.isRunning()).isTrue();
        assertThat(mySQLContainer.isCreated()).isTrue();
    }

    @Test
    public void canStartUpAppTestcontainer(){
        assertThat(backendContainer.isRunning()).isTrue();
        assertThat(backendContainer.isCreated()).isTrue();
    }
}

package com.example.pma.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManufacturerConfig {
    @Bean
    public Car anotherCar() {
        Engine engine = new Engine();
        Doors doors = new Doors();
        Tires tires = new Tires();
        return new Car(doors, engine, tires);
    }
}

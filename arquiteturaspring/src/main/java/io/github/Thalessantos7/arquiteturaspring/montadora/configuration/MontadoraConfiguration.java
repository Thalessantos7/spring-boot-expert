package io.github.Thalessantos7.arquiteturaspring.montadora.configuration;

import io.github.Thalessantos7.arquiteturaspring.montadora.Motor;
import io.github.Thalessantos7.arquiteturaspring.montadora.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MontadoraConfiguration {
    @Bean
    public Motor motor() {
        var motor = new Motor();

        motor.setCavalos(120);
        motor.setCilindros(4);
        motor.setModelo("XPTO-0");
        motor.setTipoMotor(TipoMotor.ASPIRADO);

        return motor;
    }
}
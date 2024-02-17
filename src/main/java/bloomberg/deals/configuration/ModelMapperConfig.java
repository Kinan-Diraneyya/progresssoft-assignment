package bloomberg.deals.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up ModelMapper within the Spring application context.
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Creates and configures a {@link ModelMapper} bean for use throughout the application.
     * @return A default ModelMapper instance.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

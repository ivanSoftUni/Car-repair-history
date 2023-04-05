package softuni.carrepairhistory.config;

import org.modelmapper.ModelMapper;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CacheManager cacheManager() {
        return new CacheManager() {
            @Override
            public Cache getCache(String name) {
                return null;
            }

            @Override
            public Collection<String> getCacheNames() {
                return null;
            }
        };
    }
}

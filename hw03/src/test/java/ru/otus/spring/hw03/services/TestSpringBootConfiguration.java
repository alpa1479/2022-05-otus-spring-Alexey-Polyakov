package ru.otus.spring.hw03.services;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import ru.otus.spring.hw03.config.StudentsSurveyApplicationProperties;
import ru.otus.spring.hw03.services.i18n.I18nService;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ComponentScan
@SpringBootConfiguration
@ConfigurationPropertiesScan(basePackageClasses = {StudentsSurveyApplicationProperties.class})
public class TestSpringBootConfiguration {

    @Bean
    @Primary
    I18nService i18nService() {
        I18nService i18n = mock(I18nService.class);
        when(i18n.getMessage(anyString())).then(returnsFirstArg());
        return i18n;
    }
}

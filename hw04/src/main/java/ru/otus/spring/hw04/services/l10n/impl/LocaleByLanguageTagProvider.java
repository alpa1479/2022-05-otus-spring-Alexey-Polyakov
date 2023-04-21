package ru.otus.spring.hw04.services.l10n.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw04.config.StudentsSurveyApplicationProperties;
import ru.otus.spring.hw04.services.l10n.LocaleProvider;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LocaleByLanguageTagProvider implements LocaleProvider {

    private final StudentsSurveyApplicationProperties properties;

    @Override
    public Locale getLocale() {
        return Locale.forLanguageTag(properties.getLocale());
    }
}

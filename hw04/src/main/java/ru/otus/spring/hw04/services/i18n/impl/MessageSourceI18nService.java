package ru.otus.spring.hw04.services.i18n.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw04.services.i18n.I18nService;
import ru.otus.spring.hw04.services.l10n.LocaleProvider;

@Service
@RequiredArgsConstructor
public class MessageSourceI18nService implements I18nService {

    private final MessageSource messageSource;
    private final LocaleProvider localeProvider;

    @Override
    public String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, localeProvider.getLocale());
    }
}

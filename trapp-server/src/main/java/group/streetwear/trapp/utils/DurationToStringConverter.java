package group.streetwear.trapp.utils;

import org.springframework.core.convert.converter.Converter;

import java.time.Duration;

public class DurationToStringConverter implements Converter<Duration, String> {

    private static final String DURATION_PATTERN = "%dh %dm";

    @Override
    public String convert(Duration duration) {
        return String.format(DURATION_PATTERN, duration.toHours(), duration.minusHours(duration.toHours()).toMinutes());
    }
}

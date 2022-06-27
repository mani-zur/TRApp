package group.streetwear.trapp.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DurationToStringConverterTest {

    @Autowired
    ConversionService conversionService;

    @Test
    public void shouldConvertCorrectly() {
        //given
        Duration duration = Duration.ofHours(5).plusMinutes(55);

        //when
        String result = conversionService.convert(duration, String.class);

        //then
        String expectedValue = "5h 55m";
        assertThat(expectedValue.equals(result));

    }

}
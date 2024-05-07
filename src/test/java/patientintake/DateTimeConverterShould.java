package patientintake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeConverterShould {
    @Test
    void convertTodayStringCorrectly(){
        LocalDate today = LocalDate.of(2024, 5, 6);
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 8:00 pm",
                today);
        assertEquals(result,LocalDateTime.of(2024, 5, 6, 20, 0),
                "Failed to convert 'today' string to expected date time, today passed was: " + today);

    }

    @Test
    void convertCorrectPatternToDateTime(){
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("5/6/2024 8:00 pm",
                LocalDate.of(2024, 5, 6));
        assertEquals(result,LocalDateTime.of(2024, 5, 6, 20, 0));
    }
    @Test
    void throwExceptionIfIncorrectPatternProvided(){
        Throwable error = assertThrows(RuntimeException.class, () ->
                DateTimeConverter.convertStringToDateTime("9/4/2024 800 pm",
                        LocalDate.of(2024, 9, 4)));
        assertEquals("Unable to create date time from: [9/4/2024 800 PM], please enter with format [M/d/yyyy h:mm a] Text '9/4/2024 800 PM' could not be parsed at index 12", error.getMessage());
    }

}
package telran.time;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class DateTimeTest {
    @Test
    void localDateTest() {
    LocalDate current = LocalDate.now();
    LocalDateTime currentDateTime = LocalDateTime.now();
    ZonedDateTime currentZonedTime = ZonedDateTime.now();
    Instant currentInstant = Instant.now();
    LocalTime currentLocalTime = LocalTime.now();
    System.out.printf("Current date: %s \n", current);
    System.out.printf("Current date time: %s \n", currentDateTime);
    System.out.printf("Current zoned time: %s \n", currentZonedTime);
    System.out.printf("Current instant: %s \n", currentInstant);
    System.out.printf("Current local time: %s \n", currentLocalTime);
    System.out.printf("Current date time: is %s in dd/mm/yyyy \n", current.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy", Locale.forLanguageTag("he"))));
    }

    @Test 
    void nextFriday13Test() {
        LocalDate current = LocalDate.of(2024,8,11);
        LocalDate expectDate = LocalDate.of(2024,9,13);
        assertEquals(expectDate, current.with(new NextFriday13()));
    }

    @Test
    void pastTemporalDateProximityTest() {
        LocalDate date12Jan = LocalDate.of(2024,1,12);
        LocalDate date15May = LocalDate.of(2024,5,15);
        LocalDate date11May = LocalDate.of(2024,5,11);
        LocalDate date22Aug = LocalDate.of(2023,8,18);
        LocalDate date24Apr = LocalDate.of(2023,4,24);
        LocalDate date1Sep = LocalDate.of(2022,9,1);

        Temporal[] dates = {date12Jan, date15May, date22Aug, date24Apr, date1Sep,date11May};
        PastTemporalDateProximity temporalDateProximity = new PastTemporalDateProximity(dates);
        assertEquals(date1Sep, temporalDateProximity.adjustInto(date24Apr));
        assertEquals(date11May, temporalDateProximity.adjustInto(date15May));
        assertEquals(date22Aug, temporalDateProximity.adjustInto(date12Jan));
        assertEquals(null, temporalDateProximity.adjustInto(date1Sep));

    }
}

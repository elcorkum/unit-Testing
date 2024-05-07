package patientintake;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ClinicCalendarShould {

    private ClinicCalendar calendar;

    @BeforeAll
    static void testClassSetup(){
        System.out.println("Before all...");
    }

    @BeforeEach
    void init(){
        System.out.println("Before each...");
        calendar = new ClinicCalendar(LocalDate.of(2024, 5, 3));
    }

    @Test
    public void allowEntryOfAnAppointment(){
        System.out.println("entry of appointment...");
        calendar.addAppointment("Lu", "Young", "murphy", "5/6/2024 3:15 pm");
        List<PatientAppointment>appointments = calendar.getAppointments();
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
        PatientAppointment actualAppointment = appointments.get(0);
        assertEquals("Lu", actualAppointment.getPatientFirstName());
        assertEquals("Young", actualAppointment.getPatientLastName());
        assertSame(Doctor.murphy, actualAppointment.getDoctor());
        assertEquals("5/6/2024 03:15 PM", actualAppointment.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));

    }
    @Test
    void returnTrueForHasAppointmentsIfThereAreAppointments(){
        System.out.println("Checking if appointment exists after entry...");
        calendar.addAppointment("Zu", "Mei", "avery", "05/20/2024 2:00 pm");
        assertTrue(calendar.hasAppointment(LocalDate.of(2024,5, 20)));
    }
    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments(){
        System.out.println("Confirming no appointment if no entry has been made...");
        assertFalse(calendar.hasAppointment(LocalDate.of(2024,5, 20)));
    }

    @Test
    void returnCurrentDaysAppointments(){
        System.out.println("Checking appointments for today...");
        calendar.addAppointment("Mel", "Gibb", "murphy", "5/4/2024 7:00 pm");
        calendar.addAppointment("Neo", "Fibb", "murphy", "5/4/2024 8:00 pm");
        calendar.addAppointment("Leo", "Nibb", "murphy", "5/13/2024 11:00 am");
        assertEquals(2, calendar.getTodayAppointments().size());
        //assertIterableEquals(calendar.getTodayAppointments(), calendar.getAppointments());
    }

    @AfterEach
    void tearDownEachTest(){
        System.out.println("After each...");
    }

    @AfterAll
    static void tearDownTestClass(){
        System.out.println("After all...");
    }




}
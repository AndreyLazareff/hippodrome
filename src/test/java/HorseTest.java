import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    @Test
    void constructor_ShouldThrowException_WhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 10, 5);
        });
    }

    @Test
    void constructor_ShouldThrowExceptionWithMessage_WhenNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 10, 5);
        });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    ", "\t", "\n", "\r\n", "\r"})
    void constructor_ShouldThrowException_WhenNameIsBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 10, 5);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    ", "\t", "\n", "\r\n", "\r"})
    void constructor_ShouldThrowExceptionWithMessage_WhenNameIsBlank(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 10, 5);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructor_ShouldThrowException_WhenSpeedIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Lightning", -5, 10);
        });
    }

    @Test
    void constructor_ShouldThrowExceptionWithMessage_WhenSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Lightning", -5, 10);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructor_ShouldThrowException_WhenDistanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Thunder", 10, -5);
        });
    }

    @Test
    void constructor_ShouldThrowExceptionWithMessage_WhenDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Thunder", 10, -5);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName_ShouldReturnNamePassedToConstructor() {
        Horse horse = new Horse("Lightning", 10, 5);
        assertEquals("Lightning", horse.getName());
    }

    @Test
    void getSpeed_ShouldReturnSpeedPassedToConstructor() {
        Horse horse = new Horse("Lightning", 15.5, 5);
        assertEquals(15.5, horse.getSpeed());
    }

    @Test
    void getDistance_ShouldReturnDistancePassedToConstructor() {
        Horse horse = new Horse("Thunder", 12.5, 7.8);
        assertEquals(7.8, horse.getDistance());
    }

    @Test
    void getDistance_ShouldReturnZero_WhenConstructedWithTwoParameters() {
        Horse horse = new Horse("Thunder", 12.5);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void move_ShouldCallGetRandomDoubleWithCorrectParameters() {
        Horse horse = new Horse("Lightning", 10, 5);
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "5, 10, 0.5, 10",
            "0, 12, 0.3, 3.6",
            "2, 8, 0.7, 7.6"
    })
    void move_ShouldUpdateDistanceCorrectly(double initialDistance, double speed, double mockedRandom, double expectedDistance) {
        Horse horse = new Horse("Lightning", speed, initialDistance);
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockedRandom);
            horse.move();
            assertEquals(expectedDistance, horse.getDistance(),0.001);
        }
    }
}
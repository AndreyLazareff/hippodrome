import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    void constructor_ShouldThrowException_WhenHorsesIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void constructor_ShouldThrowExceptionWithMessage_WhenHorsesIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructor_ShouldThrowException_WhenHorsesListIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
    }

    @Test
    void constructor_ShouldThrowExceptionWithMessage_WhenHorsesListIsEmpty() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new Hippodrome(List.of()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses_ShouldReturnSameListInSameOrder() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i + 2, i + 3));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> result = hippodrome.getHorses();

        assertEquals(30, result.size());
        for (int i = 0; i < result.size(); i++) {
            assertSame(horses.get(i), result.get(i));
        }
    }

    @Test
    void move_ShouldCallMoveOnAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner_ShouldReturnHorseWithMaxDistance() {
        Horse horseOne = new Horse("A", 10, 100);
        Horse horseTwo = new Horse("B", 12, 250);
        Horse horseThree = new Horse("C", 8, 150);
        Hippodrome hippodrome = new Hippodrome(List.of(horseOne, horseTwo, horseThree));
        Horse winner = hippodrome.getWinner();

        assertSame(horseTwo, winner);
    }
}

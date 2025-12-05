import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("Test is disabled to avoid long execution during regular test runs")
    void main_ShouldCompleteWithin22Seconds() {

        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}
package ch.hslu.e03;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AckermannTest {
    @Mock
    Ackermann mockAck = mock(Ackermann.class, withSettings().defaultAnswer(CALLS_REAL_METHODS));

    @Test
    void testAckermann() {
        Ackermann ackermann = new Ackermann();
        assertEquals(4, ackermann.ack(1, 2));
        assertEquals(7, ackermann.ack(2, 2));
        assertEquals(61, ackermann.ack(3, 3));
        assertEquals(65533, ackermann.ack(4, 1));
    }

    @Test
    void testNumberOfMethodCalls() {
        mockAck.ack(2, 2);
        verify(mockAck, times(27)).ack(anyLong(), anyLong());
    }
}

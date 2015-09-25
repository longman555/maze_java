import org.junit.Test;
import static org.junit.Assert.*;

public class PosTest {
    @Test
    public void testEquals() {
        Pos pos = new Pos(0, 0);
        assertEquals(new Pos(0, 0), pos);

        assertEquals(pos, pos);

        pos = new Pos(10, 10);
        assertNotEquals(new Pos(0, 0), pos);

        assertNotEquals(new Object(), pos);
    }

    @Test
    public void testHashCode() {
        Pos pos = new Pos(0, 0);
        assertEquals(new Pos(0, 0).hashCode(), pos.hashCode());

        pos = new Pos(10, 10);
        assertNotEquals(new Pos(0, 0).hashCode(), pos.hashCode());
    }
}

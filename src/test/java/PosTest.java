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

    @Test
    public void testCompareTo() {
        Pos pos00 = new Pos(0, 0);
        Pos pos00b = new Pos(0, 0);
        Pos pos10 = new Pos(1, 0);
        Pos pos01 = new Pos(0, 1);
        Pos pos12 = new Pos(1, 2);

        assertTrue(pos00.compareTo(pos00b) == 0);
        assertTrue(pos00.compareTo(pos10) < 0);
        assertTrue(pos10.compareTo(pos01) > 0);
        assertTrue(pos10.compareTo(pos12) < 0);
    }

    @Test
    public void testToString() {
        assertEquals("Pos{0,0}", new Pos(0, 0).toString());
    }
}

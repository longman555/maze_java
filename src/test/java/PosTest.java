import org.junit.Test;
import static org.junit.Assert.*;

public class PosTest {
    @Test
    public void testPosEquals() {
        Pos pos = new Pos(0, 0);
        assertEquals(new Pos(0, 0), pos);
    }

    @Test
    public void testHashCode() {
        Pos pos = new Pos(0, 0);
        assertEquals(new Pos(0, 0).hashCode(), pos.hashCode());
    }
}

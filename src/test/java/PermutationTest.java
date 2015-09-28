import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

public class PermutationTest {

    private Integer[] src123;
    private Integer[] src1234;
    private Integer[] src12345;
    private Pos[] src4pos;

    private Permutation<Integer> perm123;
    private Permutation<Integer> perm1234;
    private Permutation<Integer> perm12345;
    private Permutation<Pos> perm4pos;

    @Before
    public void setUp() {
        src123 = new Integer[] { 1, 2, 3, };
        src1234 = new Integer[] { 1, 2, 3, 4, };
        src12345 = new Integer[] { 1, 2, 3, 4, 5, };
        src4pos = new Pos[] {
            //       è„              âE             â∫              ç∂
            new Pos(0, -2), new Pos(2, 0), new Pos(0, 2), new Pos(-2, 0),
        };

        perm123 = new Permutation<>(src123);
        perm1234 = new Permutation<>(src1234);
        perm12345 = new Permutation<>(src12345);
        perm4pos = new Permutation<>(src4pos);
    }



    @After
    public void tearDown() {
        src123 = src1234 = src12345 = src4pos = null;
        perm123 = perm1234 = perm12345 = perm4pos = null;
    }

    @Test
    public void testSize() {
        assertEquals(6, perm123.size());

        assertEquals(24, perm1234.size());

        assertEquals(120, perm12345.size());

        assertEquals(24, perm4pos.size());
    }

    @Test
    public void testGetAllPermutations() {
        ArrayList<Integer[]> res123 = perm123.getAllPermutations();
        assertEquals(6, res123.size());

        ArrayList<Integer[]> res1234 = perm1234.getAllPermutations();
        assertEquals(24, res1234.size());

        ArrayList<Integer[]> res12345 = perm12345.getAllPermutations();
        assertEquals(120, res12345.size());

        ArrayList<Pos[]> res4pos = perm4pos.getAllPermutations();
        assertEquals(24, res4pos.size());
    }

}

/**
 * Permutation1~3クラスのパフォーマンスの測定
 */
import java.util.ArrayList;

public class MeasurePermutation {

    public static void main(String[] args) {

        Integer[] src = new Integer[] { 1,2,3,4,5,6,7,8,9 };
        long start = 0, elapsedNano = 0;

        // ウォームアップ
        Permutation<Integer> perm1b = new Permutation<>(src);
        ArrayList<Integer[]> resultb = perm1b.getAllPermutations();
        Permutation2<Integer> perm2b = new Permutation2<>(src);
        ArrayList<Integer[]> result2b = perm2b.getAllPermutations();
        Permutation3<Integer> perm3b = new Permutation3<>(src);
        ArrayList<Integer[]> result3b = perm3b.getAllPermutations();
        // ここまでウォームアップ

        for (int i = 0; i < 50; ++i) {
            System.out.printf("[ループ%2d回目]\n", i);
            start = System.nanoTime();
            Permutation<Integer> perm1 = new Permutation<>(src);
            ArrayList<Integer[]> result = perm1.getAllPermutations();
            elapsedNano = System.nanoTime() - start;
            System.out.printf("[Permutation1] elapsed -> %12.10f\n",
                               elapsedNano / 1000_000_000.0);

            start = System.nanoTime();
            Permutation2<Integer> perm2 = new Permutation2<>(src);
            ArrayList<Integer[]> result2 = perm2.getAllPermutations();
            elapsedNano = System.nanoTime() - start;
            System.out.printf("[Permutation2] elapsed -> %12.10f\n",
                               elapsedNano / 1000_000_000.0);

            start = System.nanoTime();
            Permutation3<Integer> perm3 = new Permutation3<>(src);
            ArrayList<Integer[]> result3 = perm3.getAllPermutations();
            elapsedNano = System.nanoTime() - start;
            System.out.printf("[Permutation3] elapsed -> %12.10f\n",
                               elapsedNano / 1000_000_000.0);
            System.out.println();
        }
    }
}

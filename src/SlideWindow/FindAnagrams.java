package SlideWindow;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        int s_length = s.length(), p_length = p.length();

        if (s_length < p_length)
            return new ArrayList<>();

        List<Integer> res = new ArrayList<Integer>();

        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < p_length; i++) {
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }
        /// 判断起始位置是否满足条件
        if (Arrays.equals(sCount, pCount)) {
            res.add(0);
        }

        for (int i = 0; i < s_length - p_length; i++) {
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + p_length) - 'a'];
            if (Arrays.equals(sCount, pCount)) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindAnagrams f = new FindAnagrams();
        System.out.println(f.findAnagrams("abbba", "bb"));
    }
}

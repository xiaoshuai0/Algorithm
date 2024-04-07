package BackTrack;

import java.util.LinkedList;
import java.util.List;

public class Combine {
    List<List<Integer>> combine_res = new LinkedList<>();
    LinkedList<Integer> combine_track = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        combine_backTrack(1, n, k);
        return combine_res;
    }

    void combine_backTrack(int start, int n, int k) {
        if (k == combine_track.size()) {
            combine_res.add(new LinkedList<>(combine_track));
            return;
        }
        for (int i = start; i <= n ; i++) {
            combine_track.addLast(i);
            combine_backTrack(i + 1, n, k);
            combine_track.removeLast();
        }
    }
}

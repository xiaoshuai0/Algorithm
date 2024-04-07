package BackTrack;

import java.util.ArrayList;
import java.util.List;

public class SolveNQueens {
    List<List<String>> res = new ArrayList<>();
    int ansCount = 0;
    public List<List<String>> solveNQueens(int n) {
        // '.'表示空，'Q'表示皇后
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append('.');
            }
            board.add(sb.toString());
        }
        backTrack(0, board);
        return res;
    }
    public int totalNQueens(int n) {
        // '.'表示空，'Q'表示皇后
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append('.');
            }
            board.add(sb.toString());
        }
        backTrack(0, board);
        return ansCount;
    }
    void backTrack(int row, List<String> board) {
        // 触发结束条件
        if (row == board.size()) {
//            res.add(new ArrayList<>(board));
            ansCount++;
            return;
        }
        int n  = board.get(row).length();
        for (int col = 0; col < n; col++) {
            // 排除不合法
            if (!isValid(board, row, col)) {
                continue;
            }
            // 做选择
            StringBuilder sb = new StringBuilder(board.get(row));
            sb.setCharAt(col, 'Q');
            board.set(row, sb.toString());
            // 进行下一步决策
            backTrack(row + 1, board);
            // 撤销选择
            sb.setCharAt(col, '.');
            board.set(row, sb.toString());
        }
    }

    boolean isValid(List<String> board, int row, int col) {
        int n = board.size();

        /// 检查列是否有皇后
        for (int i = 0; i < n; i++) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }

        /// 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n ; i--, j++) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        /// 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SolveNQueens s = new SolveNQueens();
        System.out.println(s.totalNQueens(4));
    }
}

import java.util.ArrayList;
import java.util.List;

public class CovertZ {

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        int step = 1, row = 0;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());
        for (char c: s.toCharArray()) {
            rows.get(row).append(c);
            if (row == numRows - 1) {
                step = -1;
            } else if (row == 0){
                step = 1;
            }
            row += step;
        }
        StringBuilder ret = new StringBuilder();
        for (StringBuilder str : rows) ret.append(str);
        return ret.toString();
    }

    public static void main(String args[]) {
        CovertZ c = new CovertZ();
        System.out.println(c.convert("PAYPALISHIRING", 3));
    }
}

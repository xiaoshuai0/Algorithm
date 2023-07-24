package SlideWindow;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int start = 0;
        /// 用于存储字符已经出现的位置
        Map<Character, Integer> map = new HashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            Character temp = s.charAt(i);
            if (map.containsKey(temp)) {
                /// 已经出现过所以找到出现的位置
                /// 取max 是为了防止已经出现过但是没有在start之后出现的这种情况
                start = Math.max(start, map.get(temp) + 1);
            }
            res = Math.max(res, i - start + 1);
            map.put(temp, i);
        }
        return res;
    }
}

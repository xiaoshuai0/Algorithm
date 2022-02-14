import java.util.*;

public class ReverseNumber {

    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int digit = x % 10;
            /// 主要考虑溢出问题
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }
            res = res * 10 + digit;
            x /= 10;
        }
        return res;
    }

    public int myAtoi(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        // 1.去除前导空格
        int index = 0;
        while (index < len && chars[index] == ' ') {
            index++;
        }

        // 2. 极端情况处理
        if (index == len) {
            return 0;
        }

        // 3、如果出现符号字符，仅第 1 个有效，并记录正负
        int sign = 1;
        char firstChar = chars[index];
        if (firstChar == '+') {
            index += 1;
        } else if (firstChar == '-') {
            index += 1;
            sign = -1;
        }
        // 4、将后续出现的数字字符进行转换
        // 不能使用 long 类型，这是题目说的
        int res = 0;
        while (index < len) {
            char currChar = chars[index];
            // 4.1 先判断不合法的情况
            if (currChar > '9' || currChar < '0') {
                break;
            }
            int value = (currChar - '0') * sign;
            // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判：断乘以 10 以后是否越界
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && value > 7)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && value < -8)) {
                return Integer.MIN_VALUE;
            }
            // 4.2 合法的情况下，才考虑转换，每一步都把符号位乘进去
            res = res * 10 + value;
            index++;
        }
        return res;
    }
    /*
    * 方案1：将数字转换成字符串
    * 方案2：数字本身装换，会存在越界情况
    *
    * */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        int revertedNumber = 0;
        // 由于整个过程我们不断将原始数字除以 10，然后给反转后的数字乘上 10，
        // 所以，当原始数字小于或等于反转后的数字时，就意味着我们已经处理了一半位数的数字了。
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    public int maxArea(int[] height) {
        if (height.length < 2) return 0;
        int maxArea = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int minHeight = 0;
            int width = right - left;
            if (height[left] < height[right]) {
                minHeight = height[left];
                left += 1;
            } else  {
                minHeight = height[right];
                right -= 1;
            }
            int area = minHeight * width;
            maxArea = (maxArea < area ? area : maxArea);
        }
        return maxArea;
    }

    public String intToRoman(int num) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        StringBuffer roman = new StringBuffer();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }

    public int romanToInt(String s) {
        Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = symbolValues.get(s.charAt(i));
            if (i < s.length() - 1 && value < symbolValues.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int length = strs.length;
        for (int i = 1; i < length; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }
    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int count = nums.length;
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int first = 0; first < count; first++) {
            // 须要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // 数组最右端
            int thrid = count - 1;
            int target = -nums[first];
            for (int second = first + 1; second < count; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < thrid && nums[second] + nums[thrid] > target) {
                    --thrid;
                }
                if (second == thrid) {
                    break;
                }
                if (nums[second] + nums[thrid] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[thrid]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
    public static void main(String args[]) {
        ReverseNumber r = new ReverseNumber();
//        System.out.println(r.reverse(-123));
//        System.out.println(r.myAtoi("-2147483649"));
//        int[] height = {1,8,6,2,5,4,8,3,7};
//        System.out.println(r.maxArea(height));
        int[] list = {-1,0,1,2,-1,-4};
        r.threeSum(list);
    }
}

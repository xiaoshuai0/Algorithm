package String;

public class StringSolution {



    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n ; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;
        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
            else return -dividend;
        }
        int a = dividend;
        int b = divisor;
        int sign = 1;
        if((a>0&&b<0) || (a<0&&b>0)){
            sign = -1;
        }
        a = a>0?-a:a;
        b = b>0?-b:b;
        int res = div(a,b);
        if(sign>0)return res;
        return (int) -res;
    }
    // 整理了一下思路，可以简单概括为： 60/8 = (60-32)/8 + 4 = (60-32-16)/8 + 2 + 4 = 1 + 2 + 4 = 7
    public int div(int a, int b) {
        if (a > b) return 0;
        int count = 1;
        int tb = b;
        while (tb >= a - tb) {
            count += count;
            tb += tb;
        }
        return count + div(a - tb, b);
    }

    public boolean quickAdd(int y, int z, int x) {
        // x 和 y是负数，z是整数
        // 须要判断z * y >= x 是否成立
        int result = 0, add = y;
        while (z != 0) {
            if ((z & 1) != 0) {
                if (result < x - add) {
                    return false;
                }
                result += add;
            }
            if (z != 1) {
                // 需要保证 add + add >= x
                if (add < x - add) {
                    return false;
                }
                add += add;
            }
            z >>= 1;
        }
        return true;
    }
    public static void main(String[] args) {
        StringSolution s = new StringSolution();
        System.out.println(s.strStr("sadbutsad", "sad"));
        System.out.println(s.strStr("leetcode", "leeto"));
        System.out.println(s.divide(9, 3));
    }
}

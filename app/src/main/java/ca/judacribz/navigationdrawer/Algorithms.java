package ca.judacribz.navigationdrawer;

import android.util.SparseArray;

public class Algorithms {
    private static final String DIV_3_STRING = "fizz";
    private static final String DIV_5_STRING = "buzz";

    public static boolean isPalindrome(String str) {
        int strLen;
        if ((strLen = str.length()) <= 1) {
            return true;
        }

        for (int i = 0; i < str.length()/2; i++) {
            if (str.charAt(i) != str.charAt(strLen - i - 1)) {
                return false;
            }
        }

        return true;
    }

    public static int mostOccurrences(int[] nums) {
        int mostOccurred = Integer.MIN_VALUE;
        int mostOccurredNum = nums[0];
        SparseArray<Integer> numMap = new SparseArray<>();

        for (int num : nums) {
            int currCount = numMap.get(num, -1);
            if (currCount == -1) {
                numMap.put(num, 1);
            } else {
                numMap.put(num, currCount+1);

                if (currCount+1 > mostOccurred) {
                    mostOccurred = currCount + 1;
                    mostOccurredNum = num;
                }

            }
        }

        return mostOccurredNum;
    }

    public static String fizzBuzz(int num) {
        StringBuilder sb = new StringBuilder();

        boolean div3 = num % 3 == 0;
        boolean div5 = num % 5 == 0;

        if (div3) sb.append(DIV_3_STRING);
        if (div5) {
            if (sb.length() != 0) {
                sb.append(" ");
            }

            sb.append(DIV_5_STRING);
        }

        if (!div3 && !div5) {
            sb.append(num);
        }

        System.out.println(sb.toString());
        return sb.toString();
    }


    public static boolean isArmstrongNumber(int num) {
        int temp = num;
        int sum = 0;

        while(temp != 0) {
            sum += Math.pow(temp % 10, 3);
            temp /= 10;
        }

        return sum == num;
    }
}

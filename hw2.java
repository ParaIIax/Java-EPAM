package ua.univer;

public class Main {

    public static void main(String[] args) {
        int [] nums = {1, 31, 5, 1, 31};
        //Task 1
        /*for (int i = 0; i < nums.length / 2; i++) {
            if (nums[i] % 2 == 0 && nums[nums.length - 1 - i] % 2 == 0) {
                int x = nums[i];
                nums[i] = nums[nums.length - 1 - i];
                nums[nums.length - 1 - i] = x;
            }
        }
        for (int j = 0; j < nums.length; j++) {
            System.out.println(nums[j]);
        }*/

        //Task 2
        /*int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > result) {
                result = nums[i];
            }
        }
        int x = 0;
        for (int i = nums.length; i > 0; i--) {
            if (nums[i - 1] == result)
                x = i;
        }
        int y = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == result)
                y = i;
        }
        System.out.println(y - x + 1);*/

        //Task 3
        /*int [] [] num = {   {2, 4, 3, 3},
                            {5, 7, 8, 5},
                            {2, 4, 3, 3},
                            {5, 7, 8, 5}};
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num.length; j++) {
                if (i != j) {
                    num[i][j] = 1;
                    for (int k = j; k < i; k++) {
                        num[i][j] = 0;
                    }
                }
            }
        }
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num.length; j++) {
                System.out.print(num[i][j]);
            }
            System.out.println();
        }*/
    }
}

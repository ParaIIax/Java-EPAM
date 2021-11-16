package ua.univer.task3;
import javax.swing.*;

public class Task3 {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5};
        int len = arr.length;
        if(IsSorted(arr, len, SortOrder.ASCENDING)){
            System.out.println("Array is sorted");
        }
        else {
            System.out.println("Array is not sorted");
        }
        Transform(arr, len, SortOrder.ASCENDING);
        System.out.println();
        System.out.println(MultArithmeticElements(5, 3, 4));
        System.out.println(SumGeometricElements(100, 0.5));
    }
    //Task 1
    private static boolean IsSorted(int[] arr, int len, SortOrder order){
        if (len == 1 || len == 0)
            return true;
        if (order == SortOrder.ASCENDING) {
            if (arr[len - 1] < arr[len - 2])
                return false;
            return IsSorted(arr, --len, SortOrder.ASCENDING);
        }
        else if (order == SortOrder.DESCENDING) {
            if(arr[len - 2] < arr[len - 1])
                return false;
            return IsSorted(arr, --len, SortOrder.DESCENDING);
        }
        return true;
    }
    //Task 2
    private static int Transform(int[] arr, int len, SortOrder order){
        if(IsSorted(arr, len, order)){
            if (len != 0) {
                System.out.print(len - 1 + arr[len - 1] + Transform(arr, --len, order) + " ");
            }
        }
        else {
            System.out.println("Array is not sorted");
        }
        return 0;
    }
    //Task 3
    private static int MultArithmeticElements(int a1, int t, int n){
        if (n == 0){
            return 1;
        }
        return a1 * MultArithmeticElements(a1 + t, t, --n);
    }
    //Task 4
    private static double SumGeometricElements(double a1, double t){
        if (0 < t && t < 1) {
            if (a1 != (int) a1) {
                return 0;
            }
        }
        else
            throw new IllegalArgumentException();
        return a1 + SumGeometricElements((double) (a1 * t), t);
    }
}

package main.java.ua.univer.task3;

import javax.swing.*;
import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) {
        int [] arr = {1, 2, 3, 4, 5};
        if(IsSorted(arr, arr.length, SortOrder.DESCENDING)){
            System.out.println("Array is sorted in the given order");
        }
        else {
            System.out.println("Array is not sorted in the given order");
        }
        System.out.println(Arrays.toString(Transform(arr, arr.length, SortOrder.ASCENDING)));
        System.out.println(MultiArithmeticElements(5, 3, 4));
        System.out.println(SumGeometricElements(100, 0.5));
    }
    //Task 1
    public static boolean IsSorted(int[] arr, int len, SortOrder order){
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
    public static int [] Transform(int[] arr, int len, SortOrder order){
        if(IsSorted(arr, len, order)){
            for (; len != 0; --len) {
                arr[len - 1] = len - 1 + arr[len - 1];
            }
        }
        else {
            System.out.println("Array is not sorted in the given order");
        }
        return arr;
    }
    //Task 3
    public static int MultiArithmeticElements(int a1, int t, int n){
        if (n == 0){
            return 1;
        }
        return a1 * MultiArithmeticElements(a1 + t, t, --n);
    }
    //Task 4
    public static double SumGeometricElements(double a1, double t){
        if (0 < t && t < 1) {
            if (a1 != (int) a1) {
                return 0;
            }
        }
        else
            throw new IllegalArgumentException();
        return a1 + SumGeometricElements((a1 * t), t);
    }
}
/*Task 1.  
Create function IsSorted, determining whether a given array of integer 
values of arbitrary length is sorted in a given order (the order is set up by 
enum value SortOrder). Array and sort order are passed by parameters. 
Function does not change the array. 
 
Task 2.  
Create  function  Transform,  replacing  the  value  of  each  element  of  an 
integer array with the sum of this element value and its index, only if the 
given array is sorted in the given order (the order is set up by enum value 
SortOrder). Array and sort order are passed by parameters. To check, if 
the array is sorted, the function IsSorted from the Task 1 is called. 
Example,  
For {5, 17, 24, 88, 33, 2} and “ascending” sort order values in the array do 
not change; 
For  {15,  10,  3}  and  “ascending”  sort  order  values  in  the  array  do  not 
change; 
For {15, 10, 3} and “descending” sort order the values in the array are 
changing to {15, 11, 5} 
 
Task 3.  
Create  function  MultArithmeticElements,  which  determines  the 
multiplication  of  the  first  n  elements  of  arithmetic  progression  of  real 
numbers with a given initial element of progression a1 and progression step 
t. an is calculated by the formula (an+1 = an + t).  
Example,  
For a1 = 5, t = 3,  n = 4 multiplication equals to 5*8*11*14 = 6160 

Task 4.  
Create function SumGeometricElements, determining the sum of the first 
elements of a decreasing geometric progression of real numbers with a 
given initial element of a progression a1 and a given progression step t, 
while the last element must be greater than a given alim. an is calculated 
by the formula (an+1 = an * t), 0<t<1 .  
Example,  
For a progression, where a1 = 100, and t = 0.5, the sum of the first elements, 
grater than alim = 20, equals to 100+50+25 = 175*/

package com.aliya.hashmap.demo.test;

/**
 * @author dali
 * @date 2021/3/30 下午9:20
 **/
public class Test0330 {


    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void quickSort(int[] arr, int low, int high){

        if(low >= high){
            return ;
        }

        int i = low;
        int j = high;
        int base = arr[low];

        while(i < j){
            while(arr[j]>base && i<j){
                j--;
            }

            while(arr[i]<base && i<j){
                i++;
            }
            swap(arr,i,j);
        }
        swap(arr, low, j);

        quickSort(arr, low, j-1);
        quickSort(arr, j+1, high);
    }
}

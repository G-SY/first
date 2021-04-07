package com.aliya.hashmap.demo.test;


/**
 * @author dali
 * @date 2021/3/30 下午4:55
 **/
public class Test13 {

    int[] eva;
    int t= -1;

    /**
     * 出栈
     * @return
     */
    public int pop(){
        if(t == -1){
            return 0;
        }else{
            return eva[t];
        }
    }

    /**
     * 进栈
     * @param va
     */
    public void push(int va){
            t++;
            eva[t] = va;

    }

}

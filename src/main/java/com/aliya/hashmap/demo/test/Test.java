package com.aliya.hashmap.demo.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dali
 * @date 2021/2/23 下午10:48
 **/
public class Test {

    public static void main(String[] args){
        Test test = new Test();
        int[] nu = new int[]{3,3};
        System.out.println(test.twoSum(nu,6));
        String[] testS = new String[]{"wesl","wels","wsldfk"};
        System.out.println(test.getValue(testS));

        String key = "slijgf";
        System.out.println(key.startsWith("l"));
    }

    public int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> maps = new HashMap(16);
        for(int i = 0; i<nums.length; i++){
            if(maps.containsKey(nums[i])){
                if(target == nums[i] * 2){
                    return new int[]{maps.get(nums[i]),i};
                }
            }
            maps.put(nums[i],i);
        }
        Object[] objects = maps.keySet().toArray();
        for(int i = 0; i<objects.length; i++){
            for(int j = objects.length-1; j>i; j--){
                int o1 = (int) objects[i];
                int o2 = (int) objects[j];
                if(target == (o1+o2)){
                    return new int[]{maps.get(o1),maps.get(o2)};
                }
            }
        }
        return null;
    }

    public int[] solution1(int[] nums, int target){
        Map<Integer, Integer> integerIntegerMap = new HashMap<>(12);
        for(int i = 0; i< nums.length; i++){
            if(integerIntegerMap.containsKey(target-nums[i])){
                return new int[]{nums[i],nums[target-nums[i]]};
            }
            integerIntegerMap.put(nums[i],i);
        }
        return new int[0];
    }

    public String getValue(String[] test){
        String perf = test[0];
        for(int i = 1; i<test.length; i++){
            while (!test[i].startsWith(perf)){
                perf = perf.substring(0,perf.length()-1);
            }
            if(perf.isEmpty()){
                return "";
            }
        }
        return perf;
    }
}

package com.aliya.hashmap.demo.test;

/**
 * @author dali
 * @date 2021/2/22 下午9:33
 * 底层数组中存放的元素类
 **/
public class QEntry<K,V> {
    /**
     * 存放key
     */
    K key;
    /**
     * 存放value
     */
    V value;
    /**
     * key对应的hash值
     */
    int hash;
    /**
     * hash冲突时，也就是映射的位置上已经有一个元素了
     * 那么在新家的元素作为链表头，已经存放的放在后面
     * 即保存在next中，一句话：添加新元素时，添加在表头
     */
    QEntry<K,V> next;

    public QEntry(K key, V value, int hash, QEntry next){
        this.key = key;
        this.value = value;
        this.hash = hash;
        this.next = next;
    }


}

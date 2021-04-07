package com.aliya.hashmap.demo.test;

/**
 * @author dali
 * @date 2021/2/22 下午9:55
 **/
public class QHashMap<K,V> {
    /**
     * 默认数组的大小
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    /**
     * 默认的扩容因子，当数据中元素的个数越多时，哈说冲突也容易发生
     * 所以，需要在数组还没有用完的情况下就开始扩容
     * 这个0.75就是元素的个数达到数组大小的75%的时候就开始扩容
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;
    /**
     * 存放元素的数组
     */
    private QEntry[] table;
    /**
     * 数组中元素的个数
     */
    private  int size;

    /**
     * 构造函数
     */
    public QHashMap(){
        //创建一个数组，默认大小为16
        table = new QEntry[DEFAULT_INITIAL_CAPACITY];
        //此时元素个数是0
        size = 0;
    }

    /**
     * 1.参数key, value很容易理解
     * 2.返回V，我们知道，HashMap有一个特点：如果调用多次map.put("name","tom");map.put("name","lilei");
     * 后面的值会将前面的覆盖，如果出现这种情况，返回旧值，在这里返回"tom"
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value){
        //1.为了简单，key不支持null
        if(key == null){
            throw new RuntimeException("key is null");
        }
        //不直接使用key.hashCode(),我们对key.hashCode()再做一次运算作为hash值，这个hash()方法直接从
        //HashMap源码中拷贝，可不用关心hash的算法本身，只需知道hash()输入一个数，返回一个数就行
        int hash = hash(key.hashCode());

        //用key的hash值和数组大小做一次映射，得到应该存放的位置
        int index = indexFor(hash, table.length);

        //看看数组中，有没有已存在的元素的key和参数中的key是相等的
        //相等则把老的值替换成新的，然后返回旧值
        QEntry<K, V> e = table[index];
        while (e != null){
            //先比较hash是否相等，再比较对象是否相等，或者比较equals
            //如果相等了，说明有一样的key，这是要更新旧值为新值，同时返回旧的值
            if(e.hash == hash && (key == e.key || key.equals(e.key))){
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
            e = e.next;
        }
        //如果数组中没有元素的key与传的key相等的话，把当前位置的元素保存下来
        QEntry<K,V> next = table[index];

        //next有可能为null, 也有可能不为null，不管是否为null，next都要作为新元素的下一个节点(next传给了QEntry的构造函数)
        //然后新的元素保存在了index这个位置
        table[index] = new QEntry(key, value, hash, next);

        //如果需要扩容
        if(size++ >= (table.length * DEFAULT_LOAD_FACTOR)){
            resize();
        }
        return null;

    }

    /**
     * 对hashcode进行运算，JDK中HashMap的实现
     * @param h
     * @return
     */
    public static int hash(int h){
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * 根据h求key落在数组的哪个位置，
     * @param h
     * @param length
     * @return
     */
    public static int indexFor(int h, int length){
        //或者 return h & (length-1) 性能更好
        //这里我们用最容易理解的方式对length取余数，范围就是[0， length - 1]
        //正好是table数组的所有的索引的范围
        //防止负数
        h = h > 0 ? h : -h;
        return h % length;
    }

    /**
     * 扩容函数，当元素的个数大于table.length*0.75(增长因子)时，我们就开始扩容
     * 扩容，数组扩容到原来大小的2倍
     */
    private void resize(){
        //新建一个数组，大小为原来数组大小的2倍
        int newCapacity = table.length * 2;
        QEntry[] newTable = new QEntry[newCapacity];

        QEntry[] src = table;

        //遍历旧数组，重新映射到新数组中
        for(int j = 0; j< src.length; j++){
            //获取旧数组元素
            QEntry<K, V> e = src[j];

            //释放旧数组
            src[j] = null;

            //因为e为一个链表，有可能有多个节点，循环遍历进行映射
            while( e != null){
                //把e的下一个节点保存下来
                QEntry<K, V> next = e.next;
                //e 这个当前节点惊醒在新的数组中映射
                int i = indexFor(e.hash, newCapacity);
                //newTable[i]位置上有可能是null，也有可能不为null,不管是否为null，都作为e这个节点的下一个节点
                e.next = newTable[i];
                //把e保存在新数组的i位置
                newTable[i] = e;
                //继续e的下一个节点的同样的处理
                e = next;
            }
        }
    }

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public V get(K key){
        //同样为了简单，key不支持null
        if(key == null){
            throw new RuntimeException("key is null");
        }
        //对key进行求hash值
        int hash  = hash(key.hashCode());

        //用hash值进行映射，得到应该取数组的哪个位置上取数据
        int index = indexFor(hash, table.length);

        //把index 位置的元素保存下来进行遍历，因为e是链表，我们要对链表进行遍历
        //找到和key相等的那个QEntry，并返回value
        QEntry<K, V> e = table[index];
        while (e != null){
            //比较hash值是否相等
            if(hash == e.hash && (key == e.key || key.equals(e.key))){
                return e.value;
            }
            //如果不相等，继续找下一个
            e = e.next;
        }
        return null;
    }
}

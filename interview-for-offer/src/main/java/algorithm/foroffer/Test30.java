package algorithm.foroffer;

import java.util.*;

/**
 * Created by liyazhou on 2017/5/29.
 * 面试题30：最小的 k 个数
 *
 * 题目：
 *      输入 n 个整数，找出其中最小的 k 个数。
 *      例如输入 4、5、1、6、2、7、3、8 这 8 个数字，则最小的 4 个数是 1、2、3、4。
 *
 * 问题：
 *      1. 最大堆
 *
 * 思路：
 *      1. 使用最大堆，优先队列 PriorityQueue 和比较器 Comparator
 *      2. 将数组的中前 k 个数字存储到 最大堆中
 *      3. 遍历在数组区间[k,n)的元素，
 *             如果该元素小于最大堆中的第一个元素，则最大堆中的第一个元素出队，将该元素加入最大堆中
 */
public class Test30 {

    /**
     * AC
     * 2017-8-14 10:55:53
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (k <= 0 || k > input.length) return new ArrayList<Integer>();

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, Collections.reverseOrder());
        // PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
        for (int i = 0; i < k; i ++) maxHeap.offer(input[i]);

        for (int j = k; j < input.length; j ++){
            if (input[j] < maxHeap.peek()){
                maxHeap.poll();
                maxHeap.offer(input[j]);
            }
        }

        // ArrayList<Integer> result = new ArrayList<>(k);
        // for (int i = 0; i < k; i ++){
        //    int idx = k - i - 1;  // 逆序保存
        //    result.add(idx, maxHeap.poll());
        // }

        Integer[] result = new Integer[k];
        for (int i = 0; i < k; i ++){
            int idx = k - i - 1;
            result[idx] = maxHeap.poll();
        }
        return new ArrayList<>(Arrays.asList(result));
    }

    public Integer[] minNums(int[] array, int k){
        Queue<Integer> maxHeap = getMaxHeap(k);

        for (int i = 0; i < k; i++) maxHeap.offer(array[i]);

        for (int i = k; i < array.length; i++)
            if (array[i] < maxHeap.peek()){
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }

        return maxHeap.toArray(new Integer[0]);
    }

    private Queue<Integer> getMaxHeap(int k){
        Comparator<Integer> revCmp = new Comparator<Integer>(){
            @Override
            public int compare(Integer first, Integer second){
                return second - first;
            }
        };
        // Comparator<Integer> revCmp = Collections.reverseOrder();
        return new PriorityQueue<>(k, revCmp);
    }


    public static void main(String[] args){
        int[][] arrays = {
                {4, 5, 1, 6, 2, 7, 3, 8},
                {4, 5, 1, 6, 2, 7, 3, 8, -1, 10, -2},
        };

        for (int i = 0; i < arrays.length; i++){
            Integer[] minNums = new Test30().minNums(arrays[i], 5);
            System.out.println(Arrays.toString(minNums));
        }
    }
}

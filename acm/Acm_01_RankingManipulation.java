package acm;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// import java.util.Collections;
import java.io.InputStreamReader;

/**
 * 描述
 * 你需要维护一个初始为空的整数序列，支持以下 8 种操作：
 * 1. 1.​输入格式为 `1 x`，表示向序列末尾增加一个整数 x(1≦x≦10^9)；
 * 2. 2.​输入格式为 `2`，表示删除序列末尾的元素（保证此时序列非空）；
 * 3. 3.​输入格式为 `3 i`，表示输出序列中下标为 i（起始下标为 0）的元素；
 * 4. 4.​输入格式为 `4 i x`，表示在下标为 i 的元素与下标为 i+1 的元素之间插入整数 x（起始下标为 0，0≦i<∣序列∣,1≦x≦10^9）；
 * 5. 5.​输入格式为 `5`，表示将序列按照从小到大升序排序；
 * 6. 6.​输入格式为 `6`，表示将序列按照从大到小降序排序；
 * 7. 7.​输入格式为 `7`，表示输出当前序列的长度；
 * 8. 8.​输入格式为 `8`，表示输出当前整个序列。
 * 输入描述：
 * 第一行输入一个整数 q (1≦q≦7×10^3)，表示操作总次数。
 * 接下来 q 行，每行输入一种操作，格式如题目描述所示。
 * 输出描述：
 * 对于每次操作类型 3，在一行输出对应的元素；
 * 对于每次操作类型 7，在一行输出当前序列的长度；
 * 对于每次操作类型 8，在一行输出由当前序列所有元素组成的序列，元素之间用空格分隔。
 */

public class Acm_01_RankingManipulation {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        StringTokenizer st;

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            switch (op) {
                case 1:
                    int x = Integer.parseInt(st.nextToken());
                    list.add(x);
                    break;
                case 2:
                    if (!list.isEmpty()) {
                        list.remove(list.size() - 1);
                    }
                    break;
                case 3:
                    int i = Integer.parseInt(st.nextToken());
                    System.out.println(list.get(i));
                    break;
                case 4:
                    int index = Integer.parseInt(st.nextToken());
                    int val = Integer.parseInt(st.nextToken());
                    list.add(index + 1, val);
                    break;
                case 5:
                    list.sort(Integer::compareTo);
                    // Collections.sort(list);
                    break;
                case 6:
                    list.sort((a, b) -> b.compareTo(a));
                    // list.sort(Collections.reverseOrder());
                    break;
                case 7:
                    System.out.println(list.size());
                    break;
                case 8:
                    StringBuilder sb = new StringBuilder();
                    for (int num : list) {
                        sb.append(num).append(" ");
                    }
                    System.out.println(sb.toString().trim());
                    break;
            }
        }
    }
}
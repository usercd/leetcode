package acm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 描述
 * 今年的第几天？
 * 输入年、月、日，计算该天是本年的第几天。 .
 * 
 * 输入描述：
 * 包括三个整数年(1<=Y<=3000)、月(1<=M<=12)、日(1<=D<=31)。
 *
 * 输出描述：
 * 输入可能有多组测试数据，对于每一组测试数据，
 * 输出一个整数，代表Input中的年、月、日对应本年的第几天。
 */

public class Acm_02_CalculateTheDayofTheYear {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String line;
        try {
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                st = new StringTokenizer(line);
                int year = Integer.parseInt(st.nextToken());
                int month = Integer.parseInt(st.nextToken());
                int day = Integer.parseInt(st.nextToken());
                int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
                // 判断是否为闰年
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    daysInMonth[1] = 29;
                }
                int totalDays = 0;
                for (int i = 0; i < month - 1; i++) {
                    totalDays += daysInMonth[i];
                }
                totalDays += day;
                System.out.println(totalDays);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

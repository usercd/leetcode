package basic;

/**
 * @author CD
 * @date 7/1/2026
 */
public class Code_470_ImplementRand10UsingRand7 {

    public int rand10() {
        while (true) {
            // 生成一个七进制两位数，范围0~48
            // 独立事件乘法原理 每个组合都映射到一个唯一的数字（0~48）
            // rand() + rand() 虽然也用了两个独立随机数，但因为求和操作产生了大量碰撞（重复）
            // 所以无法直接得到均匀分布，也就体现不出“乘法原理直接构造均匀随机”的效果。
            int idx = (rand7() - 1) * 7 + rand7() - 1;
            if (idx <= 39) {
                return idx % 10 + 1;
            }
        }
    }

    // 为了编译器不警告
    private int rand7() {
        return 0;
    }
}

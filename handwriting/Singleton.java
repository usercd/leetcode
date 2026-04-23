package handwriting;

public class Singleton {

    // 1. 枚举类实现单例
    // 优点：线程安全，防止反序列化攻击，懒加载（枚举类加载时才初始化）
    // 缺点：无法继承（枚举类不能被继承，但可以实现其他接口）
    public enum SingletonEnum {
        INSTANCE1;

        public void someMethod() {
            // 实例方法
        }
    }

    // 2. 静态内部类实现单例
    // 优点：线程安全，懒加载（只有在调用getInstance方法时才会加载SingletonHolder类并创建实例）
    // 缺点：无法继承（静态内部类不能被继承，但可以实现其他接口）
    private Singleton() {
        // 私有构造函数，防止外部实例化
    }
        
    // 静态内部类
    private static class SingletonHolder {
        private static final Singleton INSTANCE2 = new Singleton();
    }

    public static Singleton getInstance1() {
        return SingletonHolder.INSTANCE2;
    }

    // 3. 双重检查锁实现单例
    // 优点：只有第一次初始化时加锁，后续访问几乎无锁，性能优秀
    // 缺点：实现较复杂，可能存在指令重排序问题（需要使用volatile关键字来防止指令重排序）
    // volatile 防止指令重排序
    private static volatile Singleton instance;
    // 私有构造函数

    public static Singleton getInstance2() {
        if (instance == null) {                // 第一次检查
            synchronized (Singleton.class) {   // 加锁
                // 再次判断, 因为可能出现某个线程拿了锁之后, 还没来得及执行初始化就释放了锁
                // 而此时其他的线程拿到了锁又执行到此处 ==> 这些线程都会创建一个实例, 从而创建多个实例对象
                if (instance == null) {        // 第二次检查
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // 4. 饿汉式实现单例
    // 私有构造函数
    private static final Singleton INSTANCE3 = new Singleton();

    public static Singleton getInstance3() {
        return INSTANCE3;
    }

    // 5. 懒汉式实现单例
    // 私有构造函数
    private static Singleton instance4;

    public static synchronized Singleton getInstance4() {
        if (instance4 == null) {
            instance4 = new Singleton();
        }
        return instance4;
    }

    
}

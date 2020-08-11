---基础技能

# 泛型-1.5-将类型参数化

> 编译期检查类型安全，运行期是不可见的会被擦除为其上级类型
> 省去类型强制转换
> 常用泛型变量--E元素K关键字N数字T类型V值
> 使用--泛型类、泛型接口、泛型方法

# 代码示例

```java
/** 泛型 */
public class GenericTest {

    /** 泛型类--实例化时需指定T的具体类型 */
    // 泛型的类型只能是类类型不能是简单类型
    // 对确切的泛型类型使用instanceof操作(num instanceof Show<Number>)--会编译时报错
    static class Show<T> {
        private T t;

        public Show(T t) {
            this.t = t;
            System.out.println("Show -- show(T t) -- t:" + t);
        }
    }

    /** 泛型接口 */
    interface IShow<T> {
        void show(T t);
    }

    // 实现泛型接口不传泛型实参
    static class IShowAnimal<T> implements IShow<T> {
        @Override
        public void show(T t) {
            System.out.println("IShowAnimal -- IShow(T t) -- t:" + t);
        }
    }

    // 实现泛型接口传泛型实参
    static class IShowPerson implements IShow<String> {
        @Override
        public void show(String s) {
            System.out.println("IShowPerson -- IShow(String s) -- s:" + s);
        }
    }

    /** 泛型方法 */
    // 静态方法需添加额外的泛型声明
    // 静态方法无法访问所属类上声明的泛型
    private static <T> void speak(T... t) {
        for (int i = 0; i < t.length; i++) {
            if (t[i] instanceof Number) {
                System.out.println("speak -- Number:" + t[i]);
            } else {
                System.out.println("speak -- not Number:" + t[i]);
            }
        }
    }

    /** 通配符 */
    private static void wildcardOpen(Show<?> obj) { }

    // 上边界
    private static void wildcardExtends(Show<? extends Number> obj) { }

    // 下边界
    private static void wildcardSuper(Show<? super Integer> obj) { }

    public static void main(String[] args) {
        new Show<Integer>(7);
        new Show<String>("hello world!");

        new IShowAnimal<String>().show("hello animal!");
        new IShowAnimal<Integer>().show(7);
        new IShowPerson().show("hello person!");

        speak(7, "catface");

        wildcardOpen(new Show<>(7));
        wildcardOpen(new Show<>("catface"));

        wildcardExtends(new Show<>(7));
        // wildcardExtends(new Show<>("catface")); // 不是Number及其子类--编译报错

        wildcardSuper(new Show<Number>(7));
        // wildcardSuper(new Show<String>("catface")); // 不是Integer及其父类--编译报错
    }
}
```

# 注解-1.5

> 为java代码提供元数据，不直接影响代码执行，但也有一些类型的注解可用于影响代码执行

# 元注解-注解的注解

1. @Retention-注解的保留期

	- @Retention(RetentionPolicy.SOURCE)--注解仅存于源码中，class字节码中不包含
	- @Retention(RetentionPolicy.CLASS)--默认，class字节码中存在，运行时jvm无法获得
	- @Retention(RetentionPolicy.RUNTIME)--常用，jvm运行时可通过反射获得

2. @Target-注解的作用范围

	- @Target(ElementType.TYPE)--接口、类、枚举、注解
	- @Target(ElementType.FIELD)--属性字段、枚举的常量
	- @Target(ElementType.METHOD)--方法
	- @Target(ElementType.PARAMETER)--方法参数
	- @Target(ElementType.CONSTRUCTOR)--构造函数
	- @Target(ElementType.LOCAL_VARIABLE)--局部变量
	- @Target(ElementType.ANNOTATION_TYPE)--注解（@Retention注解中就使用该属性）
	- @Target(ElementType.PACKAGE)--包
	- @Target(ElementType.TYPE_PARAMETER)--1.8，类型泛型，即泛型方法、泛型类、泛型接口
	- @Target(ElementType.TYPE_USE)--1.8，类型使用.可以用于标注任意类型除了class

3. @Document-将注解中的元素包含到javadoc中

4. @Inherited-若其子类未加注解，则继承其所有注解

```java
// 自定义注解
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface TestAnnotation { }

// 父类注解
@TestAnnotation
public class Father { }

// 子类继承父类的所有注解
public class Son extends Father { }

// 成功获取Son类上的@TestAnnotation注解
Class<Son> sonClass = Son.class;
TestAnnotation annotation = sonClass.getAnnotation(TestAnnotation.class);
System.out.println(annotation.annotationType());
```

5. @Repeatable-1.8-可多次作用于一个对象

```java
// 玩家注解--一个玩家可玩多款游戏
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Person {
    Game[] value();
}

// 游戏注解
@Repeatable(Person.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Game {
    String value() default "";
}

@Game("lol")
@Game("dnf")
@Game("cf")
public class PlayGamesPerson { }
```

## 注解的属性-通过反射获取

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Extra {
    int age() default 0;

    String gender() default "male";
}

@Extra(age = 1, gender = "female")
public class ExtraPerson { }

// 获取注解的属性
Class<ExtraPerson> extraPersonClass = ExtraPerson.class;
Annotation[] annotations = extraPersonClass.getAnnotations();
System.out.println(Arrays.asList(annotations));
// [@base.annotation.AnnotationTest$Extra(gender=female, age=1)]
```

# java预置注解

1. @Deprecated--过时警告
2. @Override--提示复写父类方法
3. @SuppressWarnings--忽略警告
4. @SafeVarargs--1.7-提醒开发者不要用参数做一些不安全的操作(运行时抛ClassCastException )
5. @FunctionalInterface--1.8-函数式接口注解


----------------------------------
网络框架课
 
----------------------------------




# 反射-通过class类的对象获取目标类的属性、方法等信息

```java
public class ReflectionTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        /** 基础 */
        // getClass()
        String hello = "hello world!";
        System.out.println("hello.getClass().getName():" + hello.getClass().getName());

        // Class.forName(classname)
        Class<?> clz = Class.forName("java.lang.String");
        System.out.println("clz.getName():" + clz.getName());

        // Type属性
        Class<Double> type = Double.TYPE;
        System.out.println("Double.TYPE:" + type);

        /** 获取类的成员 */
        class Person {
            private int age;
            private String name;

            protected Person() {
                System.out.println("protected-Person()");
            }

            private Person(String name) {
                this.age = 18;
                this.name = name;
                System.out.println("private-Person(18, " + name + ")");
            }

            public Person(int age, String name) {
                this.age = age;
                this.name = name;
                System.out.println("public-Person(" + age + ", " + name + ")");
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            private void handle(String info) {
                System.out.println("handle -- info:" + info);
            }

            public void show() {
                System.out.println("Person{" + "age=" + age + ", name='" + name + '\'' + '}');
            }
        }

        Person person = new Person();
        Class<? extends Person> personClz = person.getClass();
        // getDeclaredConstructors--获取类的所有构造方法
        Constructor<?>[] declaredConstructors = personClz.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            // getModifiers--获取构造方法的类型
            System.out.print(Modifier.toString(declaredConstructor.getModifiers()) + "--");
            // getParameterTypes--获取构造方法的所有参数
            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.print(parameterType.getName() + "; ");
            }
            System.out.println("");
        }

        // getConstructors--获取所有public类型的构造方法
        Constructor<?>[] constructors = personClz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("getConstructors--constructor:" + constructor);
        }

        // getDeclaredConstructor--获取指定参数类型(int, String)的构造方法
        Class<?>[] clsIntString = {int.class, String.class};
        Constructor<? extends Person> constructorIntString = personClz.getDeclaredConstructor(clsIntString);
        System.out.println("getDeclaredConstructor:" + constructorIntString);

        // 调用构造方法
        Class<?>[] css = {String.class};
        Constructor<? extends Person> constructorString = personClz.getDeclaredConstructor(css);
        // 调用私有方法需设置
        constructorString.setAccessible(true);
        Person personInstance = constructorString.newInstance("catface");

        // 调用类的方法
        Method handle = personClz.getDeclaredMethod("handle", String.class);
        handle.setAccessible(true);
        handle.invoke(personInstance, "things A");

      	// 获取并修改类的私有字段值-修改的name值只对当前实例personInstance有效
        Field fieldName = personClz.getDeclaredField("name");
        fieldName.setAccessible(true);
        fieldName.set(personInstance, "akali");
        Method show = personClz.getDeclaredMethod("show");
        show.setAccessible(true);
        show.invoke(personInstance);
        String name = (String) fieldName.get(personInstance);
        System.out.println("get -- name;" + name);

        /** 记录下api */
        // Constructor[] getConstructors()--获取所有public构造方法
        // Constructor[] getDeclaredConstructors()--获取所有构造方法
        // Constructor getConstructor(Class... parameterTypes)--获取某个public构造方法
        // Constructor getDeclaredConstructor(Class... parameterTypes)--获取某个构造方法
        // Constructor.newInstance(Object... initargs)--调用构造方法

        // Field[] getFields()--获取所有public字段
        // Field[] getDeclaredFields()--获取所有字段
        // Field getField(String fieldName)--获取某个公有字段
        // Field getDeclaredField(String fieldName)--获取某个字段
        // Object get(Object obj)--获取字段值
        // void set(Object obj, Object value)--设置字段值

        // Method[] getMethods()--获取所有public方法
        // Method[] getDeclaredMethods()--获取所有方法
        // Method getMethod(String name,Class<?>... parameterTypes)--获取某个public方法
        // Method getDeclaredMethod(String name, Class<?>... parameterTypes)--获取某个方法
        // Method.invoke(Object obj, Object... args)--调用方法

        // .setAccessible(true)--解除私有限定
    }
}
```


# 并发编程

Q 基础概念

1. 原子性--类似数据库中的事物
2. 可见性--线程A复制主存中的变量x至工作内存修改后还未同步写回主存则其他线程看到的x是旧值
3. 有序性--若语句A和B无依赖则编译器可能重排序先执行语句B

Q volatile和synchronized区别

- volatile保证可见性、有序性 vs synchronized保证原子性、可见性、有序性
- volatile作用于变量 vs synchronized可作用于变量、方法、类
- volatile不会造成线程阻塞 vs synchronized可能会造成线程阻塞
- volatile标记的变量不会被编译器优化 vs synchronized标记的变量可以被编译器优化

Q synchronized说明

- 一把锁同时最多只能由一个线程持有
- 若锁被当前线程持有，其他线程只能阻塞等待该锁被释放
- 若锁被当前线程持有，其他线程可以执行没被synchronized修饰的方法

Q wait、notify、notifyAll，join、sleep、yield

Q 悲观锁、乐观锁

- 并发控制--保证并发情况下数据的准确性，否则会出现脏读、幻读、不可查重复读的问题
- 悲观锁--通过锁机制，持有锁的线程可以对数据进行处理，其他线程阻塞等待锁释放后才能读写该数据
	- 共享锁--多个事物对同一数据共享锁，都能读取但无法修改
	- 排他锁--一个事物获取某个数据的排他锁，该事物可对数据进行读写操作，其他事物无法获取该数据的锁
- 乐观锁(CompareAndSwap)
- ABA问题--已知当前栈内有BA，线程1介入知道A为栈顶且A.next是B想将B置为栈顶，此时线程2介入将A、B均出栈，并添加DCA于栈中，线程1介入，发现栈中A为栈顶，就开始CAS操作，将A出栈，B置为栈顶，结果栈中只有B，CD被丢弃
- 解决ABA问题--在改变记录时添加version(可以是时间戳)控制，避免并发操作出现ABA问题


Q 线程池










# 数据传输与序列化

Q 序列化和反序列化概念

- 序列化--将java对象转换为字节序列
- 反序列化--将字节序列转换为java对象

Q 序列化用途

- 将对象的字节序列永久保存至磁盘文件--S&P
- 网络中传输对象的字节序列--S&P
- 进程间通过Binder访问共享内存中的字节序列实现数据传递--P

Q S vs P

- S是java的api--P是安卓提供的api性能较高
- S通过io读写进行序列化，并用到反射，过程中会产生大量临时对象，从而导致gc频繁--P在内存中完成序列化
- S适合序列化需要永久保存的数据--P适合序列化在Intent或进程间传递的数据


# java虚拟机原理



# 反射与类加载

Q Hook技术

Q Dalvik和ART






------
UI&framework源码
------
# 事件分发

核心方法有三个：onInterceptTouchEvent、dispatchTouchEvent、onTouchEvent
主要存在于三个组件中：Activity、ViewGroup、View
事件主要有ACTION_DOWN、ACTION_MOVE、ACTION_UP、ACTION_CANCLE













---设计思想与代码质量优化

## 六大设计原则

## 23中设计模式

### 创建型
#### 抽象工厂
#### 生成器
#### 工厂方法
#### 原型
#### 单例

### 结构型
#### 适配器
#### 桥接
#### 组装
#### 装饰器
#### 外观
#### 享元
#### 代理

# 简介

代理模式的定义：为其他对象提供一种代理以控制对这个对象的访问。在某些情况下，一个对象不适合或者不能直接引用另一个对象，而代理对象可以在客户端和目标对象之间起到中介的作用。

著名的代理模式例子为引用计数（英语：reference counting）指针对象。

当一个复杂对象的多份副本须存在时，代理模式可以结合享元模式以减少存储器用量。典型作法是创建一个复杂对象及多个代理者，每个代理者会引用到原本的复杂对象。而作用在代理者的运算会转送到原本对象。一旦所有的代理者都不存在时，复杂对象会被移除。 

# 组成

抽象角色：通过接口或抽象类声明真实角色实现的业务方法。

代理角色：实现抽象角色，是真实角色的代理，通过真实角色的业务逻辑方法来实现抽象方法，并可以附加自己的操作。

真实角色：实现抽象角色，定义真实角色所要实现的业务逻辑，供代理角色调用。

# 优点

(1).职责清晰
真实的角色就是实现实际的业务逻辑，不用关心其他非本职责的事务，通过后期的代理完成一件完成事务，附带的结果就是编程简洁清晰。

(2).代理对象可以在客户端和目标对象之间起到中介的作用，这样起到了中介的作用和保护了目标对象的作用。

(3).高扩展性

# 模式结构

一个是真正的你要访问的对象(目标类)，一个是代理对象,真正对象与代理对象实现同一个接口,先访问代理类再访问真正要访问的对象。

代理模式分为静态代理、动态代理。

静态代理是由程序员创建或工具生成代理类的源码，再编译代理类。所谓静态也就是在程序运行前就已经存在代理类的字节码文件，代理类和委托类的关系在运行前就确定了。

动态代理是在实现阶段不用关心代理类，而在运行阶段才指定哪一个对象。

# java代码示例

```java
// 业务接口1-买鞋
public interface IShoes {
    void tryOn();

    void buyShoes();
}

// 实现接口的真实主题类1
public class NikeShop implements IShoes {
    @Override public void tryOn() {
        System.out.println("试试nike air!");
    }

    @Override public void buyShoes() {
        System.out.println("买nike air!");
    }
}

// 业务接口1-买iPhone
public interface IApple {
    void chose();

    void buyIPhone();
}

// 实现接口的真实主题类2
public class AppleStore implements IApple{
    @Override public void chose() {
        System.out.println("挑选iPhone型号!");
    }

    @Override public void buyIPhone() {
        System.out.println("买iPhone 12pro max+!");
    }
}

// 静态代理类
public class ProfessionalAgent implements IShoes, IApple {

    /** 代购nike */
    private IShoes mShoes;	// 持有真实主题类1的引用

    public ProfessionalAgent(IShoes shoes) {
        this.mShoes = shoes;
    }

    @Override public void tryOn() {
        mShoes.tryOn();
        System.out.println("试完挑好，代购我来帮你砍价!");
    }

    @Override public void buyShoes() {
        mShoes.buyShoes();
        System.out.println("买完nike，后期有问题找代购我!");
    }

    /** 代购iPhone */
    private IApple mApple;	// 持有真实主题类2的引用

    public ProfessionalAgent(IApple apple) {
        this.mApple = apple;
    }

    @Override public void chose() {
        System.out.println("代购我来给你推荐性价比高的型号!");
        mApple.chose();
    }

    @Override public void buyIPhone() {
        mApple.buyIPhone();
        System.out.println("买完iPhone，后期有问题找代购我!");
    }
}

// 动态代理
public class DynamicAgent implements InvocationHandler {

    private Object mObject;	// 被代理的类引用

    public void setObject(Object object) {
        this.mObject = object;
    }

    public Object getObject() {
        return this.mObject;
    }
	
    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("pre invoke...");
        Object object = method.invoke(mObject, args); // 调用被代理类的方法
        System.out.println("after invoke...");
        return object;
    }
}

// 测试
public class ProxyTest {
    public static void main(String[] args) {
        staticsTest(); // 代购帮买鞋和苹果并提供附加服务

        dynamicTest(); // 动态代理
    }

    private static void staticsTest() {
        // 想买鞋了
        IShoes nikeShop = new NikeShop();
        ProfessionalAgent nikeAgent = new ProfessionalAgent(nikeShop);
        nikeAgent.tryOn();
        nikeAgent.buyShoes();
        System.out.println();

        // 想买苹果了
        IApple appleStore = new AppleStore();
        ProfessionalAgent appleAgent = new ProfessionalAgent(appleStore);
        appleAgent.chose();
        appleAgent.buyIPhone();
        System.out.println();
    }

    private static void dynamicTest() {
        IShoes shoes = new NikeShop();
        DynamicAgent nikeAgent = new DynamicAgent();
        nikeAgent.setObject(shoes);
		// 动态构造代理鞋店
        IShoes shoesInstance = (IShoes) Proxy.newProxyInstance(shoes.getClass().getClassLoader(), shoes.getClass().getInterfaces(), nikeAgent);
        shoesInstance.tryOn();
        shoesInstance.buyShoes();
        System.out.println();
        IApple apple = new AppleStore();
        DynamicAgent appleAgent = new DynamicAgent();
        appleAgent.setObject(apple);
        IApple appleInstance = (IApple) Proxy.newProxyInstance(apple.getClass().getClassLoader(), apple.getClass().getInterfaces(), appleAgent);
        appleInstance.chose();
        appleInstance.buyIPhone();
    }
}

// 测试运行结果打印
/*
试试nike air!
试完挑好，代购我来帮你砍价!
买nike air!
买完nike，后期有问题找代购我!

代购我来给你推荐性价比高的型号!
挑选iPhone型号!
买iPhone 12pro max+!
买完iPhone，后期有问题找代购我!

pre invoke...
试试nike air!
after invoke...
pre invoke...
买nike air!
after invoke...

pre invoke...
挑选iPhone型号!
after invoke...
pre invoke...
买iPhone 12pro max+!
after invoke...
*/
```

# 动态代理源码*

# android中的运用*

### 行为型
#### 责任链
#### 命令
#### 解释器
#### 迭代器
#### 观察者
#### 策略
#### 中介者
#### 备忘录
#### 模版方法
#### 访问者

## 数据结构

## 算法
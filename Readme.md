# Java 基础学习
大学不是计算机专业出生，没有系统的学过Java，一开始就直接跟着做项目，都是用到什么就学什么。导致现在虽然已经工作3年多，很多框架的东西会使用，
但是原理不是很懂，看源码有一些吃力，所以现在再系统的学习巩固一下。看的是韩顺平老师的，觉得视频虽然有点久远，不过讲得很好，很多原理讲得很清楚。
所以这个工程里面包含的是一些笔记以及相关的代码。

## 基础数据类型、三大特性（封装、继承、多态）  

- 基本数据类型有整数、小数、布尔以及字符：①整数：byte（1字节）、short（2字节）、int（4字节）、long（8字节）；②小数：float、double；
③布尔：true、false；④字符：char（2字节）
- 三种循环语句：for、while、do while  
- 可用作 switch 判断的类型：byte、short、int、char、enum  
- System.gc()并不会让JVM马上回收垃圾，而只是通知到JVM而已，具体什么时候回收需要看具体情况  
- Java在调用方法的时候会单独开一个栈  
- this 属于对象，不属于类  
- 静态方法只能访问类的静态属性，非静态方法能访问类的静态属性和非静态属性  
- 四种访问限制修饰符：public、protected、default、private  
- 重载要满足的条件：①方法名一样；②参数个数、参数顺序、参数类型至少一种不相同，只是返回类型不一样的话不构成重载  
- 重写要满足的条件：①子类的方法返回类型、名称、参数必须和父类一样；②子类不能缩小父类的访问权限  
- 抽象类使用来解决父类方法的不确定性
- 抽象类：①不能被实例化；②不一定包含抽象方法；③抽象方法不能有实体；④如果一个方法声明为抽象的，那么该类必须为抽象类；⑤当继承抽象类的时候需要
实现抽象类的所有抽象方法；⑥一个类只能继承一个类（单继承）
- 接口：①接口中所有方法都不能有实体；②接口中可以有变量，但是必须是以 public static final 的方式声明的；③接口可以继承别的接口；④如果要
实现一个接口，那么需要实现这个接口的所有方法；⑤一个类可以实现多个接口
- 可以通过在编译的时候是否知道确定的返回类型来判定是静态绑定还是动态绑定
- final：①使用 final 定义的属性必须在定义的时候就初始化；②如果在父类中使用 final 定义属性或者方法，那么这个属性或者方法不能被修改或者重写；
③如果一个类使用 final 定义，那么这个类不能被继承

## 数组

- 数组里存放的是同一类型的数据
- 对象数组创建过后要再对每个对象分配空间，不然每个对象都是null
- 数组在定义的时候就必须指定大小
- 数组的排序：分为内部怕排序和外部排序，内部排序有①交换式：冒泡排序、快速排序；②选择式：选择排序、堆排序；③插入式：插入排序、希尔排序、
二叉树排序；外部排序有①合并排序
- 数组的查找：①顺序查找；②二分查找

## 二进制

二进制相关的资料请参考[binary](https://github.com/ahao-henrry/java-learning/blob/master/src/com/ahao/java/binary/Binary.md)

## 集合、泛型、异常

- 集合有 List、Map、Set、Queue 这几个接口
- List 主要有 ArrayList、LinkedList、Vector、Stack 等实现
- Map 主要有 HashMap、HashTable、TreeMap 等实现
- Set 主要有 HashSet、LinkedHashSet、TreeSet 等实现
- Queue 主要有 PriorityQueue 等实现
- HashMap 和 HashTable 的比较主要有两点：①HashMap 线程不安全， HashTable线程安全（采用每个方法上都加上锁来达到线程安全的目的，
所以这很大程度上牺牲了性能，所以现在基本都不使用这个类，而采用JDK新提供的一个 ConcurrentHashMap 类）；②HashMap 可以使用 null 作为键和值，
而 HashTable 不行
- ArrayList 和 Vector 的比较：①ArrayList 线程不安全，Vector 线程安全（和 HashTable 类似也是在每个方法上加锁来达到线程安全，所以也有
很大的性能牺牲）；②数据增长方面，在自动扩容的时候 Vector 是增长为原来的两倍，而 ArrayList 增长为原来的1.5倍，在扩容方面需要进行数组的复制，
这个是很消耗性能的，所以如果可能最好在新建对象的时候就指定好合理的大小
- 泛型的特性：①类型安全；②向后兼容；③层次清晰；④性能较高
- 异常分为：①检查性异常；②运行时异常；③错误

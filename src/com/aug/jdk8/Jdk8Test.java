package com.aug.jdk8;

import com.aug.domain.Student;
import com.cattsoft.utility.HashMap;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://www.cnblogs.com/dinghaoran/p/11690555.html
 * JDK8 Stream 对集合进行流操作，简化了代码
 * 方法：
 *  1、遍历操作(map)：使用map操作可以遍历集合中的每个对象，并对其进行操作，map之后，用.collect(Collectors.toList())会得到操作后的集合
 *  2、过滤操作(filter)：使用filter可以对象Stream中进行过滤，通过测试的元素将会留下来生成一个新的Stream。
 *  3、循环操作(forEach)：如果只是想对流中的每个对象进行一些自定义的操作，可以使用forEach。
 *  4、返回特定的结果集合（limit/skip）：limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素。
 *  5、排序（sort/min/max/distinct）：sort可以对集合中的所有元素进行排序。max，min可以寻找出流中最大或者最小的元素，而distinct可以寻找出不重复的元素。
 *  6、匹配(Match方法)：有的时候，我们只需要判断集合中是否全部满足条件，或者判断集合中是否有满足条件的元素
 *       >allMatch：Stream 中全部元素符合传入的 predicate，返回 true
 *       >anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
 *       >noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
 *  7、collect(Collectors.toList())会得到操作后的集合
 */

public class Jdk8Test {

    private static Map<String,String>map1 = new HashMap();
    private static Map<String,String>map2 = new HashMap();;
    private static List<String>list = new ArrayList<>();
    private static List<Map> listMap = new ArrayList<>();

    static{
        map1.put("k1","v1");
        map1.put("k2","v2");
        map1.put("k3","v3");
        map2.putAll(map1);
        map2.put("k4","v4");

        list.add("l1");
        list.add("l2");
        listMap.add(map1);
        listMap.add(map2);

    }

    public static void main(String[] args) {

        //使用map 遍历数据
        //String::toUpperCase 对象::方法  DiscountActivity::getId
        List<String>upperLists = list.stream().map(String::toUpperCase).collect(Collectors.toList());//使用stream 对 list集合循环取数转大小写
        for(String temp : upperLists){// 普通写法
            System.out.println("普通循环取数：" + temp);
        }
        //使用 forEach 取数
        upperLists.forEach(tempStr ->{
            System.out.println("使用stream取数：" + tempStr);
        });

        List<String>upperLists2 = list.stream().map(str -> str + "_").collect(Collectors.toList());
        //使用 forEach 取数
        upperLists2.forEach(tempStr -> {
            System.out.println("使用stream取数：" + tempStr);
        });

        //使用filter过滤数据
        List<String>afterList = list.stream().filter(temp -> temp.equalsIgnoreCase("l1")).collect(Collectors.toList());
        //使用 forEach 取数
        afterList.forEach(tempStr -> {
            System.out.println("使用filter过滤数据：" + tempStr);
        });

        //使用 forEach 取数
        list.stream().forEach(tempStr -> {
            System.out.println("使用forEach循环取数：" + tempStr);
        });

        //获取前N个数或扔掉N个数
        List<String>skipList = list.stream().skip(1).collect(Collectors.toList());
        skipList.forEach(tempStr -> {
            System.out.println("使用skip扔掉前N个数据" + tempStr);
        });
        List<String>limitList = list.stream().limit(1).collect(Collectors.toList());
        limitList.forEach(tempStr -> {
            System.out.println("使用limitList获取前N个数据" + tempStr);
        });

        List<String> matchList = new ArrayList<>();
        matchList.add("a");
        matchList.add("a");
        matchList.add("c");
        matchList.add("d");
        matchList.add("");
        boolean isExits = matchList.stream().anyMatch(s -> s.equals("c"));//常用
        System.out.println("是否满足某个条件：" + isExits);

        List<String>distinctList = new ArrayList<>();
        distinctList.addAll(matchList);
        distinctList = distinctList.stream().distinct().collect(Collectors.toList());
        distinctList.forEach(tempStr -> System.out.println("集合去重数据：" + tempStr));


        //合并两个list操作
//        List<Map> listMap1 = new ArrayList<>();
//        List<Map> listMap2 = new ArrayList<>();
//        listMap1.add(map1);
//        listMap2.add(map2);
//        List<Map<Object, Object>> listMap3 = compareListHitData(listMap1, listMap2);
//        System.out.println(listMap1.size() + " listMap3=" + listMap3.size());


    }

    @Test
    public void testIsEmpty() {
        String str = "123";
        System.out.println(str == null ? true : "".equals(str) ? true : false);
        str = null;
        System.out.println(str == null ? true : "".equals(str) ? true : false);
    }

    //常用的方法
    /**
     *  通过遍历两个List中按id属性相等的归结到resultList中
     * @param oneList
     * @param twoList
     */
    public static List<Map<Object, Object>> compareListHitData(List<Map<Object, Object>> oneList, List<Map<Object, Object>> twoList) {
        List<Map<Object, Object>> resultList = oneList.stream().map(map -> twoList.stream()
                .filter(m -> Objects.equals(m.get("k1"), map.get("k1")))
                .findFirst().map(m -> {
                    map.putAll(m);
                    return map;
                }).orElse(null))
                .filter(Objects::nonNull).collect(Collectors.toList());
        return resultList;
    }

    @Test
    public void testStreamOf(){


        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2).limit(6);
        stream2.forEach(System.out::println);
        Stream.iterate(0, x -> x+2).limit(8).forEach(System.out :: println);// 元素的生成是重复对给定的种子值(seed)调用用户指定函数来生成的

    }

    /**
     * 注意：
     * generate 和 iterate 生成数据需要限制下条数，否则不断打印
     *
     */
    @Test
    public void testCreateStream(){

        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2).limit(6);
        stream2.forEach(System.out::println);
        Stream.iterate(0, x -> x+2).limit(8).forEach(System.out :: println);// 元素的生成是重复对给定的种子值(seed)调用用户指定函数来生成的

        Integer[] nums = new Integer[10];
        Stream<Integer> stream = Arrays.stream(nums);// 数组创建stream 对象

        Integer[] nums1 = {1,2,3,4,5,6};
        Arrays.stream(nums1).forEach(System.out::print);

        String[] str = {"a","b","c","d"};
        System.out.println(">>>" + Arrays.toString(Arrays.stream(str).toArray(String[]::new)));

        List<String>list = new ArrayList<>();
        Stream<String>streamList = list.stream();// 创建Stream 对象
        Stream<String>Stream4 = list.parallelStream();// 创建ParallelStream 并行流对象

        Stream<String> stream3 = Stream.generate(() -> "love").limit(10);//生成一个无限长度的Stream，其元素的生成是通过给定的Supplier
        String[] strArr3 = stream3.toArray(String[]::new);
        System.out.println(Arrays.toString(strArr3));//[love, love, love, love, love, love, love, love, love, love]
        Stream.generate(Math::random).limit(10).forEach(System.out::println);


        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\test.txt"), "utf8"));
            Stream<String> lineStream = reader.lines();
            //Object[] objects = lineStream.toArray();
            //System.out.println(">>>objects = " + Arrays.toString(Arrays.stream(objects).toArray(String[]::new)) + " >>> " + objects[0].toString());

            lineStream.forEach(System.out::println);// 流使用完就退出了，第二次使用需要重新创建对象
        } catch (Exception e) {
            e.printStackTrace();
        }

        Pattern pattern = Pattern.compile(",");
        Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");
        stringStream.map((str1) -> str1 + "1").forEach(System.out::println);
        //stringStream.forEach(System.out::println);



    }

    @Test
    public void testMethod() {
        /*流的中间操作
         **筛选与切片:
         * filter：过滤流中的某些元素
         * limit(n)：获取n个元素
         * skip(n)：跳过n元素，配合limit(n)可实现分页
         * distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素
         */
        Stream<Integer> stream = Stream.of(2,3,4,6,9,8,8,7);
        Stream<Integer> newStream = stream.filter(num -> num > 5)
                .distinct()
                .skip(2)
                .limit(2);
        newStream.forEach(System.out :: println);//8、7


        /*
        **映射：
        * map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
         */
        System.out.println("map和flagMap:");
        List<String>list = Arrays.asList("a,2,4", "b", "c");
        Stream<String>mapStream = list.stream().map(s -> s.toUpperCase());
        mapStream.forEach(System.out::println);

        Stream<String>flagStream = list.stream().flatMap(s -> {
            String[] split = s.split(",");
            Stream<String>tempStream = Arrays.stream(split);
            return tempStream;
        });
        flagStream.forEach(System.out::println);


        //排序
        Stream<Integer> sortStream = Stream.of(2,3,4,6,9,8,8,7);
        System.out.println("sorted:");
        sortStream.distinct().sorted().forEach(System.out::println);// sorted 自然排序

        //sorted(Comparator com)：定制排序，自定义Comparator排序器
        Student s1 = new Student("aa", 10);
        Student s2 = new Student("bb", 20);
        Student s3 = new Student("aa", 30);
        Student s4 = new Student("dd", 40);
        List<Student> studentList = Arrays.asList(s1, s2, s3, s4);

        //先按名称排序再按年龄排序
        studentList.stream().sorted(
                (stu1, stu2) -> {
                    if(stu1.getName().equals(stu2.getName())){
                        return stu1.getAge() - stu2.getAge();
                    }else{
                        return stu1.getName().compareTo(stu2.getName());
                    }
                }
        ).map(s -> s.getName()).forEach(System.out::println);


        System.out.println(">>>>>>sort排序>>>>>>");
        studentList.sort(Comparator.comparing(student -> student.getAge()));
        studentList.stream().forEach(System.out::println);
        System.out.println("<<<<<<<<<<<<");


        //peek：如同于map，能得到流中的每一个元素。
        // 但map接收的是一个Function表达式，有返回值；
        // 而peek接收的是Consumer表达式，没有返回值。
        System.out.println(">>>>peek>>>>");
        studentList.stream().peek(stu -> stu.setAge(100))
                //.map(s -> s.getAge())
                .forEach(System.out::println);


        /**流的终止操作
         *   allMatch：接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回true，否则返回false
         *   noneMatch：接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回true，否则返回false
         *   anyMatch：接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回true，否则返回false
         *   findFirst：返回流中第一个元素
         *   findAny：返回流中的任意元素
         *   count：返回流中元素的总个数
         *   max：返回流中元素最大值
         *   min：返回流中元素最小值
         */
        System.out.println(">>>>>>StopStream>>>>");
        List<Integer>stopStream = Arrays.asList(1,2,3,4,5,6);
        boolean allMatch = stopStream.stream().allMatch(num -> num > 4);
        boolean noneMatch = stopStream.stream().noneMatch(num -> num > 4);
        boolean anyMatch = stopStream.stream().anyMatch(num -> num>4);

        Integer findFirst = stopStream.stream().findFirst().get();
        Integer findAny = stopStream.stream().findAny().get();

        long count = stopStream.stream().count();
        Integer max = stopStream.stream().max(Integer::compareTo).get();
        Integer min = stopStream.stream().min(Integer::compareTo).get();

        System.out.println("allMatch=" + allMatch + " noneMatch=" + noneMatch
                + " anyMatch=" + anyMatch);
        System.out.println("findFirst=" + findFirst + " findAny=" + findAny);
        System.out.println("count=" + count + " max=" + max + " min=" + min);

    }

    /**
     * 规约操作：相当于递归操作
     * reduce((x1, x2) -> x1 + x2).get()： 第一次执行时，accumulator函数的第一个参数为流中的第一个元素，第二个参数为流中元素的第二个元素；
     *                                     第二次执行时，第一个参数为第一次函数执行的结果，第二个参数为流中的第三个元素；依次类推。
     * reduce(10, (x1, x2) -> x1 + x2)：操作跟以上类似，只是第一个参数换成了10， 第二个参数为第一个元素。
     */
    @Test
    public void testReduce(){

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);

        Integer v = list.stream().reduce((x1, x2) -> x1 + x2).get();
        System.out.println(v);   // 300

        Integer v4 = list.stream().reduce((x1, x2) -> {
            System.out.println("stream accumulator: x1:" + x1 + "  x2:" + x2);
            return x1 + x2;
        }).get();
        System.out.println("v4=" + v4);

        Integer v5 = list.stream().reduce(10, (x1, x2) -> {
            System.out.println("stream accumulator: x3:" + x1 + "  x4:" + x2);
            return x1 + x2;// 不会打印 不会起作用
        });
        System.out.println(v5);


        Integer v1 = list.stream().reduce(10, (x1, x2) -> x1 + x2);
        System.out.println(v1);  //310

        Integer v2 = list.stream().reduce(0,
                (x1, x2) -> {
                    System.out.println("stream accumulator: x6:" + x1 + "  x7:" + x2);
                    return x1 - x2;
                },
                (x1, x2) -> {
                    System.out.println("stream combiner: x8:" + x1 + "  x9:" + x2);
                    return x1 * x2;
                });// 不会打印 不会起作用
        System.out.println(v2); // -300

        Integer v3 = list.parallelStream().reduce(0,
                (x1, x2) -> {
                    System.out.println("parallelStream accumulator: x10:" + x1 + "  x11:" + x2);
                    return x1 - x2;
                },
                (x1, x2) -> {
                    System.out.println("parallelStream combiner: x12:" + x1 + "  x13:" + x2);
                    return x1 * x2;
                });
        System.out.println(v3);//-775946240

    }


    /**
     *
     */
    @Test
    public void testCollect(){

        Student s1 = new Student("aa", 10,1);
        Student s2 = new Student("bb", 20,2);
        Student s3 = new Student("cc", 10,3);
        List<Student> list = Arrays.asList(s1, s2, s3);

        //装成list
        List<Integer> ageList = list.stream().map(Student::getAge).collect(Collectors.toList()); // [10, 20, 10]
        ageList.stream().forEach(age -> {
            System.out.println("ageList=" + age);
        });

        //转成set
        Set<Integer> ageSet = list.stream().map(Student::getAge).collect(Collectors.toSet()); // [20, 10]
        ageSet.stream().forEach(age -> System.out.println("ageSet=" + age));

        //转成map,注:key不能相同，否则报错
        Map<String, Integer> studentMap = list.stream().collect(Collectors.toMap(Student::getName, Student::getAge)); // {cc=10, bb=20, aa=10}


        //字符串分隔符连接
        String joinName = list.stream().map(Student::getName).collect(Collectors.joining(",", "(", ")")); // (aa,bb,cc)
        System.out.println("joinName=" + joinName);

        //聚合操作
        //1.学生总数
        Long count = list.stream().collect(Collectors.counting()); // 3
        System.out.println("count=" + count);

        //2.最大年龄 (最小的minBy同理)
        Integer maxAge = list.stream().map(Student::getAge).collect(Collectors.maxBy(Integer::compare)).get(); // 20
        //3.所有人的年龄
        Integer sumAge = list.stream().collect(Collectors.summingInt(Student::getAge)); // 40
        //4.平均年龄
        Double averageAge = list.stream().collect(Collectors.averagingDouble(Student::getAge)); // 13.333333333333334
        // 带上以上所有方法
        DoubleSummaryStatistics statistics = list.stream().collect(Collectors.summarizingDouble(Student::getAge));
        System.out.println("count:" + statistics.getCount() + ",max:" + statistics.getMax() + ",sum:" + statistics.getSum() + ",average:" + statistics.getAverage());

        //分组
        Map<Integer, List<Student>> ageMap = list.stream().collect(Collectors.groupingBy(Student::getAge));
        //多重分组,先根据类型分再根据年龄分
        Map<Integer, Map<Integer, List<Student>>> typeAgeMap = list.stream().collect(Collectors.groupingBy(Student::getType, Collectors.groupingBy(Student::getAge)));

        //分区
        //分成两部分，一部分大于10岁，一部分小于等于10岁
        Map<Boolean, List<Student>> partMap = list.stream().collect(Collectors.partitioningBy(v -> v.getAge() > 10));

        //规约
        Integer allAge = list.stream().map(Student::getAge).collect(Collectors.reducing(Integer::sum)).get();
        System.out.println("allAge=" + allAge);
    }

    @Test
    public void test(){
        Predicate<String> p1 = (t) -> t.equals("nice");
        Predicate<String> p2 = (t) -> t.endsWith("e");

        boolean result1 = p1.test("nice");
        System.out.println(result1);

        boolean result2 = p1.negate().test("nice");
        System.out.println(result2);

        boolean result3 = p1.and(p2).test("nice");
        System.out.println(result3);

        boolean result4 = p1.or(p2).test("good");
        System.out.println(result4);

        Predicate<String> p = Predicate.isEqual("当这个参数为null，使用==判断，否则使用equal方法判断");
        boolean result5 = p.test("end");
        System.out.println(result5);
    }


    @Test
    public void testSplitAsStream(){

        List<Map<String,Object>>groupList = new ArrayList<>();
        Map<String, Object>map1 = new HashMap();
        map1.put("name", "test1");
        map1.put("xmtzly","1,2,3");
        map1.put("xms", "10,11,12");
        map1.put("xmze", "10,11,12");
        groupList.add(map1);


        Map<String, Object>map2 = new HashMap();
        map2.put("name", "test2");
        map2.put("xmtzly","1,2,3");
        map2.put("xms", "20,21,22");
        map2.put("xmze", "20,21,22");
        groupList.add(map2);

        Map<String, Object>map3 = new HashMap();
        map3.put("name", "test3");
        map3.put("xmtzly","1,2,3");
        map3.put("xms", "30,31,32");
        map3.put("xmze", "30,31,32");
        groupList.add(map3);

        Pattern pattern = Pattern.compile(",");
//        Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");
        groupList.stream().forEach(map -> {
            pattern.splitAsStream(map.get("xmtzly").toString()).forEach(str -> {

            });

        });


        /*
        //土方法
        for(Map<String, Object>map : groupList){
            String[] xmtzly = map.get("xmtzly").toString().split(",");
            String[] xms = map.get("xms").toString().split(",");
            String[] xmze = map.get("xmze").toString().split(",");

            for(int i = 0; i < xmtzly.length; i++ ){
                String tzly = xmtzly[i];
                if("1".equals(tzly)){//政府投资
                    map.put("governmentXms", xms[i]);
                    map.put("governmentXmze", xmze[i]);
                }else if("2".equals(tzly)){//社会投资
                    map.put("societyXms", xms[i]);
                    map.put("societyXmze", xmze[i]);
                }else{//其它
                    map.put("otherXms", xms[i]);
                    map.put("otherXmze", xmze[i]);
                }
            }
        }
        for(Map<String, Object>map : groupList){
            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()){
                Map.Entry<String, Object> next = iterator.next();
                System.out.print(next.getKey() + ":" + next.getValue());
                System.out.println("");
            }
        }
        */
    }

    /**
     * 新学习新开始
     *
     */
    @Test
    public void testCollectors(){
        List<String> list = Arrays.asList("123","456","789","1101","212121121","asdaa","3e3e3e");

        Integer collect = list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), e -> e.size()));

        System.out.println(collect);

    }


    @Test
    public void testCollectorsToList(){

        //转成list
        List<String> resultList1 = myList.stream().map(map -> map.get("sxzqhdm").toString()).collect(Collectors.toList());
        Collections.sort(resultList1, (s1, s2) -> s1.compareTo(s2));//排序
        resultList1.stream().forEach(e -> System.out.println(e.toString()));


        Set<String> resultList2 = myList.stream().map(map -> map.get("sxzqhdm").toString()).collect(Collectors.toSet());//转成set集合，排序去重
        resultList2.stream().forEach(e -> System.out.println(e.toString()));

        //转成set集合后转成list
        List<String> resultList3 = myList.stream().map(map -> map.get("sxzqhdm").toString()).collect(Collectors.collectingAndThen(Collectors.toSet(), ArrayList::new));

        List<String> adCodeList = myList.stream().map(map -> map.get("sxzqhdm").toString())
                .collect(Collectors.collectingAndThen(Collectors.toSet(), ArrayList::new ) );
        adCodeList.stream().forEach(e -> System.out.println(e.toString()));

    }

    private void sortUsingJava8(List<String> names){
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
    }


    /**
     * List<Map> 自然排序或倒序
     */
    @Test
    public void sortUsingJava8(){
        System.out.println(">>>>>>>>>原始排序>>>>");
        myList.stream().forEach(map -> {
            System.out.println(map.get("sxzqhdm"));
        });

        System.out.println(">>>>>>>sort 自然排序>>>>>");
        myList.sort(Comparator.comparing(map -> map.get("sxzqhdm").toString()));
        myList.stream().forEach(map -> {
            System.out.println(map.get("sxzqhdm"));
        });

        System.out.println(">>>>>>>sort 倒序>>>>>");

        myList.sort((o1, o2) -> o2.get("sxzqhdm").toString().compareTo(o1.get("sxzqhdm").toString()));
        myList.stream().forEach(map -> {
            System.out.println(map.get("sxzqhdm"));
        });

        myList.sort(Comparator.comparing(map -> map.get("sxzqhdm").toString(), Comparator.reverseOrder()));
        myList.stream().forEach(map -> {
            System.out.println(map.get("sxzqhdm"));
        });

        List<Map> results = myList.stream().sorted(Comparator.comparing(map -> map.get("sxzqhdm").toString(), Comparator.reverseOrder())).collect(Collectors.toList());
        results.stream().forEach(map -> {
            System.out.println(map.get("sxzqhdm"));
        });

    }


    public static  List<Map<String, Object>>myList = new ArrayList<>();
    static {
        Map<String, Object>myMap1 = new HashMap();
        myMap1.put("sxzqhdm", "110000");
        myMap1.put("cityName", "北京");
        myList.add(myMap1);

        Map<String, Object>myMap2 = new HashMap();
        myMap2.put("sxzqhdm", "130000");
        myMap2.put("cityName", "河北");
        myList.add(myMap2);

        Map<String, Object>myMap3 = new HashMap();
        myMap3.put("sxzqhdm", "120000");
        myMap3.put("cityName", "天津");
        myList.add(myMap3);
    }


}

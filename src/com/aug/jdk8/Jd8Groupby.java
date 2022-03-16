package com.aug.jdk8;

import com.google.common.collect.ArrayListMultimap;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Jd8Groupby {
    public static class Apple {

        /**
         * 主键
         */
        private String id;

        /**
         * 名称
         */
        private String name;

        /**
         * 价格
         */
        private BigDecimal price;

        /**
         * 总数
         */
        private Long count;

        /**
         * 类别
         */
        private String type;

        private Apple() {

        }

        public Apple(String name) {
            this.name = name;
        }

        public Apple(String id, String name, BigDecimal price, Long count) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.count = count;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
    private static List<Apple> appleList = new ArrayList();
    static{
        Apple apple1 = new Apple();
        apple1.setId("1");
        apple1.setName("fendo1");
        apple1.setCount((long) 10);
        apple1.setType("1");
        apple1.setPrice(new BigDecimal(20));
        appleList.add(apple1);

        Apple apple2 = new Apple();
        apple2.setId("2");
        apple2.setName("fendo2");
        apple2.setCount((long) 10);
        apple2.setType("1");
        apple2.setPrice(new BigDecimal(20));
        appleList.add(apple2);

        Apple apple6 = new Apple();
        apple6.setId("6");
        apple6.setName("fendo6");
        apple6.setCount((long) 30);
        apple6.setType("1");
        apple6.setPrice(new BigDecimal(20));
        appleList.add(apple6);

        Apple apple3 = new Apple();
        apple3.setId("3");
        apple3.setName("fendo3");
        apple3.setCount((long) 10);
        apple3.setType("2");
        apple3.setPrice(new BigDecimal(20));
        appleList.add(apple3);

        Apple apple4 = new Apple();
        apple4.setId("4");
        apple4.setName("fendo5");
        apple4.setCount((long) 10);
        apple4.setType("3");
        apple4.setPrice(new BigDecimal(20));
        appleList.add(apple4);

        Apple apple5 = new Apple();
        apple5.setId("5");
        apple5.setName("fendo5");
        apple5.setCount((long) 10);
        apple5.setType("4");
        apple5.setPrice(new BigDecimal(20));
        appleList.add(apple5);

        Apple apple7 = new Apple();
        apple7.setId("5");
        apple7.setName("fendo5");
        apple7.setCount((long) 10);
        apple7.setType("4");
        apple7.setPrice(new BigDecimal(20));
        appleList.add(apple7);

    }

    public static void main(String[] args) {


        ArrayListMultimap<String, Apple> appleMul = ArrayListMultimap.create();
        for(Apple apple : appleList){
            String id = apple.getId();
            appleMul.put(id, apple);
        }
        for(Map.Entry<String, Apple> entry : appleMul.entries()){
            String key = entry.getKey();
            Apple apple = entry.getValue();
            System.out.println(key + ":" + apple.getId());

            List<Apple> apples = appleMul.get(key);
            System.out.println(key + "  apples.size():" + apples.size());
        }


        //分组
        Map<String,List<Apple>> map = appleList.stream().collect(Collectors.groupingBy(Apple::getType));
        for (Map.Entry<String, List<Apple>> entry : map.entrySet()) {
            System.out.println("分组" + entry.getKey() + ":" + entry.getValue().size());
        }

        for (Map.Entry<String, List<Apple>> tempList : map.entrySet()){
            System.out.println("原来数据量排序：" + tempList.getValue().size());
        }

        //对Map<String,List<Apple> List大小进行排序
        HashMap<String, List<Apple>> finalOut = new LinkedHashMap<>();
        map.entrySet()
                .stream()
                .sorted((p1, p2) -> p2.getValue().size() - (p1.getValue().size()))
                .collect(Collectors.toList()).forEach(ele ->
                {
                    if(finalOut.size() < 2){//保留前2个的数据
                        finalOut.put(ele.getKey(), ele.getValue());
                    }else{
                        return;
                    }
                }
        );

        for (Map.Entry<String, List<Apple>> tempList : finalOut.entrySet()){
            System.out.println("处理数据量排序：" + tempList.getValue().size());
        }


        //分组求和
        Map<String, LongSummaryStatistics> collect = appleList.stream().collect(
                Collectors.groupingBy(Apple::getType, Collectors.summarizingLong(Apple::getCount)));

        //Map<String, LongSummaryStatistics> collect1 = appleList.stream().collect(Collectors.groupingBy(t -> t.getType(), Collectors.summarizingLong(Apple::getCount)));
        //Map<String, IntSummaryStatistics>
        //Map<String, DoubleSummaryStatistics>


        for (Map.Entry<String, LongSummaryStatistics> entry : collect.entrySet()) {
            LongSummaryStatistics longSummaryStatistics = entry.getValue();
            System.out.println("----------------key----------------" + entry.getKey());
            System.out.println("求和:" + longSummaryStatistics.getSum());
            System.out.println("求平均" + longSummaryStatistics.getAverage());
            System.out.println("求最大:" + longSummaryStatistics.getMax());
            System.out.println("求最小:" + longSummaryStatistics.getMin());
            System.out.println("求总数:" + longSummaryStatistics.getCount());
        }

    }

    //复杂去重
    @Test
    public void test1(){
        ArrayList<Apple> collect = appleList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(Apple::getName))), ArrayList::new));

        System.out.println(collect.size());
    }

    //将流中的每一个元素 T 映射为一个流，再把每一个流连接成为一个流
    @Test
    public void test2() {
        List<String> list2 = new ArrayList<>();
        list2.add("aaa bbb ccc");
        list2.add("ddd eee fff");
        list2.add("ggg hhh iii");
        list2 = list2.stream().map(s -> s.split(" ")).flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println(list2);
    }

    //排序
    @Test
    public void test3() {
        //asc排序
        appleList.stream().sorted(Comparator.comparingLong(Apple::getCount)).forEach(s -> System.out.print(s.getName() + " "));
        System.out.println("------------------------------------------------------------------");
        //desc排序
        appleList.stream().sorted(Comparator.comparingLong(Apple::getCount).reversed()).forEach(s -> System.out.print(s.getName() + " "));
    }

    //skip跳过前n个  limit截取前n个
    @Test
    public void test4(){
        appleList.stream().limit(1).forEach(s -> System.out.print(s.getName()));
        System.out.println("=====");
        appleList.stream().skip(1).forEach(s -> System.out.print(s.getName() + " "));

        System.out.println("=====");
        //分页操作
        int pageSize = 3;
        int pageNo = 1;
        appleList.stream().skip((pageNo-1)*pageSize).limit(pageSize).forEach(s -> System.out.print(s.getName() + " "));
    }

    @Test
    public void test5(){

        //其中一个满足条件
        boolean isHave = appleList.stream().anyMatch(s -> StringUtils.equals(s.getName(),"fendo3"));
        System.out.println("anyMatch:" + isHave);

        //所有满足条件
        boolean allMatch1 = appleList.stream().allMatch(s -> StringUtils.equals(s.getName(), "fendo3"));
        boolean allMatch2 = appleList.stream().allMatch(s -> StringUtils.isNotEmpty(s.getName()));
        System.out.println("allMatch1:" + allMatch1 + " allMatch2:" + allMatch2);

        //是否满足没有符合的
        boolean noneMatch = appleList.stream().noneMatch(s -> StringUtils.equals(s.getName(),"fendo7"));
        System.out.println(noneMatch);

        //任一对象
        Optional<Apple>findAny = appleList.stream().findAny();
        Apple apple = findAny.get();
        System.out.println("findAny:" + apple.getName());

        //找第一个对象
        Optional<Apple> findFirst = appleList.stream().findFirst();
        Apple apple1 = findFirst.get();
        System.out.println("findFirst:" + apple1.getName());
    }

    //Collectors.groupingBy 分组
    @Test
    public void test6(){
        //分组
        Map<String, List<Apple>> apples = appleList.stream().collect(Collectors.groupingBy(s -> s.getName()));
        System.out.println("groupBy:" + apples.size());

        Map<String, Long> groupByNum = appleList.stream().collect(Collectors.groupingBy(s -> s.getName(), Collectors.counting()));
        System.out.println("groupByNum:" + groupByNum.get("fendo5"));
    }

    //汇总函数
    @Test
    public void test7(){
        long count = appleList.stream().count();
        System.out.println("count:" + count);

        //平均值,  collectingAndThen:先如何，在如何
        String average = appleList.stream().collect(Collectors.collectingAndThen(
                Collectors.averagingLong(a -> a.getCount()), s -> "平均数：" + s));
        System.out.println("average:" + average);

        //求数量
        Long counting = appleList.stream().collect(Collectors.counting());
        System.out.println("counting:" + counting);
    }

    //拼接字符串，还有joining(str, prefix, suffix)，前缀后缀拼接
    @Test
    public void test8(){
        String joining = appleList.stream().map(s -> s.getName()).collect(Collectors.joining(","));
        System.out.println("joining:" + joining);
    }

    // 求年龄的最大值、最小值、平均值、综合以及人数
    @Test
    public void test9(){
        LongSummaryStatistics result1 = appleList.stream().collect(Collectors.summarizingLong(Apple::getCount));
        System.out.println("LongSummaryStatistics:" + result1);

        IntSummaryStatistics result2 = appleList.stream().collect(Collectors.summarizingInt(s -> s.getCount().intValue()));
        System.out.println("IntSummaryStatistics:" + result2);

        DoubleSummaryStatistics result3 = appleList.stream().collect(Collectors.summarizingDouble(s -> s.getCount()));
        System.out.println("DoubleSummaryStatistics:" + result3);

    }

    //JDK8 时间操作
    @Test
    public void test10(){
        Instant instant = Instant.now();//获取当前时间戳
        LocalDate localDate = LocalDate.now();//获取当前日期
        LocalTime localTime = LocalTime.now();//获取当前时刻
        LocalDateTime localDateTime = LocalDateTime.now();//获取当前具体时间
        ZonedDateTime zonedDateTime = ZonedDateTime.now();//获取带有时区的时间

        System.out.println("Instant=" + instant + "====LocalDate=" + localDate);
        System.out.println("LocalTime=" + localTime + "===localDateTime=" + localDateTime);
        System.out.println("ZonedDateTime=" + zonedDateTime);

//        getYear()                         int        获取当前日期的年份
//        getMonth()                        Month      获取当前日期的月份对象
//        getMonthValue()                   int        获取当前日期是第几月
//        getDayOfWeek()                    DayOfWeek  表示该对象表示的日期是星期几
//        getDayOfMonth()                   int        表示该对象表示的日期是这个月第几天
//        getDayOfYear()                    int        表示该对象表示的日期是今年第几天
//        withYear(int year)                LocalDate  修改当前对象的年份
//        withMonth(int month)              LocalDate  修改当前对象的月份
//        withDayOfMonth(intdayOfMonth)     LocalDate  修改当前对象在当月的日期
//        isLeapYear()                      boolean    是否是闰年
//        lengthOfMonth()                   int        这个月有多少天
//        lengthOfYear()                    int        该对象表示的年份有多少天（365或者366）
//        plusYears(longyearsToAdd)         LocalDate  当前对象增加指定的年份数
//        plusMonths(longmonthsToAdd)       LocalDate  当前对象增加指定的月份数
//        plusWeeks(longweeksToAdd)         LocalDate  当前对象增加指定的周数
//        plusDays(longdaysToAdd)           LocalDate  当前对象增加指定的天数
//        minusYears(longyearsToSubtract)   LocalDate  当前对象减去指定的年数
//        minusMonths(longmonthsToSubtract) LocalDate  当前对象减去注定的月数
//        minusWeeks(longweeksToSubtract)   LocalDate  当前对象减去指定的周数
//        minusDays(longdaysToSubtract)     LocalDate  当前对象减去指定的天数
//        compareTo(ChronoLocalDateother)   int        比较当前对象和other对象在时间上的大小，返回值如果
//        为正，则当前对象时间较晚，
//        isBefore(ChronoLocalDateother)    boolean    比较当前对象日期是否在other对象日期之前
//        isAfter(ChronoLocalDateother)     boolean    比较当前对象日期是否在other对象日期之后
//        isEqual(ChronoLocalDateother)     boolean    比较两个日期对象是否相等

    }

    //Optional 用法，对nullpointexception 进行控制，orElseGet后面跟着supplier()函数
    @Test
    public void test11(){
        Apple apple = null;
        String orElseName = Optional.ofNullable(apple).map(s -> s.getName()).orElse("orElse为null默认名称!");
        String orElsegetName = Optional.ofNullable(apple).map(s -> s.getName()).orElseGet(() -> "orElseGet为null默认名称!".toString());
        System.out.println("orElseName=" + orElseName);
        System.out.println("orElsegetName=" + orElsegetName);

        apple = new Apple("测试名称.");
        String orElseName1 = Optional.ofNullable(apple).map(s -> s.getName()).orElse("orElse为null默认名称!");
        String orElsegetName1 = Optional.ofNullable(apple).map(s -> s.getName()).orElseGet(() -> "orElseGet为null默认名称!".toString());
        System.out.println("orElseName1=" + orElseName1);
        System.out.println("orElsegetName1=" + orElsegetName1);

        int i =0;
        i++;
    }

    @Test
    public void testPartitioningBy(){
        Map<Boolean, List<Integer>> map = Stream.of(1, 3, 3, 2).collect(Collectors.partitioningBy(x -> x > 2));
        map.entrySet().stream().forEach(m -> System.out.println("key=" + m.getKey() + ",value=" + m.getValue()));
    }


}

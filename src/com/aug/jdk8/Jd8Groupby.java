package com.aug.jdk8;

import com.google.common.collect.ArrayListMultimap;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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

        public Apple() {

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


    public static void main(String[] args) {

        List<Apple> appleList = new ArrayList();
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
        apple4.setName("fendo4");
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
        apple7.setName("fendo6");
        apple7.setCount((long) 10);
        apple7.setType("4");
        apple7.setPrice(new BigDecimal(20));
        appleList.add(apple7);

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
}

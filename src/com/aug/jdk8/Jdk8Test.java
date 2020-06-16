package com.aug.jdk8;

import com.cattsoft.utility.HashMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    static{
        map1.put("k1","v1");
        map1.put("k2","v2");
        map1.put("k3","v3");
        map2.putAll(map1);
        map2.put("k4","v4");

        list.add("l1");
        list.add("l2");
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
}

package com.aug;

public class EnumTest {

    enum SeasonEnum1{
        SPRING,SUMMER,FALL,WINTER;
    }

    public void judge(SeasonEnum1 s)
    {
        switch(s)
        {
            case SPRING:
                System.out.println("春天适合踏青。");
                break;
            case SUMMER:
                System.out.println("夏天要去游泳啦。");
                break;
            case FALL:
                System.out.println("秋天一定要去旅游哦。");
                break;
            case WINTER:
                System.out.println("冬天要是下雪就好啦。");
                break;
        }
    }

    public enum SeasonEnum2{
        SPRING("春天", 1),SUMMER("夏天", 2),FALL("秋天", 3),WINTER("冬天", 4);
        private final String name;
        private final Integer id;

        private SeasonEnum2(String name, Integer id)
        {
            this.name = name;
            this.id = id;

        }

        public String getName() {
            return name;
        }

        public Integer getID() {
            return id;
        }

        public static void print(SeasonEnum2 seasonEnum2){

            switch (seasonEnum2){
                case SPRING:
                    System.out.println("SPING!");
                    break;
                case SUMMER:
                    System.out.println("SUMMER!");
                    break;
                case FALL:
                    System.out.println("FALL!");
                    break;
                case WINTER:
                    System.out.println("WINTER!");
                    break;
                default:
                    System.out.println("不配!");
            }
        }

    }

    public enum SeassonEnum3{
//        SPRING("春天"),SUMMER("夏天"),FALL("秋天"),WINTER("冬天");

        SPING("春天") {
            @Override
            public String getNextLamp() {
                return "春天适合踏青。";
            }
        };

        private final String name;
        private SeassonEnum3(String name){
            this.name = name;
        }
        private String getName(){
            return name;
        }
        //一个抽象方法
        public abstract String getNextLamp();

    }


    public static void main(String[] args) {
        SeasonEnum1 s = SeasonEnum1.SPRING;
        EnumTest test = new EnumTest();
        test.judge(s);

        System.out.println(SeasonEnum2.SPRING);

        for(SeasonEnum2 seasonEnum2 : SeasonEnum2.values()){//枚举定义方法循环获取其值
            System.out.println(seasonEnum2  + "=====" + seasonEnum2.getName() + "=====" + seasonEnum2.getID());
        }
        SeasonEnum2.print(SeasonEnum2.SPRING);

        SeassonEnum3 seassonEnum3 = SeassonEnum3.SPING;//定义枚举定义抽象方法对其调用
        System.out.println(seassonEnum3.getNextLamp());

    }


}

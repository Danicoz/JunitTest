package com.cattsoft.string;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
public class StringTest {
	public static final String tp = "test";

	
public static void main(String[] args) {
		
//		String a = "ab";
//		String b = "cd";
//		String c = "ab" + "cd";
//		String d = a + "cd";
//		String e = "ab" + b;
//		String f = a + b;
//		String g = "abcd";
//		
//		if(c == g ){
//			System.out.println("cg字符串池共享对象，不加入！");
//		}else{
//			System.out.println("g加入字符串池！");
//		}
//		
//		if(c == d ){
//			System.out.println("cd字符串池共享对象，不加入！");
//		}else{
//			System.out.println("d加入字符串池！");
//		}
//		
//		if(d == e ){
//			System.out.println("de字符串池共享对象，不加入！");
//		}else{
//			System.out.println("e加入字符串池！");
//		}
//		
//		if(c == f ){
//			System.out.println("cf字符串池共享对象，不加入！");
//		}else{
//			System.out.println("f加入字符串池！");
//		}
//		
//		/*****************************************/
//		String ab = static_a + static_b;
//		if(g == ab){
//			System.out.println("g/ab共享同个对象！");
//		}else{
//			System.out.println("ab加入字符串池!");
//		}
//		
//		String cd = static_c + static_d;
//		if(g == cd){
//			System.out.println("g/cd共享同个对象！");
//		}else{
//			System.out.println("cd加入字符串池!");
//		}
		/*****************************************/
	String str[] = {"1","2","3"};
	System.out.println(str[0] + str[2]);
	str[str.length-1] = "5";
	str[str.length-2] = "6";
	System.out.println(str[1] + str[2]);
	}


	@Test
	public void test1(){
		String str = "12,13,14";
		Boolean flag = str.contains("123");
		System.out.println(flag);
	}

	@Test
	public void test() {
		String str = "abcd.efgh.yzkl";
		System.out.println("拼接字符串" + str.concat("aa"));
		System.out.println("是否包含字符串" + str.contains("cd"));
		System.out.println("最后出现的下标" + str.lastIndexOf(".") + str.charAt(str.lastIndexOf(".")));
		System.out.println("首次出现的下标" + str.indexOf("."));
		System.out.println(str.lastIndexOf("ced"));//-1
		
		System.out.println("子字符串" + str.substring(2));
		System.out.println("子字符串" + str.substring(0, 4));//abc---不含下标为4
		
		
		System.out.println("***********************************");
		
		String str3 = "aabbcc";
		String str4 = str3.replace("a", "d");
		String str1 = str3.replace("d", "e");
		System.out.println("str1=" + str1);
		System.out.println("str4=" + str4);
		
		if ("null".equals("11") || "".equals("33")) {
			System.out.println("dd");
		}
		
	}
	
	@Test
	public void arrayTest(){
		Integer[] str1 = null;
		Integer[] str = {1,2,3,4};
		Integer num = str.length;
		
		System.out.print("字符串=" + str.toString());
		
		for(int i=0; i<2;i++){
		if(num<2){
			continue;
		}else{
			System.out.println("str=" + str);
			str1 = str;//引用赋值
		}
		
		}
		for(int i =0; i< str1.length; i++){
			System.out.println(str[i]);
		}
		System.out.println(str1.length + ":" );//4:
		
		System.out.println("441075500000001062016397".substring(23));
	}
	
	@Test
	public void splitTest(){
		List<String[]> list = new ArrayList<String[]>();
		String str1 = ",d,e,d,d,,s,f,3,2,3,";
		String[] splitStr = sPlit(str1, ",");
		
		String[] s = str1.split(",");//最后空数据不打印出来 3,2,3,
		System.out.println(s.length);
		for(int i = 0; i < s.length; i++){
			System.out.println("s=" + s[i]);
		}
		
		for(String str : splitStr){
			System.out.println("打印" + str);
		}
		if(true && splitStr.length == 13){
			System.out.println("增加13个字段的");
		list.add(splitStr);
		}else if(true && splitStr.length == 12){
			System.out.println("增加12个字段的");
			list.add(splitStr);
		}else{
			System.out.println("不增加这行数据");
		}
		
	}
	
	//字符串按照分割符放进数组中
	private static String[] sPlit(String lineStr, String sPlit) {
		List<String> list = new ArrayList<String>();
		String temp = lineStr;
		int index = temp.indexOf(sPlit);
		int le = sPlit.length();
		while (index > -1) {
			String temp2 = temp.substring(0, index);
			list.add(temp2);
			temp = temp.substring(index + le, temp.length());
			index = temp.indexOf(sPlit);
		}
		list.add(temp);
		Pattern pattern = Pattern.compile("[0-9]*");
		List<String>list1 = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			if (pattern.matcher(str).matches()) {
				list1.add(str);
			}
		}

		String[] aa = new String[list1.size()];
		for (int i = 0; i < aa.length; i++) {
			aa[i] = list1.get(i);
		}
		return aa;
	}
	
	@Test
	public void getNum(){
		String str1 = "2,3,34";
		String str2 = "1^6^7^_^7";
		
		//String[] splitStr1 = sPlit1(str1, ",");
		String[] splitStr2 = sPlit1(str2, "^");
		
//		for(String temp : splitStr1){
//			System.out.print("  splitStr1=" + temp);
//		}
		System.out.println();
		for(String temp : splitStr2){
			System.out.print("  splitStr2=" + temp);
		}
	}
	
	//分割符只存数字数组中
	private static String[] sPlit1(String lineStr, String sPlit) {
		   List<String> list = new ArrayList<String>();
	        String temp = lineStr;
	        int index = temp.indexOf(sPlit);
	        int le = sPlit.length();
	        while (index > -1) {
	            String temp2 = temp.substring(0, index);
	            list.add(temp2);
	            temp = temp.substring(index + le, temp.length());
	            index = temp.indexOf(sPlit);
	        }
	        list.add(temp);
	        Pattern pattern = Pattern.compile("[0-9]*");
	       
	        for(int i = 0; i < list.size(); i++){
	        	String str = list.get(i);
	        	if(!pattern.matcher(str).matches()){
	        		list.remove(i);
	        	}
	        }
	        
	        String[] aa = new String[list.size()];
	        for (int i = 0; i < aa.length; i++){
	        	aa[i] = list.get(i);
	        }
	        return aa;
	}
	
	/**
	 * replace : 没有不替代，
	 * replaceAll : 可替代正则表达式
	 */
	@Test
	public void testReplace(){
		String str = new String("abcda\\b\\d\\42tt");
		System.out.println(str.replace("a", "b"));
		System.out.println(str.replace("e","f"));
		System.out.println(str.replaceAll("\\d", "*"));//abcda\b\d\**tt
		System.out.println(str.replaceAll("a", "b"));
		
		System.out.println(">>>" + str.replaceAll("\\\\", "A"));
		String str1 = new String("\\/*?<>:|");
		System.out.println(">>>/////" + str1.replaceAll("/", "A"));      //        **********10
		System.out.println("***>>>>>" + str1.replaceAll("[\\\\/*?<>:|\"]", "*"));//***********
		
		System.out.println("SELECT S_TITLE FROM T_IP_ORDER where S_TITLE like ${condition}".replace("${condition}", "'%设备EMS名称和TELNET IP纠错%'"));
		
		String str2 = "501-天水平安人寿 9-150定西党政专用局1";
		System.out.println("str2=" + str2.replaceAll("\\d|-", ""));
		str2 = StringUtils.substring(str2.replaceAll("\\d|-", ""), 0 ,2);
		System.out.println("str2=" + str2);
		
		
	}
	
	/**
	 * 测试Null 用法
	 */
	@Test
	public void testNull(){
		//System.out.println(null.size());
		//System.out.println(null.equals(""));//这两个说明必须先判断字段是否为null    
		
		String str1 = null;
		System.out.println("1".equals(str1));
		
		int x = 1;
		int y=1;
		
		String str = "1";
	//	System.out.println("null".equals(str));
		System.out.println(str.equals(null));
		
		if(++y==2 & x++==2){
			x =7;
		}
		System.out.println("x="+x+",y="+y);

	}


	@Test
	public void testSet(){
		 Set<Object> obj = new HashSet<Object>();
	        obj.add("a");
	        obj.add("b");
	        obj.add("c");
	        System.out.println("移除前：" + obj.toString());
	        
	        Iterator<Object> it = obj.iterator();
	        for(int i=0; i<obj.size(); i++){
	        	
	            System.out.println(i);
	            
	            Object name = it.next();
	            if("a".equals(name) || "b".equals(name)){
	                it.remove();
	                i--;
	            }
	        }
	        System.out.println("移除后： " + obj.toString());
	    }
	
	@Test
	public void getSubString(){
		String temp = "/Ems=东莞烽火传输东城网管/Ne=1103-企石新局/Shelf=01/Board=33/Port=1";
		int i = temp.lastIndexOf("/");
		String temp1 = temp.substring(i+1,temp.length());
		System.out.println("i=" + i + "\nnewString=" + temp1);
		
		Integer j = 3;
		Integer a = 3;
		int b = 3;
		System.out.println(a == b);
		System.out.println(a == j);
		
		System.out.println("4030005@!@TransMulSegOTN".substring(10));
		
	}
	
	
	@Test
	public void getSplit(){
		String temp = "1^2^3^4";
		String[] temp1 = sPlit(temp, "^");
		int num = temp1.length;
		System.out.println(num);
		for(String a : temp1){
			System.out.println("a=" + a + " ");
		}
		String stimeslot = "AU4/TU12=" + temp1[0] + "/"
				+ ((Integer.parseInt(temp1[1]) - 1) * 21 + (Integer.parseInt(temp1[2]) - 1) * 3 + Integer.parseInt(temp1[3]));
		System.out.println(stimeslot);
		String moiName = "/Ems=华为SDH省集中网管C/Ne=525-佛山勒流/Shelf=1/Board=2/Port=SDH-4";
		String str =moiName.substring(moiName.lastIndexOf("-") + 1, moiName.length());
		System.out.println(str);
		
		
		System.out.println("=23/".replaceFirst("=[0-9]+/", "="+"44"+"/"));
		
	}
	
	@Test
	public void testPattern(){
//		String reg = "内部端口@155M(\\d+)\\^(\\d+)\\^(\\d+)\\^(\\d+)";
//		Pattern pattern = Pattern.compile(reg);
//		String timeSlot = "内部端口@155M04^2^5^1";
//
//		System.out.println(pattern.matcher(timeSlot).matches());
		
		String reg = null;
		Pattern pattern = null;
		String timeSlot =null;
		String[] rate = null;
		
//		reg ="155M_(\\d+)\\^-\\^-\\^-\\^(\\d+)\\^(\\d+)\\^(\\d+)";
//		pattern = Pattern.compile(reg);
//		timeSlot = "155M_02^-^-^-^2^4^2";
//		if(pattern.matcher(timeSlot).matches()){
//			timeSlot = timeSlot.substring(timeSlot.indexOf("_") + 1, timeSlot.length());	
//			System.out.println(timeSlot);
//			String[] rate = sPlit(timeSlot, "^");
//			System.out.println(rate.length);
//			Integer rateFirst = Integer.parseInt(rate[0]);
//			for(String str : rate){
//				System.out.println("转换的值==="+str);
//			}
//			
//			String tempSlot = ((Integer.parseInt(rate[3]) - 1) * 21 + (Integer.parseInt(rate[2]) - 1) * 3 + Integer.parseInt(rate[1])) + "";
//			String	stimeslot = "AU4/TU12=" + rateFirst + "/" + tempSlot;
//			System.out.println(stimeslot);
			
			
			timeSlot = "2^2^2^1";
			reg = "^(\\d+)\\^(\\d+)\\^(\\d+)\\^(\\d+)$";
			pattern = Pattern.compile(reg);
			if (pattern.matcher(timeSlot).matches()) {
				rate = sPlit(timeSlot, "^");
				String logicalNo = ((Integer.parseInt(rate[3]) - 1) * 21
						 + (Integer.parseInt(rate[2]) - 1)* 3 + Integer.parseInt(rate[1])) + "";
				System.out.println("AU4/TU12=" + rate[0] + "/" + logicalNo);
		
			}
		}

	//List 转成  带分割号字符串
	public String listToString(List list, char separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append(separator);
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}
	
	@Test
	public void list2StringTest(){
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("1");
		
		list.add("2");
		list.add("3");
		//System.out.println(listToString(list, '_'));
		
//		String str2[] = new String[list.size()];
//		str2 = list.toArray(str2);
//		System.out.println(str2.toString());
		
		int i = 0;
		StringBuffer sb = new StringBuffer();
		for (String a : list) {
			
			i = i + 1;
			
			if(i <= 10){
				//sb = new StringBuffer();
				sb.append("'" + a + "',");
			}
			else if(i > 10){
				
				String str = "(" + sb.substring(0,sb.length()-1) + ")";
				System.out.println(">=10的情况:" + str);
				i = 0;
				sb = new StringBuffer();
				sb.append("'" + a + "',");
			}
		}
		String str = "(" + sb.toString().substring(0,sb.toString().length()-1) + ")";
		System.out.println("<=10的情况" + str);
	}

	/*************************** 测试创建String 对象 **********************
          输出： cg字符串池共享对象，不加入！
		d加入字符串池！
		e加入字符串池！
		f加入字符串池！
		g/ab共享同个对象！
		cd加入字符串池!
	总： 
		① 对于使用引号包含文本的方式创建String对象 "+" 连接产生的对象不会加入字符串池中。如 "ab" + "cd";
		② 对于包含new方式(变量)创建String对象 "+" 连接产生的对象加入字符串中。如 a + "cd";
		③ 常量使用 "+" 连接不会加入字符串池中，对于未初始化的会加入(常量在编译期已确定值)。如static_a, static_b;
		④ 当执行String a="abc";时，JAVA虚拟机会在栈中创建三个char型的值'a'、'b'和'c'，然后在堆中创建一个String对象，
		      它的值（value）是刚才在栈中创建的三个char型值组成的数组{'a','b','c'}，最后这个新创建的String对象会被添加到字符串池中。
		      如果我们接着执行String b=new String("abc");代码，由于"abc"已经被创建并保存于字符串池中，
		      因此JAVA虚拟机只会在堆中新创建一个String对象，但是它的值（value）是共享前一行代码执行时在栈中创
		      建的三个char型值值'a'、'b'和'c'。
	 */
	
	static final String static_a = "ab";
	static final String static_b = "cd";
	static final String static_c;//值未确定，类似变量
	static final String static_d;
	
	static{
		static_c = "ab";
		static_d = "cd";
	}
	
	
	@Test
	public void testJson(){
		String jsonStr = "[{\"age\":\"25\",\"gender\":\"female\"}]";
		JSONObject jsonobj =  JSON.parseObject(jsonStr.substring(1,jsonStr.length()-1));
		//JSONObject  = JSON.parseObject(jsonStr);
		String id = jsonobj.getString("age");
		String ge = jsonobj.getString("gender");
		System.out.println("age=" + id + " gender="+ ge);
		
	}
	
	@Test
	public void testTransform(){
		
		short s1 = 1;
		s1 = (short) (s1 + 1);
		
		float f = 3.43f;
		
	}

	@Test
	public void testRN(){
		String str = "02#IU1" + "\r\n"
						+ "#2";
		System.out.println(str.replace("\r\n", " "));
	}
	
	@Test
	public void testBuffer(){
		StringBuffer sb = new StringBuffer();
		sb.append("a");
		sb.append("b");
		System.out.println(sb + "c");
		
		System.out.println(sb.toString() + "c");
	}
	
	@Test
	public void testCopy(){
		Object[][] notNullParams = new Object[2][2];
		Object[][] resNullParams = new Object[2][2];
		notNullParams[0][0] = 00;
		notNullParams[0][1] = 01;
		notNullParams[1][0] = 10;
		notNullParams[1][1] = 11;
		
		copyParams(notNullParams, resNullParams);
	}
	
	private static void copyParams(Object[] source, Object[] target) {
		System.out.println(target.length);
		int length = Math.min(source.length, target.length);
		System.arraycopy(source, 0, target, 0, length);
		System.out.println(target.length);
		
	}
	
	@Test
	public void testStr(){
		///////////////////////////////
		String str ="GX-YL,07751,陆川县汇丰局Z01/中兴ZXA10C220-OLT02(FTTH/EPON),10.105.67.23,NA-0-3-2,28,LOID,0775000004006141,0775000004006141,,,2018-06-11 20:31:42,-23.85,4.82,-21.77,2.19,26.59,26.04,上行光衰质量达标/下行光衰质量达标,150002,07757310760,,,N,,EPON,下行光衰达标";
		String myStr = str + "SzX"; //重组每一行数据，为了在切割时后面全部是空的情况
		String[] myFields = myStr.split(",");
		myFields[myFields.length-1] = myFields[myFields.length-1].replace("SzX", "");
		System.out.println(myFields[18]);
		///////////////////////////////////////
		
		System.out.println("==================");
		String str1 = "11-492-南昌迎宾-2-1-M40V-1(OUT)";
		System.out.println(str1.indexOf("(OUT)"));
		System.out.println(str1.indexOf("(OUT1)"));
		
	}
	
	
	@Test
	public void testSubString(){
//		String str = "梧州市微波局宿舍-艾默生电源整流屏";
//		int i = str.indexOf("_");
//		int j = str.lastIndexOf("_");
//		System.out.println(str.substring(i+1,j));
//		String path = "/home/cattsoft/CollectData_BIMS/file/acquisition/dailysession_20180903.gz";
//		int i = path.lastIndexOf("/");
//		int j = path.length();
//		path = path.substring(i+1,j).replace(".gz", "");
//		System.out.println("path=" + path);
//		String str2 = "#" + (0 + 2);
//		System.out.println(str2);
		
		String checkName = "1-01-18^2-20";
		int i = checkName.lastIndexOf("^");
		int j = checkName.length();
		System.out.println(checkName.substring(0,i));//1-01-18
		System.out.println(checkName.substring(i+1));//2-20
		
		String preCheckName = checkName.substring(0,i);
		String sufCheckName = checkName.substring(i+1);
		
		int sufFirst = Integer.parseInt(sufCheckName.substring(0,1));
		int sufSecond = Integer.parseInt(sufCheckName.substring(sufCheckName.lastIndexOf("-")+1));
		int tarName = 0;
		
		System.out.println(sufFirst);
		System.out.println(sufSecond);
		
		switch(sufFirst){
			case 1 : tarName = 9;break;
			case 2 : tarName = sufSecond + 92;break;
			case 3 : tarName = sufSecond + 154;break;
			case 4 : tarName = sufSecond + 238;break;
			case 5 : tarName = sufSecond + 322;break;
			case 6 : tarName = sufSecond + 406;break;
			case 7 : tarName = sufSecond + 490;break;
			case 8 : tarName = sufSecond + 574;break;
			default: tarName = -1;
		}
		
		checkName = preCheckName + "-" + tarName;
		System.out.println(checkName);
				
		
	}
	
	@Test
	public void testString(){
		String str = "xSite=2195,ySite=3177##白银市靖远县北湾环_天字";
		str = str.substring(str.indexOf("##") + 2);
		System.out.println(str);
		
		String str1 = "09";
		str1 = str1.substring(1);
		System.out.println(str1);
		
		System.out.println(StringUtils.contains("嘉峪关", "嘉峪"));
		
	}
	
	//去掉前面的0
	@Test
	public void testDelZero(){
		String str = "0,00,01,10,010,011";
		String str0[] = str.split(",");
				
		for(String s : str0){
			
			if("00".equals(s)){
				s = "0";
			}else if(s.length() > 1 && s.startsWith("0")){
				s = s.replaceAll("^(0+)", "");
			}
			System.out.println(s);
			
		}
	}
	
	@Test
	public void testMatcher(){
		String str = "EnginerMeasurTaskTest_-1.txt";
		System.out.println(str.matches("onu_power_\\d{8}.txt"));
		
		System.out.println(StringTest.tp);

		System.out.println("503405".substring(0,2));
	
	}

	@Test
	public void testIndex(){

		String columnName = "JHFHGS";
		String str1 = columnName.substring(0, 1).toUpperCase();
		String str2 = columnName.substring(1).toLowerCase();
		String str3 = columnName.substring(0, 1).toUpperCase()+ columnName.substring(1).toLowerCase();
		String str4 = str1 + str2;

		System.out.println("111".contains("1"));

		System.out.println( "str1=" + str1 + "str2=" + str2 + "str3=" + str3 + "str4=" +  str4);

		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("5");

		System.out.println(list);

		if("4,5".indexOf("4")!=-1){
			list.add(0,"4");
		}
		if("6".indexOf("6")!=-1){
			list.add(0,"6");
		}
		System.out.println(list);
//		if("110000".endsWith("130000")){
//			System.out.println("500000,110000,120000".indexOf("110000"));
//		}


		System.out.println("1,3".contains("1,2,3"));
	}


	public static Object getGetMethod(Object ob , String name)throws Exception{
		Method[] m = ob.getClass().getMethods();
		for(int i = 0;i < m.length;i++){
			if(("get"+name).toLowerCase().equals( m[i].getName().toLowerCase() )){
				return m[i].invoke(ob);
			}
		}
		return null;
	}
}
	

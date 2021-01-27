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
//			System.out.println("cg�ַ����ع�����󣬲����룡");
//		}else{
//			System.out.println("g�����ַ����أ�");
//		}
//		
//		if(c == d ){
//			System.out.println("cd�ַ����ع�����󣬲����룡");
//		}else{
//			System.out.println("d�����ַ����أ�");
//		}
//		
//		if(d == e ){
//			System.out.println("de�ַ����ع�����󣬲����룡");
//		}else{
//			System.out.println("e�����ַ����أ�");
//		}
//		
//		if(c == f ){
//			System.out.println("cf�ַ����ع�����󣬲����룡");
//		}else{
//			System.out.println("f�����ַ����أ�");
//		}
//		
//		/*****************************************/
//		String ab = static_a + static_b;
//		if(g == ab){
//			System.out.println("g/ab����ͬ������");
//		}else{
//			System.out.println("ab�����ַ�����!");
//		}
//		
//		String cd = static_c + static_d;
//		if(g == cd){
//			System.out.println("g/cd����ͬ������");
//		}else{
//			System.out.println("cd�����ַ�����!");
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
		System.out.println("ƴ���ַ���" + str.concat("aa"));
		System.out.println("�Ƿ�����ַ���" + str.contains("cd"));
		System.out.println("�����ֵ��±�" + str.lastIndexOf(".") + str.charAt(str.lastIndexOf(".")));
		System.out.println("�״γ��ֵ��±�" + str.indexOf("."));
		System.out.println(str.lastIndexOf("ced"));//-1
		
		System.out.println("���ַ���" + str.substring(2));
		System.out.println("���ַ���" + str.substring(0, 4));//abc---�����±�Ϊ4
		
		
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
		
		System.out.print("�ַ���=" + str.toString());
		
		for(int i=0; i<2;i++){
		if(num<2){
			continue;
		}else{
			System.out.println("str=" + str);
			str1 = str;//���ø�ֵ
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
		
		String[] s = str1.split(",");//�������ݲ���ӡ���� 3,2,3,
		System.out.println(s.length);
		for(int i = 0; i < s.length; i++){
			System.out.println("s=" + s[i]);
		}
		
		for(String str : splitStr){
			System.out.println("��ӡ" + str);
		}
		if(true && splitStr.length == 13){
			System.out.println("����13���ֶε�");
		list.add(splitStr);
		}else if(true && splitStr.length == 12){
			System.out.println("����12���ֶε�");
			list.add(splitStr);
		}else{
			System.out.println("��������������");
		}
		
	}
	
	//�ַ������շָ���Ž�������
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
	
	//�ָ��ֻ������������
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
	 * replace : û�в������
	 * replaceAll : �����������ʽ
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
		
		System.out.println("SELECT S_TITLE FROM T_IP_ORDER where S_TITLE like ${condition}".replace("${condition}", "'%�豸EMS���ƺ�TELNET IP����%'"));
		
		String str2 = "501-��ˮƽ������ 9-150��������ר�þ�1";
		System.out.println("str2=" + str2.replaceAll("\\d|-", ""));
		str2 = StringUtils.substring(str2.replaceAll("\\d|-", ""), 0 ,2);
		System.out.println("str2=" + str2);
		
		
	}
	
	/**
	 * ����Null �÷�
	 */
	@Test
	public void testNull(){
		//System.out.println(null.size());
		//System.out.println(null.equals(""));//������˵���������ж��ֶ��Ƿ�Ϊnull    
		
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
	        System.out.println("�Ƴ�ǰ��" + obj.toString());
	        
	        Iterator<Object> it = obj.iterator();
	        for(int i=0; i<obj.size(); i++){
	        	
	            System.out.println(i);
	            
	            Object name = it.next();
	            if("a".equals(name) || "b".equals(name)){
	                it.remove();
	                i--;
	            }
	        }
	        System.out.println("�Ƴ��� " + obj.toString());
	    }
	
	@Test
	public void getSubString(){
		String temp = "/Ems=��ݸ����䶫������/Ne=1103-��ʯ�¾�/Shelf=01/Board=33/Port=1";
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
		String moiName = "/Ems=��ΪSDHʡ��������C/Ne=525-��ɽ����/Shelf=1/Board=2/Port=SDH-4";
		String str =moiName.substring(moiName.lastIndexOf("-") + 1, moiName.length());
		System.out.println(str);
		
		
		System.out.println("=23/".replaceFirst("=[0-9]+/", "="+"44"+"/"));
		
	}
	
	@Test
	public void testPattern(){
//		String reg = "�ڲ��˿�@155M(\\d+)\\^(\\d+)\\^(\\d+)\\^(\\d+)";
//		Pattern pattern = Pattern.compile(reg);
//		String timeSlot = "�ڲ��˿�@155M04^2^5^1";
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
//				System.out.println("ת����ֵ==="+str);
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

	//List ת��  ���ָ���ַ���
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
				System.out.println(">=10�����:" + str);
				i = 0;
				sb = new StringBuffer();
				sb.append("'" + a + "',");
			}
		}
		String str = "(" + sb.toString().substring(0,sb.toString().length()-1) + ")";
		System.out.println("<=10�����" + str);
	}

	/*************************** ���Դ���String ���� **********************
          ����� cg�ַ����ع�����󣬲����룡
		d�����ַ����أ�
		e�����ַ����أ�
		f�����ַ����أ�
		g/ab����ͬ������
		cd�����ַ�����!
	�ܣ� 
		�� ����ʹ�����Ű����ı��ķ�ʽ����String���� "+" ���Ӳ����Ķ��󲻻�����ַ������С��� "ab" + "cd";
		�� ���ڰ���new��ʽ(����)����String���� "+" ���Ӳ����Ķ�������ַ����С��� a + "cd";
		�� ����ʹ�� "+" ���Ӳ�������ַ������У�����δ��ʼ���Ļ����(�����ڱ�������ȷ��ֵ)����static_a, static_b;
		�� ��ִ��String a="abc";ʱ��JAVA���������ջ�д�������char�͵�ֵ'a'��'b'��'c'��Ȼ���ڶ��д���һ��String����
		      ����ֵ��value���Ǹղ���ջ�д���������char��ֵ��ɵ�����{'a','b','c'}���������´�����String����ᱻ��ӵ��ַ������С�
		      ������ǽ���ִ��String b=new String("abc");���룬����"abc"�Ѿ����������������ַ������У�
		      ���JAVA�����ֻ���ڶ����´���һ��String���󣬵�������ֵ��value���ǹ���ǰһ�д���ִ��ʱ��ջ�д�
		      ��������char��ֵֵ'a'��'b'��'c'��
	 */
	
	static final String static_a = "ab";
	static final String static_b = "cd";
	static final String static_c;//ֵδȷ�������Ʊ���
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
		String str ="GX-YL,07751,½���ػ���Z01/����ZXA10C220-OLT02(FTTH/EPON),10.105.67.23,NA-0-3-2,28,LOID,0775000004006141,0775000004006141,,,2018-06-11 20:31:42,-23.85,4.82,-21.77,2.19,26.59,26.04,���й�˥�������/���й�˥�������,150002,07757310760,,,N,,EPON,���й�˥���";
		String myStr = str + "SzX"; //����ÿһ�����ݣ�Ϊ�����и�ʱ����ȫ���ǿյ����
		String[] myFields = myStr.split(",");
		myFields[myFields.length-1] = myFields[myFields.length-1].replace("SzX", "");
		System.out.println(myFields[18]);
		///////////////////////////////////////
		
		System.out.println("==================");
		String str1 = "11-492-�ϲ�ӭ��-2-1-M40V-1(OUT)";
		System.out.println(str1.indexOf("(OUT)"));
		System.out.println(str1.indexOf("(OUT1)"));
		
	}
	
	
	@Test
	public void testSubString(){
//		String str = "������΢��������-��Ĭ����Դ������";
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
		String str = "xSite=2195,ySite=3177##�����о�Զ�ر��廷_����";
		str = str.substring(str.indexOf("##") + 2);
		System.out.println(str);
		
		String str1 = "09";
		str1 = str1.substring(1);
		System.out.println(str1);
		
		System.out.println(StringUtils.contains("������", "����"));
		
	}
	
	//ȥ��ǰ���0
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


		String str22 = "1111#333";
		System.out.println(str22.substring(0, str22.indexOf("#")));
		System.out.println(str22.substring(str22.indexOf("#")+1, str22.length()));

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
	

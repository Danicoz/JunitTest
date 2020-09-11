package com.cattsoft.utils;
import java.util.ArrayList;
import java.util.List;

public class StringHelper {
	public static String getString(String[] arrStr,String splitchar){
		if(arrStr==null){
			return "";
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<arrStr.length;i++){
			sb.append(arrStr[i]);
			if(i<arrStr.length-1){
				sb.append(splitchar);
			}
		}
		return sb.toString();
	}
	public static String getString(List list,String splitchar){
		if(list==null){
			return "";
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<list.size();i++){
			sb.append(list.get(i).toString());
			if(i<(list.size()-1)){
				sb.append(splitchar);
			}
		}
		return sb.toString();
	}
	/** 
	 * 
	 * @param str
	 * @param separator
	 * @param isEndWithSeparator  �н�β�Ƿ��мӷָ���
	 * @return
	 */
	public static List<String> split(String str,String separator) {
		List<String> list=new ArrayList();
		int index = 0;
		int offset = 0;
		int separatorlength=separator.length();
		while ((index = str.indexOf(separator, offset)) != -1) {
			list.add(str.substring(offset, index));
			offset = index + separatorlength;
		}
		String endstr=str.substring(str.length()-separatorlength, str.length());
		if(!endstr.equals(separator)){
			list.add(str.substring(str.lastIndexOf(separator) + separatorlength));
		}else{
			list.add("");
		}
		return list;
	}
//	 private static String getOriginStr(int len){
//	        
//	        StringBuffer sb = new StringBuffer();
//	        StringBuffer result = new StringBuffer();
//	        Random random = new Random();
//	        for(int i = 0; i < len; i++){
//	            sb.append(random.nextInt(9)).append(random.nextInt(9)).append(random.nextInt(9));
//	            result.append(sb.toString());
//	            sb.delete(0, sb.length());
//	            if(i != len-1)
//	                result.append(",");
//	        }
//	        
//	        return result.toString();
//	    }
	public static void main(String args[]){
		String str="BSSAP||||A1CALL||0||460030922673211||13322680848||13692983434||DC8||DC1||8801||28B5||20130911172132||20130911172136||20130911172142||20130911172206||20130911172207||5||0||16||||365213118||13322680848||1332268||133||1||12||-404||0||�㶫||10000||�й�||1060017||��β||16000002||�����ֹ�˾||16000018||����Ӫ���������ģ�������||20000001||����Ӫ���������ģ�������Ӫ������������������Ԫ||-404||177021750||����||-404||365213118||����||-404||-404||-404||����||����||-404||||-404||����||����||����||Apple||IPHONE4||����||3||1||iOS||2013-02-03||0||�㶫||10000||�й�||1060017||��β||660002||�����ֹ�˾||66058||��β��������||-404||����||-404||177021750||����||23||����||6600200||0||66001000562||562||��β���ǾƼ�||2||3613FFFF28B5||115.31640||22.83645||3613||FFFF||6601300056202||562||-404||13692983434||1369298||136||-404||2||�й��ƶ�||22||�ƶ�GSM||-404||0||�㶫||10000||�й�||1060017||��β||-404||����||-404||����||-404||����||-404||177021750||����||-404||-404||����||-404||-404||-404||����||����||-404||||-404||����||-404||����||����||����||����||����||-404||-404||����||||0||0||24||35||1||1||1||1||1||SN8||35||24||1||1||1||1||1||35||24||1||1||1||1||1||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||35||24||1||1||1||1||1||0||0||0||0||0||0||0||0||0||0||0||0||0||0||24||1||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||35||24||1||1||1||1||1||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||1||";
		System.out.println(split(str,"||").size());
		String[] strarr = {"1","2","2","2","2"};
		System.out.println(getString(strarr, "','"));
		
		
//		 String orginStr = getOriginStr(10)+",";
//		 long st1 = System.nanoTime(); 
//		 System.out.println(getString(split(orginStr,","),"&"));
//		 System.out.println("�Զ���split��ȡ�ַ�����ʱ��" + (System.nanoTime()-st1));
//		 
//		 long st2 = System.nanoTime();  
//		 StringTokenizer st=new StringTokenizer(orginStr,",");  
//         List<String> list1 = new ArrayList<String>();
//         for (int i = 0; st.hasMoreTokens(); i++) {
//        	list1.add(st.nextToken());
//         }
//         System.out.println(getString(list1,"&"));
//         System.out.println("StringTokenizer��ȡ�ַ�����ʱ:"+(System.nanoTime()-st2)); 
//         
//         long st3 = System.nanoTime();  
//         System.out.println(getString(orginStr.split(","),"&"));
//         System.out.println("String split��ȡ�ַ�����ʱ:"+(System.nanoTime()-st3)); 
         
//		System.out.println(StringUtil.getString(new String[]{"aaa","bbb","ccc"}, ","));
//		List list=new ArrayList();
//		list.add("aaa");
//		list.add("bbb");
//		list.add("ccc");
//		System.out.println(StringUtil.getString(list, ","));
	}
}

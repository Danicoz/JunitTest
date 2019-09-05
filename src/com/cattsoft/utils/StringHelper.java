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
	 * @param isEndWithSeparator  行结尾是否有加分隔符
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
		String str="BSSAP||130911172207||A1CALL||0||460030922673211||13322680848||13692983434||DC8||DC1||8801||28B5||20130911172132||20130911172136||20130911172142||20130911172206||20130911172207||5||0||16||||365213118||13322680848||1332268||133||1||12||-404||0||广东||10000||中国||1060017||汕尾||16000002||城区分公司||16000018||城区营销服务中心（待建）||20000001||城区营销服务中心（待建）营销服务中心虚拟网格单元||-404||177021750||粤东||-404||365213118||其它||-404||-404||-404||其它||其它||-404||||-404||其它||其它||其它||Apple||IPHONE4||其它||3||1||iOS||2013-02-03||0||广东||10000||中国||1060017||汕尾||660002||城区分公司||66058||汕尾城区本部||-404||其它||-404||177021750||粤东||23||其它||6600200||0||66001000562||562||汕尾富城酒家||2||3613FFFF28B5||115.31640||22.83645||3613||FFFF||6601300056202||562||-404||13692983434||1369298||136||-404||2||中国移动||22||移动GSM||-404||0||广东||10000||中国||1060017||汕尾||-404||其它||-404||其它||-404||其它||-404||177021750||粤东||-404||-404||其它||-404||-404||-404||其它||其它||-404||||-404||其它||-404||其它||其它||其它||其它||其它||-404||-404||其它||||0||0||24||35||1||1||1||1||1||SN8||35||24||1||1||1||1||1||35||24||1||1||1||1||1||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||35||24||1||1||1||1||1||0||0||0||0||0||0||0||0||0||0||0||0||0||0||24||1||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||35||24||1||1||1||1||1||0||0||0||0||0||0||0||0||0||0||0||0||0||0||0||1||";
		System.out.println(split(str,"||").size());
		String[] strarr = {"1","2","2","2","2"};
		System.out.println(getString(strarr, "','"));
		
		
//		 String orginStr = getOriginStr(10)+",";
//		 long st1 = System.nanoTime(); 
//		 System.out.println(getString(split(orginStr,","),"&"));
//		 System.out.println("自定义split截取字符串用时：" + (System.nanoTime()-st1));
//		 
//		 long st2 = System.nanoTime();  
//		 StringTokenizer st=new StringTokenizer(orginStr,",");  
//         List<String> list1 = new ArrayList<String>();
//         for (int i = 0; st.hasMoreTokens(); i++) {
//        	list1.add(st.nextToken());
//         }
//         System.out.println(getString(list1,"&"));
//         System.out.println("StringTokenizer截取字符串用时:"+(System.nanoTime()-st2)); 
//         
//         long st3 = System.nanoTime();  
//         System.out.println(getString(orginStr.split(","),"&"));
//         System.out.println("String split截取字符串用时:"+(System.nanoTime()-st3)); 
         
//		System.out.println(StringUtil.getString(new String[]{"aaa","bbb","ccc"}, ","));
//		List list=new ArrayList();
//		list.add("aaa");
//		list.add("bbb");
//		list.add("ccc");
//		System.out.println(StringUtil.getString(list, ","));
	}
}

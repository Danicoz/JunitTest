package com.cattsoft.json.qinxizhen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @version  1.0
 * @date     2016年5月30日
 * @author   覃熙振
 * @since    1.6.0.35
 */
public class JacksonTest {

	@Test
	public void MapListToJson() {
		LinkedHashMap<String, Object> jsonObject = new LinkedHashMap<String, Object>(); //只有使用LinkedHashMap，输出结果才是顺序的
		jsonObject.put("city", "754,755,756");
		jsonObject.put("deviceIp", "192.168.1.10;192.168.1.101;192.168.1.102");

		LinkedHashMap<String, String> wayMap = new LinkedHashMap<String, String>();
		wayMap.put("1", "大明路");
		wayMap.put("2", "正光路");
		wayMap.put("3", null);
		jsonObject.put("way", wayMap);

//		ArrayList<String> zone = new ArrayList<String>();
//		zone.add("天河区");
//		zone.add("越秀区");
//		zone.add("白云区");
		String[] zone = new String[]{"天河区","白云区","越秀区"};
		jsonObject.put("zone", zone);


		ObjectMapper jsonMapper = new ObjectMapper();
		jsonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);  //格式化JOSN字符串
		jsonMapper.setSerializationInclusion(Include.NON_EMPTY);  //忽略空属性，但是没看到有什么效果
		System.out.println("toString:" + jsonMapper.toString());
		try {
			StringWriter sw = new StringWriter();
			jsonMapper.writeValue(sw, jsonObject);
			System.out.println("StringWriter:" + sw.toString());

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			jsonMapper.writeValue(out, jsonObject);
			System.out.println("OutputStream:" + out.toString());

			ByteArrayOutputStream jg_out = new ByteArrayOutputStream();
			JsonGenerator jg = jsonMapper.getFactory().createGenerator(jg_out);
			jsonMapper.writeValue(jg, jsonObject);
			System.out.println("JsonGenerator:" + jg.toString());  //未能得到JSON字符串

			String str = null;
			jsonMapper.writeValueAsString(str);   //未能得到JSON字符串 
			System.out.println(str);

			//Jackson好像没有直接获得字符串的函数?都要通过数据流
			//jsonMapper.writeValue(new File("E:/个人/学习/JSON/jacksonTest.txt"), jsonObject);;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("##########");
		ObjectNode jsonObjectNode = jsonMapper.createObjectNode();
		System.out.println(jsonObjectNode.asText());
	}

	@Test
	public void jsonToMapList() {
		ObjectMapper jsonMapper = new ObjectMapper();
		try {
			String map = "{\"verified\":true,\"verified\":false,\"name\":{\"last\":\"Hankcs\",\"first\":\"Joe\"},\"userImage\":\"Rm9vYmFyIQ==\",\"gender\":\"\"}";
			LinkedHashMap<String, Object> user = new LinkedHashMap<String,Object>();
			//user = jsonMapper.readValue(map, LinkedHashMap.class);
			user = jsonMapper.readValue(map, new TypeReference<LinkedHashMap<String, Object>>(){});
			System.out.println("gender:" + user.get("AAA"));
			System.out.println(user.toString());

			String list = "[1,2,3,4,5]";
			ArrayList<String> array = jsonMapper.readValue(list, new TypeReference<ArrayList<String>>(){});
			System.out.println(array.toString());

			//value为字符串时一定要用双引号包起来，否则Jackson会当成其它的类型的数据来解析，从而导致报错
			String listMap = "[1,2,3,4,{\"一\":\"壹\",\"二\":\"贰\",\"三\":\"叁\",\"四\":\"肆\"}]";
			ArrayList<Object> arrayObject = jsonMapper.readValue(listMap, new TypeReference<ArrayList<Object>>(){});
			System.out.println(arrayObject.toString());

			JsonNode root = jsonMapper.readTree(map);
//			System.out.println(root.toString());
//			JsonNode child1 = root.get("verified");  //返回最后一个节点
//			System.out.println(child1.toString());
//			JsonNode child2 = root.path("verified");  //结果同get
//			System.out.println(child2.toString());

			List<JsonNode> nodeList = root.findValues("verified"); //返回最后一个节点
			for (JsonNode jn : nodeList) {
				System.out.println(jn.toString());
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void complexObjec() {
		//KEY值相同时，最后一个会覆盖前面所有的
		/*String json = "{"
				+ "\"aaa\":{\"JtSvlanId\":\"pmx0304IPR\",\"JtSvlanNum\":\"1\"},"
				+ "\"aaa\":{\"JtSvlanId\":\"AAAAA\",\"JtSvlanNum\":\"2\"}"
				+ "}";*/
		//当目标类型为String时，如果VALUE值没有被""包起来，转换也会成功。
		String json = "{"
				+ "\"bbb\":{\"JtSvlanId\":\"pmx0304IPR\",\"JtSvlanNum\":1},"
				+ "\"ccc\":{\"JtSvlanId\":\"AAAAA\",\"JtSvlanNum\":2},"
				+ "\"ddd\":123}";
		System.out.println(json);
		ObjectMapper jsonMapper = new ObjectMapper();
		LinkedHashMap<String, LinkedHashMap<String,Integer>> jsonObj = new LinkedHashMap<String,LinkedHashMap<String,Integer>>();
		try {
			jsonObj = jsonMapper.readValue(json, new TypeReference<LinkedHashMap<String, Object>>(){});
			for (Entry<String, LinkedHashMap<String,Integer>> e : jsonObj.entrySet()) {
				System.out.println(e.getKey());
				LinkedHashMap<String,Integer> child = e.getValue();
				System.out.println(child.toString());
				for (Entry<String,Integer> ee : child.entrySet()) {
					System.out.println(ee.getKey()+"##"+ee.getValue());
				}
			}
//			JsonNode jNode = jsonMapper.readTree(json);
//			JsonNode node = jsonMapper.readTree(jNode.get("bbb").toString());
//			System.out.println(jNode.get("ddd"));
//			System.out.println(node);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

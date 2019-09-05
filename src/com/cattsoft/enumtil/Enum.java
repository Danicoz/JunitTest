package com.cattsoft.enumtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Enum {

	private static Logger log = LoggerFactory.getLogger(Enum.class);
	private static Element[] enums = null;

	public static Enum instance = new Enum();

	// 重新加载配置文件
	public static void reLoad() {
		instance = null;
		instance = new Enum();
	}

	private Enum() {
		initEnum();
	}

	@SuppressWarnings("unchecked")
	private void initEnum() {

		String path = System.getProperties().getProperty("user.dir")
				+ File.separator + "conf" + File.separator + "ENUM.xml";
		File file = null;
		Reader reader = null;
		Element rootElement = null;
		List<Element> enumList = null;
		try {
			file = new File(path);
			reader = new InputStreamReader(new FileInputStream(file), "GB2312");
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(reader);
			rootElement = doc.getRootElement();
			enumList = rootElement.getChildren();
			if (enumList != null) {
				enums = new Element[enumList.size()];
				int size = enumList.size();
				for (int i = 0; i < size; i++) {
					enums[i] = (Element) enumList.get(i);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("解析Enum.xml异常", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					log.error("", e);
				}
			}
		}
	}

	/**
	 * 根据网元类型枚举出对应的 子专业 HW-LSTP-->8
	 * 
	 * @param enumName
	 * @param srcValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getEnumValueToStr(String enumName, String srcValue) {
		String value = "1";// 没有对应的枚举值默认为1
		List<Element> valueList = null;
		Element element = null;
		Element valueElement = null;
		boolean find = false;

		for (int i = 0; i < enums.length && !find; i++) {
			element = enums[i];

			if (enumName.equals(element.getAttributeValue("name"))) {
				find = true;
				if (srcValue == null || srcValue.equals("")) {
					value = element.getAttributeValue("default");
					break;
				}

				valueList = element.getChildren();
				for (int idx = 0; idx < valueList.size(); idx++) {
					valueElement = (Element) valueList.get(idx);
					if (srcValue.equals(valueElement.getAttributeValue("name"))) {
						value = valueElement.getValue();
						break;
					}
				}

				break;
			}
		}
		return value;
	}

	/**
	 * 根据地市编码获取地市名称 756-->珠海
	 * 
	 * @param enumName
	 * @param srcValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getEnumValueToCity(String enumName, String srcValue) {
		String value = "-1";
		List<Element> valueList = null;
		Element element = null;
		Element valueElement = null;
		boolean find = false;
		for (int i = 0; i < enums.length && !find; i++) {
			element = enums[i];

			if (enumName.equals(element.getAttributeValue("name"))) {
				find = true;
				if (srcValue == null || srcValue.equals("")) {
					srcValue = element.getAttributeValue("default");
					break;
				}

				valueList = element.getChildren();
				for (int idx = 0; idx < valueList.size(); idx++) {
					valueElement = (Element) valueList.get(idx);
					if (srcValue.equals(valueElement.getAttributeValue("name"))) {
						value = valueElement.getAttributeValue("description");
						break;
					}
				}

				break;
			}
		}
		return value;
	}

	/**
	 * 获取整型的枚举数值
	 * 
	 * @param enumType
	 * @param srcValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int getEnumValueToInt(String enumType, String srcValue) {
		int value = -1;
	
		List<Element> valueList = null;
		Element element = null;
		Element valueElement = null;
		
		for (int i = 0; i < enums.length; i++) {
			element = enums[i];

			if (enumType.equals(element.getAttributeValue("name"))) {
				
				if (srcValue == null || srcValue.equals("")) {
					srcValue = element.getAttributeValue("default");
					break;
				}

				valueList = element.getChildren();
				for (int idx = 0; idx < valueList.size(); idx++) {
					valueElement = (Element) valueList.get(idx);
					if (srcValue.equals(valueElement.getAttributeValue("name"))) {
						value = Integer.parseInt(valueElement.getValue());
						break;
					}
				}
				
				break;
			}
		}

		return value;
	}

	/**
	 * 获取Long型的枚举数值
	 * 
	 * @param enumType
	 * @param srcValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Long getEnumValueToLong(String enumType, String srcValue) {
		Long value = -1l;
		if ("".equals(value)) {
			value = -1l;
		}
		List<Element> valueList = null;
		Element element = null;
		Element valueElement = null;
		@SuppressWarnings("unused")
		boolean find = false;
		for (int i = 0; i < enums.length; i++) {
			element = enums[i];

			if (enumType.equals(element.getAttributeValue("name"))) {
				valueList = element.getChildren();
				if (srcValue == null || srcValue.equals("")) {
					srcValue = element.getAttributeValue("default");
				}

				if (srcValue.equals("")) {
					value = -1l;
					break;
				}

				int size = valueList.size();

				for (int idx = 0; idx < size; idx++) {
					valueElement = (Element) valueList.get(idx);
					if (srcValue.equals(valueElement.getAttributeValue("name"))) {
						value = Long.parseLong(valueElement.getValue());
						break;
					}
				}
				break;
			}
		}

		return value;
	}

	public static void main(String[] args) {
		String str = new Enum().getEnumValueToStr("ConvertData", "BL-LSTP");
		System.out.println(str);
	}

}

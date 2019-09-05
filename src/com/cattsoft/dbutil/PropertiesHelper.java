package com.cattsoft.dbutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesHelper {
	private final static Logger LOG = LoggerFactory.getLogger(PropertiesHelper.class);

	public static Properties getProperties(String config_file_path) throws IOException {
		InputStream in;
		Properties props = new Properties();
		try {
			in = new FileInputStream(config_file_path);
			props.load(in);
		} catch (Exception e) {
			LOG.error("配置文件加载失败：" + e.getMessage(), e);
		}
		return props;
	}
}
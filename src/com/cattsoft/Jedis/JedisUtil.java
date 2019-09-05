package com.cattsoft.Jedis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisUtil {
	
	private static JedisCluster jedisClusterClient;
	private static Map<String, Object> initConfig;
	private static List<HostAndPort> hostAndPort;
	GenericObjectPoolConfig objPoolConfig;
	
	public JedisUtil(Map<String, Object> initConfig, List<HostAndPort> hostAndPort, GenericObjectPoolConfig objPoolConfig) {
		this.initConfig = initConfig;
		this.hostAndPort = hostAndPort;
		this.objPoolConfig = objPoolConfig;
	}

	/**
	 * ��Ⱥ Redis
	 * @return
	 * @throws Exception
	 */
	public JedisCluster getJedisCluster() throws Exception{

		if (jedisClusterClient == null) {
			synchronized (JedisCluster.class) {
				if (jedisClusterClient == null) {
					jedisClusterClient = initJedisCluster(initConfig);
				}
			}
		}
		return jedisClusterClient;
	}
	

	/** ����  redis client�� **/
	private static JedisPool jedisPool;
	public JedisPool getJedisPoolAlone(){
		if (jedisPool == null) {
			synchronized (JedisUtil.class) {
				if (jedisPool == null) {
					jedisPool = initJedisPool(initConfig);
				}
			}
		}
		return jedisPool;
	}
	
	
	/**
	 * ��Ⱥ
	 * @return 
	 */
	private JedisCluster initJedisCluster(Map<? extends Object, ? extends Object> initConfig) {
		if(initConfig == null){
			throw new RuntimeException("���������ش���:redis�����ʼ��ʧ�ܣ��޷���ȡ�������ã�");
		}
		try {
			GenericObjectPoolConfig objPoolConfig = getObjectPoolConfig(initConfig);
			List<HostAndPort> hostAndPort = getHostAndPort(initConfig);
			if(hostAndPort.isEmpty()){
				throw new Exception("�޷���������ڵ����á�");
			}
			
			//���ӳ�ʱʱ��
			int connectTimeout = MapUtils.getIntValue(initConfig, "redis.ConnectTimeout", 1000);
			//socket��ʱʱ��
			int socketTimeout = MapUtils.getIntValue(initConfig, "redis.SocketTimeout", 2000);

			jedisClusterClient = new JedisCluster(new HashSet<HostAndPort>(hostAndPort), connectTimeout, socketTimeout, objPoolConfig);
			return jedisClusterClient;
		} catch (Exception e) {
			throw new RuntimeException("���������ش���:redis�����ʼ��ʧ�ܣ�", e);
		}
	}

	
	/**
	 * ����
	 */
	private JedisPool initJedisPool(Map<? extends Object, ? extends Object> initConfig) {
		if(initConfig == null){
			throw new RuntimeException("���������ش���:redis�����ʼ��ʧ�ܣ��޷���ȡ�������ã�");
		}
		try {
			GenericObjectPoolConfig objPoolConfig = getObjectPoolConfig(initConfig);
			List<HostAndPort> hostAndPort = getHostAndPort(initConfig);
			if(hostAndPort.isEmpty()){
				throw new Exception("�޷���������ڵ����á�");
			}
			//���ӳ�ʱʱ��
			int connectTimeout = MapUtils.getIntValue(initConfig, "redis.ConnectTimeout", 1000);
			//socket��ʱʱ��
			int socketTimeout = MapUtils.getIntValue(initConfig, "redis.SocketTimeout", 2000);
			jedisPool = new JedisPool(objPoolConfig, hostAndPort.get(0).getHost(), hostAndPort.get(0).getPort(), connectTimeout, socketTimeout, null, 0, null);
			
			return jedisPool;
		} catch (Exception e) {
			throw new RuntimeException("���������ش���:redis�����ʼ��ʧ�ܣ�",e);
		}
	}
	
	/**
	 * ��ȡ����ڵ�����
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	protected static List<HostAndPort> getHostAndPort(Map<? extends Object, ? extends Object> initConfig) throws FileNotFoundException, IOException {
		List<HostAndPort> cacheNodes = new ArrayList<HostAndPort>();
		String nodes = MapUtils.getString(initConfig, "redis.Nodes", "");
		for (String node : nodes.split(",")) {
			String[] addr = node.split(":");
			cacheNodes.add(new HostAndPort(addr[0], Integer.parseInt(addr[1])));
		}
		return cacheNodes;
	}
	
	/**
	 * ��ȡ���ӳ�����
	 * @param cacheInitConfig
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	protected static GenericObjectPoolConfig getObjectPoolConfig(Map<? extends Object, ? extends Object> initConfig) throws FileNotFoundException, IOException {
		boolean isIBMSerializeType = MapUtils.getBooleanValue(initConfig, "redis.UseIBMVendor", false);

		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		//���������, Ĭ��8��
		config.setMaxTotal(MapUtils.getIntValue(initConfig, "redis.MaxTotal", 100));
		//������������, Ĭ��8��
		config.setMaxIdle(MapUtils.getIntValue(initConfig, "redis.MaxIdle", 20));
		//��С����������, Ĭ��0
		config.setMinIdle(MapUtils.getIntValue(initConfig, "redis.MinIdle", 2));
		//�����������С����ʱ�� Ĭ��1800000����(30����)
		config.setMinEvictableIdleTimeMillis(MapUtils.getIntValue(initConfig, "redis.MinEvictableIdleTimeMillis", 300000));
		//���ӿ��ж�ú����, ������ʱ��>��ֵ �� ��������>�������� ʱֱ�����,���ٸ���MinEvictableIdleTimeMillis�ж�  (Ĭ���������)   
		config.setSoftMinEvictableIdleTimeMillis(MapUtils.getIntValue(initConfig, "redis.SoftMinEvictableIdleTimeMillis", 300000));
		//�������ɨ���ʱ����(����) ���Ϊ����,����������߳�, Ĭ��-1
		config.setTimeBetweenEvictionRunsMillis(MapUtils.getIntValue(initConfig, "redis.TimeBetweenEvictionRunsMillis", 2000));

		//���Ӻľ�ʱ�Ƿ�����, false���쳣,ture����ֱ����ʱ, Ĭ��true
		config.setBlockWhenExhausted(MapUtils.getBooleanValue(initConfig, "redis.BlockWhenExhausted", true));
		//��ȡ����ʱ�����ȴ�������(�������Ϊ����ʱBlockWhenExhausted),�����ʱ�����쳣,
		//С����:������ȷ����ʱ��,  Ĭ��-1
		config.setMaxWaitMillis(MapUtils.getIntValue(initConfig, "redis.MaxWaitMillis", 1000));
		config.setTestWhileIdle(true);
		config.setTestOnCreate(true);
		config.setTestOnReturn(true);
		config.setTestOnBorrow(true);
		return config;
	}
	

	
	
}

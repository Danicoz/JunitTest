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
	 * 集群 Redis
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
	

	/** 单机  redis client类 **/
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
	 * 集群
	 * @return 
	 */
	private JedisCluster initJedisCluster(Map<? extends Object, ? extends Object> initConfig) {
		if(initConfig == null){
			throw new RuntimeException("！！！严重错误:redis缓存初始化失败！无法读取缓存配置！");
		}
		try {
			GenericObjectPoolConfig objPoolConfig = getObjectPoolConfig(initConfig);
			List<HostAndPort> hostAndPort = getHostAndPort(initConfig);
			if(hostAndPort.isEmpty()){
				throw new Exception("无法解析缓存节点配置。");
			}
			
			//连接超时时间
			int connectTimeout = MapUtils.getIntValue(initConfig, "redis.ConnectTimeout", 1000);
			//socket超时时间
			int socketTimeout = MapUtils.getIntValue(initConfig, "redis.SocketTimeout", 2000);

			jedisClusterClient = new JedisCluster(new HashSet<HostAndPort>(hostAndPort), connectTimeout, socketTimeout, objPoolConfig);
			return jedisClusterClient;
		} catch (Exception e) {
			throw new RuntimeException("！！！严重错误:redis缓存初始化失败！", e);
		}
	}

	
	/**
	 * 单机
	 */
	private JedisPool initJedisPool(Map<? extends Object, ? extends Object> initConfig) {
		if(initConfig == null){
			throw new RuntimeException("！！！严重错误:redis缓存初始化失败！无法读取缓存配置！");
		}
		try {
			GenericObjectPoolConfig objPoolConfig = getObjectPoolConfig(initConfig);
			List<HostAndPort> hostAndPort = getHostAndPort(initConfig);
			if(hostAndPort.isEmpty()){
				throw new Exception("无法解析缓存节点配置。");
			}
			//连接超时时间
			int connectTimeout = MapUtils.getIntValue(initConfig, "redis.ConnectTimeout", 1000);
			//socket超时时间
			int socketTimeout = MapUtils.getIntValue(initConfig, "redis.SocketTimeout", 2000);
			jedisPool = new JedisPool(objPoolConfig, hostAndPort.get(0).getHost(), hostAndPort.get(0).getPort(), connectTimeout, socketTimeout, null, 0, null);
			
			return jedisPool;
		} catch (Exception e) {
			throw new RuntimeException("！！！严重错误:redis缓存初始化失败！",e);
		}
	}
	
	/**
	 * 获取缓存节点配置
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
	 * 获取连接池配置
	 * @param cacheInitConfig
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	protected static GenericObjectPoolConfig getObjectPoolConfig(Map<? extends Object, ? extends Object> initConfig) throws FileNotFoundException, IOException {
		boolean isIBMSerializeType = MapUtils.getBooleanValue(initConfig, "redis.UseIBMVendor", false);

		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		//最大连接数, 默认8个
		config.setMaxTotal(MapUtils.getIntValue(initConfig, "redis.MaxTotal", 100));
		//最大空闲连接数, 默认8个
		config.setMaxIdle(MapUtils.getIntValue(initConfig, "redis.MaxIdle", 20));
		//最小空闲连接数, 默认0
		config.setMinIdle(MapUtils.getIntValue(initConfig, "redis.MinIdle", 2));
		//连接逐出的最小空闲时间 默认1800000毫秒(30分钟)
		config.setMinEvictableIdleTimeMillis(MapUtils.getIntValue(initConfig, "redis.MinEvictableIdleTimeMillis", 300000));
		//连接空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)   
		config.setSoftMinEvictableIdleTimeMillis(MapUtils.getIntValue(initConfig, "redis.SoftMinEvictableIdleTimeMillis", 300000));
		//连接逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
		config.setTimeBetweenEvictionRunsMillis(MapUtils.getIntValue(initConfig, "redis.TimeBetweenEvictionRunsMillis", 2000));

		//连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		config.setBlockWhenExhausted(MapUtils.getBooleanValue(initConfig, "redis.BlockWhenExhausted", true));
		//获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常,
		//小于零:阻塞不确定的时间,  默认-1
		config.setMaxWaitMillis(MapUtils.getIntValue(initConfig, "redis.MaxWaitMillis", 1000));
		config.setTestWhileIdle(true);
		config.setTestOnCreate(true);
		config.setTestOnReturn(true);
		config.setTestOnBorrow(true);
		return config;
	}
	

	
	
}

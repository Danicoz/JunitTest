package com.cattsoft.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;

public class JedisPoolTest {
	
	private static JedisPool jp = null;
	static {
		Map<String, Object> initConfig = new HashMap<String, Object>();
		initConfig.put("redis.Nodes",
				"192.168.13.2:6379,172.168.63.233:6380,172.168.63.233:6381");
		try {
			List<HostAndPort> hostAndPort = JedisUtil
					.getHostAndPort(initConfig);
			if (hostAndPort.isEmpty()) {

				throw new Exception("�޷���������ڵ����á�");
			}
			GenericObjectPoolConfig objPoolConfig = JedisUtil.getObjectPoolConfig(initConfig);
			jp = new JedisUtil(initConfig, hostAndPort, objPoolConfig).getJedisPoolAlone();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testJedisPoolTest() throws Exception {
		Map<String, Object> initConfig = new HashMap<String, Object>();
		initConfig.put("redis.Nodes",
				"192.13.2:6379,1763.6381");

		List<HostAndPort> hostAndPort = JedisUtil.getHostAndPort(initConfig);
		if (hostAndPort.isEmpty()) {
			throw new Exception("�޷���������ڵ����á�");
		}
		GenericObjectPoolConfig objPoolConfig = JedisUtil
				.getObjectPoolConfig(initConfig);

		JedisPool jp = new JedisUtil(initConfig, hostAndPort, objPoolConfig)
				.getJedisPoolAlone();

		Jedis jedis = jp.getResource();// ��ȡ����jedis����

		System.out.println("===========��������===========");
		System.out.println("������ݣ�" + jedis.flushDB());
		System.out.println(jedis.set("k1", "v1"));// ���� key value ����str=OK
		System.out.println("�жϼ��Ƿ���ڣ�" + jedis.exists("k1"));// �ж�key�Ƿ����
		System.out.println("��ȡkey��" + jedis.get("k1"));// ��ȡkey��Ӧ��ֵ
		System.out.println("׷��valueֵ��" + jedis.append("k1", "append"));// value׷���ַ���������8��ʾvalue����
		System.out.println(jedis.get("k1"));// v1append
		System.out.println("ɾ������:" + jedis.del("k1"));// ɾ��key��Ӧ��ֵ ����1
		System.out.println(jedis.del("k2"));// ����0

		System.out.println("���Ӷ����ֵ�ԣ�" + jedis.mset("k2", "v2", "k3", "v3"));// OK
		System.out.println("��ȡ�����ֵ�ԣ�" + jedis.mget("k1", "k2", "k3", "k4"));// [null,
																	
		System.out.println("ɾ�������ֵ�ԣ�" + jedis.del(new String[] { "k1", "k2" }));// 1
		System.out.println("ɾ�������ֵ�ԣ�" + jedis.del("k1", "k2", "k3"));// [null,
		System.out.println(jedis.get("k3"));

		System.out.println("===========��ֹ����ԭ��ֵ===========");
		jedis.flushDB();
		System.out.println(jedis.set("k1", "v1"));
		System.out.println(jedis.get("k1"));// v1
		System.out.println("����ԭ��ֵ��" + jedis.set("k1", "v11"));
		System.out.println(jedis.get("k1"));// v11
		System.out.println("������ԭ��ֵ��" + jedis.setnx("k1", "v111"));
		System.out.println(jedis.get("k1"));// v11

		System.out.println();
		System.out.println("===========���ü�ֵ��Чʱ��===========");
		System.out.println(jedis.setex("k2", 1, "v2"));
		System.out.println(jedis.get("k2"));
		//System.out.println(jedis.ttl("k2"));//ʣ������ʱ��
		//System.out.println(jedis.persist("k2"));//�Ƴ�����ʱ��
		TimeUnit.SECONDS.sleep(1);
		System.out.println(jedis.get("k2"));

		System.out.println();
		System.out.println("===========��ȡԭֵ������Ϊ��ֵ============");

		jedis.set("k3", "v3");
		System.out.println(jedis.getSet("k3", "k3AndSet"));// v3
		System.out.println(jedis.get("k3"));// k3AndSet
		System.out.println("��ȡk3ֵ���ַ�����" + jedis.getrange("k3", 2, 4));// And

	}

	@Test
	public void testNumber() {
		Jedis jedis = jp.getResource();
		jedis.flushDB();
		
		jedis.set("k1", "1");
		jedis.set("k2","2.3");
		System.out.println("k1ֵ����1��" + jedis.incr("k1"));//2
		System.out.println("k1ֵ�Լ�1��" + jedis.decr("k1"));//1
		System.out.println("k1ֵ����5��" + jedis.incrBy("k1", 5));//6
		System.out.println("k1ֵ�Լ�5��" + jedis.decrBy("k1", 5));//1
		
		//��������������2.3�����������쳣
		//System.out.println(jedis.incr("k2"));//redis.clients.jedis.exceptions.JedisDataException: ERR value is not an integer or out of range
	}
	
	/**
	 * �б�������˫������ṹ���ɴ���������������ߵ���Ԫ�ء�
	 * RPUSH : RPUSH key-name value  [value1 value2,...] ------------��һ������ֵ�����б���
	 * LPUSH �� LPUSH key-name value  [value1 value2,...] ------------��һ������ֵ�����б����
	 * RPOP  �� RPOP key-name----------�Ƴ��������б����Ҷ�Ԫ��
	 * LPOP  ��LPOP key-name----------�Ƴ��������б������Ԫ��
	 * LINDEX �� LINDEX key-name  offset --------------�����б���ƫ����Ϊoffset��Ԫ��
	 * LRANGE : LRANGE key-name start end -------------�����б���ƫ������start��end��Χ�ڵ�Ԫ��
	 * LTRIM : LTRIM key-name start end ----------------���б�����޼���ֻ����ƫ������start��end��Χ�ڵ�Ԫ��
	 */
	@Test
	public void testList(){
			Jedis jedis = jp.getResource();
			jedis.flushDB();
			
			//Set<String> set = jedis.keys("*");
		 	System.out.println("===========���һ��list===========");
	        jedis.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap", "LinkedHashMap");//collections ��ʾΪ key
	        jedis.lpush("collections", "HashSet"); // ����
	        jedis.lpush("collections", "TreeSet"); // ����
	        jedis.lpush("collections", "TreeMap"); // ����
	        
	        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));//-1��������һ��Ԫ�أ�-2�������ڶ���Ԫ�� �� ���� ����ֵ
	        System.out.println("collections����0-3��Ԫ�أ�"+jedis.lrange("collections",0,3)); // ǰ��4��ֵ
	        System.out.println("===============================");
	        
	        
	       
	        jedis.lpush("collections", "HashMap");
	        jedis.lpush("collections", "HashMap");
	        // ɾ���б�ָ����ֵ ���ڶ�������Ϊɾ���ĸ��������ظ�ʱ������add��ȥ��ֵ�ȱ�ɾ�������ڳ�ջ
	        System.out.println("ɾ��ָ��Ԫ�ظ�����"+jedis.lrem("collections", 2, "HashMap"));//ɾ����ȳ��ֵ�ֵ
	        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));
	        
	        System.out.println("ɾ���±�0-3����֮���Ԫ�أ�"+jedis.ltrim("collections", 0, 3));//����ǰ��4��ֵ
	        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));
	        
	        System.out.println("collections�б��ջ����ˣ���"+jedis.lpop("collections"));//TreeMap  ���س�ջ��ֵ
	        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));
	        System.out.println("collections���Ԫ�أ����б��Ҷˣ���lpush���Ӧ��"+jedis.rpush("collections", "EnumMap"));//4
	        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));//[TreeSet, HashSet, LinkedHashMap, EnumMap]
	        System.out.println("collections�б��ջ���Ҷˣ���"+jedis.rpop("collections"));
	        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));//[TreeSet, HashSet, LinkedHashMap]
	        
	        
	        System.out.println("�޸�collectionsָ���±�1�����ݣ�"+jedis.lset("collections", 1, "LinkedArrayList"));
	        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));//[TreeSet, LinkedArrayList, LinkedHashMap]
	       
	        System.out.println("===============================");
	        System.out.println("collections�ĳ��ȣ�"+jedis.llen("collections"));
	        System.out.println("��ȡcollections�±�Ϊ2��Ԫ�أ�"+jedis.lindex("collections", 2));
	        System.out.println("===============================");
	        jedis.lpush("sortedList", "3","6","2","0","7","4");
	        System.out.println("sortedList����ǰ��"+jedis.lrange("sortedList", 0, -1));
	        System.out.println(jedis.sort("sortedList"));//[0, 2, 3, 4, 6, 7]
	        System.out.println("sortedList�����"+jedis.lrange("sortedList", 0, -1));//[4, 7, 0, 2, 6, 3]
	}
	
	@Test
	public void testSet(){
		Jedis jedis = jp.getResource();
		jedis.flushDB();
		
		System.out.println("============�򼯺������Ԫ��============");
        System.out.println(jedis.sadd("eleSet", "e1","e2","e4","e3","e0","e8","e7","e5"));//8
        System.out.println(jedis.sadd("eleSet", "e6"));//1
        System.out.println(jedis.sadd("eleSet", "e6")); // ����0���������Ѿ�����
        
        System.out.println("eleSet������Ԫ��Ϊ��"+jedis.smembers("eleSet"));//[e7, e2, e3, e5, e4, e0, e1, e6, e8]
        System.out.println("ɾ��һ��Ԫ��e0��"+jedis.srem("eleSet", "e0"));
        System.out.println("eleSet������Ԫ��Ϊ��"+jedis.smembers("eleSet"));//[e2, e3, e5, e4, e1, e6, e8, e7]
        
        System.out.println("ɾ������Ԫ��e7��e6��"+jedis.srem("eleSet", "e7","e6"));
        System.out.println("eleSet������Ԫ��Ϊ��"+jedis.smembers("eleSet"));
        
        System.out.println("������Ƴ������е�һ��Ԫ�أ�"+jedis.spop("eleSet"));
        System.out.println("������Ƴ������е�һ��Ԫ�أ�"+jedis.spop("eleSet"));
        System.out.println("eleSet������Ԫ��Ϊ��"+jedis.smembers("eleSet"));//[e3, e2, e1, e8]
        
        System.out.println("eleSet�а���Ԫ�صĸ�����"+jedis.scard("eleSet"));//4  ����������
        
        System.out.println("e3�Ƿ���eleSet�У�"+jedis.sismember("eleSet", "e3"));
        System.out.println("e1�Ƿ���eleSet�У�"+jedis.sismember("eleSet", "e1"));
        System.out.println("e5�Ƿ���eleSet�У�"+jedis.sismember("eleSet", "e5"));

        // ��Ⱥ�²���ᱨ��redis.clients.jedis.exceptions.JedisClusterException: No way to dispatch this command to Redis Cluster because keys have different slots.
        // Redis��Ⱥ����key1������key2���ϲ��桢�������������������crc16�㷨�����в�ͬ�Ĳۡ�
        /*System.out.println("=================================");
        System.out.println(jedis.sadd("eleSet1", "e1","e2","e4","e3","e0","e8","e7","e5"));
        System.out.println(jedis.sadd("eleSet2", "e1","e2","e4","e3","e0","e8"));
        System.out.println("��eleSet1��ɾ��e1������eleSet3�У�"+jedis.smove("eleSet1", "eleSet3", "e1"));
        System.out.println("��eleSet1��ɾ��e2������eleSet3�У�"+jedis.smove("eleSet1", "eleSet3", "e2"));
        System.out.println("eleSet1�е�Ԫ�أ�"+jedis.smembers("eleSet1"));
        System.out.println("eleSet3�е�Ԫ�أ�"+jedis.smembers("eleSet3"));*/

        /*System.out.println("============��������=================");
        System.out.println("eleSet1�е�Ԫ�أ�"+jedis.smembers("eleSet1"));
        System.out.println("eleSet2�е�Ԫ�أ�"+jedis.smembers("eleSet2"));
        System.out.println("eleSet1��eleSet2�Ľ���:"+jedis.sinter("eleSet1","eleSet2"));
        System.out.println("eleSet1��eleSet2�Ĳ���:"+jedis.sunion("eleSet1","eleSet2"));
        System.out.println("eleSet1��eleSet2�Ĳ:"+jedis.sdiff("eleSet1","eleSet2"));*/
        jedis.del("eleSet");
        jedis.del("eleSet1");
        jedis.del("eleSet2");
        jedis.del("eleSet3");
	}
	
    /***
     * ɢ��
     */
    @Test
    public void testHash() {
    	Jedis jedis = jp.getResource();
		jedis.flushDB();
    	
        Map<String,String> map = new HashMap<String,String>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        jedis.hmset("hash",map);
        jedis.hset("hash", "key5", "value5");
        
        System.out.println("ɢ��hash�����м�ֵ��Ϊ��"+jedis.hgetAll("hash"));//return Map<String,String>//{key4=value4, key3=value3, key5=value5, key2=value2, key1=value1}
        System.out.println("ɢ��hash�����м�Ϊ��"+jedis.hkeys("hash"));//return Set<String>//[key4, key3, key5, key2, key1]
        System.out.println("ɢ��hash������ֵΪ��"+jedis.hvals("hash"));//return List<String>//[value1, value2, value4, value3, value5]
        
        System.out.println("��key6�����ֵ����һ�����������key6�����������key6��"+jedis.hincrBy("hash", "key6", 6));//��������
        System.out.println("ɢ��hash�����м�ֵ��Ϊ��"+jedis.hgetAll("hash"));
        System.out.println("��key6�����ֵ����һ�����������key6�����������key6��"+jedis.hincrBy("hash", "key6", 3));
        System.out.println("ɢ��hash�����м�ֵ��Ϊ��"+jedis.hgetAll("hash"));
        
        
        System.out.println("ɾ��һ�����߶����ֵ�ԣ�"+jedis.hdel("hash", "key2"));//1
        System.out.println("ɢ��hash�����м�ֵ��Ϊ��"+jedis.hgetAll("hash"));//{key4=value4, key3=value3, key6=9, key5=value5, key1=value1}
        System.out.println("ɢ��hash�м�ֵ�Եĸ�����"+jedis.hlen("hash"));//5
        System.out.println("�ж�hash���Ƿ����key2��"+jedis.hexists("hash","key2"));
        System.out.println("�ж�hash���Ƿ����key3��"+jedis.hexists("hash","key3"));
        System.out.println("��ȡhash�е�ֵ��"+jedis.hmget("hash","key3"));
        System.out.println("��ȡhash�е�ֵ��"+jedis.hmget("hash","key3","key4"));//[value3, value4]
    }
    
    /**
     * ���򼯺�
     */
    @Test
    public void testSortedSet() {
    	
    	Jedis jedis = jp.getResource();
		jedis.flushDB();
		
		
        Map<String,Double> map = new HashMap<String,Double>();
        map.put("key2",1.2);
        map.put("key3",4.0);
        map.put("key4",5.0);
        map.put("key5",0.2);
        // ��һ������ member Ԫ�ؼ��� score ֵ���뵽���� key ���У����ĳ�� member �Ѿ������򼯵ĳ�Ա����ô������� member �� score ֵ
        // score ֵ����������ֵ��˫���ȸ�����
        System.out.println(jedis.zadd("zset", 3,"key1"));
        System.out.println(jedis.zadd("zset",map));
        System.out.println("zset�е�����Ԫ�أ�"+jedis.zrange("zset", 0, -1));
        
        System.out.println("zset�е�����Ԫ�أ�"+jedis.zrangeWithScores("zset", 0, -1));
        System.out.println("zset�е�����Ԫ�أ�"+jedis.zrangeByScore("zset", 0,100));
        System.out.println("zset�е�����Ԫ�أ�"+jedis.zrangeByScoreWithScores("zset", 0,100));
        System.out.println("zset��key2�ķ�ֵ��"+jedis.zscore("zset", "key2"));
        System.out.println("zset��key2��������"+jedis.zrank("zset", "key2"));
        System.out.println("ɾ��zset�е�Ԫ��key3��"+jedis.zrem("zset", "key3"));
        System.out.println("zset�е�����Ԫ�أ�"+jedis.zrange("zset", 0, -1));
        System.out.println("zset��Ԫ�صĸ�����"+jedis.zcard("zset"));
        System.out.println("zset�з�ֵ��1-4֮���Ԫ�صĸ�����"+jedis.zcount("zset", 1, 4));
        System.out.println("key2�ķ�ֵ����5��"+jedis.zincrby("zset", 5, "key2"));
        System.out.println("key3�ķ�ֵ����4��"+jedis.zincrby("zset", 4, "key3"));
        System.out.println("zset�е�����Ԫ�أ�"+jedis.zrange("zset", 0, -1));
    }

    /**
     * ����
     */
    @Test
    public void testSort() {
    	Jedis jedis = jp.getResource();
		jedis.flushDB();
    	
        jedis.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap", "LinkedHashMap");
        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));
        SortingParams sortingParameters = new SortingParams();
        // �����ݼ��б�������ַ���ֵʱ��������� ALPHA,Ĭ��������
        System.out.println("alpha����ʽ��" + jedis.sort("collections",sortingParameters.alpha()));
        System.out.println("===============================");
        jedis.lpush("sortedList", "3","6","2","0","7","4");
        System.out.println("sortedList����ǰ��"+jedis.lrange("sortedList", 0, -1));//[4, 7, 0, 2, 6, 3]
        System.out.println("����"+jedis.sort("sortedList", sortingParameters.asc()));//[0, 2, 3, 4, 6, 7]
        System.out.println("����"+jedis.sort("sortedList", sortingParameters.desc()));//[7, 6, 4, 3, 2, 0]
        System.out.println("===============================");
        // ��Ⱥ�²�֧�ַָ������
        /*jedis.lpush("userlist", "33");
        jedis.lpush("userlist", "22");
        jedis.lpush("userlist", "55");
        jedis.lpush("userlist", "11");
        jedis.hset("user:66", "name", "66");
        jedis.hset("user:55", "name", "55");
        jedis.hset("user:33", "name", "33");
        jedis.hset("user:22", "name", "79");
        jedis.hset("user:11", "name", "24");
        jedis.hset("user:11", "add", "beijing");
        jedis.hset("user:22", "add", "shanghai");
        jedis.hset("user:33", "add", "guangzhou");
        jedis.hset("user:55", "add", "chongqing");
        jedis.hset("user:66", "add", "xi'an");
        sortingParameters = new SortingParams();
        // ���� "->" ���ڷָ��ϣ��ļ���(key name)��������(hash field)����ʽΪ "key->field"
        sortingParameters.get("user:*->name");
        sortingParameters.get("user:*->add");
        System.out.println(jedis.sort("userlist",sortingParameters));*/
    }

}

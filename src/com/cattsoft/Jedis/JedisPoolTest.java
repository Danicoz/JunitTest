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

				throw new Exception("无法解析缓存节点配置。");
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
				"192.168.13.2:6379,172.168.63.233:6380,172.168.63.233:6381");

		List<HostAndPort> hostAndPort = JedisUtil.getHostAndPort(initConfig);
		if (hostAndPort.isEmpty()) {
			throw new Exception("无法解析缓存节点配置。");
		}
		GenericObjectPoolConfig objPoolConfig = JedisUtil
				.getObjectPoolConfig(initConfig);

		JedisPool jp = new JedisUtil(initConfig, hostAndPort, objPoolConfig)
				.getJedisPoolAlone();

		Jedis jedis = jp.getResource();// 获取缓存jedis对象

		System.out.println("===========增加数据===========");
		System.out.println("清空数据：" + jedis.flushDB());
		System.out.println(jedis.set("k1", "v1"));// 保存 key value 返回str=OK
		System.out.println("判断键是否存在：" + jedis.exists("k1"));// 判断key是否存在
		System.out.println("获取key：" + jedis.get("k1"));// 获取key对应的值
		System.out.println("追加value值：" + jedis.append("k1", "append"));// value追加字符串，返回8表示value长度
		System.out.println(jedis.get("k1"));// v1append
		System.out.println("删除数据:" + jedis.del("k1"));// 删除key对应的值 返回1
		System.out.println(jedis.del("k2"));// 返回0

		System.out.println("增加多个键值对：" + jedis.mset("k2", "v2", "k3", "v3"));// OK
		System.out.println("获取多个键值对：" + jedis.mget("k1", "k2", "k3", "k4"));// [null,
																	
		System.out.println("删除多个键值对：" + jedis.del(new String[] { "k1", "k2" }));// 1
		System.out.println("删除多个键值对：" + jedis.del("k1", "k2", "k3"));// [null,
		System.out.println(jedis.get("k3"));

		System.out.println("===========防止覆盖原先值===========");
		jedis.flushDB();
		System.out.println(jedis.set("k1", "v1"));
		System.out.println(jedis.get("k1"));// v1
		System.out.println("覆盖原先值：" + jedis.set("k1", "v11"));
		System.out.println(jedis.get("k1"));// v11
		System.out.println("不覆盖原先值：" + jedis.setnx("k1", "v111"));
		System.out.println(jedis.get("k1"));// v11

		System.out.println();
		System.out.println("===========设置键值有效时间===========");
		System.out.println(jedis.setex("k2", 1, "v2"));
		System.out.println(jedis.get("k2"));
		//System.out.println(jedis.ttl("k2"));//剩下生存时间
		//System.out.println(jedis.persist("k2"));//移除设置时间
		TimeUnit.SECONDS.sleep(1);
		System.out.println(jedis.get("k2"));

		System.out.println();
		System.out.println("===========获取原值，更新为新值============");

		jedis.set("k3", "v3");
		System.out.println(jedis.getSet("k3", "k3AndSet"));// v3
		System.out.println(jedis.get("k3"));// k3AndSet
		System.out.println("截取k3值子字符串：" + jedis.getrange("k3", 2, 4));// And

	}

	@Test
	public void testNumber() {
		Jedis jedis = jp.getResource();
		jedis.flushDB();
		
		jedis.set("k1", "1");
		jedis.set("k2","2.3");
		System.out.println("k1值自增1：" + jedis.incr("k1"));//2
		System.out.println("k1值自减1：" + jedis.decr("k1"));//1
		System.out.println("k1值自增5：" + jedis.incrBy("k1", 5));//6
		System.out.println("k1值自减5：" + jedis.decrBy("k1", 5));//1
		
		//自增的是整数，2.3不是整数报异常
		//System.out.println(jedis.incr("k2"));//redis.clients.jedis.exceptions.JedisDataException: ERR value is not an integer or out of range
	}
	
	/**
	 * 列表类似于双向链表结构，可从序列两端推入或者弹出元素。
	 * RPUSH : RPUSH key-name value  [value1 value2,...] ------------将一个或多个值推入列表右
	 * LPUSH ： LPUSH key-name value  [value1 value2,...] ------------将一个或多个值推入列表左端
	 * RPOP  ： RPOP key-name----------移除并返回列表最右端元素
	 * LPOP  ：LPOP key-name----------移除并返回列表最左端元素
	 * LINDEX ： LINDEX key-name  offset --------------返回列表中偏移量为offset的元素
	 * LRANGE : LRANGE key-name start end -------------返回列表中偏移量从start到end范围内的元素
	 * LTRIM : LTRIM key-name start end ----------------对列表进行修剪，只保留偏移量从start到end范围内的元素
	 */
	@Test
	public void testList(){
			Jedis jedis = jp.getResource();
			jedis.flushDB();
			
			//Set<String> set = jedis.keys("*");
		 	System.out.println("===========添加一个list===========");
	        jedis.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap", "LinkedHashMap");//collections 表示为 key
	        jedis.lpush("collections", "HashSet"); // 叠加
	        jedis.lpush("collections", "TreeSet"); // 叠加
	        jedis.lpush("collections", "TreeMap"); // 叠加
	        
	        System.out.println("collections的内容："+jedis.lrange("collections", 0, -1));//-1代表倒数第一个元素，-2代表倒数第二个元素 ； 返回 所有值
	        System.out.println("collections区间0-3的元素："+jedis.lrange("collections",0,3)); // 前面4个值
	        System.out.println("===============================");
	        
	        
	       
	        jedis.lpush("collections", "HashMap");
	        jedis.lpush("collections", "HashMap");
	        // 删除列表指定的值 ，第二个参数为删除的个数（有重复时），后add进去的值先被删，类似于出栈
	        System.out.println("删除指定元素个数："+jedis.lrem("collections", 2, "HashMap"));//删左边先出现的值
	        System.out.println("collections的内容："+jedis.lrange("collections", 0, -1));
	        
	        System.out.println("删除下表0-3区间之外的元素："+jedis.ltrim("collections", 0, 3));//保存前面4个值
	        System.out.println("collections的内容："+jedis.lrange("collections", 0, -1));
	        
	        System.out.println("collections列表出栈（左端）："+jedis.lpop("collections"));//TreeMap  返回出栈的值
	        System.out.println("collections的内容："+jedis.lrange("collections", 0, -1));
	        System.out.println("collections添加元素，从列表右端，与lpush相对应："+jedis.rpush("collections", "EnumMap"));//4
	        System.out.println("collections的内容："+jedis.lrange("collections", 0, -1));//[TreeSet, HashSet, LinkedHashMap, EnumMap]
	        System.out.println("collections列表出栈（右端）："+jedis.rpop("collections"));
	        System.out.println("collections的内容："+jedis.lrange("collections", 0, -1));//[TreeSet, HashSet, LinkedHashMap]
	        
	        
	        System.out.println("修改collections指定下标1的内容："+jedis.lset("collections", 1, "LinkedArrayList"));
	        System.out.println("collections的内容："+jedis.lrange("collections", 0, -1));//[TreeSet, LinkedArrayList, LinkedHashMap]
	       
	        System.out.println("===============================");
	        System.out.println("collections的长度："+jedis.llen("collections"));
	        System.out.println("获取collections下标为2的元素："+jedis.lindex("collections", 2));
	        System.out.println("===============================");
	        jedis.lpush("sortedList", "3","6","2","0","7","4");
	        System.out.println("sortedList排序前："+jedis.lrange("sortedList", 0, -1));
	        System.out.println(jedis.sort("sortedList"));//[0, 2, 3, 4, 6, 7]
	        System.out.println("sortedList排序后："+jedis.lrange("sortedList", 0, -1));//[4, 7, 0, 2, 6, 3]
	}
	
	@Test
	public void testSet(){
		Jedis jedis = jp.getResource();
		jedis.flushDB();
		
		System.out.println("============向集合中添加元素============");
        System.out.println(jedis.sadd("eleSet", "e1","e2","e4","e3","e0","e8","e7","e5"));//8
        System.out.println(jedis.sadd("eleSet", "e6"));//1
        System.out.println(jedis.sadd("eleSet", "e6")); // 返回0，集合中已经存在
        
        System.out.println("eleSet的所有元素为："+jedis.smembers("eleSet"));//[e7, e2, e3, e5, e4, e0, e1, e6, e8]
        System.out.println("删除一个元素e0："+jedis.srem("eleSet", "e0"));
        System.out.println("eleSet的所有元素为："+jedis.smembers("eleSet"));//[e2, e3, e5, e4, e1, e6, e8, e7]
        
        System.out.println("删除两个元素e7和e6："+jedis.srem("eleSet", "e7","e6"));
        System.out.println("eleSet的所有元素为："+jedis.smembers("eleSet"));
        
        System.out.println("随机的移除集合中的一个元素："+jedis.spop("eleSet"));
        System.out.println("随机的移除集合中的一个元素："+jedis.spop("eleSet"));
        System.out.println("eleSet的所有元素为："+jedis.smembers("eleSet"));//[e3, e2, e1, e8]
        
        System.out.println("eleSet中包含元素的个数："+jedis.scard("eleSet"));//4  返回数据量
        
        System.out.println("e3是否在eleSet中："+jedis.sismember("eleSet", "e3"));
        System.out.println("e1是否在eleSet中："+jedis.sismember("eleSet", "e1"));
        System.out.println("e5是否在eleSet中："+jedis.sismember("eleSet", "e5"));

        // 集群下并存会报错：redis.clients.jedis.exceptions.JedisClusterException: No way to dispatch this command to Redis Cluster because keys have different slots.
        // Redis集群，从key1集合与key2集合并存、交集、差集，两个键经过crc16算法可能有不同的槽。
        /*System.out.println("=================================");
        System.out.println(jedis.sadd("eleSet1", "e1","e2","e4","e3","e0","e8","e7","e5"));
        System.out.println(jedis.sadd("eleSet2", "e1","e2","e4","e3","e0","e8"));
        System.out.println("将eleSet1中删除e1并存入eleSet3中："+jedis.smove("eleSet1", "eleSet3", "e1"));
        System.out.println("将eleSet1中删除e2并存入eleSet3中："+jedis.smove("eleSet1", "eleSet3", "e2"));
        System.out.println("eleSet1中的元素："+jedis.smembers("eleSet1"));
        System.out.println("eleSet3中的元素："+jedis.smembers("eleSet3"));*/

        /*System.out.println("============集合运算=================");
        System.out.println("eleSet1中的元素："+jedis.smembers("eleSet1"));
        System.out.println("eleSet2中的元素："+jedis.smembers("eleSet2"));
        System.out.println("eleSet1和eleSet2的交集:"+jedis.sinter("eleSet1","eleSet2"));
        System.out.println("eleSet1和eleSet2的并集:"+jedis.sunion("eleSet1","eleSet2"));
        System.out.println("eleSet1和eleSet2的差集:"+jedis.sdiff("eleSet1","eleSet2"));*/
        jedis.del("eleSet");
        jedis.del("eleSet1");
        jedis.del("eleSet2");
        jedis.del("eleSet3");
	}
	
    /***
     * 散列
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
        
        System.out.println("散列hash的所有键值对为："+jedis.hgetAll("hash"));//return Map<String,String>//{key4=value4, key3=value3, key5=value5, key2=value2, key1=value1}
        System.out.println("散列hash的所有键为："+jedis.hkeys("hash"));//return Set<String>//[key4, key3, key5, key2, key1]
        System.out.println("散列hash的所有值为："+jedis.hvals("hash"));//return List<String>//[value1, value2, value4, value3, value5]
        
        System.out.println("将key6保存的值加上一个整数，如果key6不存在则添加key6："+jedis.hincrBy("hash", "key6", 6));//自增整数
        System.out.println("散列hash的所有键值对为："+jedis.hgetAll("hash"));
        System.out.println("将key6保存的值加上一个整数，如果key6不存在则添加key6："+jedis.hincrBy("hash", "key6", 3));
        System.out.println("散列hash的所有键值对为："+jedis.hgetAll("hash"));
        
        
        System.out.println("删除一个或者多个键值对："+jedis.hdel("hash", "key2"));//1
        System.out.println("散列hash的所有键值对为："+jedis.hgetAll("hash"));//{key4=value4, key3=value3, key6=9, key5=value5, key1=value1}
        System.out.println("散列hash中键值对的个数："+jedis.hlen("hash"));//5
        System.out.println("判断hash中是否存在key2："+jedis.hexists("hash","key2"));
        System.out.println("判断hash中是否存在key3："+jedis.hexists("hash","key3"));
        System.out.println("获取hash中的值："+jedis.hmget("hash","key3"));
        System.out.println("获取hash中的值："+jedis.hmget("hash","key3","key4"));//[value3, value4]
    }
    
    /**
     * 有序集合
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
        // 将一个或多个 member 元素及其 score 值加入到有序集 key 当中，如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值
        // score 值可以是整数值或双精度浮点数
        System.out.println(jedis.zadd("zset", 3,"key1"));
        System.out.println(jedis.zadd("zset",map));
        System.out.println("zset中的所有元素："+jedis.zrange("zset", 0, -1));
        
        System.out.println("zset中的所有元素："+jedis.zrangeWithScores("zset", 0, -1));
        System.out.println("zset中的所有元素："+jedis.zrangeByScore("zset", 0,100));
        System.out.println("zset中的所有元素："+jedis.zrangeByScoreWithScores("zset", 0,100));
        System.out.println("zset中key2的分值："+jedis.zscore("zset", "key2"));
        System.out.println("zset中key2的排名："+jedis.zrank("zset", "key2"));
        System.out.println("删除zset中的元素key3："+jedis.zrem("zset", "key3"));
        System.out.println("zset中的所有元素："+jedis.zrange("zset", 0, -1));
        System.out.println("zset中元素的个数："+jedis.zcard("zset"));
        System.out.println("zset中分值在1-4之间的元素的个数："+jedis.zcount("zset", 1, 4));
        System.out.println("key2的分值加上5："+jedis.zincrby("zset", 5, "key2"));
        System.out.println("key3的分值加上4："+jedis.zincrby("zset", 4, "key3"));
        System.out.println("zset中的所有元素："+jedis.zrange("zset", 0, -1));
    }

    /**
     * 排序
     */
    @Test
    public void testSort() {
    	Jedis jedis = jp.getResource();
		jedis.flushDB();
    	
        jedis.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap", "LinkedHashMap");
        System.out.println("collections的内容："+jedis.lrange("collections", 0, -1));
        SortingParams sortingParameters = new SortingParams();
        // 当数据集中保存的是字符串值时，你可以用 ALPHA,默认是升序
        System.out.println("alpha排序方式：" + jedis.sort("collections",sortingParameters.alpha()));
        System.out.println("===============================");
        jedis.lpush("sortedList", "3","6","2","0","7","4");
        System.out.println("sortedList排序前："+jedis.lrange("sortedList", 0, -1));//[4, 7, 0, 2, 6, 3]
        System.out.println("升序："+jedis.sort("sortedList", sortingParameters.asc()));//[0, 2, 3, 4, 6, 7]
        System.out.println("降序："+jedis.sort("sortedList", sortingParameters.desc()));//[7, 6, 4, 3, 2, 0]
        System.out.println("===============================");
        // 集群下不支持分割表排序
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
        // 符号 "->" 用于分割哈希表的键名(key name)和索引域(hash field)，格式为 "key->field"
        sortingParameters.get("user:*->name");
        sortingParameters.get("user:*->add");
        System.out.println(jedis.sort("userlist",sortingParameters));*/
    }

}

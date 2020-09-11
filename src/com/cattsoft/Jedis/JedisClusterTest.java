package com.cattsoft.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.SortingParams;

public class JedisClusterTest {
		
	private static JedisCluster jedis = null;
	static {
		Map<String, Object> initConfig = new HashMap<String, Object>();
		initConfig.put("redis.Nodes", ".168..2:6379,.168.63.:6380,..63.:6381");
		try {
			List<HostAndPort> hostAndPort = JedisUtil
					.getHostAndPort(initConfig);
			if (hostAndPort.isEmpty()) {

				throw new Exception("�޷���������ڵ����á�");
			}
			GenericObjectPoolConfig objPoolConfig = JedisUtil.getObjectPoolConfig(initConfig);
			jedis = new JedisUtil(initConfig, hostAndPort, objPoolConfig).getJedisCluster();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
     * ����key:value����
     * ��Ⱥ��flushDB��keys����
     */
    @Test
    public void testKey() throws InterruptedException {
        //System.out.println("������ݣ�"+jedis.flushDB());
        System.out.println("�ж�ĳ�����Ƿ���ڣ�"+jedis.exists("username"));
        System.out.println("����<'username','wukong'>�ļ�ֵ�ԣ�"+jedis.set("username", "xiaohai"));
        System.out.println("�Ƿ����:"+jedis.exists("username"));
        System.out.println("����<'password','password'>�ļ�ֵ�ԣ�"+jedis.set("password", "123456"));
        //Set<String> keys = jedis.keys("*");
        // System.out.println("ϵͳ�����еļ����£�"+keys);
        System.out.println("ɾ����password:"+jedis.del("password"));
        System.out.println("�жϼ�password�Ƿ���ڣ�"+jedis.exists("password"));
        System.out.println("���ü�username�Ĺ���ʱ��Ϊ10s:"+jedis.expire("username", 10));
        TimeUnit.SECONDS.sleep(2); // �߳�˯��2��System.out.println("�鿴��username��ʣ������ʱ�䣺"+jedis.ttl("username"));
        System.out.println("�鿴��username��ʣ������ʱ�䣺"+jedis.ttl("username"));
        System.out.println("�Ƴ���username������ʱ�䣺"+jedis.persist("username"));
        System.out.println("�鿴��username��ʣ������ʱ�䣺"+jedis.ttl("username"));
        System.out.println("�鿴��username���洢��ֵ�����ͣ�"+jedis.type("username"));
    }

    /***
     * �ַ�������
     * memcached��redisͬ����append�Ĳ���������memcached��prepend�Ĳ�����redis�в�û�С�
     * ��Ⱥ��flushDB��keys��del(���ֵ)��mset(���ֵ)����
     * @throws InterruptedException
     */
    @Test
    public void testString() throws InterruptedException {
        //jedis.flushDB();
        System.out.println("===========��������===========");
        System.out.println(jedis.set("key1","value1"));
        System.out.println(jedis.set("key2","value2"));
        System.out.println(jedis.set("key3", "value3"));
        System.out.println("ɾ����key2:"+jedis.del("key2"));
        System.out.println("��ȡ��key2:"+jedis.get("key2"));
        System.out.println("�޸�key1:"+jedis.set("key1", "value1Changed"));
        System.out.println("��ȡkey1��ֵ��"+jedis.get("key1"));
        System.out.println("��key3�������ֵ��"+jedis.append("key3", "End"));
        System.out.println("key3��ֵ��"+jedis.get("key3"));
        //�����ʱ��Ż�ȥ�������ӣ���Ⱥ�������Ƕ�һ���ڵ����ӣ������ж϶��key����crc16�㷨����Ӧ�Ĳ���һ���ڵ��ϣ���֧�ֶ�key��ȡ��ɾ��
        //System.out.println("���Ӷ����ֵ�ԣ�"+jedis.mset("key01","value01","key02","value02"));
        //System.out.println("��ȡ�����ֵ�ԣ�"+jedis.mget("key01","key02","key03"));
        //System.out.println("��ȡ�����ֵ�ԣ�"+jedis.mget("key01","key02","key03","key04"));
        //System.out.println("ɾ�������ֵ�ԣ�"+jedis.del(new String[]{"key01","key02"}));
        //System.out.println("��ȡ�����ֵ�ԣ�"+jedis.mget("key01","key02","key03"));

        //jedis.flushDB();
        System.out.println("===========������ֵ�Է�ֹ����ԭ��ֵ==============");
        System.out.println(jedis.setnx("key1", "value1"));
        System.out.println(jedis.setnx("key2", "value2"));
        System.out.println(jedis.setnx("key2", "value2-new"));
        System.out.println(jedis.get("key1"));
        System.out.println(jedis.get("key2"));

        System.out.println("===========������ֵ�Բ�������Чʱ��=============");
        System.out.println(jedis.setex("key3", 2, "value3"));
        System.out.println(jedis.get("key3"));
        TimeUnit.SECONDS.sleep(3);
        System.out.println(jedis.get("key3"));

        System.out.println("===========��ȡԭֵ������Ϊ��ֵ==========");//GETSET is an atomic set this value and return the old value command.
        System.out.println(jedis.getSet("key2", "key2GetSet"));
        System.out.println(jedis.get("key2"));
        System.out.println("���key2��ֵ���ִ���"+jedis.getrange("key2", 2, 4)); // �൱��ȡ�ַ����ĵڶ���λ��-���ĸ�λ�õ��ַ���
    }

    /***
     * �����͸�����
     */
    @Test
    public void testNumber() {
        jedis.set("key1", "1");
        jedis.set("key2", "2");
        jedis.set("key3", "2.3");
        System.out.println("key1��ֵ��"+jedis.get("key1"));
        System.out.println("key2��ֵ��"+jedis.get("key2"));
        System.out.println("key1��ֵ��1��"+jedis.incr("key1"));
        System.out.println("��ȡkey1��ֵ��"+jedis.get("key1"));
        System.out.println("key2��ֵ��1��"+jedis.decr("key2"));
        System.out.println("��ȡkey2��ֵ��"+jedis.get("key2"));
        System.out.println("��key1��ֵ��������5��"+jedis.incrBy("key1", 5));
        System.out.println("��ȡkey1��ֵ��"+jedis.get("key1"));
        System.out.println("��key2��ֵ��ȥ����5��"+jedis.decrBy("key2", 5));
        System.out.println("��ȡkey2��ֵ��"+jedis.get("key2"));
        System.out.println("key3��ֵ��"+jedis.get("key3"));
        // ����ᱨ����Ϊkey3������������������:redis.clients.jedis.exceptions.JedisDataException: ERR value is not an integer or out of range
        // System.out.println("key2��ֵ��1��"+jedis.decr("key3"));
    }

    /***
     * �б�
     */
    @Test
    public void testList() {
        System.out.println("===========���һ��list===========");
        jedis.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap", "LinkedHashMap");
        jedis.lpush("collections", "HashSet"); // ����
        jedis.lpush("collections", "TreeSet"); // ����
        jedis.lpush("collections", "TreeMap"); // ����
        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));//-1��������һ��Ԫ�أ�-2�������ڶ���Ԫ��
        System.out.println("collections����0-3��Ԫ�أ�"+jedis.lrange("collections",0,3)); // ǰ��4��ֵ
        System.out.println("===============================");
        // ɾ���б�ָ����ֵ ���ڶ�������Ϊɾ���ĸ��������ظ�ʱ������add��ȥ��ֵ�ȱ�ɾ�������ڳ�ջ
        System.out.println("ɾ��ָ��Ԫ�ظ�����"+jedis.lrem("collections", 2, "HashMap"));
        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));
        System.out.println("ɾ���±�0-3����֮���Ԫ�أ�"+jedis.ltrim("collections", 0, 3));
        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));
        System.out.println("collections�б��ջ����ˣ���"+jedis.lpop("collections"));
        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));
        System.out.println("collections���Ԫ�أ����б��Ҷˣ���lpush���Ӧ��"+jedis.rpush("collections", "EnumMap"));
        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));
        System.out.println("collections�б��ջ���Ҷˣ���"+jedis.rpop("collections"));
        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));
        System.out.println("�޸�collectionsָ���±�1�����ݣ�"+jedis.lset("collections", 1, "LinkedArrayList"));
        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));
        System.out.println("===============================");
        System.out.println("collections�ĳ��ȣ�"+jedis.llen("collections"));
        System.out.println("��ȡcollections�±�Ϊ2��Ԫ�أ�"+jedis.lindex("collections", 2));
        System.out.println("===============================");
        jedis.lpush("sortedList", "3","6","2","0","7","4");
        System.out.println("sortedList����ǰ��"+jedis.lrange("sortedList", 0, -1));
        System.out.println(jedis.sort("sortedList"));
        System.out.println("sortedList�����"+jedis.lrange("sortedList", 0, -1));
    }

    /***
     * set����
     */
    @Test
    public void testSet() {
        System.out.println("============�򼯺������Ԫ��============");
        System.out.println(jedis.sadd("eleSet", "e1","e2","e4","e3","e0","e8","e7","e5"));
        System.out.println(jedis.sadd("eleSet", "e6"));
        System.out.println(jedis.sadd("eleSet", "e6")); // ����0���������Ѿ�����
        System.out.println("eleSet������Ԫ��Ϊ��"+jedis.smembers("eleSet"));
        System.out.println("ɾ��һ��Ԫ��e0��"+jedis.srem("eleSet", "e0"));
        System.out.println("eleSet������Ԫ��Ϊ��"+jedis.smembers("eleSet"));
        System.out.println("ɾ������Ԫ��e7��e6��"+jedis.srem("eleSet", "e7","e6"));
        System.out.println("eleSet������Ԫ��Ϊ��"+jedis.smembers("eleSet"));
        System.out.println("������Ƴ������е�һ��Ԫ�أ�"+jedis.spop("eleSet"));
        System.out.println("������Ƴ������е�һ��Ԫ�أ�"+jedis.spop("eleSet"));
        System.out.println("eleSet������Ԫ��Ϊ��"+jedis.smembers("eleSet"));
        System.out.println("eleSet�а���Ԫ�صĸ�����"+jedis.scard("eleSet"));
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
        Map<String,String> map = new HashMap<String,String>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        jedis.hmset("hash",map);
        jedis.hset("hash", "key5", "value5");
        System.out.println("ɢ��hash�����м�ֵ��Ϊ��"+jedis.hgetAll("hash"));//return Map<String,String>
        System.out.println("ɢ��hash�����м�Ϊ��"+jedis.hkeys("hash"));//return Set<String>
        System.out.println("ɢ��hash������ֵΪ��"+jedis.hvals("hash"));//return List<String>
        System.out.println("��key6�����ֵ����һ�����������key6�����������key6��"+jedis.hincrBy("hash", "key6", 6));
        System.out.println("ɢ��hash�����м�ֵ��Ϊ��"+jedis.hgetAll("hash"));
        System.out.println("��key6�����ֵ����һ�����������key6�����������key6��"+jedis.hincrBy("hash", "key6", 3));
        System.out.println("ɢ��hash�����м�ֵ��Ϊ��"+jedis.hgetAll("hash"));
        System.out.println("ɾ��һ�����߶����ֵ�ԣ�"+jedis.hdel("hash", "key2"));
        System.out.println("ɢ��hash�����м�ֵ��Ϊ��"+jedis.hgetAll("hash"));
        System.out.println("ɢ��hash�м�ֵ�Եĸ�����"+jedis.hlen("hash"));
        System.out.println("�ж�hash���Ƿ����key2��"+jedis.hexists("hash","key2"));
        System.out.println("�ж�hash���Ƿ����key3��"+jedis.hexists("hash","key3"));
        System.out.println("��ȡhash�е�ֵ��"+jedis.hmget("hash","key3"));
        System.out.println("��ȡhash�е�ֵ��"+jedis.hmget("hash","key3","key4"));
    }

    /**
     * ���򼯺�
     */
    @Test
    public void testSortedSet() {
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
        jedis.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap", "LinkedHashMap");
        System.out.println("collections�����ݣ�"+jedis.lrange("collections", 0, -1));
        SortingParams sortingParameters = new SortingParams();
        // �����ݼ��б�������ַ���ֵʱ��������� ALPHA,Ĭ��������
        System.out.println("alpha����ʽ��" + jedis.sort("collections",sortingParameters.alpha()));
        System.out.println("===============================");
        jedis.lpush("sortedList", "3","6","2","0","7","4");
        System.out.println("sortedList����ǰ��"+jedis.lrange("sortedList", 0, -1));
        System.out.println("����"+jedis.sort("sortedList", sortingParameters.asc()));
        System.out.println("����"+jedis.sort("sortedList", sortingParameters.desc()));
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

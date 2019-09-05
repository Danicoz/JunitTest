package com.cattsoft.serialize;

import java.io.ByteArrayOutputStream;

import com.cattsoft.dbutil.bean.Address;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;


public class SerializeTest {

	private static final String JAVA_VENDOR = System.getProperty("java.vendor");
	/** IBM J9 vm **/
	private static final boolean IS_IBM_JAVA_VENDOR = JAVA_VENDOR.contains("IBM");
	/** 是否适用IBM jvm序列化方式 **/
	private boolean isIBMSerializeType = false;
	
	
	/**
	 * 序列化
	 * 
	 * @param obj
	 * @return
	 */
	private byte[] serialize(Object obj) {
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		Output output = new Output(bs);
		try {
			Kryo kryo = new Kryo();
			if (IS_IBM_JAVA_VENDOR || isIBMSerializeType) {
				// kryo.setAsmEnabled(true);
				kryo.setReferences(false);
			}
			output.setOutputStream(bs);
			kryo.writeObject(output, obj);
		} finally {
			output.flush();
			output.close();
		}
		return bs.toByteArray();
	}

	/**
	 * 反序列化
	 * 
	 * @param ba
	 * @param clazz
	 * @return
	 */
	private <T> T deserialize(byte[] ba, Class<T> clazz) {
		Input input = new Input();
		try {
			Kryo kryo = new Kryo();
			if (IS_IBM_JAVA_VENDOR || isIBMSerializeType) {
				// kryo.setAsmEnabled(true);
				kryo.setReferences(false);
			}
			input.setBuffer(ba);
			return kryo.readObject(input, clazz);
		} finally {
			input.close();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException  {
		SerializeTest main = new SerializeTest();
		String str = "Danicoz";
		byte[] b = main.serialize(str);
		System.out.println("b=" + b.toString());
		
		System.out.println(str.getClass().getName());
		
		String str1 = (String) main.deserialize(b, Class.forName(str.getClass().getName()));
		System.out.println("str1=" + str1);
		
		
		System.out.println(JAVA_VENDOR);
		
		Address address = new Address();
		address.setXh("33");
		address.setZz("广州");
		
		byte[] b1 = main.serialize(address);
		Address address2 = (Address) main.deserialize(b1, Class.forName(address.getClass().getName()));
		System.out.println("XH=" + address2.getXh() + "  ZZ=" + address2.getZz());
	}
	
	
}
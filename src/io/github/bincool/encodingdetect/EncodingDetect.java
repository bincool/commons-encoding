/**
* @FileName: EncodingDetect.java
* @Package: io.github.bincool.encodingdetect
* @Copyright: 2018 bincool.github.io Inc. All Rights Reserved.
* @Description: EncodingDetect.java: ������.
* @Author wchy����������(891946049).
* @Date 2018��8��2�� ����9:16:23.
* @Content: ����.
* @Version: V1.0.
*/
package io.github.bincool.encodingdetect;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.ByteOrderMarkDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

/**
* @ClassName: EncodingDetect.java
* 
* @Description: 
* <p>
* ������.
* </p>
* <p>
* ��ϸ����.
* </p>
* <p>
* ʾ������.
* </p>
*
* @Author: wchy����������(891946049).
* 
* @Date: 2018��8��2�� ����9:16:23.
* 
*/
public class EncodingDetect 
{
	
	/**
	 * ����ȡ�ֽ�:8.1<<8�����ƣ�ֵ��Ŵ�256������߲�8��0.
	 */
	private static final int MAX_READBYTE_FAST = 256;

	/**
	 * ˽�й��캯��.
	 */
	private EncodingDetect() 
	{
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * ʹ�ÿ��ٲ��Լ�����������룬���ɨ����8���ֽ�.
	 * @param buffIn
	 * 		������.InputStream buffIn = new BufferedInputStream(fInputStream);
	 * @return
	 * @throws IOException
	 */
	public static Charset getEncoding(InputStream buffIn) throws IOException 
	{
		return detectEncoding(buffIn, MAX_READBYTE_FAST, EncodingDetectStrategy.FAST);
	}
	
	/**
	 * ʹ�ÿ��ٲ��Լ���ֽڱ��룬���ɨ����8���ֽ�.
	 * @param byteArr
	 * 		�ֽ�.
	 * @return
	 * @throws IOException
	 */
	public static Charset getEncoding(byte[] byteArr) throws IOException 
	{
		return detectEncoding(byteArr, MAX_READBYTE_FAST, EncodingDetectStrategy.FAST);
	}
	
	/**
	 * ���url����.
	 * @param url
	 * 		url.
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static Charset getEncoding(URL url) throws IOException 
	{
		CodepageDetectorProxy detector = getDetector(EncodingDetectStrategy.OTHER);
		return detector.detectCodepage(url);
	}
	
	/**
	 * �������������,ɨ�����СΪ���������ô�С.
	 * @param buffIn
	 * 		������.InputStream buffIn = new BufferedInputStream(fInputStream);
	 * @param strategy
	 * 		������.
	 * @return
	 * @throws IOException
	 */
	public static Charset getEncoding(InputStream buffIn, EncodingDetectStrategy strategy) throws IOException 
	{
		return detectEncoding(buffIn, buffIn.available(), strategy);
	}
	
	/**
	 * ����ֽڱ���,ɨ�����СΪ�ֽڿ��ô�С.
	 * @param byteArr
	 * 		�ֽ�.
	 * @param strategy
	 * 		������.
	 * @return
	 * @throws IOException
	 */
	public static Charset getEncoding(byte[] byteArr, EncodingDetectStrategy strategy) throws IOException 
	{
		return detectEncoding(byteArr, byteArr.length, strategy);
	}
	
	/**
	 * �������������.
	 * @param in
	 * 		������.
	 * @param size
	 * 		��С.
	 * @param strategy
	 * 		���������.
	 * @return
	 * @throws IOException
	 */
	private static Charset detectEncoding(InputStream buffIn, int size, EncodingDetectStrategy strategy) throws IOException
	{
		Charset charset = null;
		size = getSmaller(size, buffIn.available());
		
		// if in support mark method, 
		if(buffIn.markSupported()) 
		{
			if(EncodingDetectStrategy.isFast(strategy)) 
			{
				size = getSmaller(size, MAX_READBYTE_FAST);
				buffIn.mark(size++);
			} 
			else 
			{
				buffIn.mark(size++);
			}
			
			charset = getDetector(strategy).detectCodepage(buffIn, size);
			buffIn.reset();
		} 
		else 
		{
			if(EncodingDetectStrategy.isFast(strategy)) 
			{
				size = getSmaller(size, MAX_READBYTE_FAST);
			}
			charset = getDetector(strategy).detectCodepage(buffIn, size);
		}
		
	    return charset;
	}
	
	/**
	 * ����ֽڱ���.
	 * @param byteArr
	 * 		�ֽ�.
	 * @param size
	 * 		��С.
	 * @param strategy
	 * 		���������.
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	private static Charset detectEncoding(byte[] byteArr, int size, EncodingDetectStrategy strategy) throws IOException 
	{
		size = getSmaller(size, byteArr.length);
		if (EncodingDetectStrategy.isFast(strategy)) 
		{
			size = getSmaller(size, MAX_READBYTE_FAST);
		} 
		
		InputStream bais = new ByteArrayInputStream(byteArr, 0, size);
		InputStream bis = new BufferedInputStream(bais);
		return getDetector(strategy).detectCodepage(bis, size);
	}
	
	/**
	 * <pre> 
	 * 1��cpDetector������һЩ���õ�̽��ʵ����,��Щ̽��ʵ�����ʵ������ͨ��add�����ӽ���, 
	 * ��:ParsingDetector�� JChardetFacade��ASCIIDetector��UnicodeDetector.  
	 * 2��detector���ա�˭���ȷ��طǿյ�̽����,���Ըý��Ϊ׼����ԭ��.  
	 * 3��cpDetector�ǻ���ͳ��ѧԭ���,����֤��ȫ��ȷ. 
	 * </pre>
	 * @param isFast
	 * 		������:true��ʾ���ټ�⣬false��ʾ�������.
	 * @return
	 */
	private static CodepageDetectorProxy getDetector(EncodingDetectStrategy strategy) 
	{
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		
		
		detector.add(new ByteOrderMarkDetector());
		// �ڲ�������chardet.jar.
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		
		if (EncodingDetectStrategy.isFast(strategy)) 
		{
			detector.add(UnicodeDetector.getInstance());
		} 
		else if (EncodingDetectStrategy.isNormal(strategy)) 
		{
			detector.add(new ParsingDetector(false));
		}
		else 
		{
			detector.add(new ParsingDetector(false));
			detector.add(UnicodeDetector.getInstance());
		}
		
		return detector;
	}
	
	/**
	 * ȡa��b�еĽ�Сֵ.
	 * @param a
	 * 		����.
	 * @param b
	 * 		����.
	 * @return
	 */
	private static int getSmaller(int a, int b) 
	{
		return a < b ? a : b;
	}
	
}

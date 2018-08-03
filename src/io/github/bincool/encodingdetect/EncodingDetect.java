/**
* @FileName: EncodingDetect.java
* @Package: io.github.bincool.encodingdetect
* @Copyright: 2018 bincool.github.io Inc. All Rights Reserved.
* @Description: EncodingDetect.java: 编码检测.
* @Author wchy，技术交流(891946049).
* @Date 2018年8月2日 下午9:16:23.
* @Content: 新增.
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
* 编码检测.
* </p>
* <p>
* 详细描述.
* </p>
* <p>
* 示例代码.
* </p>
*
* @Author: wchy，技术交流(891946049).
* 
* @Date: 2018年8月2日 下午9:16:23.
* 
*/
public class EncodingDetect 
{
	
	/**
	 * 最大读取字节:8.1<<8是左移，值会放大256倍，左边补8个0.
	 */
	private static final int MAX_READBYTE_FAST = 256;

	/**
	 * 私有构造函数.
	 */
	private EncodingDetect() 
	{
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * 使用快速策略检测输入流编码，最多扫描检测8个字节.
	 * @param buffIn
	 * 		输入流.InputStream buffIn = new BufferedInputStream(fInputStream);
	 * @return
	 * @throws IOException
	 */
	public static Charset getEncoding(InputStream buffIn) throws IOException 
	{
		return detectEncoding(buffIn, MAX_READBYTE_FAST, EncodingDetectStrategy.FAST);
	}
	
	/**
	 * 使用快速策略检测字节编码，最多扫描检测8个字节.
	 * @param byteArr
	 * 		字节.
	 * @return
	 * @throws IOException
	 */
	public static Charset getEncoding(byte[] byteArr) throws IOException 
	{
		return detectEncoding(byteArr, MAX_READBYTE_FAST, EncodingDetectStrategy.FAST);
	}
	
	/**
	 * 检测url编码.
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
	 * 检测输入流编码,扫描检测大小为输入流可用大小.
	 * @param buffIn
	 * 		输入流.InputStream buffIn = new BufferedInputStream(fInputStream);
	 * @param strategy
	 * 		检测策略.
	 * @return
	 * @throws IOException
	 */
	public static Charset getEncoding(InputStream buffIn, EncodingDetectStrategy strategy) throws IOException 
	{
		return detectEncoding(buffIn, buffIn.available(), strategy);
	}
	
	/**
	 * 检测字节编码,扫描检测大小为字节可用大小.
	 * @param byteArr
	 * 		字节.
	 * @param strategy
	 * 		检测策略.
	 * @return
	 * @throws IOException
	 */
	public static Charset getEncoding(byte[] byteArr, EncodingDetectStrategy strategy) throws IOException 
	{
		return detectEncoding(byteArr, byteArr.length, strategy);
	}
	
	/**
	 * 检测输入流编码.
	 * @param in
	 * 		输入流.
	 * @param size
	 * 		大小.
	 * @param strategy
	 * 		编码检测策略.
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
	 * 检测字节编码.
	 * @param byteArr
	 * 		字节.
	 * @param size
	 * 		大小.
	 * @param strategy
	 * 		编码检测策略.
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
	 * 1、cpDetector内置了一些常用的探测实现类,这些探测实现类的实例可以通过add方法加进来, 
	 * 如:ParsingDetector、 JChardetFacade、ASCIIDetector、UnicodeDetector.  
	 * 2、detector按照“谁最先返回非空的探测结果,就以该结果为准”的原则.  
	 * 3、cpDetector是基于统计学原理的,不保证完全正确. 
	 * </pre>
	 * @param isFast
	 * 		检测策略:true表示快速检测，false表示正常检测.
	 * @return
	 */
	private static CodepageDetectorProxy getDetector(EncodingDetectStrategy strategy) 
	{
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		
		
		detector.add(new ByteOrderMarkDetector());
		// 内部引用了chardet.jar.
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
	 * 取a和b中的较小值.
	 * @param a
	 * 		整数.
	 * @param b
	 * 		整数.
	 * @return
	 */
	private static int getSmaller(int a, int b) 
	{
		return a < b ? a : b;
	}
	
}

/**
* @FileName: EncodingDetectTest.java
* @Package: io.github.bincool.test
* @Copyright: 2018 bincool.github.io Inc. All Rights Reserved.
* @Description: EncodingDetectTest.java: 编码检测测试类.
* @Author wchy，技术交流(891946049).
* @Date 2018年8月3日 上午11:49:27.
* @Content: 新增.
* @Version: V1.0.
*/
package io.github.bincool.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;

import io.github.bincool.encodingdetect.EncodingDetect;
import io.github.bincool.encodingdetect.EncodingDetectStrategy;
import io.github.bincool.test.base.BaseTest;

/**
* @ClassName: EncodingDetectTest.java
* 
* @Description: 
* <p>
* 编码检测测试类.
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
* @Date: 2018年8月3日 上午11:49:27.
* 
*/
public class EncodingDetectTest extends BaseTest 
{
	
	/**
	 * url.
	 */
	private static final String URL = "http://www.runoob.com/index.html?language=cn#j2se";
	
	/**
	 * 文件路径.
	 */
	private static final String FILE_PATH = System.getProperty("user.dir") + "\\file\\MakeRandCode.java";
	
	/* (non-Javadoc)
	 * @see io.github.bincool.test.base.BaseTest#setUp()
	 */
	@Override
	public void setUp() 
	{
		LOGGER.info("setUp");
	}

	/* (non-Javadoc)
	 * @see io.github.bincool.test.base.BaseTest#tearDown()
	 */
	@Override
	public void tearDown() 
	{
		LOGGER.info("tearDown");
	}

	/* (non-Javadoc)
	 * @see io.github.bincool.test.base.BaseTest#test()
	 */
	@Override
	public void test() throws Exception 
	{
		URL url = new URL(URL);
		Charset charset = EncodingDetect.getEncoding(url);
		LOGGER.info(charset.name());
		
		byte[] bytes = toByteArray3(FILE_PATH);
		LOGGER.info(EncodingDetect.getEncoding(bytes).name());
		LOGGER.info(EncodingDetect.getEncoding(bytes, EncodingDetectStrategy.FAST).name());
		LOGGER.info(EncodingDetect.getEncoding(bytes, EncodingDetectStrategy.NORMAL).name());
		LOGGER.info(EncodingDetect.getEncoding(bytes, EncodingDetectStrategy.OTHER).name());
		
		File file = new File(FILE_PATH);
		InputStream fInputStream = new FileInputStream(file);
		InputStream buffIn = new BufferedInputStream(fInputStream);
		LOGGER.info(EncodingDetect.getEncoding(buffIn).name());
		LOGGER.info(EncodingDetect.getEncoding(buffIn, EncodingDetectStrategy.FAST).name());
		LOGGER.info(EncodingDetect.getEncoding(buffIn, EncodingDetectStrategy.NORMAL).name());
		LOGGER.info(EncodingDetect.getEncoding(buffIn, EncodingDetectStrategy.OTHER).name());
		
		LOGGER.info(1 << 8);
	}
	
	/**
	 * 读取文件内容到byte[]中.
	 * @param filename
	 * 		文件名.
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray3(String filename) throws IOException 
	{
		try (FileChannel fc = new RandomAccessFile(filename, "r").getChannel()) 
		{
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size()).load();
			LOGGER.info(byteBuffer.isLoaded());
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) 
			{
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}

			return result;
		} 
		catch (IOException e) 
		{
			throw new IOException();
		}
    }
	
}

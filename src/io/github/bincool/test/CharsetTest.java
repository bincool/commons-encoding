/**
* @FileName: CharsetTest.java
* @Package: io.github.bincool.test
* @Copyright: 2018 bincool.github.io Inc. All Rights Reserved.
* @Description: CharsetTest.java: 可用字符集测试.
* @Author wchy，技术交流(891946049).
* @Date 2018年8月2日 下午8:17:22.
* @Content: 新增.
* @Version: V1.0.
*/
package io.github.bincool.test;

import java.nio.charset.Charset;
import java.util.Set;

import io.github.bincool.test.base.BaseTest;

/**
* @ClassName: CharsetTest.java
* 
* @Description: 
* <p>
* 可用字符集测试.
* </p>
* <p>
* 详细描述.
* </p>
* <p>
* 示例代码:
* for (String value : charsets) 
* {
*     System.out.println(TEMPLATE_ANNOTATION.replace("{value}", value));
*     System.out.println(TEMPLATE_CODE.replace("{variable}", value.replace("-", "_").toUpperCase()).replace("{value}", value));
* }.
* </p>
*
* @Author: wchy，技术交流(891946049).
* 
* @Date: 2018年8月2日 下午8:17:22.
* 
*/
public class CharsetTest extends BaseTest 
{
	
	/**
	 * 注释模版.
	 */
	private static final String TEMPLATE_ANNOTATION = "	\r\n	/**\r\n	 * 字符集编码:{value}.\r\n	 */";

	/**
	 * 代码模版.
	 */
	private static final String TEMPLATE_CODE = "	public static final String {variable} = \"{value}\";";
	
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
		// 获得Java支持编码集合：
		Set<String> charsets = Charset.availableCharsets().keySet();
		LOGGER.info(charsets);

		// 获得系统默认编码：
		Charset charset = Charset.defaultCharset();
		LOGGER.info(charset);

		LOGGER.info(TEMPLATE_ANNOTATION);

		LOGGER.info(TEMPLATE_CODE);
	}

}

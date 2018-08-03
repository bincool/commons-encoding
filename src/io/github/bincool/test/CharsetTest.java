/**
* @FileName: CharsetTest.java
* @Package: io.github.bincool.test
* @Copyright: 2018 bincool.github.io Inc. All Rights Reserved.
* @Description: CharsetTest.java: �����ַ�������.
* @Author wchy����������(891946049).
* @Date 2018��8��2�� ����8:17:22.
* @Content: ����.
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
* �����ַ�������.
* </p>
* <p>
* ��ϸ����.
* </p>
* <p>
* ʾ������:
* for (String value : charsets) 
* {
*     System.out.println(TEMPLATE_ANNOTATION.replace("{value}", value));
*     System.out.println(TEMPLATE_CODE.replace("{variable}", value.replace("-", "_").toUpperCase()).replace("{value}", value));
* }.
* </p>
*
* @Author: wchy����������(891946049).
* 
* @Date: 2018��8��2�� ����8:17:22.
* 
*/
public class CharsetTest extends BaseTest 
{
	
	/**
	 * ע��ģ��.
	 */
	private static final String TEMPLATE_ANNOTATION = "	\r\n	/**\r\n	 * �ַ�������:{value}.\r\n	 */";

	/**
	 * ����ģ��.
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
		// ���Java֧�ֱ��뼯�ϣ�
	    Set<String> charsets = Charset.availableCharsets().keySet();
	    LOGGER.info(charsets);
	    
	    // ���ϵͳĬ�ϱ��룺
	    Charset charset = Charset.defaultCharset();
	    LOGGER.info(charset);
	    
	    LOGGER.info(TEMPLATE_ANNOTATION);
    	LOGGER.info(TEMPLATE_CODE);
	}

}

/**
* @FileName: BaseTest.java
* @Package: io.github.bincool.test.base
* @Copyright: 2018 bincool.github.io Inc. All Rights Reserved.
* @Description: BaseTest.java: 测试基类.
* @Author wchy，技术交流(891946049).
* @Date 2018年8月3日 下午12:15:23.
* @Content: 新增.
* @Version: V1.0.
*/
package io.github.bincool.test.base;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
* @ClassName: BaseTest.java
* 
* @Description: 
* <p>
* 测试基类.
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
* @Date: 2018年8月3日 下午12:15:23.
* 
*/
public abstract class BaseTest 
{
	
	/**
	 * 日志对象.
	 */
	protected static final Logger LOGGER = Logger.getLogger(BaseTest.class);

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public abstract void setUp() throws Exception;

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public abstract void tearDown() throws Exception;

	@Test
	public abstract void test() throws Exception;

}

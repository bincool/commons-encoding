/**
* @FileName: EncodingDetectStrategy.java
* @Package: io.github.bincool.encodingdetect
* @Copyright: 2018 bincool.github.io Inc. All Rights Reserved.
* @Description: EncodingDetectStrategy.java: 编码检测策略.
* @Author wchy，技术交流(891946049).
* @Date 2018年8月3日 上午10:40:44.
* @Content: 新增.
* @Version: V1.0.
*/
package io.github.bincool.encodingdetect;

/**
* @ClassName: EncodingDetectStrategy.java
* 
* @Description: 
* <p>
* 编码检测策略.
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
* @Date: 2018年8月3日 上午10:40:44.
* 
*/
public class EncodingDetectStrategy 
{

	/**
	 * 私有构造函数.
	 */
	private EncodingDetectStrategy() 
	{
	}

	/**
	 * 编码检测策略:快速Fast.
	 */
	public static final EncodingDetectStrategy FAST = new EncodingDetectStrategy();

	/**
	 * 编码检测策略:正常Normal.
	 */
	public static final EncodingDetectStrategy NORMAL = new EncodingDetectStrategy();

	/**
	 * 编码检测策略:其他Other.
	 */
	public static final EncodingDetectStrategy OTHER = new EncodingDetectStrategy();

	/**
	 * 判断编码检测策略是否为快速.
	 * @param strategy
	 * 		编码检测策略.
	 * @return
	 */
	public static final boolean isFast(EncodingDetectStrategy strategy) 
	{
		return strategy.equals(EncodingDetectStrategy.FAST);
	}

	/**
	 * 判断编码检测策略是否为正常.
	 * @param strategy
	 * 		编码检测策略.
	 * @return
	 */
	public static final boolean isNormal(EncodingDetectStrategy strategy) 
	{
		return strategy.equals(EncodingDetectStrategy.NORMAL);
	}

	/**
	 * 判断编码检测策略是否为其它:非快速和正常.
	 * @param strategy
	 * 		编码检测策略.
	 * @return
	 */
	public static final boolean isOther(EncodingDetectStrategy strategy) 
	{
		return null == strategy || strategy.equals(EncodingDetectStrategy.OTHER);
	}

}

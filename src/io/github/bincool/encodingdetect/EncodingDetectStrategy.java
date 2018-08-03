/**
* @FileName: EncodingDetectStrategy.java
* @Package: io.github.bincool.encodingdetect
* @Copyright: 2018 bincool.github.io Inc. All Rights Reserved.
* @Description: EncodingDetectStrategy.java: ���������.
* @Author wchy����������(891946049).
* @Date 2018��8��3�� ����10:40:44.
* @Content: ����.
* @Version: V1.0.
*/
package io.github.bincool.encodingdetect;

/**
* @ClassName: EncodingDetectStrategy.java
* 
* @Description: 
* <p>
* ���������.
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
* @Date: 2018��8��3�� ����10:40:44.
* 
*/
public class EncodingDetectStrategy 
{
	
	/**
	 * ˽�й��캯��.
	 */
	private EncodingDetectStrategy() 
	{
	}
	
	/**
     * ���������:����Fast.
     */
    public static final EncodingDetectStrategy FAST = new EncodingDetectStrategy();
    
    /**
     * ���������:����Normal.
     */
    public static final EncodingDetectStrategy NORMAL = new EncodingDetectStrategy();
    
    /**
     * ���������:����Other.
     */
    public static final EncodingDetectStrategy OTHER = new EncodingDetectStrategy();
    
    /**
     * �жϱ���������Ƿ�Ϊ����.
     * @param strategy
     * 		���������.
     * @return
     */
    public static final boolean isFast(EncodingDetectStrategy strategy) 
	{
		return strategy.equals(EncodingDetectStrategy.FAST);
	}
	
    /**
     * �жϱ���������Ƿ�Ϊ����.
     * @param strategy
     * 		���������.
     * @return
     */
    public static final boolean isNormal(EncodingDetectStrategy strategy) 
	{
		return strategy.equals(EncodingDetectStrategy.NORMAL);
	}
    
    /**
     * �жϱ���������Ƿ�Ϊ����:�ǿ��ٺ�����.
     * @param strategy
     * 		���������.
     * @return
     */
    public static final boolean isOther(EncodingDetectStrategy strategy) 
	{
		return null == strategy || strategy.equals(EncodingDetectStrategy.OTHER);
	}
	
}

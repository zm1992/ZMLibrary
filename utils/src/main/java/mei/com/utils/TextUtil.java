package mei.com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文本操作工具
 * 
 * @author Administrator
 * 
 */
public class TextUtil {

	private Pattern mDoubleByte_Pattern;
	private static TextUtil instance;

	private TextUtil() {
		super();

	}

	public static TextUtil getInstance() {
		if (instance == null) {
			synchronized (TextUtil.class) {
				if (instance == null) {
					synchronized (TextUtil.class) {
						instance = new TextUtil();
					}
				}
			}
		}
		return instance;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param s
	 * @return
	 */
	public boolean isEmpty(String s) {
		boolean flag = false;
		if (s == null || "".equals(s)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 判断两个字符串是否相同
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean isSame(String s1, String s2) {
		boolean flag = false;
		if (s1.equals(s2)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public int countAsDoubleByteWithString(String string) {
		int singlebyte = 0;
		int doublebyte = 0;

		Pattern doublebyePattern = getDoubleBytePattern();
		Matcher matcher = doublebyePattern.matcher(string);

		while (matcher.find()) {
			doublebyte++;
		}

		singlebyte = string.length() - doublebyte;
		if (singlebyte % 2 != 0) {
			doublebyte += (singlebyte + 1) / 2;
		} else {
			doublebyte += singlebyte / 2;
		}

		return doublebyte;
	}

	/**
	 * 
	 * @return
	 */
	private Pattern getDoubleBytePattern() {
		if (mDoubleByte_Pattern == null) {
			mDoubleByte_Pattern = Pattern.compile("[^\\x00-\\xff]");
		}
		return mDoubleByte_Pattern;
	}
}

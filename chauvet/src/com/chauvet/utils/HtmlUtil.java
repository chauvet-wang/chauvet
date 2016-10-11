package com.chauvet.utils;

import org.apache.commons.lang.StringUtils;

/***
 * HTML
 * @author WXW
 *
 */
public class HtmlUtil {

	/**
     * HTML字符转义
     * @see 对输入参数中的敏感字符进行过滤替换,防止用户利用JavaScript等方式输入恶意代码
     * @see String input = <img src='http://t1.baidu.com/it/fm=0&gp=0.jpg'/>
     * @see HtmlUtils.htmlEscape(input);         //from spring.jar
     * @see StringEscapeUtils.escapeHtml(input); //from commons-lang.jar
     * @see 尽管Spring和Apache都提供了字符转义的方法,但Apache的StringEscapeUtils功能要更强大一些
     * @see StringEscapeUtils提供了对HTML,Java,JavaScript,SQL,XML等字符的转义和反转义
     * @see 但二者在转义HTML字符时,都不会对单引号和空格进行转义,而本方法则提供了对它们的转义
     * @return String 过滤后的字符串
     */
    public static String htmlEscape(String str) {
        if(StringUtils.isBlank(str)){
            return str;
        }
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll(" ", "&nbsp;");
        str = str.replaceAll("'", "&#39;");   //IE暂不支持单引号的实体名称,而支持单引号的实体编号,故单引号转义成实体编号,其它字符转义成实体名称
        str = str.replaceAll("\"", "&quot;"); //双引号也需要转义，所以加一个斜线对其进行转义
        str = str.replaceAll("\n", "<br/>");  //不能把\n的过滤放在前面，因为还要对<和>过滤，这样就会导致<br/>失效了
        return str;
    }
    
    public static void main(String[] args) {
		
    	 String str = "<img src='http://t1.baidu.com/it/fm=0&gp=0.jpg'/>";
    	 System.out.println(HtmlUtil.htmlEscape(str));
	}
}

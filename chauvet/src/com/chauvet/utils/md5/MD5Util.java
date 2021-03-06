package com.chauvet.utils.md5;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

import com.chauvet.utils.BaseUtil;

/***
 * MD5
 * @author WXW
 *
 */
public class MD5Util {
	private final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_.";
	private final String ikey = "-x6g6ZWm2G9g_vr0Bo.pOq3kRIxwangxiaowei";
	private static String key = "myNameIsChauvet20160824";// 盐
	
	/**
	 * 加密部分  加盐
	 * @param txt
	 * @param key
	 * @return
	 */
	public String encrypt(String txt,String key){
		Random random = new Random();
		int nh1 = Math.abs(random.nextInt(64));
        int nh2 = Math.abs(random.nextInt(64));
        int nh3 = Math.abs(random.nextInt(64));
        char ch1 = chars.toCharArray()[nh1];
        char ch2 = chars.toCharArray()[nh2];
        char ch3 = chars.toCharArray()[nh3];
        int nhnum = nh1 + nh2 + nh3;
        int knum = 0;
        for(int i=0;i<key.length();i++)
			knum +=(int)key.toCharArray()[i];
        String mdKey = null;
		try {
			mdKey = MD5.MD5Encode(MD5.MD5Encode(MD5.MD5Encode(key+ch1)+ch2+ikey)+ch3).substring((int)(nhnum%8),(int)(nhnum%8)+(int)(knum%8 + 16));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        txt = BaseUtil.Base64Encode(txt);
        txt = txt.replace('+','-');txt = txt.replace('/','_');txt = txt.replace('=','.');
        String tmp = "";
        int j=0;int k = 0;
        int tlen = txt.length();
        int klen = mdKey.length();
        for (int i=0; i<tlen; i++) {
            k = k == klen ? 0 : k;
            j = (nhnum+chars.indexOf(txt.toCharArray()[i])+((int)(mdKey.toCharArray()[k++])))%64;
            tmp += chars.toCharArray()[j];
        }
        int tmplen = tmp.length();
        StringBuffer br = new StringBuffer();
        br.append(tmp);
        br.insert(nh2 % ++tmplen, ch3);
        tmp = br.toString();
        StringBuffer br2 = new StringBuffer();
        br2.append(tmp);
        br2.insert(nh1 % ++tmplen, ch2);
        tmp = br2.toString();
        StringBuffer br3 = new StringBuffer();
        br3.append(tmp);
        br3.insert(knum % ++tmplen, ch1);
        tmp = br3.toString();

		return tmp;
	}
	/**
	 * 解密部分
	 * @param txt
	 * @param key
	 * @return
	 */
	public String decrypt(String txt,String key){
		int knum = 0;
        int tlen = txt.length();
        
        for(int i=0;i<key.length();i++)
			knum +=(int)key.toCharArray()[i];
        
        char ch1 = txt.toCharArray()[knum % tlen];
        int nh1 = chars.indexOf(ch1); 
        
        StringBuffer br = new StringBuffer();
        br.append(txt);
        int ttt1 = knum % tlen--;
        txt = br.replace(ttt1, ttt1+1, "").toString();
        
        char ch2 = txt.toCharArray()[nh1 % tlen];
        int nh2 = chars.indexOf(ch2);
        StringBuffer br2 = new StringBuffer();
        br2.append(txt);
        int ttt2 = nh1 % tlen--;
        txt = br2.replace(ttt2, ttt2+1, "").toString();
        
        char ch3 = txt.toCharArray()[nh2 % tlen];
        int nh3 = chars.indexOf(ch3);
        StringBuffer br3 = new StringBuffer();
        br3.append(txt);
        int ttt3 = nh2 % tlen--;
        txt = br3.replace(ttt3, ttt3+1, "").toString();
        
        int nhnum = nh1 + nh2 + nh3;
        
        String mdKey = null;
		try {
			mdKey = MD5.MD5Encode(MD5.MD5Encode(MD5.MD5Encode(key+ch1)+ch2+ikey)+ch3).substring((int)(nhnum % 8),(int)(nhnum % 8)+(int)(knum % 8 + 16));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
      
        String tmp = "";
        int j=0; int k = 0;
        tlen = txt.length();
        int klen = mdKey.length();
        for (int i=0; i<tlen; i++) {
            k = k == klen ? 0 : k;
            j = chars.indexOf(txt.toCharArray()[i])-nhnum - ((int)(mdKey.toCharArray()[k++]));
            while (j<0) 
            	j+=64;
            tmp += chars.toCharArray()[j];
        }
        tmp = tmp.replace('-','+');tmp = tmp.replace('_','/');tmp = tmp.replace('.','=');
        tmp = BaseUtil.Base64Decode(tmp);
        tmp = tmp.replace((char)0, ' ').replace(" ", "");
		return tmp;
	}
	
	public static void main(String[] args) {
		String a = new MD5Util().encrypt("wxw@chauvet.2016", key);
		System.out.println(a);
		String b = new MD5Util().decrypt("HvLV0ICDQNP_fUON5pDdqcp3d48", key);
		System.out.println(b);
	}

}

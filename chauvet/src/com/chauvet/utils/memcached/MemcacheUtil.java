package com.chauvet.utils.memcached;

import com.chauvet.utils.ConfigUtil;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcacheUtil {
	
    private static MemcacheUtil util;
    private static MemCachedClient cache = new MemCachedClient();       

    private MemcacheUtil() {
    }

    public static MemcacheUtil getInstance() {
        if (util == null) {
            util = new MemcacheUtil();
        }
        return util;
    }
     
           
    static {
    	ConfigUtil configUtil = ConfigUtil.getInstance();
    	String memcacheServer = configUtil.getMemcacheServer(); // 从配置文件中获取memcache服务器地址
    	System.out.println(memcacheServer);
    	
    	String[] servers ={// 设置缓存服务器列表，当使用分布式缓存的时，可以指定多个缓存服务器。
        			memcacheServer
        			/**
        			 * IP:端口，
        			 * IP:端口，
        			 * IP:端口
        			 */
        		};       
       
        Integer[] weights = { 3 }; // 设置服务器权重
       
        //创建一个实例对象SockIOPool     
        SockIOPool pool = SockIOPool.getInstance();       
       
        // 设置servers 和  weights    
        pool.setServers( servers );       
        pool.setWeights( weights );       
       
        // set some basic pool settings       
        // 5 initial, 5 min, and 250 max conns       
        // and set the max idle time for a conn       
        // to 6 hours       
        pool.setInitConn( 5 );       
        pool.setMinConn( 5 );       
        pool.setMaxConn( 250 );       
        pool.setMaxIdle( 1000 * 60 * 60 * 6 );       
       
        // set the sleep for the maint thread       
        // it will wake up every x seconds and       
        // maintain the pool size       
        pool.setMaintSleep( 30 );       
       
		// Tcp的规则就是在发送一个包之前，本地机器会等待远程主机    
		// 对上一次发送的包的确认信息到来；这个方法就可以关闭套接字的缓存，    
		// 以至这个包准备好了就发；    
		pool.setNagle( false );       
        //连接建立后对超时的控制    
        pool.setSocketTO( 3000 );    
        //连接建立时对超时的控制    
        pool.setSocketConnectTO( 0 );       
       
        // initialize the connection pool       
        //初始化一些值并与MemutildServer段建立连接    
        pool.initialize();    
               
       
        // lets set some compression on for the client       
        // compress anything larger than 64k       
		//cache.setCompressEnable( true );       
		//cache.setCompressThreshold( 64 * 1024 );       
    }       
    
    public static void buildCacheWithKey(String key,String value){
    	cache.set(key, value); // 将key存入缓存
    }
    
    public static void bulidCache(){       
        //set(key,value,Date) ,Date是一个过期时间，如果想让这个过期时间生效的话，这里传递的new Date(long date) 中参数date，需要是个大于或等于1000的值。    
        //因为java client的实现源码里是这样实现的 expiry.getTime() / 1000 ，也就是说，如果 小于1000的值，除以1000以后都是0，即永不过期    
        cache.set("DEVICE_NETWORK_TYPE:01A111201406010214", "bulidCacheSuccess",0);   
    }
    
    /** 清空cache**/
    public static void flushAll(){
    	cache.flushAll();
    }
    
    public Object getValue(String key) {      
    	return cache.get(key);
    }       
    
    public static void main(String[] args){
    	 flushAll();
    	 bulidCache();
		//MemCachedUtil.buildCacheWithKey("wwwwwww");
		Object obj = MemcacheUtil.getInstance().getValue("DEVICE_NETWORK_TYPE:01A111201406010214");
		System.out.println(obj);
    }     

}

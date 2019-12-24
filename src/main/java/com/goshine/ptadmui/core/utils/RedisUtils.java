package com.goshine.ptadmui.core.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
/**
 * Redis工具类
 * @author goshine
 */
public class RedisUtils{
	private Logger logger=LoggerFactory.getLogger(RedisUtils.class);
    private RedisTemplate<String, Object> redisTemplate;  
    
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
   
    /** 
     * 指定缓存失效时间 
     * @param key 键 
     * @param time 时间(秒) 
     * @return 
     */  
    public boolean expire(String key,long time){  
        try {  
            if(time>0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);  
            }
            return true;  
        }catch(Exception e){  
        	logger.debug("RedisUtils expire error:"+e.getMessage());
        }
        return false;
    }  

    /** 
     * 根据key 获取过期时间 
     * @param key 键 不能为null 
     * @return 时间(秒) 返回0代表为永久有效 
     */  
    public long getExpire(String key){
    	try{
            return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    	}catch(Exception e){  
        	logger.debug("RedisUtils getExpire error:"+e.getMessage());
        }
    	return 0;
    }  

    /** 
     * 判断key是否存在 
     * @param key 键 
     * @return true 存在 false不存在 
     */  
    public boolean hasKey(String key){  
        try{  
            return redisTemplate.hasKey(key);  
        }catch(Exception e){  
        	logger.debug("RedisUtils hasKey error:"+e.getMessage());
        }
        return false;
    }  

    /** 
     * 删除缓存 
     * @param key 可以传一个值 或多个 
     */  
    @SuppressWarnings("unchecked")  
    public void del(String ... key){  
        if(key!=null&&key.length>0){  
            if(key.length==1){  
                redisTemplate.delete(key[0]);  
            }else{  
                redisTemplate.delete(CollectionUtils.arrayToList(key));  
            }  
        }  
    }  

    /** 
     * 普通缓存获取 
     * @param key 键 
     * @return 值 
     */  
    public Object get(String key){  
        return key==null?null:redisTemplate.opsForValue().get(key);  
    }

    /** 
     * 普通缓存放入并设置时间 
     * @param key 键 
     * @param value 值 
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期 
     * @return true成功 false 失败 
     */  
    public boolean set(String key,Object value,long time){  
        try {  
            if(time>0){  
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);  
            }else{
            	redisTemplate.opsForValue().set(key, value);  
            }  
            return true;  
        } catch (Exception e) {  
        	logger.debug("RedisUtils set string with expire time error:"+e.getMessage());
        }
        return false;
    }  

    /** 
     * 递增 
     * @param key 键 
     * @param by 要增加几(大于0) 
     * @return 
     */  
    public long increment(String key,long delta){    
        if(delta==0){  
            throw new RuntimeException("递增因子不能等于0");  
        }  
        return redisTemplate.opsForValue().increment(key,delta);  
    }  

    /** 
     * HashGet 
     * @param key 键 不能为null 
     * @param item 项 不能为null 
     * @return 值 
     */  
    public Object getFromHash(String key,String item){  
        return redisTemplate.opsForHash().get(key,item);  
    }  

    /** 
     * 获取hashKey对应的所有键值 
     * @param key 键 
     * @return 对应的多个键值 
     */  
    public Map<Object,Object> getFromHash(String key){  
        return redisTemplate.opsForHash().entries(key);  
    }  

    /** 
     * HashSet 
     * @param key 键 
     * @param map 对应多个键值 
     * @return true 成功 false 失败 
     */  
    public boolean setToHash(String key, Map<String,Object> map){    
        try {  
            redisTemplate.opsForHash().putAll(key, map);  
            return true;  
        }catch(Exception e){  
        	logger.debug("RedisUtils setMapToHash no expire time error:"+e.getMessage());
        }
        return false;
    }  

    /** 
     * HashSet 并设置时间 
     * @param key 键 
     * @param map 对应多个键值 
     * @param time 时间(秒) 
     * @return true成功 false失败 
     */  
    public boolean setToHash(String key, Map<String,Object> map, long time){    
        try {  
            redisTemplate.opsForHash().putAll(key,map);  
            if(time>0){  
                expire(key, time);  
            }  
            return true;  
        }catch(Exception e){  
        	logger.debug("RedisUtils setToHash with expire time error:"+e.getMessage());
        }
        return false;
    }  

    /** 
     * 向一张hash表中放入数据,如果不存在将创建 
     * @param key 键 
     * @param item 项 
     * @param value 值 
     * @return true 成功 false失败 
     */  
    public boolean setToHash(String key,String item,Object value) {  
         try {  
            redisTemplate.opsForHash().put(key, item, value);  
            return true;  
        }catch(Exception e){  
        	logger.debug("RedisUtils setToHash no expire time error:"+e.getMessage());
        }
         return false;
    }  

    /** 
     * 向一张hash表中放入数据,如果不存在将创建 
     * @param key 键 
     * @param item 项 
     * @param value 值 
     * @param time 时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间 
     * @return true 成功 false失败 
     */  
    public boolean setToHash(String key,String item,Object value,long time) {  
         try{
            redisTemplate.opsForHash().put(key, item, value);  
            if(time>0){  
                expire(key, time);  
            }  
            return true;  
        }catch(Exception e){  
        	logger.debug("RedisUtils setToHash with expire time error:"+e.getMessage());
        }
        return false;
    }  

    /** 
     * 删除hash表中的值 
     * @param key 键 不能为null 
     * @param item 项 可以使多个 不能为null 
     */  
    public void removeFromHash(String key, Object... item){    
        redisTemplate.opsForHash().delete(key,item);  
    }   

    /** 
     * 判断hash表中是否有该项的值 
     * @param key 键 不能为null 
     * @param item 项 不能为null 
     * @return true 存在 false不存在 
     */  
    public boolean hasKeyFromHash(String key, String item){  
        return redisTemplate.opsForHash().hasKey(key, item);  
    } 

    /** 
     * hash递减 
     * @param key 键 
     * @param item 项 
     * @param by 要增加或者减少的，增加为正，减少为负 
     * @return 
     */  
    public double incrementHash(String key, String item,double by){    
        return redisTemplate.opsForHash().increment(key,item,by);    
    }    

    /** 
     * 根据key获取Set中的所有值 
     * @param key 键 
     * @return 
     */  
    public Set<Object> getFromSet(String key){  
        try {  
            return redisTemplate.opsForSet().members(key);  
        } catch (Exception e) {  
        	logger.debug("RedisUtils getFromSet error:"+e.getMessage());
        }
        return null;
    }  

    /** 
     * 根据value从一个set中查询,是否存在 
     * @param key 键 
     * @param value 值 
     * @return true 存在 false不存在 
     */  
    public boolean hasFromSet(String key,Object value){  
        try{
            return redisTemplate.opsForSet().isMember(key,value);  
        }catch(Exception e){  
        	logger.debug("RedisUtils hasFromSet error:"+e.getMessage());
        }
        return false;
    }  

    /** 
     * 将数据放入set缓存 
     * @param key 键 
     * @param values 值 可以是多个 
     * @return 成功个数 
     */  
    public long setAllToSet(String key,Object...values) {  
        try {  
            return redisTemplate.opsForSet().add(key, values);  
        } catch (Exception e) {  
        	logger.debug("RedisUtils setAllToSet no expire time error:"+e.getMessage());
        }
        return 0;
    }  

    /** 
     * 将set数据放入缓存 
     * @param key 键 
     * @param time 时间(秒) 
     * @param values 值 可以是多个 
     * @return 成功个数 
     */  
    public long setAllToSet(String key,long time,Object...values) {  
        try {  
            Long count = redisTemplate.opsForSet().add(key, values);  
            if(time>0) expire(key, time);  
            return count;
        } catch (Exception e) {  
        	logger.debug("RedisUtils setAllToSet with expire time error:"+e.getMessage());
        }
        return 0;
    }  

    /** 
     * 获取set缓存的长度 
     * @param key 键 
     * @return 
     */  
    public long getSetSize(String key){  
        try{  
            return redisTemplate.opsForSet().size(key);  
        }catch(Exception e){  
        	logger.debug("RedisUtils removeFromSet error:"+e.getMessage());
        }
        return 0;
    }  

    /** 
     * 移除值为value的 
     * @param key 键 
     * @param values 值 可以是多个 
     * @return 移除的个数 
     */  
    public long removeFromSet(String key, Object ...values) {  
        try {  
            return redisTemplate.opsForSet().remove(key,values);  
        }catch(Exception e){  
        	logger.debug("RedisUtils removeFromSet error:"+e.getMessage());
        }
        return 0;
    }  
    
    /** 
     * 获取list缓存的内容 
     * @param key 键 
     * @param start 开始 
     * @param end 结束  0 到 -1代表所有值 
     * @return 
     */  
    public List<Object> getByRange(String key,long start,long end){  
        try {  
            return redisTemplate.opsForList().range(key,start,end);  
        }catch(Exception e){  
        	logger.debug("RedisUtils getByRange error:"+e.getMessage());
        }
        return null;
    }  

    /** 
     * 获取list缓存的长度 
     * @param key 键 
     * @return 
     */  
    public long getListSize(String key){  
        try{  
            return redisTemplate.opsForList().size(key);  
        }catch(Exception e){  
        	logger.debug("RedisUtils getListSize error:"+e.getMessage());
        }
        return 0;
    }  

    /** 
     * 通过索引 获取list中的值 
     * @param key 键 
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推 
     * @return 
     */  
    public Object getFromListByIndex(String key,long index){  
        try{
            return redisTemplate.opsForList().index(key,index);  
        }catch(Exception e){  
        	logger.debug("RedisUtils getValueFromListByIndex error:"+e.getMessage());
        }
        return null;
    }  

    /** 
     * 将list放入缓存 
     * @param key 键 
     * @param value 值 
     * @param time 时间(秒) 
     * @return 
     */  
    public boolean setSingleToList(String key,Object value){  
        try{  
            redisTemplate.opsForList().rightPush(key,value);  
            return true;
        }catch(Exception e){  
        	logger.debug("RedisUtils setSingleToList no expire time error:"+e.getMessage());
        }
        return false;
    }  

    /** 
     * 将list放入缓存 
     * @param key 键 
     * @param value 值 
     * @param time 时间(秒) 
     * @return 
     */  
    public boolean setSingleToList(String key,Object value,long time) {  
        try {  
            redisTemplate.opsForList().rightPush(key,value);  
            if(time>0) expire(key, time);  
            return true;  
        }catch (Exception e){  
        	logger.debug("RedisUtils setSingleToList with expire time error:"+e.getMessage());
        }
        return false;
    }  

    /** 
     * 将list放入缓存 
     * @param key 键 
     * @param value 值 
     * @param time 时间(秒) 
     * @return 
     */  
    public boolean setAllToList(String key,List<Object> value) {  
        try {  
            redisTemplate.opsForList().rightPushAll(key, value);  
            return true;  
        }catch(Exception e){  
        	logger.debug("RedisUtils setAllToList no expire time error:"+e.getMessage());
        }
        return false;
    }  

    /** 
     * 将list放入缓存 
     * @param key 键 
     * @param value 值 
     * @param time 时间(秒) 
     * @return 
     */  
    public boolean setAllToList(String key, List<Object> value, long time) {  
        try {  
            redisTemplate.opsForList().rightPushAll(key,value);  
            if(time > 0)expire(key, time);  
            return true;
        } catch (Exception e) {  
        	logger.debug("RedisUtils list setAllToList with expire time error:"+e.getMessage());
        }
        return false;
    }  

    /** 
     * 根据索引修改list中的某条数据 
     * @param key 键 
     * @param index 索引 
     * @param value 值 
     * @return 
     */  
    public boolean updateListByIndex(String key, long index,Object value) {  
        try{  
            redisTemplate.opsForList().set(key,index,value);  
            return true;  
        }catch(Exception e){  
        	logger.debug("RedisUtils list updateListByIndex error:"+e.getMessage());
        }  
        return false;
    }   

    /** 
     * 移除N个值为value  
     * @param key 键 
     * @param count 移除多少个 
     * @param value 值 
     * @return 移除的个数 
     */  
    public long removeFromList(String key,long count,Object value) {  
        try {  
            return redisTemplate.opsForList().remove(key,count,value);  
        } catch (Exception e) {  
        	logger.debug("RedisUtils removeFromList error:"+e.getMessage());
        }
        return 0;
    }  
}
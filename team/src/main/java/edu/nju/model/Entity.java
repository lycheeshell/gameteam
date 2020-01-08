package edu.nju.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.sql.Timestamp;

/**
 * 所有实体类继承此公共父类
 * @Author ：lycheeshell
 * @Date ：Created in 16:33 2020/1/8
 */
public abstract class Entity {

    protected boolean blockFlag;
    protected Timestamp createAt;

    public Entity() {
        this.blockFlag = false;
        this.createAt = new Timestamp(System.currentTimeMillis());
    }

    public boolean isBlockFlag() {
        return blockFlag;
    }

    public void setBlockFlag(boolean blockFlag) {
        this.blockFlag = blockFlag;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
    }

}

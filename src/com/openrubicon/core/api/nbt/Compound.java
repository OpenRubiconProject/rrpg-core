package com.openrubicon.core.api.nbt;

import de.tr7zw.itemnbtapi.NBTCompound;

import java.util.Set;

public class Compound extends de.tr7zw.itemnbtapi.NBTCompound {
    public Compound(NBTCompound owner, String name) {
        super(owner, name);
    }

    public void setString(String key, String value) {
        super.setString(key, value);
    }

    public String getString(String key) {
        return super.getString(key);
    }

    protected String getContent(String key) {
        return super.getContent(key);
    }

    public void setInteger(String key, Integer value) {
        super.setInteger(key, value);
    }

    public Integer getInteger(String key) {
        return super.getInteger(key);
    }

    public void setDouble(String key, Double value) {
        super.setDouble(key, value);
    }

    public Double getDouble(String key) {
        return super.getDouble(key);
    }

    public void setByte(String key, Byte value) {
        super.setByte(key, value);
    }

    public Byte getByte(String key) {
        return super.getByte(key);
    }

    public void setShort(String key, Short value) {
        super.setShort(key, value);
    }

    public Short getShort(String key) {
        return super.getShort(key);
    }

    public void setLong(String key, Long value) {
        super.setLong(key, value);
    }

    public Long getLong(String key) {
        return super.getLong(key);
    }

    public void setFloat(String key, Float value) {
        super.setFloat(key, value);
    }

    public Float getFloat(String key) {
        return super.getFloat(key);
    }

    public void setByteArray(String key, byte[] value) {
        super.setByteArray(key, value);
    }

    public byte[] getByteArray(String key) {
        return super.getByteArray(key);
    }

    public void setIntArray(String key, int[] value) {
        super.setIntArray(key, value);
    }

    public int[] getIntArray(String key) {
        return super.getIntArray(key);
    }

    public void setBoolean(String key, Boolean value) {
        super.setBoolean(key, value);
    }

    protected void set(String key, Object val) {
        super.set(key, val);
    }

    public Boolean getBoolean(String key) {
        return super.getBoolean(key);
    }

    public void setObject(String key, Object value) {
        super.setObject(key, value);
    }

    public <T> T getObject(String key, Class<T> type) {
        return super.getObject(key, type);
    }

    public Boolean hasKey(String key) {
        return super.hasKey(key);
    }

    public void removeKey(String key) {
        super.removeKey(key);
    }

    public Set<String> getKeys() {
        return super.getKeys();
    }
    
}

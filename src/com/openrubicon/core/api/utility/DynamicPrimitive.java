package com.openrubicon.core.api.utility;

import com.openrubicon.core.api.interfaces.Observeable;

import java.util.ArrayList;

public class DynamicPrimitive<T> implements Observeable {

    private T property;

    // These are for primitive returns
    private int rint;
    private float rfloat;
    private double rdouble;
    private long rlong;
    private char rchar;
    private byte rbyte;
    private short rshort;
    private boolean rboolean;

    public DynamicPrimitive() {
    }

    public DynamicPrimitive(T property) {
        this.property = property;
        this.setPrim();
    }

    public DynamicPrimitive(String property) {
        this.setProperty(property);
    }

    public T getProperty() {
        return property;
    }

    public void setProperty(String property) {
        if(property == null)
        {
            this.property = null;
        }
        else
        {
            if(this.getProperty() instanceof Boolean)
                this.changeProperty((T)Boolean.valueOf(property));
            else if(this.getProperty()instanceof Integer)
                this.changeProperty((T)Integer.valueOf(property));
            else if(this.getProperty()instanceof Short)
                this.changeProperty((T)Short.valueOf(property));
            else if(this.getProperty()instanceof Byte)
                this.changeProperty((T)Byte.valueOf(property));
            else if(this.getProperty()instanceof Long)
                this.changeProperty((T)Long.valueOf(property));
            else if(this.getProperty()instanceof Character)
                this.changeProperty((T)Character.valueOf(property.charAt(0)));
            else if(this.getProperty()instanceof Float)
                this.changeProperty((T)Float.valueOf(property));
            else if(this.getProperty()instanceof Double)
                this.changeProperty((T)Double.valueOf(property));
            else
                this.changeProperty((T)property);
        }

        this.setPrim();
    }

    protected void changeProperty(T prop)
    {
        this.property = prop;
    }

    public void setPrim()
    {
        if(property == null)
        {
            rint = 0;
            rbyte = (byte)rint;
            rdouble = rint;
            rfloat = rint;
            rlong = rint;
            rshort = (short)rint;
            rchar = (char)rint;
        }

        else if(property instanceof Number)
        {
            Number propertyNumber = (Number)property;
            rint = propertyNumber.intValue();
            rbyte = propertyNumber.byteValue();
            rdouble = propertyNumber.doubleValue();
            rfloat = propertyNumber.floatValue();
            rlong = propertyNumber.longValue();
            rshort = propertyNumber.shortValue();
            rchar = (char)rint;
        }

        else if(property instanceof Character)
        {
            rchar = ((Character)property).charValue();
            rint = Character.getNumericValue(rchar);
            rbyte = (byte)rint;
            rdouble = rint;
            rfloat = rint;
            rlong = rint;
            rshort = (short)rint;
        }

        else if(property instanceof Boolean)
        {
            rboolean = ((Boolean)property).booleanValue();
            if(rboolean)
                rint = 1;
            else
                rint = 0;
            rbyte = (byte)rint;
            rdouble = rint;
            rfloat = rint;
            rlong = rint;
            rshort = (short)rint;
            rchar = (char)rint;
        }

        if(rint == 0 || this.property == null)
            rboolean = false;
        else
            rboolean = true;
    }

    public String getString()
    {
        return this.getProperty().toString();
    }

    public boolean getBoolean()
    {
        return rboolean;
    }

    public int getInt() { return rint; }

    public short getShort() { return rshort; }

    public long getLong() { return rlong; }

    public float getFloat() { return rfloat; }

    public double getDouble() { return rdouble; }

    public char getChar() { return rchar; }

    public byte getByte() { return rbyte; }

    @Override
    public ArrayList<String> getObservation() {
        ArrayList<String> observation = new ArrayList<>();
        observation.add(this.getProperty().toString());
        return observation;
    }
}

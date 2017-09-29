package com.openrubicon.core.api.nbt;

/**
 * @Author de.tr7zw
 */
public enum DataType {
    NBTTagEnd(0),
    NBTTagByte(1),
    NBTTagShort(2),
    NBTTagInt(3),
    NBTTagLong(4),
    NBTTagFloat(5),
    NBTTagDouble(6),
    NBTTagByteArray(7),
    NBTTagIntArray(11),
    NBTTagString(8),
    NBTTagList(9),
    NBTTagCompound(10);

    private final int id;

    private DataType(int i) {
        this.id = i;
    }

    public int getId() {
        return this.id;
    }

    public static DataType valueOf(int id) {
        DataType[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            DataType t = var4[var2];
            if(t.getId() == id) {
                return t;
            }
        }

        return NBTTagEnd;
    }
}

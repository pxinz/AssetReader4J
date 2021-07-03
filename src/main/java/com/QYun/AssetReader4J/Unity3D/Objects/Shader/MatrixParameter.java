package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class MatrixParameter {
    public int m_NameIndex;
    public int m_Index;
    public int m_ArraySize;
    public byte m_Type;
    public byte m_RowCount;

    public MatrixParameter(ObjectReader reader)
    {
        m_NameIndex = reader.ReadInt32();
        m_Index = reader.ReadInt32();
        m_ArraySize = reader.ReadInt32();
        m_Type = reader.readByte();     //m_Type = reader.ReadSByte();
        m_RowCount = reader.readByte(); //m_RowCount = reader.ReadSByte();
        reader.AlignStream();
    }
}

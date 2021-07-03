package com.QYun.AssetReader4J.Unity3D.Objects.Material;

import com.QYun.AssetReader4J.Unity3D.Contracts.Texture;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;

import javax.vecmath.Vector2f;

public class UnityTexEnv {
    public PPtr<Texture> m_Texture;
    public Vector2f m_Scale;
    public Vector2f m_Offset;

    public UnityTexEnv(ObjectReader reader) {
        m_Texture = new PPtr<Texture>(reader);
        m_Scale = reader.ReadVector2();
        m_Offset = reader.ReadVector2();
    }
}

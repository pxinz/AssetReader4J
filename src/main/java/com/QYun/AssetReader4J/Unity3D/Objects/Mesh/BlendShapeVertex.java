package com.QYun.AssetReader4J.Unity3D.Objects.Mesh;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

import javax.vecmath.Vector3f;

public class BlendShapeVertex {
    public Vector3f vertex;
    public Vector3f normal;
    public Vector3f tangent;
    public int index;

    public BlendShapeVertex(ObjectReader reader) {
        vertex = reader.ReadVector3();
        normal = reader.ReadVector3();
        tangent = reader.ReadVector3();
        index = reader.ReadUInt32();
    }
}

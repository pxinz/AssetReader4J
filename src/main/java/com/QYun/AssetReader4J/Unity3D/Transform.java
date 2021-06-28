package com.QYun.AssetReader4J.Unity3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class Transform extends Component {
    public Quat4f m_LocalRotation;
    public Vector3f m_LocalPosition;
    public Vector3f m_LocalScale;
    public PPtr<Transform>[] m_Children;
    public PPtr<Transform> m_Father;

    public Transform(UObjectReader reader) {
        super(reader);
        // TODO
    }
}
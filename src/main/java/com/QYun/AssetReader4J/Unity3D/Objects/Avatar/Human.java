package com.QYun.AssetReader4J.Unity3D.Objects.Avatar;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip.xform;

public class Human {
    public xform m_RootX;
    public Skeleton m_Skeleton;
    public SkeletonPose m_SkeletonPose;
    public Hand m_LeftHand;
    public Hand m_RightHand;
    public Handle[] m_Handles;
    public Collider[] m_ColliderArray;
    public Integer[] m_HumanBoneIndex;
    public Float[] m_HumanBoneMass;
    public Integer[] m_ColliderIndex;
    public float m_Scale;
    public float m_ArmTwist;
    public float m_ForeArmTwist;
    public float m_UpperLegTwist;
    public float m_LegTwist;
    public float m_ArmStretch;
    public float m_LegStretch;
    public float m_FeetSpacing;
    public boolean m_HasLeftHand;
    public boolean m_HasRightHand;
    public boolean m_HasTDoF;

    public Human(ObjectReader reader) {
        var version = reader.version();
        m_RootX = new xform(reader);
        m_Skeleton = new Skeleton(reader);
        m_SkeletonPose = new SkeletonPose(reader);
        m_LeftHand = new Hand(reader);
        m_RightHand = new Hand(reader);

        if (version[0] < 2018 || (version[0] == 2018 && version[1] < 2)) { //2018.2 down
            int numHandles = reader.ReadInt32();
            m_Handles = new Handle[numHandles];
            for (int i = 0; i < numHandles; i++) {
                m_Handles[i] = new Handle(reader);
            }

            int numColliders = reader.ReadInt32();
            m_ColliderArray = new Collider[numColliders];
            for (int i = 0; i < numColliders; i++) {
                m_ColliderArray[i] = new Collider(reader);
            }
        }

        m_HumanBoneIndex = reader.ReadInt32Array();

        m_HumanBoneMass = reader.ReadSingleArray();

        if (version[0] < 2018 || (version[0] == 2018 && version[1] < 2)) { //2018.2 down
            m_ColliderIndex = reader.ReadInt32Array();
        }

        m_Scale = reader.ReadSingle();
        m_ArmTwist = reader.ReadSingle();
        m_ForeArmTwist = reader.ReadSingle();
        m_UpperLegTwist = reader.ReadSingle();
        m_LegTwist = reader.ReadSingle();
        m_ArmStretch = reader.ReadSingle();
        m_LegStretch = reader.ReadSingle();
        m_FeetSpacing = reader.ReadSingle();
        m_HasLeftHand = reader.ReadBoolean();
        m_HasRightHand = reader.ReadBoolean();
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 2)) { //5.2 and up
            m_HasTDoF = reader.ReadBoolean();
        }
        reader.AlignStream();
    }
}

package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.Contracts.Renderer;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.Mesh.Mesh;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

public class SkinnedMeshRenderer extends Renderer {
    public PPtr<Mesh> m_Mesh;
    public MutableList<PPtr<Transform>> m_Bones;
    public Float[] m_BlendShapeWeights;

    public SkinnedMeshRenderer(ObjectReader reader) {
        super(reader);
        int m_Quality = reader.ReadInt32();
        var m_UpdateWhenOffscreen = reader.ReadBoolean();
        var m_SkinNormals = reader.ReadBoolean(); //3.1.0 and below
        reader.AlignStream();

        if (version[0] == 2 && version[1] < 6) { //2.6 down
            var m_DisableAnimationWhenOffscreen = new PPtr<Animation>(reader);
        }

        m_Mesh = new PPtr<>(reader);

        m_Bones = Lists.mutable.withInitialCapacity(reader.ReadInt32());
        for (int b = 0; b < m_Bones.size(); b++) {
            m_Bones.add(b, new PPtr<>(reader));
        }

        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) { //4.3 and up
            m_BlendShapeWeights = reader.ReadSingleArray();
        }
    }
}

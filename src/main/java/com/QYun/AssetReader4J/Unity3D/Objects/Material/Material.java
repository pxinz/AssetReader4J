package com.QYun.AssetReader4J.Unity3D.Objects.Material;

import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.Objects.Shader.Shader;

public class Material extends NamedObject {
    public PPtr<Shader> m_Shader;
    public UnityPropertySheet m_SavedProperties;

    public Material(ObjectReader reader) {
        super(reader);
        m_Shader = new PPtr<>(reader);

        if (version[0] == 4 && version[1] >= 1) { //4.x
            var m_ShaderKeywords = reader.ReadStringArray();
        }

        if (version[0] >= 5) { //5.0 and up
            var m_ShaderKeywords = reader.ReadAlignedString();
            var m_LightmapFlags = reader.ReadUInt32();
        }

        if (version[0] > 5 || (version[0] == 5 && version[1] >= 6)) { //5.6 and up
            var m_EnableInstancingVariants = reader.ReadBoolean();
            //var m_DoubleSidedGI = a_Stream.ReadBoolean(); //2017 and up
            reader.AlignStream();
        }

        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) { //4.3 and up
            var m_CustomRenderQueue = reader.ReadInt32();
        }

        if (version[0] > 5 || (version[0] == 5 && version[1] >= 1)) { //5.1 and up
            var stringTagMapSize = reader.ReadInt32();
            for (int i = 0; i < stringTagMapSize; i++) {
                var first = reader.ReadAlignedString();
                var second = reader.ReadAlignedString();
            }
        }

        if (version[0] > 5 || (version[0] == 5 && version[1] >= 6)) { //5.6 and up
            var disabledShaderPasses = reader.ReadStringArray();
        }

        m_SavedProperties = new UnityPropertySheet(reader);

        //vector m_BuildTextureStacks 2020 and up
    }
}

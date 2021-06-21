package com.QYun.AssetReader4J;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BundleFile {
    public Header m_Header = new Header();

    public BundleFile(BinaryReader reader, File file) throws IOException {
        m_Header.signature = reader.readStringToNull();
        m_Header.version = reader.readInt();
        m_Header.unityVersion = reader.readStringToNull();
        m_Header.unityRevision = reader.readStringToNull();

        switch (m_Header.signature) {
            case "UnityArchive" -> {
            }
            case "UnityWeb", "UnityRaw" -> {
                if (m_Header.version == 6)
                    handleFS(reader, file);
            }
            case "UnityFS" -> handleFS(reader, file);
        }
    }

    private createBlocksStream(File file) {
    }

    private void handleFS(BinaryReader reader, File file) throws IOException {
        readHeader(reader);
        readBlocksInfoAndDirectory(reader);
        blocksStream = createBlocksStream(file);
        readBlocks(reader, blocksStream);
        readFiles(blocksStream, file);
    }

    private void readHeader(BinaryReader reader) throws IOException {
        m_Header.size = reader.readLong();
        m_Header.compressedBlocksInfoSize = reader.readInt();
        m_Header.uncompressedBlocksInfoSize = reader.readInt();
        m_Header.flags = reader.readInt();
        if (!m_Header.signature.equals("UnityFS"))
            reader.readByte();
    }

    private void readBlocksInfoAndDirectory(BinaryReader reader) throws IOException {
        byte[] blocksInfoBytes;
        if (m_Header.version >= 7)
            reader.alignStream(16);

        if ((m_Header.flags & 0x80) != 0) {
            int position = reader.getPos();
            reader.setPos((int) (reader.fileLen - m_Header.compressedBlocksInfoSize));
            blocksInfoBytes = reader.readBytes(m_Header.compressedBlocksInfoSize);
            reader.setPos(position);
        } else blocksInfoBytes = reader.readBytes(m_Header.compressedBlocksInfoSize);

        ByteBuffer blocksInfoCompressedStream = ByteBuffer.wrap(blocksInfoBytes);
        ByteBuffer blocksInfoUncompressedStream;

        switch (m_Header.flags & 0x3F) {
            case 1 -> {
                blocksInfoUncompressedStream = ByteBuffer.allocate(m_Header.uncompressedBlocksInfoSize);

            }
            default -> blocksInfoUncompressedStream = blocksInfoCompressedStream;
        }

    }

    public class Header {
        public String signature;
        public int version;
        public String unityVersion;
        public String unityRevision;
        public long size;
        public int compressedBlocksInfoSize;
        public int uncompressedBlocksInfoSize;
        public int flags;
    }

    public class StorageBlock {
        public int compressedSize;
        public int uncompressedSize;
        public short flags;
    }

    public class Node {
        public long offset;
        public long size;
        public int flags;
        public String path;
    }
}
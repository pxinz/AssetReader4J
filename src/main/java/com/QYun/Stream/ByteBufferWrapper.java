package com.QYun.Stream;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class ByteBufferWrapper {
    ByteBuffer byteBuffer;

    public ByteBufferWrapper(int capacity) {
        byteBuffer = ByteBuffer.allocate(capacity);
    }

    public ByteBufferWrapper(byte[] array) {
        byteBuffer = ByteBuffer.wrap(array);
    }

    public ByteBufferWrapper(byte[] array, int offset, int length) {
        byteBuffer = ByteBuffer.wrap(array, offset, length);
    }

    public ByteBufferWrapper(InputStream inputStream) throws IOException {
        this(inputStream.readAllBytes());
    }

    public ByteBufferWrapper(ByteArrayOutputStream outputStream) {
        this(outputStream.toByteArray());
    }

    public ByteBufferWrapper(File file) throws IOException {
        this(new FileInputStream(file));
    }

    public int getPos() {
        return byteBuffer.position();
    }

    public void setPos(int pos) {
        byteBuffer.position(pos);
    }

    public ByteBufferWrapper setToReadOnly() {
        byteBuffer = byteBuffer.asReadOnlyBuffer();
        return this;
    }

    public ByteBufferWrapper setByteOrder(ByteOrder byteOrder) {
        byteBuffer.order(byteOrder);
        return this;
    }

    public ByteArrayInputStream toInputStream() {
        return new ByteArrayInputStream(byteBuffer.array());
    }

    public ByteArrayOutputStream toOutputStream() {
        var tmp = new ByteArrayOutputStream(byteBuffer.capacity());
        tmp.writeBytes(byteBuffer.array());
        return tmp;
    }
}
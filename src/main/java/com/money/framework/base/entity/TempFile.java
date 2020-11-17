package com.money.framework.base.entity;

import java.io.Closeable;
import java.io.File;
import java.util.Objects;

public class TempFile implements Closeable {

    private File file;

    public TempFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public void close() {
        if (Objects.isNull(file)) {
            return;
        }
        if (file.exists()) {
            file.delete();
        }
        file = null;
    }
}

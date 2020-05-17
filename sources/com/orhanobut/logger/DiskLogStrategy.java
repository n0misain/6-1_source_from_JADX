package com.orhanobut.logger;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DiskLogStrategy implements LogStrategy {
    private final Handler handler;

    static class WriteHandler extends Handler {
        private final String folder;
        private final int maxFileSize;

        WriteHandler(Looper looper, String folder, int maxFileSize) {
            super(looper);
            this.folder = folder;
            this.maxFileSize = maxFileSize;
        }

        public void handleMessage(Message msg) {
            String content = msg.obj;
            FileWriter fileWriter = null;
            try {
                FileWriter fileWriter2 = new FileWriter(getLogFile(this.folder, "logs"), true);
                try {
                    writeLog(fileWriter2, content);
                    fileWriter2.flush();
                    fileWriter2.close();
                    fileWriter = fileWriter2;
                } catch (IOException e) {
                    fileWriter = fileWriter2;
                    if (fileWriter != null) {
                        try {
                            fileWriter.flush();
                            fileWriter.close();
                        } catch (IOException e2) {
                        }
                    }
                }
            } catch (IOException e3) {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            }
        }

        private void writeLog(FileWriter fileWriter, String content) throws IOException {
            fileWriter.append(content);
        }

        private File getLogFile(String folderName, String fileName) {
            File folder = new File(folderName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            int newFileCount = 0;
            File existingFile = null;
            File newFile = new File(folder, String.format("%s_%s.csv", new Object[]{fileName, Integer.valueOf(0)}));
            while (newFile.exists()) {
                existingFile = newFile;
                newFileCount++;
                newFile = new File(folder, String.format("%s_%s.csv", new Object[]{fileName, Integer.valueOf(newFileCount)}));
            }
            return (existingFile == null || existingFile.length() >= ((long) this.maxFileSize)) ? newFile : existingFile;
        }
    }

    public DiskLogStrategy(Handler handler) {
        this.handler = handler;
    }

    public void log(int level, String tag, String message) {
        this.handler.sendMessage(this.handler.obtainMessage(level, message));
    }
}

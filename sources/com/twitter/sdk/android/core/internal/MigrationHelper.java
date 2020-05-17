package com.twitter.sdk.android.core.internal;

import android.content.Context;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

public class MigrationHelper {
    private static final String SHARED_PREFS_DIR = "shared_prefs";

    static class FileLastModifiedComparator implements Comparator<File> {
        FileLastModifiedComparator() {
        }

        public int compare(File file1, File file2) {
            return Long.valueOf(file2.lastModified()).compareTo(Long.valueOf(file1.lastModified()));
        }
    }

    static class PrefixFileNameFilter implements FilenameFilter {
        final String prefix;

        public PrefixFileNameFilter(String prefix) {
            this.prefix = prefix;
        }

        public boolean accept(File file, String filename) {
            return filename.startsWith(this.prefix);
        }
    }

    public void migrateSessionStore(Context context, String prefixMatch, String expectedFileName) {
        File sharedPrefsDir = getSharedPreferencesDir(context);
        if (sharedPrefsDir.exists() && sharedPrefsDir.isDirectory()) {
            File expectedSharedPrefsFile = new File(sharedPrefsDir, expectedFileName);
            if (!expectedSharedPrefsFile.exists()) {
                File oldPrefsharedPrefsFile = getLatestFile(sharedPrefsDir, prefixMatch);
                if (oldPrefsharedPrefsFile != null) {
                    oldPrefsharedPrefsFile.renameTo(expectedSharedPrefsFile);
                }
            }
        }
    }

    File getSharedPreferencesDir(Context context) {
        return new File(context.getApplicationInfo().dataDir, SHARED_PREFS_DIR);
    }

    File getLatestFile(File sharedPrefsDir, String prefix) {
        File[] files = sharedPrefsDir.listFiles(new PrefixFileNameFilter(prefix));
        Arrays.sort(files, new FileLastModifiedComparator());
        return files.length > 0 ? files[0] : null;
    }
}

package com.twitter.sdk.android.tweetcomposer;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;

class FileUtils {
    private static final String MEDIA_SCHEME = "com.android.providers.media.documents";

    FileUtils() {
    }

    @TargetApi(19)
    static String getPath(Context context, Uri uri) {
        boolean isKitKat;
        if (VERSION.SDK_INT >= 19) {
            isKitKat = true;
        } else {
            isKitKat = false;
        }
        if (isKitKat && isMediaDocumentAuthority(uri)) {
            if (!"image".equals(DocumentsContract.getDocumentId(uri).split(":")[0])) {
                return null;
            }
            String selection = "_id=?";
            return resolveFilePath(context, Media.EXTERNAL_CONTENT_URI, "_id=?", new String[]{parts[1]});
        } else if (isContentScheme(uri)) {
            return resolveFilePath(context, uri, null, null);
        } else {
            if (isFileScheme(uri)) {
                return uri.getPath();
            }
            return null;
        }
    }

    public static boolean isMediaDocumentAuthority(Uri uri) {
        return MEDIA_SCHEME.equalsIgnoreCase(uri.getAuthority());
    }

    public static boolean isContentScheme(Uri uri) {
        return "content".equalsIgnoreCase(uri.getScheme());
    }

    public static boolean isFileScheme(Uri uri) {
        return "file".equalsIgnoreCase(uri.getScheme());
    }

    static String resolveFilePath(Context context, Uri uri, String selection, String[] args) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, selection, args, null);
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            }
            String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
            return string;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    static String getMimeType(File file) {
        String ext = getExtension(file.getName());
        if (TextUtils.isEmpty(ext)) {
            return "application/octet-stream";
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);
    }

    static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int i = filename.lastIndexOf(".");
        return i < 0 ? "" : filename.substring(i + 1);
    }
}

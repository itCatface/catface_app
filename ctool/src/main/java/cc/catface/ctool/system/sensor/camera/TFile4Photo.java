package cc.catface.ctool.system.sensor.camera;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

public class TFile4Photo {


    /**
     * 根据URI获取文件真实路径[兼容多版本]
     */
    public static String getFilePathByUri(Context context, Uri uri) {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            int sdkVersion = Build.VERSION.SDK_INT;
            if (sdkVersion >= 19) {
                return getRealPathFromUriAboveApi19(context, uri);
            } else {
                return getRealPathFromUriBelowAPI19(context, uri);
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * 根据uri获取图片的绝对路径
     */
    @SuppressLint("NewApi") private static String getRealPathFromUriAboveApi19(Context context, Uri uri) {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            String documentId = DocumentsContract.getDocumentId(uri);   // document类型uri
            if (isMediaDocument(uri)) {
                String type = documentId.split(":")[0];
                String id = documentId.split(":")[1];

                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                filePath = getDataColumn(context, contentUri, selection, selectionArgs);
            } else if (isDownloadsDocument(uri)) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(context, contentUri, null, null);
            } else if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    filePath = Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else {
                // 路径错误
            }

        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            filePath = getDataColumn(context, uri, null, null); // content类型uri

        } else if ("file".equals(uri.getScheme())) {
            filePath = uri.getPath();   // file类型uri
        }


        return filePath;
    }


    /**
     * 根据uri获取图片的绝对路径
     */
    private static String getRealPathFromUriBelowAPI19(Context context, Uri uri) {
        return getDataColumn(context, uri, null, null);
    }


    /**
     * 获取数据库表中的_data列即返回Uri对应的文件路径
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;

        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }


    /**
     * check uri
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
}
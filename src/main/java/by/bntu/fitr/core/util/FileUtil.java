package by.bntu.fitr.core.util;

import by.bntu.fitr.core.constant.CommonConstant;

public class FileUtil {

    public static String changeSeparator(final String str, final String currentSeparator, final String newSeparator) {
        return str.replaceAll(currentSeparator, newSeparator);
    }

    public static boolean isFileBelongToJava(final String fileName) {
        return fileName.endsWith(".class");
    }

    public static String getFileNameWithOutExtension(final String fileName) {
        String[] partOfFileNames = fileName.split(CommonConstant.DOT_SEPARATOR);
        StringBuilder fileNameWithOutExtension = new StringBuilder();
        for (int i = 0; i < partOfFileNames.length - 1; i++) {
            fileNameWithOutExtension.append(partOfFileNames[i]);
        }
        return fileNameWithOutExtension.toString();
    }
}

package by.bntu.fitr.core.provider;

import by.bntu.fitr.core.exception.ProfilerCoreException;
import by.bntu.fitr.core.util.FileUtil;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionApiProvider {

    public List<Class> getClassesMarkedWithAnnotation(final String packageForScanRoot,
                                                      final String packageForScan,
                                                      final String searchedAnnotation) {
        List<String> allClassesNames = new ArrayList<>();
        getClassesNameWithPackages(new File(packageForScanRoot),
                allClassesNames,
                packageForScan);


        List<Class> classesWithAnnotation = new ArrayList<>();

        try {
            for (String className : allClassesNames) {
                Class clazz = Class.forName(className);

                for (Annotation annotation : clazz.getAnnotations()) {

                    if (annotation.annotationType().getName().equals(searchedAnnotation)) {
                        classesWithAnnotation.add(clazz);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new ProfilerCoreException("");
        }

        return classesWithAnnotation;
    }

    public Map<Class, List<String>> getMethodsMarkedWithAnnotations(final List<Class> classes,
                                                                    final String searchedAnnotation) {
        Map<Class, List<String>> classMethods = new HashMap<>();
        for (Class clazz : classes) {
            for (Method method : clazz.getDeclaredMethods()) {
                for (Annotation annotation : method.getDeclaredAnnotations()) {
                    if (annotation.annotationType().getName().equals(searchedAnnotation)) {
                        if (classMethods.get(clazz) == null) {
                            List<String> methodNameList = new ArrayList<>();
                            methodNameList.add(method.getName());
                            classMethods.put(clazz, methodNameList);
                        } else {
                            List<String> methodNameList = classMethods.get(clazz);
                            methodNameList.add(method.getName());
                        }
                    }
                }
            }
        }
        return classMethods;
    }


    private void getClassesNameWithPackages(final File rootFile, List<String> classesName, String currentDirectory) {
        if (rootFile.isDirectory()) {
            for (File file : rootFile.listFiles()) {
                if (file.isDirectory()) {
                    getClassesNameWithPackages(file, classesName, currentDirectory + "." + file.getName());
                } else {
                    String fileName = file.getName();
                    if (FileUtil.isFileBelongToJava(fileName)) {
                        classesName.add(currentDirectory + "." + FileUtil.getFileNameWithOutExtension(fileName));
                    }
                }
            }
        }
    }
}

package by.bntu.fitr.core.bytecode;

import java.util.List;
import java.util.Map;

public interface ByteCodeExecutor {

    void redefineMethodForTimedMetric(final Map<Class, List<String>> classMethodNames, final ClassLoader classLoader);
}

package by.bntu.fitr.core.bytecode;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.List;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ByteBuddyExecutor implements ByteCodeExecutor {

    @Override
    public void redefineMethodForTimedMetric(final Map<Class, List<String>> classMethodNames, final ClassLoader classLoader) {
        for (Map.Entry<Class, List<String>> entrySet : classMethodNames.entrySet()) {
            for (String methodName : entrySet.getValue()) {
                ByteBuddyAgent.install();
                new ByteBuddy()
                        .redefine(entrySet.getKey())
                        .visit(Advice.to(TimedMetricAdvice.class).on(ElementMatchers.isMethod()).method(named(methodName)))
                        .make()
                        .load(
                                classLoader,
                                ClassReloadingStrategy.fromInstalledAgent());
            }
        }
    }
}

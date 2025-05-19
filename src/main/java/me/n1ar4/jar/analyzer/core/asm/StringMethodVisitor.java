/*
 * GPLv3 License
 *
 * Copyright (c) 2023-2025 4ra1n (Jar Analyzer Team)
 *
 * This project is distributed under the GPLv3 license.
 *
 * https://github.com/jar-analyzer/jar-analyzer/blob/master/LICENSE
 */

package me.n1ar4.jar.analyzer.core.asm;

import me.n1ar4.jar.analyzer.core.reference.ClassReference;
import me.n1ar4.jar.analyzer.core.reference.MethodReference;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StringMethodVisitor extends MethodVisitor {
    private final Map<MethodReference.Handle, List<String>> strMap;

    private MethodReference ownerHandle = null;

    public StringMethodVisitor(int api, MethodVisitor methodVisitor,
                               String owner, String methodName, String desc,
                               Map<MethodReference.Handle, List<String>> strMap,
                               Map<ClassReference.Handle, ClassReference> classMap,
                               Map<MethodReference.Handle, MethodReference> methodMap) {
        super(api, methodVisitor);
        this.strMap = strMap;
        ClassReference.Handle ch = new ClassReference.Handle(owner);
        if (classMap.get(ch) != null) {
            MethodReference m = methodMap.get(new MethodReference.Handle(ch, methodName, desc));
            if (m != null) {
                this.ownerHandle = m;
            }
        }
    }

    @Override
    public void visitLdcInsn(Object o) {
        if (this.ownerHandle == null)
            return;
        if (o instanceof String) {
            String str = (String) o;
            if (str.trim().isEmpty()) {
                return;
            }
            List<String> mList = strMap.getOrDefault(this.ownerHandle.getHandle(), new ArrayList<>());
            mList.add(str);
            strMap.put(this.ownerHandle.getHandle(), mList);
        }
        super.visitLdcInsn(o);
    }
}

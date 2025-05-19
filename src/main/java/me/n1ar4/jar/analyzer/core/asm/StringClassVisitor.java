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
import me.n1ar4.jar.analyzer.starter.Const;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.List;
import java.util.Map;

public class StringClassVisitor extends ClassVisitor {
    private String name;
    private final Map<MethodReference.Handle, List<String>> strMap;
    private final Map<ClassReference.Handle, ClassReference> classMap;
    private final Map<MethodReference.Handle, MethodReference> methodMap;

    public StringClassVisitor(Map<MethodReference.Handle, List<String>> strMap,
                              Map<ClassReference.Handle, ClassReference> classMap,
                              Map<MethodReference.Handle, MethodReference> methodMap) {
        super(Const.ASMVersion);
        this.strMap = strMap;
        this.classMap = classMap;
        this.methodMap = methodMap;
    }

    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.name = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        return new StringMethodVisitor(api, mv, this.name, name, desc, strMap, classMap, methodMap);
    }
}


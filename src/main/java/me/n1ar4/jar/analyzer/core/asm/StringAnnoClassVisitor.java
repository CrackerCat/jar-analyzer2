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

public class StringAnnoClassVisitor extends ClassVisitor {
    private String className;
    private final Map<MethodReference.Handle, List<String>> stringAnnoMap;

    public StringAnnoClassVisitor(Map<MethodReference.Handle, List<String>> stringAnnoMap) {
        super(Const.ASMVersion);
        this.stringAnnoMap = stringAnnoMap;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        className = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        MethodReference.Handle mh = new MethodReference.Handle(
                new ClassReference.Handle(className),
                name,
                descriptor
        );
        return new StringAnnoMethodAdapter(mv, mh, this.stringAnnoMap);
    }
}

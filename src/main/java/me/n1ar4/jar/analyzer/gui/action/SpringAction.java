/*
 * GPLv3 License
 *
 * Copyright (c) 2023-2025 4ra1n (Jar Analyzer Team)
 *
 * This project is distributed under the GPLv3 license.
 *
 * https://github.com/jar-analyzer/jar-analyzer/blob/master/LICENSE
 */

package me.n1ar4.jar.analyzer.gui.action;

import me.n1ar4.jar.analyzer.engine.CoreHelper;
import me.n1ar4.jar.analyzer.gui.MainForm;

import javax.swing.*;

public class SpringAction {
    public static void run() {
        JButton spRefreshBtn = MainForm.getInstance().getRefreshButton();
        spRefreshBtn.addActionListener(e -> {
            CoreHelper.refreshSpringC();
            CoreHelper.refreshSpringI();
            CoreHelper.refreshServlets();
            CoreHelper.refreshFilters();
            CoreHelper.refreshLiteners();
        });

        JButton pathSearchButton = MainForm.getInstance().getPathSearchButton();
        pathSearchButton.addActionListener(e -> CoreHelper.pathSearchC());
    }
}

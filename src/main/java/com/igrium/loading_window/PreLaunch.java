package com.igrium.loading_window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import com.mojang.logging.LogUtils;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class PreLaunch implements PreLaunchEntrypoint {

    public static Optional<JFrame> frame = Optional.empty();

    @Override
    public void onPreLaunch() {
        try {
            createAndShowUI();
        } catch (Exception e) {
            LogUtils.getLogger().error("Unable to launch loading screen.");
            return;
        }
    }

    private void createAndShowUI() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JFrame loadingFrame = new JFrame("Minecraft");
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setPreferredSize(new Dimension(256, (int) progressBar.getPreferredSize().getHeight()));

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(new JLabel("Initializing Fabric..."), BorderLayout.NORTH);
        mainPanel.add(progressBar, BorderLayout.CENTER);

        loadingFrame.setContentPane(mainPanel);
        loadingFrame.pack();
        loadingFrame.setLocationRelativeTo(null);
        loadingFrame.setVisible(true);
        frame = Optional.of(loadingFrame);
    }
    
}

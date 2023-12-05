package com.igrium.loading_window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Optional;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class PreLaunch implements PreLaunchEntrypoint {

    public static Optional<JFrame> frame = Optional.empty();

    public static Logger LOGGER = LoggerFactory.getLogger("loading-window");

    @Override
    public void onPreLaunch() {
        if (isMac()) {
            LOGGER.warn("Cannot open loading window on Mac due to OS limitations regarding AWT.");
            return;
        }
        try {
            createAndShowUI();
        } catch (Exception e) {
            LOGGER.error("Unable to launch loading screen.", e.getMessage());
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
    
    private static boolean isMac() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.indexOf("mac") >= 0;
    }

    public static class UIAppender extends AbstractAppender {

        private final Consumer<String> consumer;

        @SuppressWarnings("deprecation")        
        protected UIAppender(String name, Consumer<String> consumer) {
            super(name, null, null);
            this.consumer = consumer;
        }
        

        @Override
        public void append(LogEvent event) {
            consumer.accept(event.getMessage().getFormattedMessage());
        }
        
    }
}

package core;

import inputs.GameAction;
import inputs.Keyboard;
import inputs.Mouse;
import utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class Window extends JFrame {

    @SuppressWarnings("compatibility:-4186116203204807366")
    private static final long serialVersionUID = 1L;

    private final GameAction quit = new GameAction("Exit Game", GameAction.NORMAL);
    private final Dimension minSize;
    private final Dimension maxSize;
    private final String title;
    private final JPanel panel;


    public Window(Dimension minSize, Dimension maxSize, String title) {
        super(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
        this.title = title;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.panel = new JPanel();
    }

    public synchronized void initFrame() {
        this.setMaximumSize(maxSize);
        this.setMinimumSize(minSize);
        this.setPreferredSize(maxSize);
        this.setTitle(title);
        addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.err.close();
                setVisible(false);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        panel.setMinimumSize(minSize);
        panel.setMaximumSize(maxSize);
        panel.setPreferredSize(maxSize);
        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.pack();

        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.requestFocusInWindow();
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
    }

    protected void processWindowEvent(WindowEvent e) {
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
            int exit = JOptionPane.showConfirmDialog(this, "Are you sure?");

            if(exit == JOptionPane.YES_OPTION) {
                setVisible(false);
                Util.exit(0);
            }
        }
    }

    public void input(Keyboard key, Mouse mouse) {
        key.mapToKey(quit, KeyEvent.VK_Q);

        if(quit.isPressed()) {
            Util.closingWindow();
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public JFrame getJFrame() {
        return this;
    }
}

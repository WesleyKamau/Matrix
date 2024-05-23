package Checkers;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.matrix.SimpleMatrix;
import components.matrix.SimpleMatrix1L;

public final class CheckersView1 extends JFrame implements CheckersView {

    private class ButtonPiece {

        private final Icon red = new ImageIcon("img/Checker_Red.png"),
                redKing = new ImageIcon("img/Checker_Red_King.png"),
                black = new ImageIcon("img/Checker_Black.png"),
                blackKing = new ImageIcon("img/Checker_Black_King.png");
        JButton button;
        Piece piece;

        public class PlainJButton extends JButton {

            public PlainJButton() {
                super();
                this.setBorder(null);
                this.setBorderPainted(false);
                this.setContentAreaFilled(false);
                this.setOpaque(false);
            }

            public PlainJButton(Icon i) {
                super(i);
                this.setBorder(null);
                this.setBorderPainted(false);
                this.setContentAreaFilled(false);
                this.setOpaque(false);
            }
        }

        int iconHeight = this.red.getIconHeight();

        ButtonPiece(Piece p) {
            if (p.isEmpty) {
                this.button = new PlainJButton();
            } else {
                if (p.color.equals(Piece.Color.BLACK)) {
                    this.button = new PlainJButton(this.black);
                } else {
                    this.button = new PlainJButton(this.red);
                }
            }
            this.piece = p;
        }

        ButtonPiece() {
            this.button = new PlainJButton();
            this.piece = new Piece();
        }

        private void update() {
            if (!this.piece.isEmpty) {
                if (this.piece.isKing) {
                    if (this.piece.color.equals(Piece.Color.BLACK)) {
                        this.button.setIcon(this.blackKing);
                    } else {
                        this.button.setIcon(this.redKing);
                    }
                } else {
                    if (this.piece.color.equals(Piece.Color.BLACK)) {
                        this.button.setIcon(this.black);
                    } else {
                        this.button.setIcon(this.red);
                    }
                }
            } else {
                this.button.setIcon(null);
            }
        }

        JButton getButton() {
            this.update();
            return this.button;
        }

        Piece getPiece() {
            this.update();
            return this.piece;
        }
    }

    @Override
    public void update() {
        for (ButtonPiece bp : this.board) {
            bp.update();
        }
    }

    private SimpleMatrix<ButtonPiece> board;

    CheckersController controller;

    /**
     * Controller object registered with this view to observe user-interaction
     *
     * /** Useful constants.
     */
    private static final int TEXT_AREA_HEIGHT = 5, TEXT_AREA_WIDTH = 20,
            MAIN_BUTTON_PANEL_GRID_ROWS = 8, MAIN_BUTTON_PANEL_GRID_COLUMNS = 8;

    /**
     * Default constructor.
     */
    public CheckersView1(CheckersModel board) {
        // Create the JFrame being extended

        /*
         * Call the JFrame (superclass) constructor with a String parameter to
         * name the window in its title bar
         */
        super("Checkers");

        this.board = new SimpleMatrix1L<ButtonPiece>();

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                this.board.setElement(i, j,
                        new ButtonPiece(board.board().element(i, j)));
            }
        }

        // Set up the GUI widgets --------------------------------------------

        /*
         * Create main button panel
         */
        JPanel buttonPanel = new JPanel(new GridLayout(
                MAIN_BUTTON_PANEL_GRID_ROWS, MAIN_BUTTON_PANEL_GRID_COLUMNS));
        /*
         * Add the buttons to the main button panel, from left to right and top
         * to bottom
         */
        for (ButtonPiece butt : this.board) {
            buttonPanel.add(butt.getButton());
            butt.button.addActionListener(this);
            butt.button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent evt) {
                    // System.out.println(evt);
                }

                @Override
                public void mouseExited(MouseEvent evt) {
                    //System.out.println(evt);
                }
            });

        }

        /*
         * Organize main window
         */

        this.add(buttonPanel);

        // Set up the main application window --------------------------------

        /*
         * Make sure the main window is appropriately sized, exits this program
         * on close, and becomes visible to the user
         */
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /*
         * Set cursor to indicate computation on-going; this matters only if
         * processing the event might take a noticeable amount of time as seen
         * by the user
         */
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        System.out.println(event);

        for (ButtonPiece bp : this.board) {
            if (bp.button == event.getSource()) {
                System.out.println("FOUND!");
                this.controller.processClickEvent(bp.piece);
            }
        }

        /*
         * Set the cursor back to normal (because we changed it at the beginning
         * of the method body)
         */
        this.setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void registerObserver(CheckersController controller) {
        this.controller = controller;
    }

    @Override
    public void showPossibleMoves(int x, int y) {
        // TODO Auto-generated method stub

    }

    @Override
    public void clearPossibleMoves() {
        // TODO Auto-generated method stub

    }

    @Override
    public void winner(String playerName) {
        // TODO Auto-generated method stub

    }

}
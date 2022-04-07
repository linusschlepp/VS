package a2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;


import javax.swing.*;

public class Browser extends JFrame implements ActionListener {

    private final JButton startButton;

    // Deklaration Ihrer Synchronisations-Hilfsklassen hier:

    CountDownLatch start;
    CountDownLatch stop;

    public Browser(int downloads, CountDownLatch start, CountDownLatch stop) {
        super("Mein Download-Browser");
        this.start = start;
        this.stop = stop;

        // Initialisierung Ihrer Synchronisations-Hilfsklassen hier:

        // Aufbau der GUI-Elemente:
        JProgressBar[] balken = new JProgressBar[downloads];
        JPanel zeilen = new JPanel(new GridLayout(downloads, 1));

        for (int i = 0; i < downloads; i++) {
            JPanel reihe = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 10));
            balken[i] = new JProgressBar(0, 100);
            balken[i].setPreferredSize(new Dimension(500, 20));
            reihe.add(balken[i]);
            zeilen.add(reihe);

            //  downloadList.add(new Download(start, stop, balken[i]));
            new Download(start, stop, balken[i]).start();


            // neue Download-Threads erzeugen und starten
            // ggf. müssen Synchronisations-Objekte im Konstruktor übergeben werden!!
            // balken ist ebenfalls zu übergeben!
        }

        startButton = new JButton("Downloads starten");
        startButton.addActionListener(this);

        this.add(zeilen, BorderLayout.CENTER);
        this.add(startButton, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args)  {

        int downloads = 5;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch stop = new CountDownLatch(downloads);
        new Browser(downloads, start, stop);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Blockierte Threads jetzt laufen lassen:
        start.countDown();
        startButton.setEnabled(false);
        startButton.setSelected(false);
        startButton.setText("Downloads laufen...");

        // Auf Ende aller Download-Threads warten ... erst dann die Beschriftung ändern
        // Achtung, damit die Oberflaeche "reaktiv" bleibt dies in einem eigenen Runnable ausfuehren!

        new Thread(() -> {
            try {
                stop.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            startButton.setText("Ende");
        }).start();

        }
}



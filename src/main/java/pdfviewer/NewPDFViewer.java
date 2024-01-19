package pdfviewer;

import javax.swing.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewPDFViewer extends JPanel {

    private JScrollPane[] pdfScrollPanes;
    private JPanel[] pdfPanels;

    private int currentPage = 0;
    private float zoomFactor = 1.0f;
    private final JPanel card;

    private final JProgressBar progressBar;
    private head head_panel;
    int total_pages = 0;
    File file = null;
    String book = "C:\\Users\\haris\\OneDrive\\Desktop\\TYBOOKS\\Java_Programming.pdf";
    PDDocument document;
    public NewPDFViewer(File file) {

//        this.file = new File(book);
        this.file = file;

        setLayout(new BorderLayout());

        card = new JPanel(new CardLayout());

        // Add previous, next, zoom in, and zoom out buttons
        JButton prevButton = new JButton("Previous");
        prevButton.addActionListener(e -> showPreviousPage());

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> showNextPage());

        JButton zoomInButton = new JButton("Zoom In");
        zoomInButton.addActionListener(e -> zoomIn());

        JButton zoomOutButton = new JButton("Zoom Out");
        zoomOutButton.addActionListener(e -> zoomOut());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(zoomInButton);
        buttonPanel.add(zoomOutButton);

        progressBar = new JProgressBar();
//        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(1000, 4));
        progressBar.setForeground(new Color(0, 0, 102));
        add(progressBar, BorderLayout.PAGE_END);

        // Add button panel to the JFrame
//        add(buttonPanel, BorderLayout.PAGE_START);
        head_panel = new head(this);
        add(head_panel, BorderLayout.PAGE_START);

        add(card, BorderLayout.CENTER);

        addMouseWheelListener(new ZoomMouseWheelListener());

        // Show the first page initially
        loadPDFPagesInBackground();

    }

    private class ZoomMouseWheelListener implements MouseWheelListener {

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (e.getWheelRotation() < 0) {
                // Zoom in when scrolling up
//                zoomIn();
            } else {
                // Zoom out when scrolling down
//                zoomOut();
            }
        }
    }

    private void loadPDFPagesInBackground() {
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                loadPDFPages();
                return null;
            }

            @Override
            protected void done() {
                progressBar.setVisible(false);
            }
        };
        worker.execute();

    }

    private void loadPDFPages() {
        try {
            document = PDDocument.load(this.file);
        } catch (IOException ex) {
            Logger.getLogger(NewPDFViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        int numberOfPages = document.getNumberOfPages();
        total_pages = numberOfPages;
        pdfPanels = new JPanel[numberOfPages];
        pdfScrollPanes = new JScrollPane[numberOfPages];
        PDFRenderer renderer = new PDFRenderer(document);
        for (int i = 0; i < numberOfPages; i++) {
            
            final int pageIndex = i;
            Runnable rh = new Runnable() {
                @Override
                public void run() {
                    addPages(pageIndex, document, renderer);
                    
                }
                
            };
            new Thread(rh).run();
            
            // Update progress
            int progress = (int) (((i + 1.0) / numberOfPages) * 100);
            progressBar.setValue(progress);
            
        }
        
        // Do not close the document here; let it remain open for the lifetime of your application.
    }

    private void addPages(int pageIndex, PDDocument document, PDFRenderer renderer) {

        PDPage page = document.getPage(pageIndex);
        Dimension pageSize = new Dimension((int) page.getMediaBox().getWidth(),
                (int) page.getMediaBox().getHeight());

        zoomFactor = 1.2f;
        pdfPanels[pageIndex] = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int width = getWidth();
                int height = getHeight();

                int centerX = (int) (pageSize.width * zoomFactor) / 2;
                int centerY = (int) (pageSize.height * zoomFactor) / 2;

                int x = (width / 2) - centerX + 20;
                int y = (height / 2) - centerY;

                Graphics2D g2d = (Graphics2D) g.create();

                g2d.translate(x, y);
                g2d.scale(zoomFactor, zoomFactor);

                try {
                    renderer.renderPageToGraphics(pageIndex, g2d);
                } catch (IOException ex) {
                }

                g2d.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                int preferredWidth = (int) (pageSize.width * zoomFactor);
                int preferredHeight = (int) (pageSize.height * zoomFactor);
                return new Dimension(preferredWidth, preferredHeight);
            }
        };

        pdfPanels[pageIndex].setPreferredSize(pageSize);
        pdfScrollPanes[pageIndex] = new JScrollPane(pdfPanels[pageIndex]);
        card.add(pdfScrollPanes[pageIndex], String.valueOf(pageIndex));

        if (pageIndex == 0) {

            showPage(currentPage);
        }
        pdfPanels[pageIndex].revalidate();
        pdfPanels[pageIndex].repaint();
    }

    public void showPage(int pageIndex) {
        CardLayout mycard = (CardLayout) card.getLayout();
        mycard.show(card, String.valueOf(pageIndex));

//        head_panel.setFileName("PDF Viewer - Page " + (pageIndex+1));
//        head_panel.setFileName(this.file.getName() + "- Page " + (pageIndex+1) +"/" + total_pages);
        head_panel.setFileName(this.file.getName());
        head_panel.setTotalPage(total_pages);
        head_panel.setCurrentPage(pageIndex + 1);
        card.revalidate();
        card.repaint();
        currentPage = pageIndex;
    }

    public void showPreviousPage() {
        if (currentPage > 0) {
            currentPage--;
            showPage(currentPage);
        }
    }

    public void showNextPage() {
        if (currentPage < pdfPanels.length - 1) {
            currentPage++;
            showPage(currentPage);
        }
    }

    public void zoomIn() {
        zoomFactor += 0.1f;
        updateZoom();
        card.revalidate();
        card.repaint();
    }

    public void zoomOut() {
        if (zoomFactor > 0.1f) {
            zoomFactor -= 0.1f;
            updateZoom();
            card.revalidate();
            card.repaint();
        }
    }

    private void updateZoom() {
        for (JPanel pdfPanel : pdfPanels) {
            pdfPanel.revalidate();
            pdfPanel.repaint();
        }
    }
    public void closeDocuemnt()
    {
        try {
            document.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
//    public static void main(String[] args) throws IOException {
//
//        String book = "C:\\Users\\haris\\OneDrive\\Desktop\\TYBOOKS\\Java_Programming.pdf";
////        NewPDFViewer pdfViewer = new NewPDFViewer(new File(book));
////       
////        Frame fr = new JFrame();
////        fr.setLayout(new BorderLayout());
////        fr.add(pdfViewer, BorderLayout.CENTER);
////        fr.setSize(800, 800);
////        fr.setLocationRelativeTo(null);
////        
//////        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        fr.setVisible(true);
//
//        File file = new File(book);
//        Desktop desktop = Desktop.getDesktop();
//        desktop.open(file);
//        //desktop.print(file);
//
//    }
}

package com.mbs.adminlogin;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;

public class Pdfgen {

    public static void gen(String name, int ticketbooked, String st, int rc) {
        try {
            // Generate the ticket as a PDF
        	String dest = "E:/Games/ticket_" + System.currentTimeMillis() + ".pdf";


            // Ensure the directory exists
            File file = new File(dest);
            file.getParentFile().mkdirs();

            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
            document.open();

            // Creating a method to center the text
            Paragraph title = new Paragraph("Ticket booked successfully");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph line1 = new Paragraph("\n-------------------------------------");
            line1.setAlignment(Element.ALIGN_CENTER);
            document.add(line1);

            Paragraph ticket = new Paragraph("            Ticket                    ");
            ticket.setAlignment(Element.ALIGN_CENTER);
            document.add(ticket);

            Paragraph line2 = new Paragraph("-------------------------------------");
            line2.setAlignment(Element.ALIGN_CENTER);
            document.add(line2);

            Paragraph movieName = new Paragraph("Moviename: " + name);
            movieName.setAlignment(Element.ALIGN_CENTER);
            document.add(movieName);

            Paragraph bookedSeats = new Paragraph("BookedSeats: " + ticketbooked);
            bookedSeats.setAlignment(Element.ALIGN_CENTER);
            document.add(bookedSeats);

            Paragraph showTime = new Paragraph("Showtime: " + st);
            showTime.setAlignment(Element.ALIGN_CENTER);
            document.add(showTime);

            Paragraph secretCode = new Paragraph("Secretcode: " + rc);
            secretCode.setAlignment(Element.ALIGN_CENTER);
            document.add(secretCode);

            Paragraph line3 = new Paragraph("-------------------------------------");
            line3.setAlignment(Element.ALIGN_CENTER);
            document.add(line3);

            document.close();
            writer.close();
            System.out.println("PDF generated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

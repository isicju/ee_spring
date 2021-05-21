package com.example.demo.services;

import com.example.demo.dao.EmployeeDetails;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileOutputStream;

import static java.awt.Color.BLACK;
import static java.awt.Color.GREEN;

@Service
public class PdfGeneratorService {
    public static final String DEST = "hello.pdf";

    public String  generatePdf(EmployeeDetails employeeDetails, String message) {
        try {
            File file = new File(DEST);
            if (!file.exists()) {
                file.createNewFile();
            }
            Document doc = new Document();
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(DEST));
            doc.open();

            Paragraph firstNameRecord = new Paragraph("Employee first name: " + employeeDetails.getFirstName(),
                    new Font(Font.NORMAL, 14, Font.BOLDITALIC, BLACK));
            Paragraph lastNameRecord = new Paragraph("Employee last name: " + employeeDetails.getLastName(),
                    new Font(Font.NORMAL, 14, Font.BOLDITALIC, BLACK));
            Paragraph messageToPrint = new Paragraph("Messsage:  " + message, new Font(Font.NORMAL, 14,
                    Font.BOLDITALIC, GREEN));

            PdfContentByte cb = writer.getDirectContent();

            PdfPTable table = new PdfPTable(2);

            table.addCell("Salary");
            table.addCell(String.valueOf(employeeDetails.getSalary()));

            table.addCell("Location");
            table.addCell(employeeDetails.getLocation());

            table.addCell("Manager");
            table.addCell(employeeDetails.getManagerName());

            table.addCell("Department");
            table.addCell(employeeDetails.getDepartmentName());

            table.addCell("Job Title");
            table.addCell(employeeDetails.getJobTitle());

            table.setTotalWidth(400);

            table.writeSelectedRows(0, 5, 0, -1, 25, 700, cb);


            doc.add(firstNameRecord);
            doc.add(lastNameRecord);
            doc.add(messageToPrint);


            doc.close();
            writer.flush();
            writer.close();
            return Base64Utils.encodeToString( FileUtils.readFileToByteArray(file));
        } catch (Exception e) {
            throw new RuntimeException("Pdf generation failed: " + e.getMessage());
        }
    }

//    public String generateBase64() throws Exception{
//        String filePath = "/home/user/sample.pdf";
//        File pdfFile = new File(filePath);
//        byte[] encoded = Files.readAllBytes(Paths.get(pdfFile.getAbsolutePath()));
//        Base64.Encoder enc = Base64.getEncoder();
//        byte[] strenc = enc.encode(encoded);
//        String encode = new String(strenc, "UTF-8");
//        Base64.Decoder dec = Base64.getDecoder();
//        byte[] strdec = dec.decode(encode);
//        OutputStream out = new FileOutputStream(DEST);
//        out.write(strdec);
//        out.close();
//        return null;
////        return
//    }

//    public static void main(String[] args) throws  Exception{
//         byte[]  bytes = FileUtils.readFileToByteArray(new File(DEST));
//       String bytesStr =   Base64Utils.encodeToString(bytes);
//        System.out.println("");
//    }
//
//    public String generateBase64Pdf(EmployeeDetails employeeDetails, String message){
//        byte[] bytes = generatePdf(employeeDetails, message);
//        return new String(Base64Utils.decodeFromString( Base64Utils.encodeToString(bytes)));
//    }


}


package com.test.autobusiness.services.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.test.autobusiness.dto.car.CarResponse;
import com.test.autobusiness.services.ExportService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class PdfExportServiceImpl implements ExportService {

    private static final int FONT_SIZE_SMALL = 16;
    private static final int FONT_SIZE_BIG = 32;
    private static final int OFFSET = 40;
    Document document;
    private Font headerFont;
    private Font columnNameFont;
    private Font columnFont;
    @Value("${export.car.columns}")
    private String[] columns;

    @Override
    public Resource getExportFile(List<CarResponse> carResponses) throws Exception {

        File file = createFile(carResponses);
        return new UrlResource(Paths.get(file.toURI()).toUri());
    }

    private File createFile(List<CarResponse> carResponses) throws Exception {

        document = new Document();
        headerFont = new Font(Font.FontFamily.HELVETICA, FONT_SIZE_BIG, Font.BOLD);
        columnNameFont = new Font(Font.FontFamily.HELVETICA, FONT_SIZE_SMALL,
                Font.ITALIC | Font.UNDERLINE | Font.BOLD);
        columnFont = new Font(Font.FontFamily.HELVETICA, FONT_SIZE_SMALL);
        File file = new File("cars.pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();

        Paragraph title = new Paragraph("Cars", headerFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(OFFSET);
        document.add(title);

        for (CarResponse carResponse : carResponses) {

            writeField(columns[0], carResponse.getId());
            writeField(columns[1], carResponse.getBrand());
            writeField(columns[2], carResponse.getModel());
            writeField(columns[3], carResponse.getGeneration());
            writeField(columns[4], carResponse.getBody());
            writeField(columns[5], carResponse.getDriveUnit());
            writeField(columns[6], carResponse.getTransmission());
            writeField(columns[7], carResponse.getEngineType());
            writeField(columns[12], carResponse.getPrice() + " " + carResponse.getCurrency());
            writeField(columns[9], carResponse.getEngineVolume());
            writeField(columns[10], carResponse.getAge());
            writeField(columns[11], carResponse.getMileAge());
            writeField(columns[13], carResponse.getDetailsResponses()
                    .stream()
                    .map(detailsResponse ->
                            "\n" + detailsResponse.getDetailType() + " : " + detailsResponse.getDetailName())
                    .collect(Collectors.toList()));
            Paragraph spacing = new Paragraph();
            spacing.setSpacingBefore(OFFSET);
            document.add(spacing);
        }

        Paragraph footer = new Paragraph("- End of the records list -");
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setSpacingBefore(OFFSET);
        document.add(footer);
        document.close();

        return file;
    }

    private void writeField(String columnName, Object value) throws Exception {

        Paragraph date = new Paragraph(columnName, columnNameFont);
        date.setFont(columnFont);
        Phrase phrase = new Phrase();
        phrase.add(new Chunk(": " + value.toString()));
        date.add(phrase);
        document.add(date);
    }
}

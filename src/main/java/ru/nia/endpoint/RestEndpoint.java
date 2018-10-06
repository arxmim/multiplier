package ru.nia.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apfloat.Apfloat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nia.service.MultiplierService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class RestEndpoint {

    private MultiplierService service;

    public RestEndpoint(MultiplierService service) {
        this.service = service;
    }

    @RequestMapping("/doRecurCos")
    public List<String> doRecurCos(@RequestParam(value = "step") BigDecimal step_size, int step_count) {
        log.info("invoke COS for params: step_size=" + step_size + ", step_count=" + step_count);
        ArrayList<String> strings = new ArrayList<>();
        Apfloat first = service.getCos(step_size);
        Apfloat second = service.getCos(step_size.multiply(new BigDecimal(2)));

        strings.add(first.toString(true));
        strings.add(second.toString(true));
        for (int i = 0; i < step_count - 2; i++) {
            Apfloat third = service.doRecur(second, first, step_size);
            strings.add(third.toString(true));
            first = second;
            second = third;
        }
        return strings;
    }

    @RequestMapping("/doRecurSin")
    public List<String> doRecurSin(@RequestParam(value = "step") BigDecimal step_size, int step_count) {
        log.info("invoke SIN for params: step_size=" + step_size + ", step_count=" + step_count);
        ArrayList<String> strings = new ArrayList<>();
        Apfloat first = service.getSin(step_size);
        Apfloat second = service.getSin(step_size.multiply(new BigDecimal(2)));

        strings.add(first.toString(true));
        strings.add(second.toString(true));
        for (int i = 0; i < step_count - 2; i++) {
            Apfloat third = service.doRecur(second, first, step_size);
            strings.add(third.toString(true));
            first = second;
            second = third;
        }
        return strings;
    }

    @RequestMapping("/toExcel")
    public byte[] toExcel(@RequestParam(value = "step") BigDecimal step_size, int step_count, boolean isCos) throws IOException {
        log.info("isCos= " + isCos);
        List<String> result;
        if (isCos) {
            result = doRecurCos(step_size, step_count);
        } else {
            result = doRecurSin(step_size, step_count);
        }
        XSSFWorkbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("result");

        int i = 0;
        for (String res : result) {
            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue(i);
            cell = row.createCell(1);
            cell.setCellValue(res);
            i++;
        }

        // Меняем размер столбца
//        sheet.autoSizeColumn(0);
//        sheet.autoSizeColumn(1);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        book.write(stream);
        return stream.toByteArray();
    }
}

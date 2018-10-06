package ru.nia.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.apfloat.Apfloat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nia.service.MultiplierService;

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
}

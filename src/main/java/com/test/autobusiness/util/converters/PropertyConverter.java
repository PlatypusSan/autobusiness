package com.test.autobusiness.util.converters;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.test.autobusiness.services.DealershipService;

//@Component
public class PropertyConverter extends AbstractBeanField {

    //@Autowired
    private DealershipService dealershipService;


    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return s;
    }
}

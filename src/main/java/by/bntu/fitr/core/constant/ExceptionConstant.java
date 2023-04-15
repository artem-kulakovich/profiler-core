package by.bntu.fitr.core.constant;

public interface ExceptionConstant {
    String PROPERTY_FILE_NOF_FOUND_EXCEPTION = "Property file with name profiler.properties not found. Please check if this file exists!";
    String CANT_LOAD_PROPERTY_FILE_EXCEPTION = "Property file exists, but couldn't load. Please check if this file hasnt problem!";
    String NOT_VALID_PACKAGE_FOR_SCAN_ATTR = "Package for scan is not valid in profiler.property. Please check!";
    String ATTRIBUTE_NOT_FOUND_EXCEPTION = "Attribute not found, please check if this attribute exists!";
    String CANT_CONNECT_TO_DATABASE_EXCEPTION = "Cant connect to database, please check if connection parameters correct";
    String INCORRECT_VALUE_IN_PROPERTY_FILE_EXCEPTION = "Incorrect value in property file exception! Please, check the documentation!";
    String CANT_GET_METRIC_REGISTRY_EXCEPTION = "Couldn't get metric registry, check properties.";
}

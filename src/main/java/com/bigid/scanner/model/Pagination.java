package com.bigid.scanner.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Pagination implements Serializable {
    int total_records;
    int total_pages;
    int current_page;
}

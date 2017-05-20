package com.hadly.spi.impl;

import com.hadly.spi.Search;

import java.util.List;

/**
 * Created by hadly on 2017/2/21.
 */
public class FileSearch implements Search {
    @Override
    public List search() {
        System.out.println("search in file");
        return null;
    }
}

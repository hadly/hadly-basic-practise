package com.hadly.pattern.chain.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hadly
 * @since 2018/3/13
 */
public class FilterChain {
    private List<Filter> filterList = new ArrayList<>();
    private int position;

    public void addFilter(Filter filter){
        filterList.add(filter);
    }

    public void doFilter(String request){
        if (position == filterList.size() -1){
            System.out.println("the end of the filter.");
            return;
        }else {
            position ++;
            filterList.get(position).doFilter(this, request);
        }
    }
}

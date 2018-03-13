package com.hadly.pattern.chain.filter;

/**
 * @author hadly
 * @since 2018/3/13
 */
public class FilterChainTest {
    public static void main(String[] args) {
        FilterChain filterChain = new FilterChain();
        Filter filter1 = new StatFilter();
        Filter filter2 = new LogFilter();
        filterChain.addFilter(filter1);
        filterChain.addFilter(filter2);
        filter1.doFilter(filterChain, "baiduRequest");
    }
}

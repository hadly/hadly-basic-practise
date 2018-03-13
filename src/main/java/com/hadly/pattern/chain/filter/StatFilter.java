package com.hadly.pattern.chain.filter;

/**
 * @author hadly
 * @since 2018/3/13
 */
public class StatFilter implements Filter{
    @Override
    public void doFilter(FilterChain filterChain, String request) {
        System.out.println("stat filter process, " + request);
        filterChain.doFilter(request);
    }
}

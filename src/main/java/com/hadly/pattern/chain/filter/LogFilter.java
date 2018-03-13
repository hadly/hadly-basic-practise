package com.hadly.pattern.chain.filter;

/**
 * @author hadly
 * @since 2018/3/13
 */
public class LogFilter implements Filter{
    @Override
    public void doFilter(FilterChain filterChain, String request) {
        System.out.println("log filter processed, " + request);
        filterChain.doFilter(request);
    }
}

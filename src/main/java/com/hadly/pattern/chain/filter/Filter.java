package com.hadly.pattern.chain.filter;

/**
 * @author hadly
 * @since 2018/3/13
 */
public interface Filter {
    void doFilter(FilterChain filterChain, String request);
}

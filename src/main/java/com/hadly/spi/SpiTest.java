package com.hadly.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by hadly on 2017/2/21.
 * <p>
 * 注意：
 * 1.如果将META-INF放到和src同目录下，没办法找到相应的对应的实现类,
 * 需要将META-INF放到src下面才可以正常运行
 */
public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<Search> loader = ServiceLoader.load(Search.class);
        Iterator<Search> it = loader.iterator();
        print(loader);
        while (it.hasNext()) {
            Search search = it.next();
            search.search();
        }
    }

    private static void print(Object o) {
        System.out.println(o);
    }
}

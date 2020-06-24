package org.personal.springfluxdemo.reactorlib;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TestReactorLib
{
    @Test
    public void testDefinition()
    {
        Flux.just(1, 2, 3, 4);
        Mono.just(1);

        Flux.fromArray(new Integer[] { 1, 2, 3, 4, 5 });
        Flux.fromIterable(Lists.newArrayList(1, 2, 3, 4));

        Stream<Integer> stream = Stream.of(1, 2, 3, 5);
        Flux.fromStream(stream);
    }

    @Test
    public void testSubscribe()
    {
        Flux.fromStream(Stream.of(1, 2, 3, 5)).subscribe(System.out::println);
    }

    @Test
    public void testMapOperator()
    {
        Flux.just(1, 2, 3, 4).map(x -> x + x).subscribe(System.out::println);
    }

    @Test
    public void testFlatMapOperator()
    {
        List<Integer> list1 = Lists.newArrayList(11, 12, 13, 14, 15);
        List<Integer> list2 = Lists.newArrayList(21, 22, 23, 24, 25);
        List<Integer> list3 = Lists.newArrayList(31, 32, 33, 34, 35);

        Flux.just(list1, list2, list3).flatMap(Flux::fromIterable).subscribe(System.out::println);
    }
}

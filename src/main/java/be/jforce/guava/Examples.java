package be.jforce.guava;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.*;
import com.google.common.math.DoubleMath;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Examples {

    Collection<Block> filter() {
        Collection<Block> blocks = Lists.newArrayList(
                new Block(Color.BLUE),
                new Block(Color.RED),
                new Block(Color.BLUE),
                new Block(Color.BLUE),
                new Block(Color.RED));

        return Collections2.filter(blocks, colorIs(Color.BLUE));
    }

    Predicate<Block> colorIs(final Color color) {
        return new Predicate<Block>() {
            @Override
            public boolean apply(Block block) {
                return block.color == color;
            }
        };
    }

    List<Disc> transform() {
        List<Block> blocks = Lists.newArrayList(
                new Block(Color.BLUE),
                new Block(Color.RED),
                new Block(Color.BLUE),
                new Block(Color.BLUE),
                new Block(Color.RED));

        return Lists.transform(blocks, toDisc());
    }

    Function<Block, Disc> toDisc() {
        return new Function<Block, Disc>() {
            @Override
            public Disc apply(Block block) {
                return new Disc(block.color);
            }
        };
    }

    Multimap<Color, Block> index() {
        Collection<Block> blocks = Lists.newArrayList(
                new Block(Color.BLUE),
                new Block(Color.RED),
                new Block(Color.BLUE),
                new Block(Color.BLUE),
                new Block(Color.RED));

        return Multimaps.index(blocks, byColor());
    }

    Function<Block, Color> byColor() {
        return new Function<Block, Color>() {
            @Override
            public Color apply(Block block) {
                return block.color;
            }
        };
    }

    List<Block> sort() {
        List<Block> blocks = Lists.newArrayList(
                new Block(Color.BLUE),
                new Block(Color.RED),
                new Block(Color.BLUE),
                new Block(Color.BLUE),
                new Block(Color.RED));

        return Ordering.natural().onResultOf(colorAsRGB()).sortedCopy(blocks);
    }

    Function<Block, Integer> colorAsRGB() {
        return new Function<Block, Integer>() {
            @Override
            public Integer apply(Block input) {
                return input.color.getRGB();
            }
        };
    }

    static BiMap<String, Color> NAME_TO_COLOR = HashBiMap.create();

    static {
        NAME_TO_COLOR.put("red", Color.RED);
        NAME_TO_COLOR.put("green", Color.GREEN);
        NAME_TO_COLOR.put("blue", Color.BLUE);
    }

    Color nameToColor(String name) {
        return NAME_TO_COLOR.get(name);
    }

    String colorToName(Color color) {
        return NAME_TO_COLOR.inverse().get(color);
    }

    void cache() {
        Cache<String, Color> cache = CacheBuilder.newBuilder()
                .weakValues() // Entries are garbage collected when no longer referenced
                .softValues() // Entries are garbage collected when no longer referenced and out of memory
                .maximumSize(1000) // Max number of entries, entries are evicted using LRU-like algorithm
                .expireAfterWrite(24, TimeUnit.HOURS) // Entries are evicted after time expires
                .build();
    }

    static ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    static Random random = new Random();
    static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    static double averageTemperatureSync(String... locations) {
        List<Integer> temperatures = Lists.transform(Lists.newArrayList(locations),
                new Function<String, Integer>() {
                    @Override
                    public Integer apply(String location) {
                        return findTemperatureAt(location);
                    }
                }
        );
        return DoubleMath.mean(temperatures);
    }

    static double averageTemperatureAsync(String... locations) {
        List<ListenableFuture<Integer>> tasks = Lists.transform(Lists.newArrayList(locations),
                new Function<String, ListenableFuture<Integer>>() {
                    @Override
                    public ListenableFuture<Integer> apply(String location) {
                        return service.submit(findTemperatureTask(location));
                    }
                }
        );
        try {
            List<Integer> temperatures = Futures.allAsList(tasks).get();
            return DoubleMath.mean(temperatures);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static Callable<Integer> findTemperatureTask(final String location) {
        return new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return findTemperatureAt(location);
            }
        };
    }

    public static void main(String[] args) {
        System.out.println("Synchronous:");
        long start = System.currentTimeMillis();
        double averageSync = averageTemperatureSync("Brussels", "Paris", "London");
        System.out.println(dateFormat.format(new Date()) + " - Average: " + averageSync + "°C");
        long duration = System.currentTimeMillis() - start;
        System.out.println("Total time: " + duration + "ms");
        System.out.println();

        System.out.println("Asynchronous:");
        start = System.currentTimeMillis();
        double averageAsync = averageTemperatureAsync("Brussels", "Paris", "London");
        System.out.println(dateFormat.format(new Date()) + " - Average: " + averageAsync + "°C");
        duration = System.currentTimeMillis() - start;
        System.out.println("Total time: " + duration + "ms");
    }

    static int findTemperatureAt(String location) {
        try {
            Thread.sleep(1500);
            int temperature = 10 + random.nextInt(15);
            System.out.println(dateFormat.format(new Date()) + " - Temperature in " + location + ": " + temperature + "°C");
            return temperature;
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted", e);
        }
    }


    static class Block {
        Color color;

        Block(Color color) {
            this.color = color;
        }
    }

    static class Disc {
        Color color;

        Disc(Color color) {
            this.color = color;
        }
    }
}

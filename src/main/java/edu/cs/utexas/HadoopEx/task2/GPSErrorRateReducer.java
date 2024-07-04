// This class was written with the help of GPT
package task2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class GPSErrorRateReducer extends Reducer<Text, IntWritable, Text, Text> {
    private Map<String, Integer> errorCountMap = new HashMap<>();
    private Map<String, Integer> totalCountMap = new HashMap<>();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int errorCount = 0;
        int totalCount = 0;

        for (IntWritable val : values) {
            errorCount += val.get();
            totalCount += 1;
        }

        errorCountMap.put(key.toString(), errorCountMap.getOrDefault(key.toString(), 0) + errorCount);
        totalCountMap.put(key.toString(), totalCountMap.getOrDefault(key.toString(), 0) + totalCount);
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        PriorityQueue<Map.Entry<String, Double>> topFive = new PriorityQueue<>((a, b) -> Double.compare(a.getValue(), b.getValue()));

        for (String medallion : errorCountMap.keySet()) {
            int errorCount = errorCountMap.get(medallion);
            int totalCount = totalCountMap.get(medallion);
            double errorRate = (double) errorCount / totalCount;

            topFive.add(new SimpleEntry(medallion, errorRate));
            if (topFive.size() > 5) {
                topFive.poll();
            }
        }

        while (!topFive.isEmpty()) {
            Map.Entry<String, Double> entry = topFive.poll();
            context.write(new Text(entry.getKey()), new Text(String.format("%.2f%%", entry.getValue() * 100)));
        }
    }

    private static class SimpleEntry implements Map.Entry<String, Double> {
        private final String key;
        private Double value;

        public SimpleEntry(String key, Double value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Double getValue() {
            return value;
        }

        @Override
        public Double setValue(Double value) {
            Double oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}

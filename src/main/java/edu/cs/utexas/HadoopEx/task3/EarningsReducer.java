package task3;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class EarningsReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
    private Map<String, Double> earningsMap = new HashMap<>();

    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double sumEarnings = 0.0;
        int count = 0;

        for (DoubleWritable val : values) {
            sumEarnings += val.get();
            count++;
        }

        double averageEarnings = sumEarnings / count;
        earningsMap.put(key.toString(), averageEarnings);
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        PriorityQueue<Map.Entry<String, Double>> topTen = new PriorityQueue<>((a, b) -> Double.compare(a.getValue(), b.getValue()));

        for (Map.Entry<String, Double> entry : earningsMap.entrySet()) {
            topTen.add(entry);
            if (topTen.size() > 10) {
                topTen.poll();
            }
        }

        while (!topTen.isEmpty()) {
            Map.Entry<String, Double> entry = topTen.poll();
            context.write(new Text(entry.getKey()), new DoubleWritable(entry.getValue()));
        }
    }
}

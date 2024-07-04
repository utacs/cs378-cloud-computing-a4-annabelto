package task3;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class EarningsMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    private Text driver = new Text();
    private DoubleWritable earningsPerMinute = new DoubleWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        if (fields.length < 17) {
            return; // Skip lines that do not have all fields
        }

        driver.set(fields[1]);
        try {
            double totalAmount = Double.parseDouble(fields[16]);
            double tripTimeInSecs = Double.parseDouble(fields[4]);
            double earningsPerMin = totalAmount / (tripTimeInSecs / 60.0);
            earningsPerMinute.set(earningsPerMin);
            context.write(driver, earningsPerMinute);
        } catch (NumberFormatException e) {
            // Ignore parsing exceptions
        }
    }
}

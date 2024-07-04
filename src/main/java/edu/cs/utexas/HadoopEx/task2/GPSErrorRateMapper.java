// This class was written with the help of GPT
package task2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class GPSErrorRateMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private final static IntWritable zero = new IntWritable(0);
    private Text medallion = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        if (fields.length < 17) {
            return; // Skip lines that do not have all fields
        }

        medallion.set(fields[0]);

        try {
            double pickupLongitude = fields[6].isEmpty() ? 0 : Double.parseDouble(fields[6]);
            double pickupLatitude = fields[7].isEmpty() ? 0 : Double.parseDouble(fields[7]);
            double dropoffLongitude = fields[8].isEmpty() ? 0 : Double.parseDouble(fields[8]);
            double dropoffLatitude = fields[9].isEmpty() ? 0 : Double.parseDouble(fields[9]);

            if (pickupLongitude == 0 || pickupLatitude == 0 || dropoffLongitude == 0 || dropoffLatitude == 0) {
                context.write(medallion, one);
            } else {
                context.write(medallion, zero);
            }
        } catch (NumberFormatException e) {
            // Ignore parsing exceptions
        }
    }
}

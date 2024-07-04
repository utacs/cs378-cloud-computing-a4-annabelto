// This class was written with the help of GPT
package task1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GPSErrorMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private IntWritable hour = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        if (fields.length < 17) {
            return; // Skip lines that do not have all fields
        }

        try {
            double pickupLongitude = fields[6].isEmpty() ? 0 : Double.parseDouble(fields[6]);
            double pickupLatitude = fields[7].isEmpty() ? 0 : Double.parseDouble(fields[7]);
            double dropoffLongitude = fields[8].isEmpty() ? 0 : Double.parseDouble(fields[8]);
            double dropoffLatitude = fields[9].isEmpty() ? 0 : Double.parseDouble(fields[9]);

            if (pickupLongitude == 0 || pickupLatitude == 0 || dropoffLongitude == 0 || dropoffLatitude == 0) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date pickupDate = dateFormat.parse(fields[2]);
                hour.set(pickupDate.getHours());
                context.write(hour, one);
            }
        } catch (NumberFormatException | ParseException e) {
            // Ignore parsing exceptions
        }
    }
}

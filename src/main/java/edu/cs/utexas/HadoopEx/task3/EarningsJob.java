package task3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class EarningsJob {
    public static Job createJob(Configuration conf, String inputPath, String outputPath) throws Exception {
        Job job = Job.getInstance(conf, "Earnings per Minute");
        job.setJarByClass(EarningsJob.class);
        job.setMapperClass(EarningsMapper.class);
        job.setReducerClass(EarningsReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);
        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));
        return job;
    }
}

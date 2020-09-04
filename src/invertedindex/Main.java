package invertedindex;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;


public class Main extends Configured implements Tool 
{
	@Override
	public int run(String args[]) throws Exception
	{
		Job job = Job.getInstance(getConf());
		job.setJobName("invertedindex");
		job.setJarByClass(Main.class);
		
		job.getConfiguration().set("mapreduce.output.textoutputformat.seperator","|");
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.setMapperClass(Map.class);
		job.setCombinerClass(Reduce.class);
		job.setReducerClass(Reduce.class);
		
		Path inputFilePath = new Path("D:/SometimeOf20/Coursera/MapReduce/PluralSight/src");
		Path outputFilePath = new Path("D:/SometimeOf20/Coursera/MapReduce/PluralSight/data/output");
		
		
		FileInputFormat.addInputPath(job, inputFilePath);
		FileOutputFormat.setOutputPath(job,outputFilePath);
		
		FileInputFormat.setInputDirRecursive(job, true);
		
		return job.waitForCompletion(true) ? 0 : 1;
	}
	
	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Main(),args);
		System.exit(exitCode);
	}
}

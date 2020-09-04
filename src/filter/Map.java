package filter;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Map extends Mapper<LongWritable, Text, NullWritable, Text>
{
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException
	{
		String line = value.toString();
		String[] data = line.split("\t");
		
		try
		{
			if(Integer.parseInt(data[3]) >= 500)
			{
				context.write(NullWritable.get(),value);
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
package wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * reducer处理map输出的数据，相同的数据为一个可循环量，遍历获取总共个数，然后写入context
 * @author dj
 *
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	protected void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException
	{
		Integer count = 0;
		for(IntWritable value:values)
		{
			count += value.get();			
		}
		context.write(key, new IntWritable(count));
	}
}

package dj;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	protected void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException {
		String line  = value.toString();	//得到输入的每行数据
		String[] words = line.split(" ");	//对每行数据进行拆分
		for(String word: words)		//遍历数据，输出
		{
			context.write(new Text(word), new IntWritable(1));
		}
	}
}

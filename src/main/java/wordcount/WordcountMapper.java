package wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	/**
	 * mapreduce框架每读取一行数据就会调用次方法
	 * 该方法将一行数据进行分割，然后遍历数据，将其与“1”写入context中，
	 * 还需要进行排序便于不同机器上输出的数据进行整合，合并相同的数据获得其出现的次数
	 */
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

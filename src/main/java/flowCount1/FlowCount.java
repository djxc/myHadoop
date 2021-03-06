package flowCount1;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import flowCount.FlowBean;


public class FlowCount {
	static class FlowCountMapper extends Mapper<LongWritable, Text,
	Text, FlowBean>{
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, 
				Text, FlowBean>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String[] fields = line.split("\t");
			String phoneNbr = fields[0];
			long upFlow = Long.parseLong(fields[1]);
			long dFlow = Long.parseLong(fields[2]);			
			context.write(new Text(phoneNbr), new FlowBean(upFlow, dFlow));			
		}		
	}
	
	static class FlowCountReducer extends Reducer<Text, FlowBean,
	Text, FlowBean>{
		@Override
		protected void reduce(Text key, Iterable<FlowBean> values, 
				Reducer<Text, FlowBean, Text, FlowBean>.Context context)
				throws IOException, InterruptedException {
			long sum_upFlow = 0;
			long sum_dFlow = 0;
			for (FlowBean bean: values) {
				sum_upFlow += bean.getUpFlow();
				sum_dFlow += bean.getdFlow();
			}
			FlowBean resultBean = new FlowBean(sum_upFlow, sum_dFlow);
			context.write(key, resultBean);
		}
	}
	
	public static void main(String[] args) throws Exception{
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		
		job.setJarByClass(FlowCount.class);
		
		job.setMapperClass(FlowCountMapper.class);
		job.setReducerClass(FlowCountReducer.class);
		
		job.setPartitionerClass(ProvincePartitioner.class);
		job.setNumReduceTasks(5);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FlowBean.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);
		//设置输入输出参数
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		boolean b = job.waitForCompletion(true);
		if(!b)
		{
			System.out.println("task fail!");
		}else
		{
			System.out.println("ok");
		}
	}
	
}

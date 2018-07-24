package com.dj.demo1_hadoop;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FlowCount {
	static class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean>{
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FlowBean>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String[] fields = line.split("***"); 
			String phonNbr = fields[0];
			long upFlow = Long.parseLong(fields[1]);

		}
	}
}

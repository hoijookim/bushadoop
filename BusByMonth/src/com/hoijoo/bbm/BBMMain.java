package com.hoijoo.bbm;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class BBMMain {
	public static void main(String[] args) {
		try {
			Configuration c = new Configuration();
			Job j = Job.getInstance(c);		
			
			j.setMapperClass(BBMMapper.class);
			j.setCombinerClass(BBMReducer.class);
			j.setReducerClass(BBMReducer.class);
			
			j.setOutputKeyClass(Text.class);
			j.setOutputValueClass(LongWritable.class);
			
			String fName = null;
			for (int i = 15; i <= 19; i++) {
				fName = String.format("/bus20%02d.csv", i);
				FileInputFormat.addInputPath(j, new Path(fName));
			}
			FileOutputFormat.setOutputPath(j, new Path("/bbmResult"));
			j.waitForCompletion(true);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

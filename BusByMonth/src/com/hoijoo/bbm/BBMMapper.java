package com.hoijoo.bbm;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//날짜,번호,정류장명,탄,내린
//20150101,100번(하계동~용산구청),명륜3가.성대입구,64,102
public class BBMMapper extends Mapper<Object, Text, Text, LongWritable> {
	
	private static final Text MONTH = new Text();
	private static final LongWritable SUM = new LongWritable();
	
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		
		String[] line = value.toString().split(",");
		
		MONTH.set(line[0].substring(4,6));
		SUM.set(Integer.parseInt(line[3]) + Integer.parseInt(line[4]));
		context.write(MONTH, SUM);
	}
}

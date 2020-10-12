package com.hoijoo.bh.main;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//날짜,번호,정류장명,탄,내린
//20150101,100번(하계동~용산구청),명륜3가.성대입구,64,102
public class BHMapper extends Mapper<Object, Text, Text, LongWritable> {
	
	private static final Text BUS = new Text();
	private static final LongWritable SUM = new LongWritable();
	
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		String[] line2 = line.split(",");
		
		String no = line2[1].substring(0, line2[1].indexOf("("));
		BUS.set(no);
		SUM.set(Integer.parseInt(line2[3]) + Integer.parseInt(line2[4]));
		context.write(BUS, SUM);
	}
}

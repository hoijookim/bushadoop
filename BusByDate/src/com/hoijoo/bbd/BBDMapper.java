package com.hoijoo.bbd;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//날짜,번호,정류장명,탄,내린
//20150101,100번(하계동~용산구청),명륜3가.성대입구,64,102
public class BBDMapper extends Mapper<Object, Text, Text, LongWritable> {
	
	private static final Text DATE = new Text();
	private static final LongWritable SUM = new LongWritable();
	
	private static final SimpleDateFormat SDF1 = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat SDF2 = new SimpleDateFormat("E", Locale.KOREAN);
	
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		try {
		
		String[] line = value.toString().split(",");
		Date when = SDF1.parse(line[0]);
		
		DATE.set(SDF2.format(when));
		SUM.set(Long.parseLong(line[3]) + Long.parseLong(line[4]));
		context.write(DATE, SUM);	// 요일, 몇명
		} catch (ParseException e) {
			e.printStackTrace();
		} 
	}
}

package com.rich.hadoop.mr;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.rich.hadoop.mr.temperature.MaxTemperatureMapper;
import com.rich.hadoop.mr.temperature.MaxTemperatureReducer;



public class MaxTemperatureTest {
	
    MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
    ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
    MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
    
    @Before
    public void setUp() {
        MaxTemperatureMapper mapper = new MaxTemperatureMapper();
        MaxTemperatureReducer reducer = new MaxTemperatureReducer();
        
        mapDriver = MapDriver.newMapDriver(mapper);
        reduceDriver = ReduceDriver.newReduceDriver(reducer);
        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }

    @Test
    public void testMaxTemperatureMapper() throws IOException {
      mapDriver.withInput(new LongWritable(), new Text(
          "0043011990999991950051512004+68750+023550FM-12+038299999V0203201N00671220001CN9999999N9+00221+99999999999"));
      mapDriver.withOutput(new Text("1950"), new IntWritable(22));
      mapDriver.runTest();
    }
    
    @Test
    public void testMaxTemperatureReducer() throws IOException {
      List<IntWritable> values = new ArrayList<IntWritable>();
      values.add(new IntWritable(22));
      values.add(new IntWritable(10));
      reduceDriver.withInput(new Text("1950"), values);
      reduceDriver.withOutput(new Text("1950"), new IntWritable(22));
      reduceDriver.runTest();
    }
    
    @Test
    public void testMaxTemperatureMapReduce() throws IOException {
      mapReduceDriver.withInput(new LongWritable(), new Text(
              "0043011990999991950051512004+68750+023550FM-12+038299999V0203201N00671220001CN9999999N9+00221+99999999999")).withInput(
                      new LongWritable(), new Text(
              "0043011990999991950051518004+68750+023550FM-12+038299999V0203201N00261220001CN9999999N9+00441+99999999999"));
      mapReduceDriver.withOutput(new Text("1950"), new IntWritable(44));
      mapReduceDriver.runTest();
    }

}

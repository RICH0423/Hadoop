package com.rich.hadoop.mr;

import org.apache.hadoop.util.ToolRunner;

import com.rich.hadoop.mr.temperature.MaxTemperatureDriver;

public class RunMrApp {

	public static void main(String[] args) throws Exception {
		MaxTemperatureDriver driver = new MaxTemperatureDriver();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}

}

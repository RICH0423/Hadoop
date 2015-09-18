# Hadoop Map-Reduce Job project
# This project is made by maven, which use to test Hadoop MR task.
# The MR job is to compute the max temperature of each years.

# Input temperature data into HDFS: 
hadoop fs -copyFromLocal ~/Downloads/temperature_data.txt /user/rich/temperatureInput

# Remove output directory if existed in HDFS:
hadoop fs -rmr /user/rich/MaxTempOutput

# Execute MR job
# Step1 build project
mvn clean package

# Step2 execute jar and run Max temperature output
hadoop jar /RICH_HD/Projects/BigData_Project/MRJob/target/MRJob-1.0-job.jar /user/rich/temperatureInput /user/rich/MaxTempOutput

# Step3 view result 
hadoop fs -cat /user/rich/MaxTempOutput/part-00000



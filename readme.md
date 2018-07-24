程序完成后输出jar包，将其放在服务器上运行，运行方式：hadoop jar wordcount.jar com/dj/WordCountMapReduce /wordcount/input /wordcount/output		具体写那个包下的那个类，并写出输入参数以及输出参数
执行成功后，查看结果：hdfs dfs -cat /wordcount/output/*
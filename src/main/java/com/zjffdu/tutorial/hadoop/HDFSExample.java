package com.zjffdu.tutorial.hadoop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSExample {

  public static void main(String[] args) throws IOException, URISyntaxException {
    Configuration conf=new Configuration();
    conf.set("fs.hdfs.impl", 
        org.apache.hadoop.hdfs.DistributedFileSystem.class.getName()
    );
    conf.set("fs.file.impl",
        org.apache.hadoop.fs.LocalFileSystem.class.getName());
    FileSystem fs =
        FileSystem.get(new URI("hdfs://localhost:9000"), conf);
    FileStatus[] statues=fs.listStatus(new Path("/"));
    for (FileStatus s:statues){
      System.out.println(s.getPath().getName());
    }
  }
}

package com.dj.demo1_hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBean {
	private long upFlow;
	private long dFlow;
	private long sumFlow;
	
	public FlowBean() {
		
	}
	
	public FlowBean(long upFlow, long dFlow) {
		this.upFlow = upFlow;
		this.dFlow  = dFlow;
		this.sumFlow = upFlow + dFlow;
		
	}

	public long getUpFlow() {
		return upFlow;
	}

	public void setUpFlow(long upFlow) {
		this.upFlow = upFlow;
	}

	public long getdFlow() {
		return dFlow;
	}

	public void setdFlow(long dFlow) {
		this.dFlow = dFlow;
	}

	public long getSumFlow() {
		return sumFlow;
	}

	public void setSumFlow(long sumFlow) {
		this.sumFlow = sumFlow;
	}
	
	public void write(DataOutput out) throws IOException {
		out.writeLong(upFlow);
		out.writeLong(dFlow);
		out.writeLong(sumFlow);
	}
	
	public void readFile(DataInput in) throws IOException {
		upFlow = in.readLong();
		dFlow = in.readLong();
		sumFlow = in.readLong();
	}
	
	public String toString() {
		return upFlow + "***" + dFlow + "***" + sumFlow;				
	}
	
}

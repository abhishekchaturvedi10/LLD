package lld.lldproblems.messagequeue;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;


@Getter
public class Topic {
	
	private final String name;
	private final List<Partition> partitions;
	
	public Topic(String name, int partitionCount) {
		this.name = name;
		this.partitions = new ArrayList<>();
		for (int i = 0; i < partitionCount; i++) {
			partitions.add(new Partition(i));
		}
	}
	
	public Partition getPartition(int partitionId) {
		return partitions.get(partitionId);
	}
}

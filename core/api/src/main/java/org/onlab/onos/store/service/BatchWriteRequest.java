package org.onlab.onos.store.service;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Collection of write requests to be submitted as one batch.
 */
public class BatchWriteRequest {

	private final List<WriteRequest> writeRequests;

	/**
	 * Creates a new BatchWriteRequest object from the specified list of write requests.
	 * @param writeRequests write requests.
	 * @return BatchWriteRequest object.
	 */
	public static BatchWriteRequest create(List<WriteRequest> writeRequests) {
		return new BatchWriteRequest(writeRequests);
	}

	private BatchWriteRequest(List<WriteRequest> writeRequests) {
		this.writeRequests = Collections.unmodifiableList(writeRequests);
	}

	/**
	 * Returns the requests in this batch as a list.
	 * @return list of write requests
	 */
	public List<WriteRequest> getAsList() {
		return writeRequests;
	}

	/**
	 * Returns the number of requests in this batch.
	 * @return size of request batch.
	 */
	public int batchSize() {
		return writeRequests.size();
	}

	/**
	 * Builder for BatchWriteRequest.
	 */
	public static class Builder {

		private final List<WriteRequest> writeRequests = Lists.newLinkedList();

		public Builder put(String tableName, String key, byte[] value) {
			writeRequests.add(WriteRequest.put(tableName, key, value));
			return this;
		}

		public Builder putIfAbsent(String tableName, String key, byte[] value) {
			writeRequests.add(WriteRequest.putIfAbsent(tableName, key, value));
			return this;
		}

		public Builder putIfValueMatches(String tableName, String key, byte[] oldValue, byte[] newValue) {
			writeRequests.add(WriteRequest.putIfValueMatches(tableName, key, oldValue, newValue));
			return this;
		}

		public Builder putIfVersionMatches(String tableName, String key, byte[] value, long version) {
			writeRequests.add(WriteRequest.putIfVersionMatches(tableName, key, value, version));
			return this;
		}

		public Builder remove(String tableName, String key) {
			writeRequests.add(WriteRequest.remove(tableName, key));
			return this;
		}

		public Builder removeIfVersionMatches(String tableName, String key, long version) {
			writeRequests.add(WriteRequest.removeIfVersionMatches(tableName, key, version));
			return this;
		}

		public Builder removeIfValueMatches(String tableName, String key, byte[] value) {
			writeRequests.add(WriteRequest.removeIfValueMatches(tableName, key, value));
			return this;
		}

		public BatchWriteRequest build() {
			return new BatchWriteRequest(writeRequests);
		}
	}
}
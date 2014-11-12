package org.onlab.onos.store.service;

/**
 * Service interface for a strongly consistent and durable
 * key value data store.
 */
public interface DatabaseService {

    /**
     * Reads the specified key.
     * @param tableName name of the table associated with this operation.
     * @return key key to read.
     * @returns value (and version) associated with this key. This calls returns null if the key does not exist.
     */
    VersionedValue get(String tableName, String key);
    
    /**
     * Associate the key with a value.
     * @param tableName table name in which this key/value resides.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the specified key, or null if there was no mapping for the key.
     */
    VersionedValue put(String tableName, String key, byte[] value);
    
    /**
     * If the specified key is not already associated with a value, associate it with the given value.
     * @param tableName table name in which this key/value resides.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return true if put was successful, false if there is already a value associated with this key
     */
    boolean putIfAbsent(String tableName, String key, byte[] value);
    
    /**
     * Sets the key to the specified value if the version in the database (for that key)
     * matches the specified version.
     * @param tableName name of table associated with this operation.
     * @param key key
     * @param value value
     * @param version version that should present in the database for the put to be successful.
     * @return true if put was successful, false if there version in database is different from what is specified.
     */
    boolean putIfVersionMatches(String tableName, String key, byte[] value, long version);
    
    /**
     * Replaces the entry for a key only if currently mapped to a given value.
     * @param tableName name of table associated with this operation.
     * @param key with which the specified value is associated
     * @param oldValue value expected to be associated with the specified key
     * @param newValue value to be associated with the specified key
     * @return true if put was successful, false if there version in database is different from what is specified.
     */
    boolean putIfValueMatches(String tableName, String key, byte[] oldValue, byte[] newValue);
    
    /**
     * Removes the key (and associated value).
     * @param tableName name of table associated with this operation.
     * @param key key to remove
     * @return value previously associated with the key. This call returns null if the key does not exist.
     */
    VersionedValue remove(String tableName, String key);
    
    /**
     * Removes the key (and associated value) if the version in the database matches specified version.
     * @param tableName name of table associated with this operation.
     * @param key key to remove
     * @param version version that should present in the database for the remove to be successful.
     * @return true if remove was successful, false if there version in database is different from what is specified.
     */
    boolean removeIfVersionMatches(String tableName, String key, long version);
    
    /**
     * Removes the key (and associated value) if the value in the database matches specified value.
     * @param tableName name of table associated with this operation.
     * @param key key to remove
     * @param value value that should present in the database for the remove to be successful.
     * @return true if remove was successful, false if there value in database is different from what is specified.
     */
    boolean removeIfValueMatches(String tableName, String key, byte[] value);
    
    /**
     * Performs a batch read operation and returns the results.
     * @param batchRequest batch request.
     * @return result of the batch operation.
     */
    BatchReadResult batchRead(BatchReadRequest batchRequest);
    
    /**
     * Performs a batch write operation and returns the results.
     * This method provides transactional semantics. Either all writes succeed or none do.
     * Even a single write failure would cause the entire batch to be aborted.
     * In the case of unsuccessful operation, the batch result can be inspected to determine
     * which operation(s) caused the batch to fail.
     * @param batchRequest batch request.
     * @return result of the batch operation.
     */
    BatchWriteResult batchWrite(BatchWriteRequest batchRequest);
}
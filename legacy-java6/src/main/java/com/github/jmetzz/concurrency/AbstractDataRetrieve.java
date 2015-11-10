package com.github.jmetzz.concurrency;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by exi853 on 10/11/2015.
 */
public abstract class AbstractDataRetrieve<R, T> implements IBatchDataRetrieve<R> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDataRetrieve.class);
    protected Map<String, T> workingDatas;

    @Override
    public void retrieveData(Map<String, IRetriever<R>> retrieverMap) throws ConcurrencyCheckedException {
        workingDatas = new HashMap<String, T>();
        for (Map.Entry<String, IRetriever<R>> entry : retrieverMap.entrySet()) {
            final IRetriever<R> query = entry.getValue();
            final T data = retrieve(query);
            workingDatas.put(entry.getKey(), data);
        }
    }

    @Override
    public Map<String, R> getResults() throws ConcurrencyCheckedException {

        final Map<String, R> results = new HashMap<String, R>();

        // TODO : apply guava fluent iterator...
        for (String key : workingDatas.keySet()) {
            try {
                final RetrieverResult<R> result = getRetrieverResult(key);
                if (result.isSuccess() && result.getData() != null) {
                    results.put(key, result.getData());
                }
                logMessages(result.getMessages(), result.getServiceName());
            } catch (Exception e) {
                LOGGER.fatal("Unexpected error", e);
            }
        }
        return results;
    }

    protected void logMessages(List<Message> messages, String serviceName) {
        LOGGER.info(serviceName + " - " + messages);
    }

    protected abstract T retrieve(IRetriever<R> query) throws ConcurrencyCheckedException;

    protected abstract RetrieverResult<R> getRetrieverResult(String key) throws ConcurrencyCheckedException;
}

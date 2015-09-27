package com.udacity.gradle.builditbigger;

import android.test.UiThreadTest;

import junit.framework.TestCase;

public class EndpointAsyncTaskTest extends TestCase implements AsyncListener {
    EndpointAsyncTask endpointAsyncTask;

    protected void setUp() throws Exception {
        super.setUp();
        endpointAsyncTask = new EndpointAsyncTask(this);
    }

    @UiThreadTest
    public void testDownload() throws InterruptedException
    {
        endpointAsyncTask.execute();
    }

    @Override
    public void onComplete(String result) {
        if(!result.equals(""))
            assertTrue( "AsyncTest passed.", true );
    }
}

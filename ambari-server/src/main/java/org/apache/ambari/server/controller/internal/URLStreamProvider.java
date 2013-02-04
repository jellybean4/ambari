/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ambari.server.controller.internal;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.ambari.server.controller.utilities.StreamProvider;

/**
 * URL based implementation of a stream provider.
 */
public class URLStreamProvider implements StreamProvider {
  
  private int connTimeout = -1;
  
  public URLStreamProvider() {
  }
  
  /**
   * Provide the connection timeout for the underlying connection.
   * 
   * @param connectionTimeout time, in milliseconds, to attempt a connection
   */
  public URLStreamProvider(int connectionTimeout) {
    connTimeout = connectionTimeout;
  }
  
  @Override
  public InputStream readFrom(String spec) throws IOException {
    URLConnection connection = new URL(spec).openConnection();
    if (connTimeout > 0) {
      connection.setConnectTimeout(connTimeout);
    }
    connection.setDoOutput(true);
    return connection.getInputStream();
  }
}

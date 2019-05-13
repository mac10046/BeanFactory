package org.sls.service;

import java.util.Properties;

/**
 * Class implementing this interface is required to be scan for the beans
 * creation and setup.
 * 
 * @author Abdeali Chandanwala
 *
 */
public interface ObjectStore {

  public Properties getProperties(String key);

}

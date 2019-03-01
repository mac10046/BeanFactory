package org.sls.service;

import java.util.Optional;

public interface ObjectStore {

  public Object getBean(Class clazz) throws ClassNotFoundException;

  public Object getBean(Class clazz, Object... args) throws ClassNotFoundException, IllegalArgumentException;

  public Optional<String> getProperty(String key);
  
}

package org.sls;

import java.util.Optional;

import org.sls.service.ObjectStore;

public class BeanFactory {

  private static ObjectStore objectStore = null;

  private static ObjectStore getObjectStore() {
    return objectStore;
  }

  public static void initialize(ObjectStore objectStore) {
    if (BeanFactory.objectStore != null)
      throw new IllegalStateException(
          "Bean Factory has already been initialized, use factoryRefresh method to re-initialize bean factory");
    BeanFactory.objectStore = objectStore;
  }

  public static void factoryRefresh(ObjectStore objectStore) {
    BeanFactory.objectStore = objectStore;
  }

  public static Object getBean(Class clazz) throws ClassNotFoundException {
    return getObjectStore().getBean(clazz);
  }

  public static Object getBean(Class clazz, Object... args) throws ClassNotFoundException, IllegalArgumentException {
    return getObjectStore().getBean(clazz, args);
  }

  /**
   * This method will check for the requested property in the ObjectStore
   * Implementation, if not found it will check the System properties and then
   * it will check for System env for the property value.
   *  
   * @param String
   *          key
   * @return Optional<String>
   */
  public static Optional<String> getProperty(String key) {
    Optional<String> property = getObjectStore().getProperty(key);
    if (property.isPresent())
      return property;

    property = Optional.ofNullable(System.getProperty(key));
    if (property.isPresent())
      return property;

    property = Optional.ofNullable(System.getenv(key));
    if (property.isPresent())
      return property;

    return Optional.empty();
  }

}

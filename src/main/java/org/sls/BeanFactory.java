package org.sls;

import java.util.Optional;
import java.util.Properties;

import org.sls.service.ObjectStore;

public class BeanFactory {

  private static ObjectStore objectStore = null;
  private static Properties properties = null;

  private static ObjectStore getObjectStore() {
    return objectStore;
  }

  /**
   * Initialize the beans and get them ready for the system. Supply a
   * implementation of the ObjectStore interface
   */
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
    //TODO: find the annotated classes and methods in the system and maintain a map of it - so as to serve the class for injection.
    /**
     * named bean
     * singleton and prototype
     */
    return null;
  }

  public static Object getBean(Class clazz, Object... args) throws ClassNotFoundException, IllegalArgumentException {
    return null;
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
  public static Optional<Object> getProperty(String key, Object defaultValue) {
    if (properties == null)
      properties = getObjectStore().getProperties(key);

    if (properties.containsKey(key))
      return Optional.ofNullable(properties.getProperty(key));

    Optional<String> property = Optional.ofNullable(System.getProperty(key));
    if (property.isPresent())
      return Optional.ofNullable(property);

    property = Optional.ofNullable(System.getenv(key));
    if (property.isPresent())
      return Optional.ofNullable(property);

    if (defaultValue != null)
      return Optional.ofNullable(defaultValue);

    return Optional.empty();
  }

  /**
   * Check the required property in Environment Variables.
   * 
   * @param key
   * @return Optional containing the environment value or else a null.
   */
  public static Optional<String> getEnvProperty(String key) {
    return Optional.ofNullable(System.getenv(key));
  }

  /**
   * Check the required property in System Variable.
   * 
   * @param key
   * @return Optional containing the system value or else a null.
   */
  public static Optional<String> getSystemProperty(String key) {
    return Optional.ofNullable(System.getProperty(key));
  }

}

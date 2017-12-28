package net.magja.soap;

import net.magja.magento.ResourcePath;
import org.apache.axis2.AxisFault;

import java.util.List;

public interface SoapClient {

  /**
   * Calls Magento API with multiple arguments.
   *
   * @param path
   * @param args
   * @return
   * @throws AxisFault
   */
  <R> R callArgs(ResourcePath path, Object[] args) throws AxisFault;

  /**
   * Calls Magento API with single argument.
   *
   * @param path
   * @param arg
   * @return
   * @throws AxisFault
   */
  <T, R> R callSingle(ResourcePath path, T arg) throws AxisFault;

  /**
   * Calls Magento API without arguments.
   * @param path
   * @return
   * @throws AxisFault
   */
  <R> R callNoArgs(ResourcePath path) throws AxisFault;

  /**
   * Calls Magento API multiple time.
   * @param paths
   * @param args
   * @return
   * @throws AxisFault
   */
  Object multiCall(List<ResourcePath> paths, List<Object> args) throws AxisFault;

  /**
   * Retrieves the configuration.
   *
   * @return the configuration object.
   */
  SoapConfig getConfig();

}

package com.google.code.magja.model;

import com.google.code.magja.utils.PropertyLoader;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

public abstract class BaseMagentoModel implements Serializable {

    private static final long serialVersionUID = 1904599004777705000L;

    protected static final String MAPPING_FILE_NAME_SUFIX = "-mapping";

    protected Integer id;

    protected Map<String, Object> properties = new HashMap<String, Object>();

    protected Properties mapping;

    public BaseMagentoModel() {
        loadMappings();
    }

    protected void loadMappings() {
        String className = getClass().getSimpleName();
        className = className.substring(0, 1).toLowerCase() + className.substring(1);
        className = getClass().getPackage().getName() + "." + className + MAPPING_FILE_NAME_SUFIX;
        mapping = PropertyLoader.loadProperties(className);
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    protected Object invokeGetOrSetMethod(String attribute, String prefix, Object arg) throws Exception {

        if (attribute != null) {
            String methodName = prefix + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);

            if (methodName.equals("getId")) return this.getId();
            else if (methodName.equals("setId")) {
                this.setId(Integer.parseInt(arg.toString()));
                return null;
            }

            Class tClass = getClass();
            Object[] args = null;
            Class[] argTypes = null;

            try {

                if (prefix.equals("set")) {

                    if (arg == null) return null;

                    args = new Object[1];

                    // find the type of attribute
                    Field fld = null;
                    try {
                        fld = tClass.getDeclaredField(attribute);
                    } catch (NoSuchFieldException e) {
                        // means the field could be on the super class
                        Class superclass = tClass.getSuperclass();
                        fld = superclass.getDeclaredField(attribute);
                    }

                    argTypes = new Class[1];
                    argTypes[0] = fld.getType();

                    if (fld.getType().equals(Class.forName("java.lang.Boolean"))) {

                        if (arg.getClass().getSimpleName().equals("Boolean")) {
                            args[0] = arg;
                        } else {
                            if (arg.toString().equals("1") || arg.equals("true")) args[0] = new Boolean(true);
                            else if (arg.toString().equals("0") || arg.equals("false")) args[0] = new Boolean(false);
                            else args[0] = new Boolean(arg.toString());
                        }

                    } else {

                        //sometimes the arg could still have in boolean type, so we convert here
                        Object arg2;
                        if (arg instanceof Boolean) {
                            //Boolean temp = (Boolean) arg;
                            arg2 = new String("");
                        } else {
                            arg2 = arg;
                        }

                        // when the argument is a array of String's
                        if (arg instanceof LinkedList) {
                            LinkedList<String> list = (LinkedList<String>) arg;
                            String[] strs = new String[list.size()];
                            list.toArray(strs);
                            args[0] = strs;

                        } else {
                            // create the object with correct type
                            Class partypes[] = new Class[1];
                            partypes[0] = String.class;

                            Constructor ct = fld.getType().getConstructor(partypes);
                            args[0] = ct.newInstance(arg2);
                        }
                    }
                }

                Method invokeMethod = tClass.getMethod(methodName, argTypes);
                if (prefix.equals("get")) {
                    return invokeMethod.invoke(this);
                }
                invokeMethod.invoke(this, args);
                return null;

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw new Exception("IllegalArgumentException calling method " + methodName + " on " + tClass.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new Exception("IllegalAccessException calling method " + methodName + " on " + tClass.getName());
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new Exception("InvocationTargetException calling method " + methodName + " on " + tClass.getName());
            } catch (SecurityException e) {
                e.printStackTrace();
                throw new Exception("SecurityException calling method " + methodName + " on " + tClass.getName());
            } catch (NoSuchMethodException e) {
                //e.printStackTrace();
                throw new Exception("NoSuchMethodException calling method " + methodName + " on " + tClass.getName());
            }
        }

        return null;
    }


    /**
     * Get a specific, in the magento's name format, which is or isn't covered by the concret model
     */
    public Object get(String name) {
        if (name != null) {
            if (!name.trim().equals("")) {
                // remove spaces
                name = name.trim();

                // look for the property on map
                if (properties.get(name) != null)
                    return properties.get(name);

                // once we have the attribute name, just invoke the proper 'get' for that to invoke the correspondent model's method
                try {
                    return invokeGetOrSetMethod(mapping.getProperty(name), "get", null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Set a specific property, in the magento's name format
     */
    public void set(String name, Object value) {
        if (name != null && value != null) {
            name = name.trim();
            if (!name.equals("")) {
                /*
                     * first, we search for a correspondent model attribute for this property
                     * if exists, we invoke the setter for this property instead put it on the map
                     */
                String attribute = mapping.getProperty(name);
                if (attribute != null) {
                    try {
                        invokeGetOrSetMethod(mapping.getProperty(name), "set", value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // if not exists, just put the property on the map
                    properties.put(name, value);
                }
            }
        }
    }

    /**
     * @return all the properties in a Map to interchange with Magento API
     */
    public Map<String, Object> getAllProperties() {
        Map<String, Object> props = new HashMap<String, Object>();

        // put the basic properties
        for (Map.Entry<String, Object> prop : properties.entrySet()) {
            if (prop.getValue() != null) props.put(prop.getKey(), prop.getValue());
        }

        // put the mapped attributes
        for (Object obj : mapping.keySet()) {
            String key = (String) obj;
            try {

                Object value = invokeGetOrSetMethod(mapping.getProperty(key), "get", null);
                Object finalValue = null;

                // special approach for Boolean types
                if (value instanceof Boolean) {
                    Boolean flag = (Boolean) value;
                    if (flag) finalValue = new Integer(1);
                    else finalValue = new Integer(0);
                } else {
                    finalValue = value;
                }

                if (value != null) props.put(key, finalValue);
                if (finalValue != null) props.put(key, finalValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return props;
    }

    /**
     * @return serialize the object to interchange with Magento API
     */
    public abstract Object serializeToApi();


    /* (non-Javadoc)
      * @see java.lang.Object#hashCode()
      */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((properties == null) ? 0 : properties.hashCode());
        return result;
    }

    /* (non-Javadoc)
      * @see java.lang.Object#equals(java.lang.Object)
      */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseMagentoModel other = (BaseMagentoModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (properties == null) {
            if (other.properties != null)
                return false;
        } else if (!properties.equals(other.properties))
            return false;
        return true;
    }

    /* (non-Javadoc)
      * @see java.lang.Object#toString()
      */
    @Override
    public String toString() {
        return "BaseMagentoModel [id=" + id + ", properties=" + properties + "]";
    }

}

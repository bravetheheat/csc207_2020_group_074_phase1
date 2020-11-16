package main.gateways.converters;

import java.util.List;


/**
 * Generic interface for converting to and from Beans, or objects that can be serialized and deserialized from strings
 *
 * @param <Serializable> Serializable object
 * @param <T>            Original object
 */
public interface Converter<Serializable, T> {

    /**
     * Convert serializable representation into original type
     *
     * @param beans List of serializable representations
     * @return List of original representations
     */
    List<T> convertFromBeans(List<Serializable> beans);

    /**
     * Convert original types into serializable representations
     *
     * @param values List of original representations
     * @return List of serializable representations
     */
    List<Serializable> convertToBeans(List<T> values);


}

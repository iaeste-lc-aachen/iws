/*
 * =============================================================================
 * Copyright 1998-2014, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.util.Copier
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.util;

import net.iaeste.iws.api.exceptions.IWSException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Utility Class, containing a single method called "copy". This method is
 * written in different versions, depending on the given input data. The main
 * reason for using this, is to ensure that object, which is not immutable is
 * properly copied - to avoid leaking internal data references.<br />
 *   Copying is needed to ensure that all data we start processing is 100% under
 * the control of the IWS. Read-only request, such as all the fetch* requests
 * are invoking the system with read-only access. So they do not need to use
 * the copying.<br />
 *   Note; The IWS is having some heavy performance issues, some of the requests
 * have had almost abysmally poor performance. The Defensive Copying have not
 * been helpful here, and as long as we're only allowing a trusted client to
 * interact locally with the IWS, we're simply just omitting the defensive
 * copying.
 *
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
public final class Copier {

    // Note: Java Generics has a flaw, which means that it doesn't support
    // normal arrays (i.e. Object[])!
    // See: http://forums.sun.com/thread.jspa?threadID=530823 (link doesn't work after Oracle took over Sun)
    // See: http://stackoverflow.com/questions/529085/java-how-to-generic-array-creation
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    /**
     * Private Constructor, this is a utility class.
     */
    private Copier() {
    }

    /**
     * Copies the given List, to ensure that the new list is not exposing any
     * references. If the given list is null, then a new empty list is returned,
     * to avoid a potential {@code NullPointerException}.
     *
     * @param original The List to copy
     * @return Copy of the given List, or an empty List
     */
    public static <T extends Serializable> List<T> copy(final List<T> original) {
        final List<T> copy;

        if (original != null) {
            copy = original;
            //copy = new ArrayList<>(original.size());
            //for (final T t : original) {
            //    copy.add(copy(t));
            //}
        } else {
            copy = new ArrayList<>(0);
        }

        return copy;
    }

    /**
     * Copies the given Set, to ensure that the new set is not exposing any
     * references. If the given set is null, then a new empty set is returned,
     * to avoid a potential {@code NullPointerException}.
     *
     * @param original The Set to copy
     * @return Copy of the given Set, or an empty Set
     */
    public static <T extends Serializable> Set<T> copy(final Set<T> original) {
        final Set<T> copy;

        if (original != null) {
            copy = original;
            //copy = new HashSet<>(original.size());
            //for (final T t : original) {
            //    copy.add(copy(t));
            //}
        } else {
            copy = new HashSet<>(0);
        }

        return copy;
    }

    /**
     * Copies the given Map with Lists as value, to ensure that the new Map is
     * not exposing any references. If the given Map is null, then a new empty
     * Map is returned, to avoid a potential {@code NullPointerException}.<br />
     *   This method is not following the "normal" name - the reason is that the
     * Java Generics is rather primitive in comparison with C++ Templates, and
     * thus cannot easily distinguish between Objects types.
     *
     * @param original The Map with Lists to copy
     * @return Copy of the given Map, or an empty Map
     */
    public static <T extends Serializable, V extends Serializable> Map<T, List<V>> copyMapWithList(final Map<T, List<V>> original) {
        final Map<T, List<V>> copy;

        if (original != null) {
            copy = original;
            //copy = new HashMap<>(original.size());
            //for (final Map.Entry<T, List<V>> entry : original.entrySet()) {
            //    copy.put(copy(entry.getKey()), copy(entry.getValue()));
            //}
        } else {
            copy = new HashMap<>(0);
        }

        return copy;
    }

    /**
     * Copies the given Array, to ensure that the new Array is not exposing any
     * references. If the given Array is null, then a new empty Array is
     * returned, to avoid a potential {@code NullPointerException}.
     *
     * @param original The Array to copy
     * @return Copy of the given Array, or an empty Array
     */
    public static String[] copy(final String[] original) {
        final String[] copy;

        if (original != null) {
            copy = original;
            //copy = new String[original.length];
            //System.arraycopy(original, 0, copy, 0, original.length);
        } else {
            copy = EMPTY_STRING_ARRAY;
        }

        return copy;
    }

    /**
     * Copies the given Array, to ensure that the new Array is not exposing any
     * references. If the given Array is null, then a new empty Array is
     * returned, to avoid a potential {@code NullPointerException}.
     *
     * @param original The Array to copy
     * @return Copy of the given Array, or an empty Array
     */
    public static byte[] copy(final byte[] original) {
        final byte[] copy;

        if (original != null) {
            copy = original;
            //copy = new byte[original.length];
            //System.arraycopy(original, 0, copy, 0, original.length);
        } else {
            copy = EMPTY_BYTE_ARRAY;
        }

        return copy;
    }

    /**
     * Performs a deep copy of an Object.
     *
     * @param obj Object to copy
     * @return Deep copy of the original Object
     * @throws IWSException if a problem occurs with the copying
     */
    public static <T extends Serializable> T copy(final T obj) {
        return obj;
        //final T result;
        //
        //if (obj != null) {
        //    // We have to run the copy in two blocks, since the Streams will
        //    // otherwise not be able to use the Java7 autoclosing feature. So,
        //    // we need a placeholder for the result from the first block, that
        //    // performs the serialization - which can then be given to the
        //    // second block that performs a deserialization
        //    final byte[] bytes;
        //
        //    // First block, performs the serialization
        //    try (ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        //         ObjectOutputStream objectOutStream = new ObjectOutputStream(byteOutStream)) {
        //
        //        // First, we write the given Object to the Object stream
        //        objectOutStream.writeObject(obj);
        //
        //        // Before we can convert the content of the Object stream, we
        //        // have to close it, to force a flushing of unwritten data
        //        objectOutStream.close();
        //        bytes = byteOutStream.toByteArray();
        //    } catch (IOException e) {
        //        throw new IWSException(IWSErrors.ERROR, e);
        //    }
        //
        //    // Second block, performs the deserialization
        //    try (ByteArrayInputStream byteInStream = new ByteArrayInputStream(bytes);
        //         ObjectInputStream objectInStream = new ObjectInputStream(byteInStream)) {
        //
        //        // As the streams handle the magic, all we have to worry about
        //        // is reading the Object out, and return it :-)
        //        result = (T) objectInStream.readObject();
        //    } catch (IOException | ClassNotFoundException e) {
        //        throw new IWSException(IWSErrors.ERROR, e);
        //    }
        //} else {
        //    result = null;
        //}
        //
        //return result;
    }
}

/*
 * =============================================================================
 * Copyright 1998-2015, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-ejb) - net.iaeste.iws.ejb.interceptors.Profiler
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.ejb.interceptors;

import net.iaeste.iws.api.util.LogUtil;
import net.iaeste.iws.api.util.Traceable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.text.DecimalFormat;

/**
 * Interceptor Profiling Class.
 *
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
//@TransactionAttribute(TransactionAttributeType.REQUIRED)
//@TransactionManagement(TransactionManagementType.CONTAINER)
public class Profiler {

    private static final Logger log = LoggerFactory.getLogger(Profiler.class);
    private static final DecimalFormat format = new DecimalFormat("###,###.##");

    //@Inject @IWSBean private EntityManager entityManager;

    @AroundInvoke
    public Object profile(final InvocationContext invocation) throws Exception {
        final long start = System.nanoTime();

        try {
            return invocation.proceed();
        } finally {
            final double time = (double) (System.nanoTime() - start) / 1000000;
            final String name = invocation.getMethod().getName();
            final String duration = format.format(time);
            Traceable trace = findTraceable(invocation);

            //updateSession(trace);
            log.debug(LogUtil.formatLogMessage(trace, "Profile: Method %s took %s ms.", name, duration));
        }
    }

    //public void updateSession(final Traceable traceable) {
    //    if (traceable instanceof AuthenticationToken) {
    //        final AuthenticationToken token = (AuthenticationToken) traceable;
    //        final String jql =
    //                "update SessionEntity " +
    //                "set modified = current_timestamp " +
    //                "where sessionKey = :key";
    //        final Query query = entityManager.createQuery(jql);
    //        query.setParameter("key", token.getToken());
    //    }
    //}

    /**
     * For our logging, it helps if we can add more tracing. The Session Objects
     * is part of the Invocation Context, so with this method, it is possible to
     * read out the Trace and return it.
     *
     * @param invocation Invocation Context for the current IWS Request
     * @return User Session Trace, if one exists
     */
    private static Traceable findTraceable(final InvocationContext invocation) {
        Traceable trace = null;

        if (invocation != null) {
            final Object[] parameters = invocation.getParameters();

            if ((invocation != null) && (parameters != null) && (parameters.length >= 1) && (parameters[0] instanceof Traceable)) {
                trace = (Traceable) parameters[0];
            }
        }

        return trace;
    }
}

package com.pushpop.server.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.pushpop.shared.GroupMembership;

public class LdapQuery {

    private static Hashtable<String, Object> env = new Hashtable<String, Object>();

    private static final Logger logger = Logger.getLogger(LdapQuery.class.getName());

    // mobileNumberLength value should be according pattern in Preferences.java
    private static final int mobileNumberLength = 10;

    static {
        // Set the location of our trust store, this equates to the location of
        // our war + /.truststore/
        System.setProperty("javax.net.ssl.trustStore", RequestFactoryServlet.getThreadLocalServletContext().getRealPath("") + "/.truststore/hptrust.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "hptrust");

        // Set up environment for creating initial context
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldaps://ldap.hp.com:636/");

        // Specify SSL ldap.hp.com requires ssl
        env.put(Context.SECURITY_PROTOCOL, "ssl");
        // By default this needs to be anonymous to make queries.
        env.put(Context.SECURITY_AUTHENTICATION, "none");
    }

    /**
     * This will return a person's full name based on their canonical name in
     * LDAP.
     * 
     * @param email
     *            Email address of the user to look up.
     * @return String full name from ldap.
     * @throws NamingException
     *             If there is a problem communicating with ldap.
     */
    public static String getFullName(String uid) throws NamingException {
        String fullName = null;

        fullName = attributeByUID("cn", uid);

        if (fullName == null) {
            fullName = "Unknown";
        }

        return fullName;
    }

    /**
     * This will return a person's mobile number based on their uid in LDAP.
     * 
     * @param uid
     * @return
     * @throws NamingException
     */
    public static String getMobileNumber(String uid) throws NamingException {
        String mobileNumber = null;
        mobileNumber = attributeByUID("mobile", uid);
        if (mobileNumber != null) {
            mobileNumber = mobileNumber.replaceAll(" ", "");
            mobileNumber = mobileNumber.replaceAll("-", "");
            mobileNumber = mobileNumber.substring(mobileNumber.length() >= mobileNumberLength ? mobileNumber.length() - mobileNumberLength : mobileNumber.length());
            mobileNumber = mobileNumber.substring(0, 3) + "-" + mobileNumber.substring(3, 6) + "-" + mobileNumber.substring(6, 10);
        }
        return mobileNumber;
    }

    public static String attributeByUID(String attributeName, String email) throws NamingException {
        String attr = null;

        env.put(Context.SECURITY_AUTHENTICATION, "none");

        DirContext ctx = new InitialDirContext(env);

        Attributes attributes = ctx.getAttributes("uid=" + email + ",ou=people,o=hp.com");

        if (attributes.get(attributeName) != null) {
            attr = (String) attributes.get(attributeName).get();
        }
        return attr;
    }

    /**
     * This will bind to the ldap.hp.com server using simple email/password
     * authentication. It only creates an initial context as that is all that is
     * needed to test authentication.
     * 
     * @param email
     *            e-mail address of the user equates to ldap uid.
     * @param password
     *            domain password.
     * @throws AuthenticationException
     *             if there is a naming ldap authentication problem.
     * @throws NamingException
     *             if a generic authentication problem occurs.
     */
    public static void authenticate(String email, String password) throws AuthenticationException, NamingException {
        // Remove the anonymous security and set it to simple so we can test
        // login.
        env.remove(Context.SECURITY_AUTHENTICATION);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");

        // Configure the username and password.
        logger.info("Attempting to authenticate user email " + email);
        env.put(Context.SECURITY_PRINCIPAL, "uid=" + email + ", ou=people, o=hp.com");
        env.put(Context.SECURITY_CREDENTIALS, password);

        DirContext ctx = new InitialDirContext(env);
        // Close the context when we're done
        ctx.close();
    }

    /**
     * This is here as a place holder to show how to do queries. TODO turn thi s
     * into a way to query attributes by uid.
     * 
     * @param email
     * @return
     * @throws NamingException
     */
    public String searchAttribute(String query, String email) throws NamingException {
        DirContext ctx = null;
        try {
            ctx = new InitialDirContext(env);
            String base = "o=hp.com";

            SearchControls sc = new SearchControls();
            String[] attributeFilter = { "hpLegalName", "hpJobFunction", "manager" };
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String filter = "uid=" + email;

            NamingEnumeration<SearchResult> results = ctx.search(base, filter, sc);
            // This is an enumeration so we need to use the iterator syntax but
            // there should be only one.

            while (results.hasMore()) {
                SearchResult sr = results.next();
                Attributes attrs = sr.getAttributes();
            }

        } finally {
            if (ctx != null) {
                ctx.close();
            }
        }

        return "TODO FIX ME";
    }

    /**
     * This method will get the GroupMembership of email provided. Email is
     * checked against the VirtualFactoryUser or VirtualFactoryAdmin groups and
     * GroupMembership is returned.
     * 
     * @param email
     *            e-mail address of the user equates to ldap uid.
     * @throws NamingException
     *             if a generic authentication problem occurs.
     */

    public static GroupMembership getUserGroup(String email) throws NamingException {
        GroupMembership membership = null;

        env.remove(Context.SECURITY_AUTHENTICATION);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");

        DirContext ctx = new InitialDirContext(env);
        String base = "ou=Groups,o=hp.com";

        SearchControls sc = new SearchControls();
        String[] attributeFilter = { "member", "cn" };
        sc.setReturningAttributes(attributeFilter);
        sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

        String filter = "(|(&(cn=VirtualFactoryUser) (member=uid=" + email + "*)) (&(cn=VirtualFactoryAdmin) (member=uid=" + email + "*)))";

        NamingEnumeration<SearchResult> results = ctx.search(base, filter, sc);

        // This iterates over all the groups and gets the highest privilege.
        while (results.hasMore()) {
            SearchResult sr = results.next();
            Attributes attrs = sr.getAttributes();
            String group = attrs.get("cn").toString();
            if (group.startsWith("cn:")) {
                group = group.replace("cn:", "").trim();
            }

            logger.info("Email -> " + email + " Group -> " + group);

            GroupMembership _membership = GroupMembership.fromLdapString(group);

            if (_membership != null) {
                if (membership == null) {
                    membership = _membership;
                } else if (_membership.ordinal() > membership.ordinal()) {
                    membership = _membership;
                }
            }
        }
        return membership;
    }

    /**
     * 
     * Retrieves group of users from Virtual factory
     * 
     **/
    public static List<String> getUsers() throws NamingException {
        List<String> users = new ArrayList<String>();

        env.put(Context.SECURITY_AUTHENTICATION, "none");

        DirContext ctx = new InitialDirContext(env);

        String base = "ou=Groups,o=hp.com";

        SearchControls sc = new SearchControls();
        String[] attributeFilter = { "member", "cn" };
        sc.setReturningAttributes(attributeFilter);
        sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

        String filter = "(|(&(cn=VirtualFactoryUser) (member=*)) (&(cn=VirtualFactoryAdmin) (member=*)))";

        NamingEnumeration<SearchResult> results = ctx.search(base, filter, sc);

        while (results.hasMore()) {
            SearchResult sr = results.next();
            Attributes attrs = sr.getAttributes();
            NamingEnumeration<?> enums = attrs.get("member").getAll();
            while (enums.hasMoreElements()) {
                String member = enums.next().toString();
                member = member.split(",")[0].replace("uid=", "");
                if (!member.equals("VirtualFactory")) {
                    users.add(member);
                }
            }
        }
        return users;
    }
}

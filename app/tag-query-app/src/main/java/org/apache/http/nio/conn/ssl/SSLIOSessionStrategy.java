//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.apache.http.nio.conn.ssl;

import org.apache.http.HttpHost;
import org.apache.http.conn.ssl.*;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.reactor.IOSession;
import org.apache.http.nio.reactor.ssl.SSLIOSession;
import org.apache.http.nio.reactor.ssl.SSLMode;
import org.apache.http.nio.reactor.ssl.SSLSetupHandler;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;
import org.apache.http.util.TextUtils;

import javax.net.ssl.*;
import java.io.IOException;

public class SSLIOSessionStrategy implements SchemeIOSessionStrategy {
    /** @deprecated */
    @Deprecated
    public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
    /** @deprecated */
    @Deprecated
    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
    /** @deprecated */
    @Deprecated
    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new StrictHostnameVerifier();
    private final SSLContext sslContext;
    private final String[] supportedProtocols;
    private final String[] supportedCipherSuites;
    private final HostnameVerifier hostnameVerifier;

    private static String[] split(String s) {
        return TextUtils.isBlank(s) ? null : s.split(" *, *");
    }

    public static HostnameVerifier getDefaultHostnameVerifier() {
        return new DefaultHostnameVerifier(PublicSuffixMatcherLoader.getDefault());
    }

    public static SSLIOSessionStrategy getDefaultStrategy() {
        return new SSLIOSessionStrategy(SSLContexts.createDefault(), getDefaultHostnameVerifier());
    }

    public static SSLIOSessionStrategy getSystemDefaultStrategy() {
        return new SSLIOSessionStrategy(SSLContexts.createSystemDefault(), split(System.getProperty("https.protocols")), split(System.getProperty("https.cipherSuites")), getDefaultHostnameVerifier());
    }

    /** @deprecated */
    @Deprecated
    public SSLIOSessionStrategy(SSLContext sslContext, String[] supportedProtocols, String[] supportedCipherSuites, X509HostnameVerifier hostnameVerifier) {
        this(sslContext, supportedProtocols, supportedCipherSuites, (HostnameVerifier)hostnameVerifier);
    }

    /** @deprecated */
    @Deprecated
    public SSLIOSessionStrategy(SSLContext sslcontext, X509HostnameVerifier hostnameVerifier) {
        this(sslcontext, (String[])null, (String[])null, (HostnameVerifier)hostnameVerifier);
    }

    public SSLIOSessionStrategy(SSLContext sslContext, String[] supportedProtocols, String[] supportedCipherSuites, HostnameVerifier hostnameVerifier) {
        this.sslContext = (SSLContext)Args.notNull(sslContext, "SSL context");
        this.supportedProtocols = supportedProtocols;
        this.supportedCipherSuites = supportedCipherSuites;
        this.hostnameVerifier = hostnameVerifier != null ? hostnameVerifier : getDefaultHostnameVerifier();
    }

    public SSLIOSessionStrategy(SSLContext sslcontext, HostnameVerifier hostnameVerifier) {
        this(sslcontext, (String[])null, (String[])null, (HostnameVerifier)hostnameVerifier);
    }

    public SSLIOSessionStrategy(SSLContext sslcontext) {
        this(sslcontext, (String[])null, (String[])null, (HostnameVerifier)getDefaultHostnameVerifier());
    }

    public SSLIOSession upgrade(final HttpHost host, IOSession ioSession) throws IOException {
        Asserts.check(!(ioSession instanceof SSLIOSession), "I/O session is already upgraded to TLS/SSL");
        SSLIOSession sslioSession = new SSLIOSession(ioSession, SSLMode.CLIENT, host, this.sslContext, new SSLSetupHandler() {
            public void initalize(SSLEngine sslengine) throws SSLException {
                if (SSLIOSessionStrategy.this.supportedProtocols != null) {
                    sslengine.setEnabledProtocols(SSLIOSessionStrategy.this.supportedProtocols);
                }

                if (SSLIOSessionStrategy.this.supportedCipherSuites != null) {
                    sslengine.setEnabledCipherSuites(SSLIOSessionStrategy.this.supportedCipherSuites);
                }

                SSLIOSessionStrategy.this.initializeEngine(sslengine);
            }

            public void verify(IOSession ioSession, SSLSession sslsession) throws SSLException {
                SSLIOSessionStrategy.this.verifySession(host, ioSession, sslsession);
            }
        });
        ioSession.setAttribute("http.session.ssl", sslioSession);
        sslioSession.initialize();
        return sslioSession;
    }

    protected void initializeEngine(SSLEngine engine) {
    }

    protected void verifySession(HttpHost host, IOSession ioSession, SSLSession sslsession) throws SSLException {
        System.out.println();
//        if (!this.hostnameVerifier.verify(host.getHostName(), sslsession)) {
//            Certificate[] certs = sslsession.getPeerCertificates();
//            X509Certificate x509 = (X509Certificate)certs[0];
//            X500Principal x500Principal = x509.getSubjectX500Principal();
//            throw new SSLPeerUnverifiedException("Host name '" + host.getHostName() + "' does not match " + "the certificate subject provided by the peer (" + x500Principal.toString() + ")");
//        }
    }

    public boolean isLayeringRequired() {
        return true;
    }
}
